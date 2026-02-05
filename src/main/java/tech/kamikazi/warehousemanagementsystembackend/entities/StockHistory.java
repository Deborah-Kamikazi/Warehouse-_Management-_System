package tech.kamikazi.warehousemanagementsystembackend.entities;

import jakarta.persistence.*;
import lombok.*;
import tech.kamikazi.warehousemanagementsystembackend.enums.StrockAction;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "stock_history")
public class StockHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private StrockAction action;

    @Column(name = "itemNumber", nullable = false)
    private String itemNumber;

    @Column(name = "quantity_Change" , nullable = false)
    private Integer quantityChange;

    @Column(name = "fromLocation_code")
    private String fromLocationCode;

    @Column(name = "toLocation_Code")
    private String toLocationCode;

    @Column(name = "warehouse_Number" , nullable = false)
    private String warehouseNumber;

    @Column(name = "sscc")
    private String sscc;

    @Column(name = "created_Timestamp")
    private Instant createdTimestamp;

}
