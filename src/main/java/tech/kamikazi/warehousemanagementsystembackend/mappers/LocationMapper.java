package tech.kamikazi.warehousemanagementsystembackend.mappers;


import jakarta.persistence.MappedSuperclass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.kamikazi.warehousemanagementsystembackend.dto.CreateLocationDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.LocationDto;
import tech.kamikazi.warehousemanagementsystembackend.entities.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDto toDto(Location location);
    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "location_Code", ignore = true)
//    @Mapping(target = "warehouseId", ignore = true)
    @Mapping(target = "version", ignore = true)
//    @Mapping(target = "createdTimeStamp", ignore = true)
//    @Mapping(target = "updatedTimeStamp", ignore = true)
    Location toEntity(CreateLocationDto createDto);
}
