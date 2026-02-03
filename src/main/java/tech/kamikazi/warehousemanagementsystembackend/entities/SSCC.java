package tech.kamikazi.warehousemanagementsystembackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "ssccs")
public class SSCC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sscc", unique = true, nullable = false)
    private String sscc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "carton_header_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_sscc_carton_header")
    )
    private CartonHeader cartonHeader;

    @Column(name = "received_time_stamp")
    private Instant receivedTimestamp;

    @Column(name = "created_time_stamp", nullable = false)
    private Instant createdTimestamp;

    @Column(name = "updated_time_stamp", nullable = false)
    private Instant updatedTimestamp;

    @PrePersist
    protected void onCreate() {
        createdTimestamp = Instant.now();
        updatedTimestamp = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTimestamp = Instant.now();
    }
}