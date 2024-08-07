package kr.co.groupworks.materialflow.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @ToString
@Entity
@Table(name = "material_business_manager")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;
    private String name;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;
}
