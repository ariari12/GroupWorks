package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import kr.co.groupworks.materialflow.entity.BusinessManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class ManagerDTO {
    @Schema(description = "담당자 등록 번호", defaultValue = "1")
    private Long id;

    @Schema(description = "담당자 성명", defaultValue = "홍길동")
    private String name;
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 형식이 올바르지 않습니다.")
    @Schema(description = "담당자 연락처", defaultValue = "010-2345-6789")
    private String phone;
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Schema(description = "담당자 이메일", defaultValue = "홍길동전@example.com")
    private String email;

    private BusinessDTO business;

    public ManagerDTO(BusinessManager bm) {
        this
                .setId(bm.getId())
                .setName(bm.getName())
                .setPhone(bm.getPhone())
                .setEmail(bm.getEmail())
                .setBusiness(new BusinessDTO(bm.getBusiness()))
        ;
    }

    public BusinessManager dtoToEntity() {
        return BusinessManager.builder()
                .id(this.getId())
                .name(this.name)
                .phone(this.phone)
                .email(this.email)
                .business(business.dtoToEntity())
                .build();
    }
}
