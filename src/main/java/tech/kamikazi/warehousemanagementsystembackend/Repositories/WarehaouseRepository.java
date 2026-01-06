package tech.kamikazi.warehousemanagementsystembackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kamikazi.warehousemanagementsystembackend.Entities.Warehouse;

public interface WarehaouseRepository extends JpaRepository <Warehouse, Integer> {

}
