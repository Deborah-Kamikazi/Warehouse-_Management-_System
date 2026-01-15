package tech.kamikazi.warehousemanagementsystembackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.kamikazi.warehousemanagementsystembackend.dto.StockHistoryDto;
import tech.kamikazi.warehousemanagementsystembackend.services.StockHistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class StockHistoryController {

    private final StockHistoryService stockHistoryService;

    @GetMapping
    public ResponseEntity<List<StockHistoryDto>> getStockHistory(
            @RequestParam String warehouseNumber,
            @RequestParam String itemNumber,
            @RequestParam(required = false) String locationCode
    ) {
        List<StockHistoryDto> history = stockHistoryService.getHistory(
                warehouseNumber,
                itemNumber,
                locationCode
        );

        return ResponseEntity.ok(history);
    }
}
