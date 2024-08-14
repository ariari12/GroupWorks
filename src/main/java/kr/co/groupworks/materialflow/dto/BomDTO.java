package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.materialflow.entity.Bom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class BomDTO {
    @Schema(description = "자재 등록 번호", defaultValue = "1")
    private long id;

    @Schema(description = "order_id", defaultValue = "1")
    private Long orderId;

    @Schema(description = "품목 코드", defaultValue = "0A2132454-0AB7-1")
    private String itemCode;
    @Schema(description = "품목 명", defaultValue = "우유")
    private String itemName;
    @Schema(description = "수량", defaultValue = "150")
    private long quantity;
    @Schema(description = "단가", defaultValue = "1")
    private long unitPrice;
    @Schema(description = "발주/수주 완료 상태(완료:true/미완료:false)", defaultValue = "false")
    private boolean status;

    private List<MaterialItemDTO> itemList;

    public BomDTO(Bom b) {
        this
                .setId(b.getId())
                .setOrderId(b.getOrderId())
                .setItemCode(b.getItemCode())
                .setItemName(b.getItemName())
                .setQuantity(b.getQuantity())
                .setUnitPrice(b.getUnitPrice())
                .setStatus(b.isStatus())
                .setItemList(b.getItemList() == null ? null : b.getItemList().stream().map(MaterialItemDTO::new).toList());
        ;
    }

    public Bom dtoToEntity() {
        return Bom.builder()
                .id(this.id)
                .orderId(this.orderId)
                .itemCode(this.itemCode)
                .itemName(this.itemName)
                .quantity(this.quantity)
                .unitPrice(this.unitPrice)
                .status(this.status)
                .itemList(this.itemList == null ? null : this.itemList.stream().map(MaterialItemDTO::dtoToEntity).toList())
                .build();
    }
}
