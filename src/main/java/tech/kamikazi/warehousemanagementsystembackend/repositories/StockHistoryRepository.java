package tech.kamikazi.warehousemanagementsystembackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.kamikazi.warehousemanagementsystembackend.entities.StockHistory;

import java.util.List;

public interface StockHistoryRepository  extends JpaRepository<StockHistory, Long> {

    @Query("""
        SELECT h FROM StockHistory h
        WHERE h.warehouseNumber = :warehouseNumber
          AND h.itemNumber = :itemNumber
          AND (:locationCode IS NULL
               OR h.fromLocationCode = :locationCode
               OR h.toLocationCode = :locationCode)
        ORDER BY h.createdTimestamp DESC
    """)
    List<StockHistory> findHistory(
            @Param("warehouseNumber") String warehouseNumber,
            @Param("itemNumber") String itemNumber,
            @Param("locationCode") String locationCode
    );
}
