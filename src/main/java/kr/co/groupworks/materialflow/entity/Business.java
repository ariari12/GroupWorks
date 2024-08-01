package kr.co.groupworks.materialflow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "business")
@Builder
@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id")
    private Long id;

    private String businessNumber;
    private String businessName;
    private String ceo;
    private String businessAddress;
    private String businessTel;
    private String businessFax;
}
