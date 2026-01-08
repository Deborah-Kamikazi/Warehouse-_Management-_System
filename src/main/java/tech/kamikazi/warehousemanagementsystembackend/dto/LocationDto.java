package tech.kamikazi.warehousemanagementsystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDto {
    private Long id;
    private Integer row;
    private Integer section;
    private Integer shelf;
    private String locationCode;
    private Long warehouseId;
    private BigInteger version;
    private Instant created_time_stamp;
    private Instant updated_time_stamp;

}
