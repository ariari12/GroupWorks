package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.materialflow.entity.Business;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class BusinessDTO {
    @Schema(defaultValue = "1", description = "거래처 등록 번호")
    private Long id;

    @Schema(defaultValue = "12345-12346-1325", description = "사업자 등록 번호")
    private String businessNumber;
    @Schema(defaultValue = "◎◎제약™", description = "상호 명")
    private String businessName;
    @Schema(defaultValue = "홍길동", description = "대표자 성명")
    private String ceo;
    @Schema(defaultValue = "010-1234-1325", description = "대표 연락처")
    private String ceoTel;

    @Schema(defaultValue = "제조업", description = "업태")
    private String type;
    @Schema(defaultValue = "유제품", description = "종목")
    private String item;

    @Schema(defaultValue = "경기도 이천시 이천동 2000번지 2000-1", description = "본사 주소지")
    private String address;
    @Schema(defaultValue = "123-1234-1325", description = "FAX 번호")
    private String fax;

    public BusinessDTO(Business business) {
        this
                .setId(business.getId())
                .setBusinessNumber(business.getBusinessNumber())
                .setBusinessName(business.getBusinessName())
                .setCeo(business.getCeo())
                .setCeoTel(business.getCeoTel())
                .setType(business.getType())
                .setItem(business.getItem())
                .setAddress(business.getAddress())
                .setFax(business.getFax())
        ;
    }

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
