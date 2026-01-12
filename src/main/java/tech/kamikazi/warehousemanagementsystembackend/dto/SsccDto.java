package tech.kamikazi.warehousemanagementsystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SsccDto {
    private Long id;
    private String sscc;
    private Long cartonHeaderId;
    private Instant receivedTimestamp;
    private Instant createdTimestamp;
    private Instant updatedTimestamp;
}