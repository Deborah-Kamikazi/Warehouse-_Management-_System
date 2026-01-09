package tech.kamikazi.warehousemanagementsystembackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import tech.kamikazi.warehousemanagementsystembackend.dto.CartonHeaderDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.CreateCartonHeaderDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.UpdateCartonHeaderDto;
import tech.kamikazi.warehousemanagementsystembackend.entities.CartonHeader;

@Mapper(componentModel = "spring")
public interface CartonHeaderMapper {

    CartonHeaderDto toDto(CartonHeader cartonHeader);
    CartonHeader toEntity(CartonHeaderDto cartonHeaderDto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdTimeStamp", ignore = true)
    @Mapping(target = "updatedTimeStamp", ignore = true)
    CartonHeader toEntity(CreateCartonHeaderDto request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "barcode", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdTimeStamp", ignore = true)
    @Mapping(target = "updatedTimeStamp", ignore = true)
    void updateEntityFromDto(UpdateCartonHeaderDto request, @MappingTarget CartonHeader entity);
}