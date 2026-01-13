package tech.kamikazi.warehousemanagementsystembackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kamikazi.warehousemanagementsystembackend.entities.ItemEntity;
import tech.kamikazi.warehousemanagementsystembackend.entities.Location;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    Optional<ItemEntity> findByItemNumberAndLocation(String itemNumber, Location location);

}
