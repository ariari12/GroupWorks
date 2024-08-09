package kr.co.groupworks.materialflow.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import kr.co.groupworks.employee.entity.Employee;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
    @Schema(description = "주문 등록 번호", defaultValue = "1")
    private long id;
    @Schema(description = "주문 번호(발주/수주)", defaultValue = "1515-01-151")
    private String orderCode;

    @Convert(converter = OrderClassificationConverter.class)
    @Schema(description = "구분(발주/수주)", defaultValue = "발주")
    private OrderClassification classification;
    @Schema(description = "총 액수", defaultValue = "1000000")
    private long totalAmount;
    @Schema(description = "세 율", defaultValue = "10")
    private long texAmount;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Schema(description = "발주 일자", defaultValue = "1001.01.11")
    private LocalDate orderDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Schema(description = "납기 예정 일자", defaultValue = "9991.12.31")
    private LocalDate dueDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Schema(description = "납기 일자", defaultValue = "9991.12.31")
    private LocalDate deliveryDate;

    @Schema(description = "납품 주소", defaultValue = "경기도 이천시 이천동 2000번지")
    private String address;
    @Schema(description = "납품 상세주소", defaultValue = "2동 2001호")
    private String addressDetail;
    @Column(length = 5)
    @Schema(description = "납품 우편번호", defaultValue = "68447-15564")
    private String zipCode;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private BusinessManager manager;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Bom> boms;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Mes> mes;
}
