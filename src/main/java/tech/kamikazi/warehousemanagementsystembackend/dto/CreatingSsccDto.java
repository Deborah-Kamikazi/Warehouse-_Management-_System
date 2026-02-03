package tech.kamikazi.warehousemanagementsystembackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatingSsccDto {
    @NotBlank(message = "SSCC is required")
    private String sscc;
}