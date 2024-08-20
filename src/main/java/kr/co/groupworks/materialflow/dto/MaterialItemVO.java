package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import kr.co.groupworks.materialflow.entity.MaterialItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.format.DateTimeFormatter;

@Getter @ToString
@NoArgsConstructor
public class MaterialItemVO {
    @Schema(description = "자재 고유 번호", defaultValue = "0")
    private long id;

    @Schema(description = "bom 참조 번호", defaultValue = "0")
    @Column(name = "bom_id")
    private Long bomId;

    @Schema(description = "mes 참조 번호", defaultValue = "0")
    @Column(name = "mes_id")
    private Long mesId;

    @Schema(description = "자재 상태(1:입하예정, 2:입고, 3:생산, 4:출하예정, 5:출고)", defaultValue = "입고")
    private String itemStatus;
    @Schema(description = "자재 코드(품목코드 + n번째 + 식별 id)", defaultValue = "0A9294117-Y6G3-1417, (4번째, 식별 id:17)")
    private String itemCode;

    @Schema(description = "입고 담당자(소속-성명)", defaultValue = "△△운송-홍길동")
    private String storageManager;
    @Schema(name = "입고장소", defaultValue = "○○-3층 17번 입하장")
    private String storageLocation;
    @Schema(name = "입고일자", defaultValue = "1111.11.11 17:01")
    private String storageTime;

    @Schema(description = "출고 담당자(소속-성명)", defaultValue = "ㅁㅁ운송-홍길동")
    private String deliveryManager;
    @Schema(name = "출고장소", defaultValue = "ㅁㅁ창고-9번 출하장")
    private String deliveryLocation;
    @Schema(name = "출고일자", defaultValue = "9999.99.99 97:91")
    private String deliveryTime;

    private final String DATE_PATTERN = "yyyy.MM.dd";

    public MaterialItemVO(MaterialItem item) {
        this.id = item.getId();
        this.bomId = item.getBomId();

        this.itemStatus = item.getItemStatus() == null ? null : item.getItemStatus().getValue();
        this.itemCode = item.getItemCode();

        this.storageManager = item.getStorageManager();
        this.storageLocation = item.getStorageLocation();
        this.storageTime = item.getStorageTime() == null ? null : item.getStorageTime().format(DateTimeFormatter.ofPattern(DATE_PATTERN));

        this.deliveryManager = item.getDeliveryManager();
        this.deliveryLocation = item.getDeliveryLocation();
        this.deliveryTime = item.getDeliveryTime() == null ? null : item.getDeliveryTime().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public MaterialItemVO(MaterialItemDTO itemDTO) {
        this.id = itemDTO.getId();
        this.bomId = itemDTO.getBomId();
        this.mesId = itemDTO.getMesId();

        this.itemStatus = itemDTO.getItemStatus() == null ? null : itemDTO.getItemStatus().getValue();
        this.itemCode = itemDTO.getItemCode();

        this.storageManager = itemDTO.getStorageManager();
        this.storageLocation = itemDTO.getStorageLocation();
        this.storageTime = itemDTO.getStorageTime() == null ? null : itemDTO.getStorageTime().format(DateTimeFormatter.ofPattern(DATE_PATTERN));

        this.deliveryManager = itemDTO.getDeliveryManager();
        this.deliveryLocation = itemDTO.getDeliveryLocation();
        this.deliveryTime = itemDTO.getDeliveryTime() == null ? null : itemDTO.getDeliveryTime().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}
