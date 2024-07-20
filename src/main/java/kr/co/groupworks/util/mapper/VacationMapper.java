package kr.co.groupworks.util.mapper;
import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.dto.kah.HalfFormDTO;
import kr.co.groupworks.dto.kah.OtherFormDTO;
import kr.co.groupworks.dto.kah.SickFormDTO;
import kr.co.groupworks.entity.kah.Vacation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//Member 클래스 간의 변환 기능을 제공
// MapStruct 기반의 MemberMapper 인터페이스 정의
//@Mapper 애너테이션의 애트리뷰트로
// (componentModel = "spring") -> Spring Bean으로 등록

@Mapper(componentModel = "spring")
public interface VacationMapper {

    @Mapping(target = "title", constant = "연차")
    @Mapping(target = "contents", source = "contents")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "status", expression = "java(kr.co.groupworks.entity.kah.VacationStatus.PENDING)")
    @Mapping(target = "vacationType", source = "type")
    @Mapping(target = "employee.employeeId", source = "employeeId")
    Vacation toEntity(AnnualFormDTO dto);

    @Mapping(target = "employeeId", source = "employee.employeeId")
    AnnualFormDTO toDto(Vacation vacation);
}
