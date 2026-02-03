package tech.kamikazi.warehousemanagementsystembackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(
        name = "item",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"item_number", "location_id"})
        }
)
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_number", nullable = false)
    private String itemNumber;

    @PositiveOrZero
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "carton_header_id", nullable = false)
    private CartonHeader cartonHeader;

    @CreationTimestamp
    @Column(name = "created_time_stamp", nullable = false, updatable = false)
    private Instant createdTimeStamp;

    @UpdateTimestamp
    @Column(name = "updated_time_stamp")
    private Instant updatedTimeStamp;
}
