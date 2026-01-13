package tech.kamikazi.warehousemanagementsystembackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "carton_header")
public class CartonHeader {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "barcode", unique = true, nullable = false)
    private String barcode;

    @Column(name = "description")
    private String description;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version = 0;

    @Column(name = "created_time_stamp", nullable = false, updatable = false)
    private Instant createdTimeStamp;

    @Column(name = "updated_time_stamp")
    private Instant updatedTimeStamp;

    @OneToMany(
            mappedBy = "cartonHeader",  
            cascade = CascadeType.ALL,   
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default  
    private List<SSCC> ssccs = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdTimeStamp = Instant.now();
        updatedTimeStamp = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTimeStamp = Instant.now();
    }
}