package kr.co.groupworks.util.mapper;
import kr.co.groupworks.dto.kah.*;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.Vacation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//Member 클래스 간의 변환 기능을 제공
// MapStruct 기반의 MemberMapper 인터페이스 정의
//@Mapper 애너테이션의 애트리뷰트로
// (componentModel = "spring") -> Spring Bean으로 등록

@Mapper(componentModel = "spring")
public interface VacationMapper {

    @Mapping(target = "status", expression = "java(kr.co.groupworks.entity.kah.VacationStatus.PENDING)")
    @Mapping(target = "vacationType", expression = "java(kr.co.groupworks.entity.kah.VacationType.ANNUAL)")
    @Mapping(target = "title", expression = "java(kr.co.groupworks.entity.kah.VacationType.ANNUAL.getDescription())")
    Vacation toEntity(AnnualFormDTO dto, Employee employee);

    @Mapping(target = "employeeId", source = "employee.employeeId")
    AnnualFormDTO toDto(Vacation vacation);

    @Mapping(target = "startDate", source = "dto.halfStartDate")
    @Mapping(target = "contents", source = "dto.halfContents")
    @Mapping(target = "status", expression = "java(kr.co.groupworks.entity.kah.VacationStatus.PENDING)")
    @Mapping(target = "vacationType", expression = "java(kr.co.groupworks.entity.kah.VacationType.HALF)")
    @Mapping(target = "title", expression = "java(kr.co.groupworks.entity.kah.VacationType.HALF.getDescription())")
    Vacation toEntity(HalfFormDTO dto, Employee employee);

    @Mapping(target = "startDate", source = "dto.sickStartDate")
    @Mapping(target = "endDate", source = "dto.sickEndDate")
    @Mapping(target = "contents", source = "dto.sickContents")
    @Mapping(target = "status", expression = "java(kr.co.groupworks.entity.kah.VacationStatus.PENDING)")
    @Mapping(target = "vacationType", expression = "java(kr.co.groupworks.entity.kah.VacationType.SICK)")
    @Mapping(target = "title", expression = "java(kr.co.groupworks.entity.kah.VacationType.SICK.getDescription())")
    Vacation toEntity(SickFormDTO dto, Employee employee);

    @Mapping(target = "startDate", source = "dto.otherStartDate")
    @Mapping(target = "endDate", source = "dto.otherEndDate")
    @Mapping(target = "contents", source = "dto.otherContents")
    @Mapping(target = "status", expression = "java(kr.co.groupworks.entity.kah.VacationStatus.PENDING)")
    @Mapping(target = "vacationType", expression = "java(kr.co.groupworks.entity.kah.VacationType.OTHER)")
    @Mapping(target = "title", expression = "java(kr.co.groupworks.entity.kah.VacationType.OTHER.getDescription())")
    Vacation toEntity(OtherFormDTO dto, Employee employee);


    Vacation toEntity(AnnualModifyFormDTO dto, Long employeeId);
}
