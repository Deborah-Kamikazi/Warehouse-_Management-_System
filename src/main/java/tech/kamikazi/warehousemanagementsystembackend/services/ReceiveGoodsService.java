package tech.kamikazi.warehousemanagementsystembackend.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.kamikazi.warehousemanagementsystembackend.dto.ItemDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.ReceiveDto;
import tech.kamikazi.warehousemanagementsystembackend.entities.ItemEntity;
import tech.kamikazi.warehousemanagementsystembackend.entities.Location;
import tech.kamikazi.warehousemanagementsystembackend.entities.SSCC;
import tech.kamikazi.warehousemanagementsystembackend.entities.Warehouse;
import tech.kamikazi.warehousemanagementsystembackend.repositories.ItemRepository;
import tech.kamikazi.warehousemanagementsystembackend.repositories.LocationRepository;
import tech.kamikazi.warehousemanagementsystembackend.repositories.SsccRepository;
import tech.kamikazi.warehousemanagementsystembackend.repositories.WarehouseRepository;

import java.time.Instant;

@Service
@AllArgsConstructor
@Getter
@Setter
@Transactional
public class ReceiveGoodsService {
    private final WarehouseRepository warehouseRepository;
    private final SsccRepository ssccRepository;
    private final ItemRepository itemRepository;
    private final LocationRepository locationRepository;


    @Transactional
   public ItemDto receiveGoods(ReceiveDto receiveDto) {

        Warehouse warehouse = warehouseRepository
                .findByWarehouseNumberAndActiveTrue(receiveDto.getWarehouseNumber())
                .orElseThrow(() -> new IllegalArgumentException("Warehouse does not exist or is inactive"));


        SSCC sscc = ssccRepository.findBySscc(receiveDto.getSscc())
                .orElseThrow(() -> new IllegalArgumentException("Sscc not found"));

       if(sscc.getReceivedTimestamp() != null){
           throw new IllegalArgumentException("Received timestamp already exists");
       }

       String itemNumber = sscc.getCartonHeader().getBarcode();

        Location location = locationRepository
                .findByWarehouseAndLocationCode( warehouse, receiveDto.getLocationCode())
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));


        ItemEntity item = itemRepository.findByItemNumberAndLocation(itemNumber,location)
               .orElseGet(() -> ItemEntity.builder()
                       .itemNumber(itemNumber)
                       .location(location)
                       .cartonHeader(sscc.getCartonHeader())
                       .quantity(0)
                       .build()
               );

        item.setQuantity(item.getQuantity()+1);
        itemRepository.save(item);

        sscc.setReceivedTimestamp(Instant.now());
        ssccRepository.save(sscc);

        return new ItemDto(
                item.getId(),
                item.getItemNumber(),
                item.getQuantity(),
                item.getVersion(),
                item.getLocation().getId(),
                item.getCartonHeader().getId(),
                item.getCreatedTimeStamp(),
                item.getUpdatedTimeStamp()
        );

   }

}
