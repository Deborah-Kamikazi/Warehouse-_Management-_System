package tech.kamikazi.warehousemanagementsystembackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationDto {

    @NotNull(message = "Row is required")
    @Min(value = 1, message = "Row must be at least 1")
    private Integer row;

    @NotNull(message = "Section is required")
    @Min(value = 1, message = "Section must be at least 1")
    private Integer section;

    @NotNull(message = "Shelf is required")
    @Min(value = 1, message = "Shelf must be at least 1")
    private Integer shelf;
}