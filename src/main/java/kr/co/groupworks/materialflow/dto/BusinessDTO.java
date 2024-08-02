package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.materialflow.entity.Business;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDTO {
    @Schema(defaultValue = "1")
    private Long id;

    @Schema(defaultValue = "12345-12346-1325")
    private String businessNumber;
    @Schema(defaultValue = "◎◎제약™")
    private String businessName;
    @Schema(defaultValue = "홍길동")
    private String ceo;
    @Schema(defaultValue = "010-1234-1325")
    private String ceoTel;

    @Schema(defaultValue = "제조업")
    private String type;
    @Schema(defaultValue = "유제품")
    private String item;

    @Schema(defaultValue = "경기도 이천시 이천동 2000번지 2000-1")
    private String address;
    @Schema(defaultValue = "123-1234-1325")
    private String fax;

    public Business dtoToEntity() {
        return Business.builder()
                .id(id)
                .businessNumber(businessNumber)
                .businessName(businessName)
                .ceo(ceo)
                .ceoTel(ceoTel)
                .type(type)
                .item(item)
                .address(address)
                .fax(fax)
                .build();
    }
}
