package tech.kamikazi.warehousemanagementsystembackend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MoveItemDto {
    @NotNull
    private String warehouseNumber;
    private String itemNumber;
    private String fromLocationCode;
    private String toLocationCode;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    private Integer sourceRemainingQuantity;
    private Integer destinationQuantity;
}
