package tech.kamikazi.warehousemanagementsystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.kamikazi.warehousemanagementsystembackend.enums.StrockAction;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockHistoryDto {
    private Long id;
    private StrockAction action;
    private String itemNumber;
    private Integer quantityChange;
    private String fromLocationCode;
    private String toLocationCode;
    private String warehouseNumber;
    private String sscc;
    private Instant createdTimestamp;
}
