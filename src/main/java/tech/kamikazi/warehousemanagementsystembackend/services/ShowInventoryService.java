package tech.kamikazi.warehousemanagementsystembackend.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.kamikazi.warehousemanagementsystembackend.dto.ShowInventroryDto;
import tech.kamikazi.warehousemanagementsystembackend.entities.ItemEntity;
import tech.kamikazi.warehousemanagementsystembackend.entities.Location;
import tech.kamikazi.warehousemanagementsystembackend.entities.Warehouse;
import tech.kamikazi.warehousemanagementsystembackend.repositories.ItemRepository;
import tech.kamikazi.warehousemanagementsystembackend.repositories.LocationRepository;
import tech.kamikazi.warehousemanagementsystembackend.repositories.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Setter
public class ShowInventoryService {
     private WarehouseRepository warehouseRepository;
     private LocationRepository locationRepository;
    private final ItemRepository itemRepository;

    public List<ShowInventroryDto> listInventory(String warehouseNumber){
        Warehouse warehouse = warehouseRepository.findByWarehouseNumber(warehouseNumber)
                .orElseThrow(() -> new IllegalCallerException("Warehouse " + warehouseNumber + " not found"));

        Optional<ItemEntity> items = itemRepository.findAllByLocation_Warehouse(warehouse);

        return items.stream()
                .map(item -> new ShowInventroryDto(item.getItemNumber(),
                        item.getLocation().getLocationCode(),
                        item.getQuantity()))
                .collect(Collectors.toList());
    }

    public ShowInventroryDto getItemRow(String warehouseNumber, String itemNumber, String locationCode){
        Warehouse warehouse = warehouseRepository.findByWarehouseNumber(warehouseNumber)
                .orElseThrow(() -> new IllegalArgumentException("Warehouse " + warehouseNumber + " not found"));

        Location location = locationRepository.findByWarehouseAndLocationCode(warehouse, locationCode)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Location " + locationCode + " not found in warehouse " + warehouseNumber));

        ItemEntity item = itemRepository.findByItemNumberAndLocation(itemNumber, location)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Item " + itemNumber + " not found at location " + locationCode + " in warehouse " + warehouseNumber));

        return new ShowInventroryDto(item.getItemNumber(), item.getLocation().getLocationCode(), item.getQuantity());

    }


}
