package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import kr.co.groupworks.materialflow.entity.Order;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class OrderBomListVO {
    @Schema(description = "주문 등록 번호", defaultValue = "1")
    private Long id;
    @Schema(description = "주문 번호(발주/수주)", defaultValue = "1515-01-151")
    private String orderCode;

    @Schema(description = "구분(발주:1/수주:2)", defaultValue = "1", minimum = "10")
    private Integer classification;
    @Schema(description = "총 액수", defaultValue = "1000000")
    private Long totalAmount;
    @Schema(description = "세 율", defaultValue = "100000")
    private Long tex;

    @Schema(description = "발주 일자", defaultValue = "1001.01.11")
    private String orderDate;
    @Schema(description = "납기 예정 일자", defaultValue = "9991.12.31")
    private String dueDate;
    @Schema(description = "납기 일자", defaultValue = "9991.12.31")
    private String deliveryDate;

    @Schema(description = "납품 주소", defaultValue = "경기도 이천시 이천동 2000번지")
    private String address;
    @Schema(description = "납품 상세주소", defaultValue = "2동 2001호")
    private String addressDetail;
    @NotNull(message = "납품 우편번호를 입력하세요.") @NotEmpty(message = "납품 우편번호를 입력하세요.")
    @Schema(description = "납품 우편번호", defaultValue = "68447")
    private String zipCode;

    private List<BomDTO> bomList;

    private static final String DATE_FORMAT = "yyyy.MM.dd";

    public OrderBomListVO(Order o) {
        LocalDate od = o.getOrderDate(), duD = o.getDueDate(), dlD = o.getDeliveryDate();

        this
                .setId(o.getId())
                .setOrderCode(o.getOrderCode())
                .setClassification(o.getClassification().ordinal())
                .setTotalAmount(o.getTotalAmount())
                .setTex(o.getTexAmount())
                .setAddress(o.getAddress())
                .setAddressDetail(o.getAddressDetail())
                .setZipCode(o.getZipCode())
                .setOrderDate(od == null ? null : od.format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .setDueDate(duD == null ? null : o.getDueDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .setDeliveryDate(dlD == null ? null : o.getDeliveryDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .setBomList(o.getBoms().stream().map(BomDTO::new).toList())
        ;
    }
}
