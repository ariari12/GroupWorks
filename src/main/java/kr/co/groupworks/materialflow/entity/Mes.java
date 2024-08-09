package kr.co.groupworks.materialflow.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "materialflow_mes")
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Mes {
    @Schema(description = "생산 기록번호", defaultValue = "02132454")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mes_id")
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

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<MaterialItem> itemList;
}

