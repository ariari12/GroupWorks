package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.materialflow.entity.Mes;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.format.DateTimeFormatter;

@Getter @ToString
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class MesListVO {
    @Schema(description = "발주/수주 번호", defaultValue = "191716-0A6653603-X773")
    private String orderCode;

    @Schema(description = "발주서/수주서 기록 이동 url(/order-detail/order/classification)", defaultValue = "/")
    private String orderUrl;

    @Schema(description = "BOM 자재 현황 이동 Url(/item/bomId/BomItemCode/itemName)",
            defaultValue = "2/0A6653603-X773-1/A4-300")
    private String bomItemUrl;

    @Schema(description = "품목코드", defaultValue = "4122044-4AC7-1")
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

    @Schema(description = "제조 기록 일자", defaultValue = "1999.11.27")
    private String manufactureDate;

    public MesListVO(String orderCode, Long oId, int cl, Long bomid, Mes m) {
        final String DATE_PATTERN = "yyyy.MM.dd HH:mm:ss";

        this.orderCode = orderCode;
        this.orderUrl = "/materialflow/order-detail/" + oId + "/" + cl;
        this.bomItemUrl = "/materialflow/item/" + bomid + "/" + m.getItemCode() + "/" + m.getItemName();

        this.itemCode = m.getItemCode();
        this.itemName = m.getItemName();

        this.processLocation = m.getProcessLocation();
        this.quantity = m.getQuantity();
        this.defectsNum = m.getDefectsNum();
        this.unitPrice = m.getUnitPrice();
        this.manufactureDate = m.getManufactureDate().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public MesListVO(Mes m) {
        final String DATE_PATTERN = "yyyy.MM.dd HH:mm:ss";

        this.itemCode = m.getItemCode();
        this.itemName = m.getItemName();

        this.processLocation = m.getProcessLocation();
        this.quantity = m.getQuantity();
        this.defectsNum = m.getDefectsNum();
        this.unitPrice = m.getUnitPrice();
        this.manufactureDate = m.getManufactureDate().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}

