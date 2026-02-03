package tech.kamikazi.warehousemanagementsystembackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kamikazi.warehousemanagementsystembackend.dto.CreateLocationDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.LocationDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.WarehouseDto;
import tech.kamikazi.warehousemanagementsystembackend.services.LocationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping ("/api/warehouses/{warehouseId}/locations")
public class LocationController {
    private final LocationService locationService;


    @PostMapping ()
    public ResponseEntity<LocationDto> createLocation(
            @PathVariable Integer warehouseId,
            @Valid @RequestBody CreateLocationDto createDto
    ) {
        LocationDto created = locationService.createLocation(warehouseId, createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping()
    public ResponseEntity <List<LocationDto>> getAllLocations(){
        return ResponseEntity.ok(locationService.getAllLocations());
    }
}
