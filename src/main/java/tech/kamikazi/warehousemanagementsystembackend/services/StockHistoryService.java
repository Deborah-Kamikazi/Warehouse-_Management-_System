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
        List<StockHistory> histories = stockHistoryRepository
                .findHistory(warehouseNumber, itemNumber, locationCode);


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

}