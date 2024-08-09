package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$", message = "사업자 등록 번호 형식이 올바르지 않습니다.")
    @Schema(defaultValue = "125-34-14325", description = "사업자 등록 번호")
    private String businessNumber;
    @NotNull(message = "상호 명은 필수 입력 사항입니다.") @NotEmpty(message = "상호 명은 비어있을 수 없습니다.")
    @Schema(defaultValue = "◎◎제약™", description = "상호 명")
    private String businessName;
    @NotNull(message = "대표자 성명은 필수 입력 사항입니다.") @NotEmpty(message = "대표자 성명은 비어있을 수 없습니다.")
    @Schema(defaultValue = "홍길동", description = "대표자 성명")
    private String ceo;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{3,4}$", message = "연락처 번호 형식이 올바르지 않습니다.")
    @Schema(defaultValue = "010-1234-1325", description = "대표 연락처")
    private String ceoTel;

    @Schema(defaultValue = "제조업", description = "업태")
    private String type;
    @Schema(defaultValue = "유제품", description = "종목")
    private String item;

    @NotNull(message = "주소는 필수 입력 사항입니다.") @NotEmpty(message = "주소는 비어있을 수 없습니다.")
    @Schema(defaultValue = "경기도 이천시 이천동 2000번지 2000-1", description = "본사 주소지")
    private String address;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{3,4}$", message = "FAX 번호 형식이 올바르지 않습니다.")
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
