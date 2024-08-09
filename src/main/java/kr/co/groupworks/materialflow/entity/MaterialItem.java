package kr.co.groupworks.materialflow.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "materialflow_item")
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MaterialItem {
    @Schema(description = "자재 고유 번호", defaultValue = "0")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "bom_id")
    private Long bomId;

    @Schema(description = "자재 상태(1:입하예정, 2:입고, 3:생산, 4:출하예정, 5:출고)", defaultValue = "입고")
    @Convert(converter = ItemStatusConverter.class)
    private ItemStatus itemStatus;

    @Schema(description = "입고 담당자(소속-성명)", defaultValue = "△△운송-홍길동")
    private String storageManager;
    @Schema(name = "입고장소", defaultValue = "○○-3층 17번 입하장")
    private String storageLocation;
    @Schema(name = "입고일자", defaultValue = "1111.11.11 17:01")
    private LocalDateTime storageTime;

    @Schema(description = "출고 담당자(소속-성명)", defaultValue = "ㅁㅁ운송-홍길동")
    private String deliveryManager;
    @Schema(name = "출고장소", defaultValue = "ㅁㅁ창고-9번 출하장")
    private String deliveryLocation;
    @Schema(name = "출고일자", defaultValue = "9999.99.99 97:91")
    private LocalDateTime deliveryTime;
}
