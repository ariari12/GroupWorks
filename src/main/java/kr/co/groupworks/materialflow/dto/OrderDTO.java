package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.materialflow.entity.BusinessManager;
import kr.co.groupworks.materialflow.entity.Order;
import kr.co.groupworks.materialflow.entity.OrderClassification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class OrderDTO {
    @Schema(description = "주문 등록 번호", defaultValue = "1")
    private Long id;

    @NotNull(message = "PageError:새로고침 후 작성해주세요.") @NotEmpty(message = "PageError:새로고침 후 작성해주세요.")
    @Schema(description = "주문 번호(발주/수주)", defaultValue = "1515-01-151")
    private String orderCode;

    @NotNull(message = "PageError:새로고침 후 작성해주세요.")
    @Schema(description = "구분(발주:1/수주:2)", defaultValue = "1", minimum = "10")
    private Integer classification;

    @NotNull(message = "Error: 품목은 1가지 이상 존재해야 합니다.") @Min(10L)
    @Schema(description = "총 액수", defaultValue = "1000000")
    private Long totalAmount;

    @Schema(description = "세 율", defaultValue = "100000")
    private Long tex;

    @NotNull(message = "발주 일자를 입력하세요.") @NotEmpty(message = "발주 일자를 입력하세요.")
    @Pattern(regexp = "^\\d{4}.\\d{2}.\\d{2}$", message = "날짜형식이 일치하지 않습니다.")
    @Schema(description = "발주 일자", defaultValue = "1001.01.11")
    private String orderDate;

    @NotNull(message = "발주 일자를 입력하세요.") @NotEmpty(message = "발주 일자를 입력하세요.")
    @Pattern(regexp = "^\\d{4}.\\d{2}.\\d{2}$", message = "날짜형식이 일치하지 않습니다.")
    @Schema(description = "납기 예정 일자", defaultValue = "9991.12.31")
    private String dueDate;

    @Pattern(regexp = "^\\d{4}.\\d{2}.\\d{2}$", message = "날짜형식이 일치하지 않습니다.")
    @Schema(description = "납기 일자", defaultValue = "9991.12.31")
    private String deliveryDate;

    @NotNull(message = "납품 주소를 입력하세요.") @NotEmpty(message = "납품 주소를 입력하세요.")
    @Schema(description = "납품 주소", defaultValue = "경기도 이천시 이천동 2000번지")
    private String address;

    @Schema(description = "납품 상세주소", defaultValue = "2동 2001호")
    private String addressDetail;

    @NotNull(message = "납품 우편번호를 입력하세요.") @NotEmpty(message = "납품 우편번호를 입력하세요.")
    @Size(min = 5, max = 5, message = "우편번호는 5자리입니다.")
    @Schema(description = "납품 우편번호", defaultValue = "68447")
    private String zipCode;

    private EmployeeDTO employee;
    private ManagerDTO manager;

    @Size(min = 1, message = "품목은 최소 1종류 이상 존재해야 합니다.")
    private List<BomDTO> bomList;
    private List<MesDTO> mesList;

    public static final String DATE_FORMAT = "yyyy.MM.dd";

    public OrderDTO(Order o) {
        LocalDate od = o.getOrderDate(), duD = o.getDueDate(), dlD = o.getDeliveryDate();
        OrderClassification oc = o.getClassification();

        this
                .setId(o.getId())
                .setOrderCode(o.getOrderCode())
                .setClassification(oc == null ? null : oc.ordinal())
                .setTotalAmount(o.getTotalAmount())
                .setTex(o.getTexAmount())
                .setAddress(o.getAddress())
                .setAddressDetail(o.getAddressDetail())
                .setZipCode(o.getZipCode())
                .setEmployee(new EmployeeDTO(o.getEmployee()))
                .setManager(new ManagerDTO(o.getManager()))
                .setOrderDate(od == null ? null : od.format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .setDueDate(duD == null ? null : o.getDueDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .setDeliveryDate(dlD == null ? null : o.getDeliveryDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .setBomList(o.getBoms().stream().map(BomDTO::new).toList())
                .setMesList(o.getMes().stream().map(MesDTO::new).toList())
        ;
    }

    public Order dtoToEntity(Employee e, BusinessManager bm) {
        String od = this.orderDate, duD = this.dueDate, dlD = this.deliveryDate;

        return Order.builder()
                .id(this.id)
                .orderCode(this.orderCode)
                .classification(OrderClassification.getClassification(this.classification))
                .totalAmount(this.totalAmount)
                .texAmount(this.tex)
                .orderDate(od == null ? null : LocalDate.parse(od, DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .dueDate(duD == null ? null : LocalDate.parse(duD, DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .deliveryDate(dlD == null ? null : LocalDate.parse(dlD, DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .address(this.address)
                .addressDetail(this.addressDetail)
                .zipCode(this.zipCode)
                .employee(e)
                .manager(bm)
                .boms(this.bomList == null ? null : this.bomList.stream().map(BomDTO::dtoToEntity).toList())
                .mes(this.mesList == null ? null : this.mesList.stream().map(MesDTO::dtoToEntity).toList())
                .build();
    }

    public Order dtoToEntity(Employee e) {
        String od = this.orderDate, duD = this.dueDate, dlD = this.deliveryDate;

        return Order.builder()
                .id(this.id)
                .orderCode(this.orderCode)
                .classification(OrderClassification.getClassification(this.classification))
                .totalAmount(this.totalAmount)
                .texAmount(this.tex)
                .orderDate(od == null ? null : LocalDate.parse(od, DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .dueDate(duD == null ? null : LocalDate.parse(duD, DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .deliveryDate(dlD == null ? null : LocalDate.parse(dlD, DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .address(this.address)
                .addressDetail(this.addressDetail)
                .zipCode(this.zipCode)
                .employee(e)
                .manager(this.manager.dtoToEntity())
                .boms(this.bomList == null ? null : this.bomList.stream().map(BomDTO::dtoToEntity).toList())
                .mes(this.mesList == null ? null : this.mesList.stream().map(MesDTO::dtoToEntity).toList())
                .build();
    }
}
