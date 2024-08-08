package kr.co.groupworks.materialflow.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "materialflow_bom")
@Builder
@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Bom {
    @Schema(name = "자재 등록 번호", defaultValue = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bom_id")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Schema(name = "품목 코드", defaultValue = "02132454")
    private String itemCode;
    @Schema(name = "품목 명", defaultValue = "우유")
    private String itemName;
    @Schema(name = "수량", defaultValue = "100")
    private long quantity;
    @Schema(name = "단가", defaultValue = "1")
    private long unitPrice;
    @Schema(description = "입고 수량", defaultValue = "100")
    private long stockQuantity;
    @Schema(description = "출고 수량", defaultValue = "30")
    private long deliveryQuantity;

    @OneToMany
    @JoinColumn(name = "bom_id")
    private List<MaterialItem> itemList;
}
