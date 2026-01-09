package tech.kamikazi.warehousemanagementsystembackend.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.kamikazi.warehousemanagementsystembackend.dto.CreateLocationDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.LocationDto;
import tech.kamikazi.warehousemanagementsystembackend.entities.Location;
import tech.kamikazi.warehousemanagementsystembackend.entities.Warehouse;
import tech.kamikazi.warehousemanagementsystembackend.mappers.LocationMapper;
import tech.kamikazi.warehousemanagementsystembackend.repositories.LocationRepository;
import tech.kamikazi.warehousemanagementsystembackend.repositories.WarehouseRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Setter
public class LocationService {
    private final LocationRepository locationRepository;
    private final WarehouseRepository warehouseRepository;
    private final LocationMapper locationMapper;

    @Transactional
    public LocationDto createLocation(Integer warehouseId,  CreateLocationDto createDto) {

        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException(
                        "Warehouse with id " + warehouseId + " not found"
                ));

        String locationCode = generateLocationCode(
                createDto.getRow(),
                createDto.getSection(),
                createDto.getShelf()
        );

        if (locationRepository.existsByWarehouseIdAndLocationCode(warehouse.getId(), locationCode)) {
            throw new RuntimeException(
                    "Location " + locationCode + " already exists in warehouse " + warehouseId
            );
        }

        Location location = locationMapper.toEntity(createDto);


        location.setLocationCode(locationCode);
        location.setWarehouse(warehouse);

        Location savedLocation = locationRepository.save(location);
        return locationMapper.toDto(savedLocation);

    }

    public List<LocationDto> getAllLocations(){
        return locationRepository.findAll()
                .stream()
                .map(locationMapper::toDto)
                .collect(Collectors.toList());

    }

    private String generateLocationCode(Integer row, Integer section, Integer shelf) {
        return String.format("%d-%d-%d", row, section, shelf);
    }

}
