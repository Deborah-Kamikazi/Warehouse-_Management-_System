package tech.kamikazi.warehousemanagementsystembackend.controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.validation.autoconfigure.ValidationAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kamikazi.warehousemanagementsystembackend.services.WarehouseService;
import tech.kamikazi.warehousemanagementsystembackend.dto.WarehouseDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/warehouses")
public class warehouseControllers {
    private final WarehouseService warehouseService;

   @PostMapping()
    public ResponseEntity<?> createWarehouse(
            @Valid @RequestBody WarehouseDto warehouseDto) {
       try{
           WarehouseDto created = warehouseService.createWarehouse(warehouseDto);
           return ResponseEntity.ok().body(created);
       }
       catch (IllegalArgumentException ex) {
           return ResponseEntity.badRequest().body(ex.getMessage());
       }

    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseDto> updateWarehouse(
            @PathVariable Integer id,
            @RequestBody WarehouseDto warehouseDto) {

        WarehouseDto updated = warehouseService.updateWarehouse(id, warehouseDto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping ()
        public ResponseEntity<List<WarehouseDto>> getAllWarehouses(){
        return ResponseEntity.ok(warehouseService.getAllWarehouses());
    }
}
