package tech.kamikazi.warehousemanagementsystembackend.kafkaEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.kamikazi.warehousemanagementsystembackend.enums.EventType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveEvent {
    private EventType eventType;
    private String warehouseNumber;
    private String locationCode;
    private String sscc;
    private Long timestamp;
}
