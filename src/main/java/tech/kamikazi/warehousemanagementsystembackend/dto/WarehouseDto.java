package tech.kamikazi.warehousemanagementsystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class WarehouseDto {
  private  int id;
  private String warehouseNumber;
  private String name;
  private boolean active;
  private int version;
  private Instant createdTimeStamp;
  private Instant updatedTimeStamp;
}

