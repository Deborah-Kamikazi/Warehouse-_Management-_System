package tech.kamikazi.warehousemanagementsystembackend.repositories;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tech.kamikazi.warehousemanagementsystembackend.entities.SSCC;

public interface SsccRepository extends JpaRepository<SSCC, Long> {

    boolean existsBySsccAndCartonHeaderId(String sscc, Long cartonHeaderId);
}
