package kr.co.groupworks.materialflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "mes")
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Mes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mes_id")
    private Long id;

    private String itemCode;
    private String itemName;
    private String processLocation;
    private Long itemNum;
    private Long defectsNum;
    private Long unitPrice;
    private LocalDate manufactureDate;
}

