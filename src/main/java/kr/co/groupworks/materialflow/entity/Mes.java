package kr.co.groupworks.materialflow.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "mes")
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Mes {
    @Schema(name = "생산 기록번호", defaultValue = "02132454")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mes_id")
    private Long id;

    @Schema(name = "제품 코드", defaultValue = "02132454")
    private String itemCode;
    @Schema(name = "제품 명", defaultValue = "우유")
    private String itemName;
    @Schema(name = "공정 장소", defaultValue = "우유공장1")
    private String process;
    @Schema(name = "생산 수량", defaultValue = "100")
    private Long quantity;
    @Schema(name = "불량품 수량", defaultValue = "11")
    private Long defectsNum;
    @Schema(name = "단가", defaultValue = "1000")
    private Long unitPrice;
    @Schema(name = "제조일자", defaultValue = "1999.11.27")
    private LocalDate manufactureDate;
}

