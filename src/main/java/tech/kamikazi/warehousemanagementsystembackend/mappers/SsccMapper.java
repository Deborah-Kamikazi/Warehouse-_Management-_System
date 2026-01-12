package tech.kamikazi.warehousemanagementsystembackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.kamikazi.warehousemanagementsystembackend.dto.CreatingSsccDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.SsccDto;
import tech.kamikazi.warehousemanagementsystembackend.entities.SSCC;

@Mapper(componentModel = "spring")
public interface SsccMapper {

    @Mapping(target = "cartonHeader", ignore = true)
    SSCC toEntity(CreatingSsccDto dto);
    SsccDto toDto(SSCC entity);
}
