package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class MesOpenApiDTO {

    @NotNull(message = "BomItemCode(BOM 품목 코드) 항목은 비어있을 수 없습니다.")
    @NotEmpty(message = "BomItemCode(BOM 품목 코드) 항목은 비어있을 수 없습니다.")
    @Schema(description = "BOM 품목 코드", defaultValue = "02132-XAC1")
    private String itemCode;

    @NotNull(message = "itemName(제품 명) 항목은 비어있을 수 없습니다.")
    @NotEmpty(message = "itemName(제품 명) 항목은 비어있을 수 없습니다.")
    @Schema(description = "제품 명", defaultValue = "우유")
    private String itemName;

    @NotNull(message = "processLocation(공정 장소) 항목은 비어있을 수 없습니다.")
    @NotEmpty(message = "processLocation(공정 장소) 항목은 비어있을 수 없습니다.")
    @Schema(description = "공정 장소", defaultValue = "우유공장1")
    private String processLocation;

    @NotNull(message = "quantity(생산 수량) 항목은 비어있을 수 없습니다.")
    @Schema(description = "생산 수량", defaultValue = "100")
    private Long quantity;
    @Schema(description = "불량품 수량", defaultValue = "11")
    private Long defectsNum;

    @NotNull(message = "unitPrice(단가) 항목은 비어있을 수 없습니다.")
    @Schema(description = "단가", defaultValue = "1000")
    private Long unitPrice;

    @Schema(description = "제조 기록 일자, 비어있을 경우 현재시간으로 생성", defaultValue = "1999-11-27T17:01, 비어있을 경우 현재시간으로 생성")
    private String manufactureDate;

    public MesDTO toMesDTO() {
        LocalDateTime manufacture = manufactureDate == null ? LocalDateTime.now()
                : LocalDateTime.parse(manufactureDate, DateTimeFormatter.ofPattern(MesDTO.DATE_PATTERN));

        return MesDTO.builder()
                .itemCode(this.itemCode)
                .itemName(this.itemName)

                .processLocation(this.processLocation)
                .quantity(this.quantity)
                .defectsNum(this.defectsNum)
                .unitPrice(this.unitPrice)

                .manufactureDate(manufacture)
                .build();
    }

}

