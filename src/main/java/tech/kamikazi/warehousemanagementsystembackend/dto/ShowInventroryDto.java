package tech.kamikazi.warehousemanagementsystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowInventroryDto {
    private String itemName;
    private String locationCode;
    private Integer quantity;
}
