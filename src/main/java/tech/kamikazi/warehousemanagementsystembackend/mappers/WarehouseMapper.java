package tech.kamikazi.warehousemanagementsystembackend.mappers;

import org.mapstruct.Mapper;
import tech.kamikazi.warehousemanagementsystembackend.entities.Warehouse;
import tech.kamikazi.warehousemanagementsystembackend.dto.WarehouseDto;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

     WarehouseDto toDto(Warehouse warehouse);
     Warehouse toEntity (WarehouseDto warehouseDto);


}
