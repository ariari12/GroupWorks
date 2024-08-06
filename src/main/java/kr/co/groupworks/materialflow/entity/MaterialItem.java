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
    @Schema(description = "자재 고유 번호", defaultValue = "0")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id;

    @Schema(description = "자재 상태(1:입하예정, 2:입고, 3:생산, 4:출하예정, 5:출고)", defaultValue = "입고")
    @Convert(converter = ItemStatusConverter.class)
    private ItemStatus itemStatus;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "bom_id")
    private Bom bom;
}
