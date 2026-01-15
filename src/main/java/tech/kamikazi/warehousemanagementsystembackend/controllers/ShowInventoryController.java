package tech.kamikazi.warehousemanagementsystembackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kamikazi.warehousemanagementsystembackend.dto.ShowInventroryDto;
import tech.kamikazi.warehousemanagementsystembackend.services.ShowInventoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping ("/api/warehouses")
public class ShowInventoryController {

    private ShowInventoryService showInventoryService;


    @GetMapping("/{warehouseNumber}/items")
    public ResponseEntity<List<ShowInventroryDto>>  listInventory(@PathVariable String warehouseNumber){

        List<ShowInventroryDto> inventory = showInventoryService.listInventory(warehouseNumber);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/{warehouseNumber}/items/{itemNumber}")
    public  ResponseEntity<ShowInventroryDto> getItemRow(
            @PathVariable String warehouseNumber,
            @PathVariable String itemNumber,
            @RequestParam String locationCode){
        ShowInventroryDto itemRow =  showInventoryService.getItemRow(warehouseNumber, itemNumber, locationCode);
        return ResponseEntity.ok(itemRow);
    }


}
