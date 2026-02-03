package tech.kamikazi.warehousemanagementsystembackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kamikazi.warehousemanagementsystembackend.dto.CartonHeaderDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.CreateCartonHeaderDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.UpdateCartonHeaderDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.WarehouseDto;
import tech.kamikazi.warehousemanagementsystembackend.services.CartonHeaderService;

import java.util.List;

@RestController()
@RequestMapping("/api/carton-headers")
@AllArgsConstructor()
public class CartonHeaderController {
    private CartonHeaderService cartonHeaderService;

    @PostMapping
    public ResponseEntity<CartonHeaderDto> create(
            @RequestBody @Valid CreateCartonHeaderDto cartonHeaderDto) {
        CartonHeaderDto updated =  cartonHeaderService.createCartonHeader(cartonHeaderDto);
        return ResponseEntity.ok(updated);
    }


    @PutMapping("/{barcode}")
    public ResponseEntity<CartonHeaderDto> update(
            @PathVariable String barcode,
            @RequestBody @Valid UpdateCartonHeaderDto  updateCartonHeaderDto) {

        return ResponseEntity.ok(cartonHeaderService.updateCartonHeader(barcode, updateCartonHeaderDto));
    }

    @GetMapping()
    public ResponseEntity<List<CartonHeaderDto>> getAllCartonHeader(){
        return ResponseEntity.ok(cartonHeaderService.getAllCartonHeader());
    }


}
