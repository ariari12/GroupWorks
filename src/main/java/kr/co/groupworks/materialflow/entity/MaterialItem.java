package kr.co.groupworks.materialflow.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materialflow_item")
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MaterialItem {
    @Schema(name = "자재 고유 번호", defaultValue = "0")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id;

    @Schema(name = "자재 상태", defaultValue = "입고", description = "1입하예정, 2입고, 3생산, 4출하예정, 5출고")
    @Convert(converter = ItemStatusConverter.class)
    private ItemStatus itemStatus;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "bom_id")
    private Bom bom;
}
