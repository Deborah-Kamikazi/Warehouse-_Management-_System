package tech.kamikazi.warehousemanagementsystembackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kamikazi.warehousemanagementsystembackend.entities.ItemEntity;
import tech.kamikazi.warehousemanagementsystembackend.entities.Location;
import tech.kamikazi.warehousemanagementsystembackend.entities.Warehouse;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    Optional<ItemEntity> findByItemNumberAndLocation(String itemNumber, Location location);
    Optional<ItemEntity> findByLocationId(long locationId);
    Optional<ItemEntity> findAllByLocation_Warehouse(Warehouse warehouse);

    Optional<ItemEntity> findByLocationAndItemNumber(Location fromLocation, String itemNumber);
}
