package tech.kamikazi.warehousemanagementsystembackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "warehouse_number", nullable = false, unique = true)
    private String warehouseNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active" , nullable = false)
    private Boolean active = true;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "created_time_stamp", updatable = false)
    private Instant createdTimeStamp;

    @Column(name = "updated_time_stamp")
    private Instant updatedTimeStamp;

    @OneToMany(
            mappedBy = "warehouse",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Location> locations = new ArrayList<>();

}
