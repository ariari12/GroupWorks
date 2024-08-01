package kr.co.groupworks.materialflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bom")
@Builder
@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Bom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bom_id")
    private Long id;

    private String itemCode;
    private String itemName;
    @Convert(converter = ItemStatusConverter.class)
    private ItemStatus itemStatus;
    private String targetNum;
    private String currentNum;
    private String unitPrice;
    private String storageLocation;
    private LocalDateTime storageTime;
    private String deliveryLocation;
    private LocalDateTime deliveryTime;
    private String zipCode;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "mes_id")
    private List<Mes> mesList;
}
