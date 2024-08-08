package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Convert;
import kr.co.groupworks.materialflow.entity.ItemStatus;
import kr.co.groupworks.materialflow.entity.ItemStatusConverter;
import kr.co.groupworks.materialflow.entity.MaterialItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class MaterialItemDTO {
    @Schema(description = "자재 고유 번호", defaultValue = "0")
    private long id;

    @Schema(description = "자재 상태(1:입하예정, 2:입고, 3:생산, 4:출하예정, 5:출고)", defaultValue = "입고")
    @Convert(converter = ItemStatusConverter.class)
    private ItemStatus itemStatus;

    @Schema(description = "입고장소", defaultValue = "우리집")
    private String storageLocation;
    @Schema(description = "입고일자", defaultValue = "1111.11.11 17:01")
    private LocalDateTime storageTime;
    @Schema(description = "출고장소", defaultValue = "너희집")
    private String deliveryLocation;
    @Schema(description = "출고일자", defaultValue = "9999.99.99 97:91")
    private LocalDateTime deliveryTime;

    public MaterialItemDTO(MaterialItem mi) {
        this
                .setId(mi.getId())
                .setItemStatus(mi.getItemStatus())
        ;
    }

    public MaterialItem dtoToEntity() {
        return MaterialItem.builder()
                .id(this.getId())
                .itemStatus(this.getItemStatus())
                .storageLocation(this.getStorageLocation())
                .storageTime(this.getStorageTime())
                .deliveryLocation(this.getDeliveryLocation())
                .deliveryTime(this.getDeliveryTime())
                .build();
    }
}
