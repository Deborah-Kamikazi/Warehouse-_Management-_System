package tech.kamikazi.warehousemanagementsystembackend.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import tech.kamikazi.warehousemanagementsystembackend.dto.MoveItemDto;
import tech.kamikazi.warehousemanagementsystembackend.entities.ItemEntity;
import tech.kamikazi.warehousemanagementsystembackend.entities.Location;
import tech.kamikazi.warehousemanagementsystembackend.entities.Warehouse;
import tech.kamikazi.warehousemanagementsystembackend.repositories.ItemRepository;
import tech.kamikazi.warehousemanagementsystembackend.repositories.LocationRepository;
import tech.kamikazi.warehousemanagementsystembackend.repositories.WarehouseRepository;

@Service
@AllArgsConstructor
@Getter
@Setter
public class MoveItemsService {
    private WarehouseRepository warehouseRepository;
    private LocationRepository locationRepository;
    private final ItemRepository itemRepository;

    public MoveItemDto moveItem(MoveItemDto moveItemDto) {

        Warehouse warehouse = warehouseRepository.findByWarehouseNumber(moveItemDto.getWarehouseNumber())
                .orElseThrow(() -> new IllegalArgumentException("Warehouse not found"));

        if (!warehouse.getActive()) {
            throw new IllegalStateException("Warehouse is inactive");
        }

        Location fromLocation = locationRepository.findByWarehouseAndLocationCode(warehouse, moveItemDto.getFromLocationCode())
                .orElseThrow(() -> new IllegalArgumentException("Source location not found"));

        ItemEntity sourceItem = itemRepository.findByLocationAndItemNumber(fromLocation, moveItemDto.getItemNumber())
                .orElseThrow(() -> new IllegalArgumentException("Item not found in source location"));

        if (sourceItem.getQuantity() < moveItemDto.getQuantity()) {
            throw new IllegalStateException("Insufficient quantity in source location");
        }

        Location toLocation = locationRepository.findByWarehouseAndLocationCode(warehouse, moveItemDto.getToLocationCode())
                .orElseThrow(() -> new IllegalArgumentException("Destination location not found"));

        ItemEntity destItem = itemRepository.findByLocationAndItemNumber(toLocation, moveItemDto.getItemNumber())
                .orElse(null);

        sourceItem.setQuantity(sourceItem.getQuantity() - moveItemDto.getQuantity());
        itemRepository.save(sourceItem);

        if (destItem == null) {
            destItem = ItemEntity.builder()
                    .itemNumber(moveItemDto.getItemNumber())
                    .location(toLocation)
                    .quantity(moveItemDto.getQuantity())
                    .build();
        } else {
            destItem.setQuantity(destItem.getQuantity() + moveItemDto.getQuantity());
        }
        itemRepository.save(destItem);

        return MoveItemDto.builder()
                .warehouseNumber(moveItemDto.getWarehouseNumber())
                .itemNumber(moveItemDto.getItemNumber())
                .fromLocationCode(moveItemDto.getFromLocationCode())
                .toLocationCode(moveItemDto.getToLocationCode())
                .quantity(moveItemDto.getQuantity())
                .sourceRemainingQuantity(sourceItem.getQuantity())
                .destinationQuantity(destItem.getQuantity())
                .build();







    }

}
