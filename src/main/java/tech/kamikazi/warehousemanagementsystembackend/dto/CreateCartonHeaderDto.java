package tech.kamikazi.warehousemanagementsystembackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartonHeaderDto {
    @NotBlank(message = "Barcode is required")
    private String barcode;

    private String description;
}
