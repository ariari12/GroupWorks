package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import kr.co.groupworks.materialflow.entity.ItemStatus;
import kr.co.groupworks.materialflow.entity.MaterialItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class MaterialItemDTO {
    @Schema(description = "자재 고유 번호", defaultValue = "0")
    private long id;

    @Schema(description = "bom 참조 번호", defaultValue = "0")
    @Column(name = "bom_id")
    private Long bomId;

    @Schema(description = "mes 참조 번호", defaultValue = "0")
    @Column(name = "mes_id")
    private Long mesId;

    @Schema(description = "자재 상태(1:입하예정, 2:입고, 3:생산, 4:출하예정, 5:출고)", defaultValue = "입고")
    private ItemStatus itemStatus;
    @Schema(description = "자재 코드(품목코드 + n번째 + 식별 id)", defaultValue = "0A9294117-Y6G3-1417, (4번째, 식별 id:17)")
    private String itemCode;

    @Schema(description = "입고 담당자(소속-성명)", defaultValue = "△△운송-홍길동")
    private String storageManager;
    @Schema(name = "입고장소", defaultValue = "○○-3층 17번 입하장")
    private String storageLocation;
    @Schema(name = "입고일자", defaultValue = "1111-11-11T17:01")
    private LocalDate storageTime;

    @Schema(description = "출고 담당자(소속-성명)", defaultValue = "ㅁㅁ운송-홍길동")
    private String deliveryManager;
    @Schema(name = "출고장소", defaultValue = "ㅁㅁ창고-9번 출하장")
    private String deliveryLocation;
    @Schema(name = "출고일자", defaultValue = "9999-99-99T97:91")
    private LocalDate deliveryTime;

    public MaterialItemDTO(MaterialItem mi) {
        this
                .setId(mi.getId())
                .setBomId(mi.getBomId())
                .setMesId(mi.getMesId())

                .setItemStatus(mi.getItemStatus())
                .setItemCode(mi.getItemCode())

                .setStorageManager(mi.getStorageManager())
                .setStorageLocation(mi.getStorageLocation())
                .setStorageTime(mi.getStorageTime())

                .setDeliveryManager(mi.getDeliveryManager())
                .setDeliveryLocation(mi.getDeliveryLocation())
                .setDeliveryTime(mi.getDeliveryTime())
        ;
    }

    public MaterialItem dtoToEntity() {
        return MaterialItem.builder()
                .id(this.getId())
                .bomId(this.getBomId())
                .mesId(this.getMesId())

                .itemStatus(this.getItemStatus())
                .itemCode(this.getItemCode())

                .storageManager(this.getStorageManager())
                .storageLocation(this.getStorageLocation())
                .storageTime(this.getStorageTime())

                .deliveryManager(this.getDeliveryManager())
                .deliveryLocation(this.getDeliveryLocation())
                .deliveryTime(this.getDeliveryTime())
                .build();
    }
}
