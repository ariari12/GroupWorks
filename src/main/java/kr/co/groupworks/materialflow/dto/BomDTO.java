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

    @Schema(description = "품목 코드", defaultValue = "0A2132454-0AB7-1")
    private String itemCode;
    @Schema(description = "품목 명", defaultValue = "우유")
    private String itemName;
    @Schema(description = "수량", defaultValue = "150")
    private long quantity;
    @Schema(description = "단가", defaultValue = "1")
    private long unitPrice;
    @Schema(description = "입고 수량", defaultValue = "100")
    private long stockQuantity;
    @Schema(description = "출고 수량", defaultValue = "30")
    private long deliveryQuantity;

    private List<MaterialItemDTO> itemList;

    public BomDTO(Bom b) {
        this
                .setId(b.getId())
                .setItemCode(b.getItemCode())
                .setItemName(b.getItemName())
                .setQuantity(b.getQuantity())
                .setUnitPrice(b.getUnitPrice())
                .setStockQuantity(b.getStockQuantity())
                .setDeliveryQuantity(b.getDeliveryQuantity())
                .setItemList(b.getItemList() == null ? null : b.getItemList().stream().map(MaterialItemDTO::new).toList());
        ;
    }

    public Bom dtoToEntity() {
        return Bom.builder()
                .id(this.id)
                .itemCode(this.itemCode)
                .itemName(this.itemName)
                .quantity(this.quantity)
                .unitPrice(this.unitPrice)
                .stockQuantity(this.stockQuantity)
                .deliveryQuantity(this.deliveryQuantity)
                .itemList(this.itemList == null ? null : this.itemList.stream().map(MaterialItemDTO::dtoToEntity).toList())
                .build();
    }
}
