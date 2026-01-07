package tech.kamikazi.warehousemanagementsystembackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kamikazi.warehousemanagementsystembackend.entities.Warehouse;

public interface WarehouseRepository extends JpaRepository <Warehouse, Integer> {
    boolean existsByWarehouseNumber(String warehouseNumber);

}
