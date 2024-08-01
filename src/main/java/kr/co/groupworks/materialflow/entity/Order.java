package kr.co.groupworks.materialflow.entity;

import jakarta.persistence.*;
import kr.co.groupworks.employee.entity.Employee;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "materialflow_order")
@Builder
@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "business_id")
    private Business business;
    private String manager;
    private String managerPhone;
    private String managerEmail;

    @Convert(converter = OrderClassificationConverter.class)
    private OrderClassification classification;

    private Long totalAmount;
    private Long texAmount;
    private LocalDateTime orderDate;
    private LocalDateTime dueDate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "bom_id")
    private List<Bom> boms;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "mes_id")
    private List<Mes> mes;
}
