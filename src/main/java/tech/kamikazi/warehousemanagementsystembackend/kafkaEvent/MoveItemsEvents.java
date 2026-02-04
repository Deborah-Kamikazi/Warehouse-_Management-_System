package tech.kamikazi.warehousemanagementsystembackend.kafkaEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.kamikazi.warehousemanagementsystembackend.enums.EventType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveItemsEvents {
    private EventType eventType;
    private String warehouseNumber;
    private String itemNumber;
    private String fromLocation;
    private String toLocation;
    private Integer quantity;
    private Long timestamp;
}
