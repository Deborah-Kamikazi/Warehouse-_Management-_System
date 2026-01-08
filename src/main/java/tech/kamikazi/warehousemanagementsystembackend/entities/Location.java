package tech.kamikazi.warehousemanagementsystembackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(
        name = "location",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_warehouse_location_code",
                        columnNames = {"warehouse_id", "location_code"}
                )
        }
)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row", nullable = false)
    private Integer row;

    @Column(name = "section", nullable = false)
    private Integer section;

    @Column(name = "shelf", nullable = false)
    private Integer shelf;

    @Column(name = "location_code")
    private String locationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "warehouse_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_location_warehouse")
    )
    private Warehouse warehouse;

    @Column(name = "warehouse_id", insertable = false, updatable = false)
    private Integer warehouseId;

    @Version
    private Integer version;


    @Column(name = "created_time_stamp", nullable = false, updatable = false)
    private Instant createdTimeStamp;


    @Column(name = "updated_time_stamp")
    private Instant updatedTimeStamp;
}
