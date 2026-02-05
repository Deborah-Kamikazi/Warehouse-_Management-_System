package tech.kamikazi.warehousemanagementsystembackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReceiveDto {
    @NotBlank(message = "Warehouse number is required")
    private String warehouseNumber;

    @NotBlank(message = "SSCC is required")
    private String sscc;

    @NotBlank(message = "Location code is required")
    private String locationCode;

}
