package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.materialflow.entity.Order;
import kr.co.groupworks.materialflow.entity.OrderClassification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class OrderDTO {
    @Schema(description = "주문 등록 번호", defaultValue = "1")
    private Long id;
    @Schema(description = "주문 번호(발주/수주)", defaultValue = "1515-01-151")
    private String orderCode;

    @Schema(description = "구분(발주:1/수주:2)", defaultValue = "1")
    private Integer classification;
    @Schema(description = "총 액수", defaultValue = "1000000")
    private Long totalAmount;
    @Schema(description = "세 율", defaultValue = "100000")
    private Long tex;
    @Schema(description = "발주 일자", defaultValue = "1001.01.11")
    private LocalDateTime orderDate;
    @Schema(description = "납기 예정 일자", defaultValue = "9991.12.31")
    private LocalDateTime dueDate;
    @Schema(description = "납기 일자", defaultValue = "9991.12.31")
    private LocalDateTime deliveryDate;

    private EmployeeDTO employee;
    private ManagerDTO manager;
    private List<BomDTO> boms;
    private List<MesDTO> mes;

    public OrderDTO(Order o) {
        this
                .setId(o.getId())
                .setClassification(o.getClassification().ordinal())
                .setTotalAmount(o.getTotalAmount())
                .setTex(o.getTexAmount())
                .setOrderDate(o.getOrderDate())
                .setDueDate(o.getDueDate())
                .setDeliveryDate(o.getDeliveryDate())
                .setEmployee(new EmployeeDTO(o.getEmployee()))
                .setManager(new ManagerDTO(o.getManager()))
                .setBoms(o.getBoms().stream().map(BomDTO::new).toList())
                .setMes(o.getMes().stream().map(MesDTO::new).toList())
        ;
    }

    public Order dtoToEntity(Employee employee) {
        return Order.builder()
                .id(this.id)
                .classification(OrderClassification.getClassification(this.classification))
                .totalAmount(this.totalAmount)
                .texAmount(this.tex)
                .orderDate(this.orderDate)
                .dueDate(this.dueDate)
                .deliveryDate(this.deliveryDate)
                .employee(employee)
                .manager(this.manager.dtoToEntity())
                .boms(this.boms.stream().map(BomDTO::dtoToEntity).toList())
                .mes(this.mes.stream().map(MesDTO::dtoToEntity).toList())
                .build();
    }
}
