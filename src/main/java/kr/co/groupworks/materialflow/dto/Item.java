package kr.co.groupworks.materialflow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.groupworks.materialflow.entity.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Item {
    private Integer itemStatus;

    private String storageManager;
    private String storageLocation;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate storageTime;

    private String deliveryManager;
    private String deliveryLocation;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate deliveryTime;

    public MaterialItemDTO convertToDTO(MaterialItemDTO itemDTO) {
        return itemDTO
                .setItemStatus(ItemStatus.getItemStatus(this.itemStatus))

                .setStorageManager(this.storageManager)
                .setStorageLocation(this.storageLocation)
                .setStorageTime(this.storageTime)

                .setDeliveryManager(this.deliveryManager)
                .setDeliveryLocation(this.deliveryLocation)
                .setDeliveryTime(this.deliveryTime);
    }
}
