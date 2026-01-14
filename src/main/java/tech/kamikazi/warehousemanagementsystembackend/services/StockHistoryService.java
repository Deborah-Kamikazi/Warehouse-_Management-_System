package tech.kamikazi.warehousemanagementsystembackend.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import tech.kamikazi.warehousemanagementsystembackend.dto.StockHistoryDto;
import tech.kamikazi.warehousemanagementsystembackend.entities.StockHistory;
import tech.kamikazi.warehousemanagementsystembackend.enums.StrockAction;
import tech.kamikazi.warehousemanagementsystembackend.repositories.StockHistoryRepository;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Getter
@Setter
public class StockHistoryService {

    private final StockHistoryRepository stockHistoryRepository;


    public List<StockHistoryDto> getHistory(
            String warehouseNumber,
            String itemNumber,
            String locationCode
    ) {
        System.out.println("Fetching history for warehouse: " + warehouseNumber
                + ", item: " + itemNumber
                + ", location: " + locationCode);

        List<StockHistory> histories = stockHistoryRepository
                .findHistory(warehouseNumber, itemNumber, locationCode);

        System.out.println("Found " + histories.size() + " history records");

        return histories.stream()
                .map(history -> StockHistoryDto.builder()
                        .id(history.getId())
                        .action(history.getAction())
                        .itemNumber(history.getItemNumber())
                        .quantityChange(history.getQuantityChange())
                        .fromLocationCode(history.getFromLocationCode())
                        .toLocationCode(history.getToLocationCode())
                        .warehouseNumber(history.getWarehouseNumber())
                        .sscc(history.getSscc())
                        .createdTimestamp(history.getCreatedTimestamp())
                        .build()
                )
                .toList();
    }


    public void createReceiveHistory(
            String itemNumber,
            Integer quantityChange,
            String toLocationCode,
            String warehouseNumber,
            String sscc
    ) {
        StockHistory history = StockHistory.builder()
                .action(StrockAction.RECEIVE)
                .itemNumber(itemNumber)
                .quantityChange(quantityChange)
                .fromLocationCode(null)  // No source location for receives
                .toLocationCode(toLocationCode)
                .warehouseNumber(warehouseNumber)
                .sscc(sscc)
                .createdTimestamp(Instant.now())
                .build();

        stockHistoryRepository.save(history);
        System.out.println("Created RECEIVE history for item: " + itemNumber);
    }

    public void createMoveHistory(
            String itemNumber,
            Integer quantityChange,
            String fromLocationCode,
            String toLocationCode,
            String warehouseNumber
    ) {
        StockHistory history = StockHistory.builder()
                .action(StrockAction.MOVE)
                .itemNumber(itemNumber)
                .quantityChange(quantityChange)
                .fromLocationCode(fromLocationCode)
                .toLocationCode(toLocationCode)
                .warehouseNumber(warehouseNumber)
                .sscc(null)  // No SSCC for moves
                .createdTimestamp(Instant.now())
                .build();

        stockHistoryRepository.save(history);
        System.out.println("Created MOVE history for item: " + itemNumber);
    }

    public void createAdjustHistory(
            String itemNumber,
            Integer quantityChange,
            String locationCode,
            String warehouseNumber
    ) {
        StockHistory history = StockHistory.builder()
                .action(StrockAction.ADJUST)
                .itemNumber(itemNumber)
                .quantityChange(quantityChange)
                .fromLocationCode(locationCode)
                .toLocationCode(null)
                .warehouseNumber(warehouseNumber)
                .sscc(null)
                .createdTimestamp(Instant.now())
                .build();

        stockHistoryRepository.save(history);
        System.out.println("Created ADJUST history for item: " + itemNumber);
    }
}