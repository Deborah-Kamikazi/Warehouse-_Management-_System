package tech.kamikazi.warehousemanagementsystembackend.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDto {
    private Long id;
    private String itemNumber;

    @PositiveOrZero
    private Integer quantity;

    private Integer version;
    private Long locationId;
    private Long cartonHeaderId;

    private Instant createdTimeStamp;
    private Instant updatedTimeStamp;
}
