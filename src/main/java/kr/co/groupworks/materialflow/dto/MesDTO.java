package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.materialflow.entity.Mes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class MesDTO {
    @Schema(description = "생산 기록번호", defaultValue = "1")
    private long id;

    @Schema(description = "발주/수주 Id", defaultValue = "1")
    private Long orderId;
    @Schema(description = "BOM Id", defaultValue = "2")
    private Long bomId;

    @Schema(description = "품목 코드", defaultValue = "02132454")
    private String itemCode;
    @Schema(description = "제품 명", defaultValue = "우유")
    private String itemName;

    @Schema(description = "공정 장소", defaultValue = "우유공장1")
    private String processLocation;
    @Schema(description = "생산 수량", defaultValue = "100")
    private Long quantity;
    @Schema(description = "불량품 수량", defaultValue = "11")
    private Long defectsNum;
    @Schema(description = "단가", defaultValue = "1000")
    private Long unitPrice;

    @Schema(description = "제조 기록 일자", defaultValue = "1999-11-27T17:16")
    private LocalDateTime manufactureDate;
    public static final String DATE_PATTERN = "yyyy.MM.dd'T'HH:mm:ss";

    public MesDTO(Mes m) {
        this
                .setId(m.getId())
                .setOrderId(m.getOrderId())
                .setBomId(m.getBomId())

                .setItemCode(m.getItemCode())
                .setItemName(m.getItemName())

                .setProcessLocation(m.getProcessLocation())
                .setQuantity(m.getQuantity())
                .setDefectsNum(m.getDefectsNum())
                .setUnitPrice(m.getUnitPrice())

                .setManufactureDate(m.getManufactureDate())
        ;
    }

    public Mes dtoToEntity() {
        return Mes.builder()
                .id(this.getId())
                .orderId(this.getOrderId())
                .bomId(this.getBomId())

                .itemCode(this.getItemCode())
                .itemName(this.getItemName())

                .processLocation(this.getProcessLocation())
                .quantity(this.getQuantity())
                .defectsNum(this.getDefectsNum())
                .unitPrice(this.getUnitPrice())

                .manufactureDate(this.getManufactureDate())
                .build();
    }
}

