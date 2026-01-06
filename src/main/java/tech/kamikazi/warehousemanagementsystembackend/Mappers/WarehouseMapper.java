package tech.kamikazi.warehousemanagementsystembackend.Mappers;

import org.mapstruct.Mapper;
import tech.kamikazi.warehousemanagementsystembackend.Entities.Warehouse;
import tech.kamikazi.warehousemanagementsystembackend.dto.WarehouseDto;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

     WarehouseDto toDto(Warehouse warehouse);
     Warehouse toEntity (WarehouseDto warehouseDto);

}
