package kr.co.groupworks.materialflow.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materialflow_business")
@Builder
@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id")
    @Schema(name = "거래처 등록 번호", defaultValue = "1")
    private Long id;

    @Schema(name = "사업자 번호", defaultValue = "12345-12346-1325")
    private String businessNumber;
    @Schema(name = "상호명", defaultValue = "명일제약")
    private String businessName;
    @Schema(name = "대표자 명", defaultValue = "홍길동")
    private String ceo;
    @Schema(name = "사업장 주소", defaultValue = "경기도 이천시 이천동 2000번지 2000-1")
    private String businessAddress;
    @Schema(name = "거래처 연락처", defaultValue = "010-1234-1325")
    private String businessTel;
    @Schema(name = "거래처 FAX 번호", defaultValue = "123-1234-1325")
    private String businessFax;
}
