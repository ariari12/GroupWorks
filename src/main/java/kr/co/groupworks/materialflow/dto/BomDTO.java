package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.materialflow.entity.Bom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class BomDTO {
    @Schema(description = "자재 등록 번호", defaultValue = "1")
    private Long id;

    @Schema(description = "품목 코드", defaultValue = "02132454")
    private String itemCode;
    @Schema(description = "품목 명", defaultValue = "우유")
    private String itemName;
    @Schema(description = "수량", defaultValue = "100")
    private Long quantity;
    @Schema(description = "단가", defaultValue = "1")
    private String unitPrice;
    @Schema(description = "입고장소", defaultValue = "우리집")
    private String storageLocation;
    @Schema(description = "입고일자", defaultValue = "1111.11.11 17:01")
    private LocalDateTime storageTime;
    @Schema(description = "출고장소", defaultValue = "너희집")
    private String deliveryLocation;
    @Schema(description = "출고일자", defaultValue = "9999.99.99 97:91")
    private LocalDateTime deliveryTime;
    @Schema(description = "납품 우편번호", defaultValue = "1")
    private String zipCode;

    private List<MesDTO> mesList;

    public BomDTO(Bom b) {
        this
                .setId(b.getId())
                .setItemCode(b.getItemCode())
                .setItemName(b.getItemName())
                .setQuantity(b.getQuantity())
                .setUnitPrice(b.getUnitPrice())
                .setStorageLocation(b.getStorageLocation())
                .setStorageTime(b.getStorageTime())
                .setDeliveryLocation(b.getDeliveryLocation())
                .setDeliveryTime(b.getDeliveryTime())
                .setZipCode(b.getZipCode())
        ;
    }

    public Bom dtoToEntity() {
        return Bom.builder()
                .id(this.id)
                .itemCode(this.itemCode)
                .itemName(this.itemName)
                .quantity(this.quantity)
                .unitPrice(this.unitPrice)
                .storageLocation(this.storageLocation)
                .storageTime(this.storageTime)
                .deliveryLocation(this.deliveryLocation)
                .deliveryTime(this.deliveryTime)
                .zipCode(this.zipCode)
                .build();
    }
}
