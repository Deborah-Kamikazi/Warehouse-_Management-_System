package tech.kamikazi.warehousemanagementsystembackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kamikazi.warehousemanagementsystembackend.entities.CartonHeader;

import java.util.Optional;

public interface CartonHeaderRepository extends JpaRepository<CartonHeader, Long> {
    boolean existsByBarcode(String barcode);

    Optional<Object> findByBarcode(String barcode);
}
