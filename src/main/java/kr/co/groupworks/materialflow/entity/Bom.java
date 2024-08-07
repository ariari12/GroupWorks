package kr.co.groupworks.materialflow.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @Schema(name = "품목 코드", defaultValue = "02132454")
    private String itemCode;
    @Schema(name = "품목 명", defaultValue = "우유")
    private String itemName;
    @Schema(name = "수량", defaultValue = "100")
    private Long quantity;
    @Schema(name = "단가", defaultValue = "1")
    private String unitPrice;
    @Schema(name = "입고장소", defaultValue = "우리집")
    private String storageLocation;
    @Schema(name = "입고일자", defaultValue = "1111.11.11 17:01")
    private LocalDateTime storageTime;
    @Schema(name = "출고장소", defaultValue = "너희집")
    private String deliveryLocation;
    @Schema(name = "출고일자", defaultValue = "9999.99.99 97:91")
    private LocalDateTime deliveryTime;

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<MaterialItem> itemList;
}
