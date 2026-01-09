package tech.kamikazi.warehousemanagementsystembackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kamikazi.warehousemanagementsystembackend.entities.Location;

import java.util.Optional;

public interface LocationRepository  extends JpaRepository<Location,Long> {
    boolean existsByWarehouseIdAndLocationCode(int warehouseId, String locationCode);

}
