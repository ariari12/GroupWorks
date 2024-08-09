package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.materialflow.entity.Mes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class MesDTO {
    @Schema(description = "생산 기록번호", defaultValue = "02132454")
    private Long id;

    @Schema(description = "제품 코드", defaultValue = "02132454")
    private String itemCode;
    @Schema(description = "제품 명", defaultValue = "우유")
    private String itemName;
    @Schema(description = "공정 장소", defaultValue = "우유공장1")
    private String process;
    @Schema(description = "생산 수량", defaultValue = "100")
    private Long quantity;
    @Schema(description = "불량품 수량", defaultValue = "11")
    private Long defectsNum;
    @Schema(description = "단가", defaultValue = "1000")
    private Long unitPrice;
    @Schema(description = "제조일자", defaultValue = "1999.11.27")
    private LocalDate manufactureDate;

    public MesDTO(Mes m) {
        this
                .setId(m.getId())
                .setItemCode(m.getItemCode())
                .setItemName(m.getItemName())
                .setProcess(m.getProcess())
                .setQuantity(m.getQuantity())
                .setDefectsNum(m.getDefectsNum())
                .setUnitPrice(m.getUnitPrice())
                .setManufactureDate(m.getManufactureDate())
        ;
    }

    public Mes dtoToEntity() {
        return Mes.builder()
                .id(this.getId())
                .itemCode(this.getItemCode())
                .itemName(this.getItemName())
                .process(this.getProcess())
                .quantity(this.getQuantity())
                .defectsNum(this.getDefectsNum())
                .unitPrice(this.getUnitPrice())
                .manufactureDate(this.getManufactureDate())
                .build();
    }
}

