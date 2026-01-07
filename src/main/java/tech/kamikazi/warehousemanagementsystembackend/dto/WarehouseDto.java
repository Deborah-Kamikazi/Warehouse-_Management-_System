package tech.kamikazi.warehousemanagementsystembackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WarehouseDto {
  private  Integer id;

  @NotBlank( message = "Number is required")
  private String warehouseNumber;

  @NotBlank(message = "The name is required")
  private String name;

  private Boolean active;
  private Integer version;
  private Instant createdTimeStamp;
  private Instant updatedTimeStamp;
}



