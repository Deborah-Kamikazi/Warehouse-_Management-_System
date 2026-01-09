package tech.kamikazi.warehousemanagementsystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartonHeaderDto {

    private Long id;
    private String barcode;
    private String description;
    private Integer version;
    private Instant createdTimeStamp;
    private Instant updatedTimeStamp;

}
