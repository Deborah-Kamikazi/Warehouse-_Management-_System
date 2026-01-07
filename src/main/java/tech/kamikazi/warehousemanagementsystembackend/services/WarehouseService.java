package tech.kamikazi.warehousemanagementsystembackend.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.kamikazi.warehousemanagementsystembackend.entities.Warehouse;
import tech.kamikazi.warehousemanagementsystembackend.mappers.WarehouseMapper;
import tech.kamikazi.warehousemanagementsystembackend.repositories.WarehouseRepository;
import tech.kamikazi.warehousemanagementsystembackend.dto.WarehouseDto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Setter
public class WarehouseService {
  private final WarehouseMapper warehouseMapper;
  private final WarehouseRepository warehouseRepository;

  @Transactional
  public WarehouseDto createWarehouse( WarehouseDto dto){
      if (warehouseRepository.existsByWarehouseNumber(dto.getWarehouseNumber())) {
          throw new IllegalArgumentException(
                  "Warehouse with number " + dto.getWarehouseNumber() + " already exists");
      }
      Warehouse warehouse = warehouseMapper.toEntity(dto);

      warehouse.setActive(true);
      warehouse.setCreatedTimeStamp(Instant.now());
      warehouse.setUpdatedTimeStamp(Instant.now());

      Warehouse saved = warehouseRepository.save(warehouse);

      return warehouseMapper.toDto(saved);


  }
  @Transactional
  public WarehouseDto updateWarehouse(Integer id,WarehouseDto dto){
      Warehouse warehouse = warehouseRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Warehouse with this id doesn't exist "));

      warehouse.setName(dto.getName());
      warehouse.setActive(dto.getActive());
      warehouse.setUpdatedTimeStamp(Instant.now());

      try{
          Warehouse updated = warehouseRepository.save(warehouse);
          return warehouseMapper.toDto(updated);
      } catch (OptimisticLockingFailureException e) {
          throw new IllegalStateException("Conflict: Warehouse was updated by another user", e);
      }

  }

  public List<WarehouseDto> getAllWarehouses(){
      return warehouseRepository.findAll()
              .stream()
              .map(warehouseMapper::toDto)
              .collect(Collectors.toList());
  }
}
