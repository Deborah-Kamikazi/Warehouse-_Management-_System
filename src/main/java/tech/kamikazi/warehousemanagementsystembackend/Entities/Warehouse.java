package tech.kamikazi.warehousemanagementsystembackend.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

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
    private boolean active = true;

    @Version
    @Column(name = "version")
    private int version;

    @Column(name = "created_time_stamp", updatable = false)
    private Instant createdTimeStamp;

    @Column(name = "updated_time_stamp")
    private Instant updatedTimeStamp;

}
