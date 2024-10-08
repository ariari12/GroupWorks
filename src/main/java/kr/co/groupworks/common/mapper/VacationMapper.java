package kr.co.groupworks.common.mapper;

import kr.co.groupworks.calendar.dto.*;
import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.employee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//Member 클래스 간의 변환 기능을 제공
// MapStruct 기반의 MemberMapper 인터페이스 정의
//@Mapper 애너테이션의 애트리뷰트로
// (componentModel = "spring") -> Spring Bean으로 등록

@Mapper(componentModel = "spring")
public interface VacationMapper {

    @Mapping(target = "status", expression = "java(kr.co.groupworks.calendar.entity.VacationStatus.PENDING)")
    @Mapping(target = "vacationType", expression = "java(kr.co.groupworks.calendar.entity.VacationType.ANNUAL)")
    @Mapping(target = "title", expression = "java(kr.co.groupworks.calendar.entity.VacationType.ANNUAL.getDescription())")
    @Mapping(target = "employee", source = "employee") // Employee 객체를 명시적 매핑
    Vacation toEntity(AnnualFormDTO dto, Employee employee);

    VacationModifyFormDTO toModifyFormDto(Vacation vacation);

    @Mapping(target = "startDate", source = "dto.halfStartDate")
    @Mapping(target = "contents", source = "dto.halfContents")
    @Mapping(target = "status", expression = "java(kr.co.groupworks.calendar.entity.VacationStatus.PENDING)")
    @Mapping(target = "vacationType", expression = "java(kr.co.groupworks.calendar.entity.VacationType.HALF)")
    @Mapping(target = "title", expression = "java(kr.co.groupworks.calendar.entity.VacationType.HALF.getDescription())")
    @Mapping(target = "employee", source = "employee") // Employee 객체를 명시적 매핑
    Vacation toEntity(HalfFormDTO dto, Employee employee);

    @Mapping(target = "startDate", source = "dto.sickStartDate")
    @Mapping(target = "endDate", source = "dto.sickEndDate")
    @Mapping(target = "contents", source = "dto.sickContents")
    @Mapping(target = "status", expression = "java(kr.co.groupworks.calendar.entity.VacationStatus.PENDING)")
    @Mapping(target = "vacationType", expression = "java(kr.co.groupworks.calendar.entity.VacationType.SICK)")
    @Mapping(target = "title", expression = "java(kr.co.groupworks.calendar.entity.VacationType.SICK.getDescription())")
    @Mapping(target = "employee", source = "employee") // Employee 객체를 명시적 매핑
    Vacation toEntity(SickFormDTO dto, Employee employee);

    @Mapping(target = "startDate", source = "dto.otherStartDate")
    @Mapping(target = "endDate", source = "dto.otherEndDate")
    @Mapping(target = "contents", source = "dto.otherContents")
    @Mapping(target = "status", expression = "java(kr.co.groupworks.calendar.entity.VacationStatus.PENDING)")
    @Mapping(target = "vacationType", expression = "java(kr.co.groupworks.calendar.entity.VacationType.OTHER)")
    @Mapping(target = "title", expression = "java(kr.co.groupworks.calendar.entity.VacationType.OTHER.getDescription())")
    @Mapping(target = "employee", source = "employee") // Employee 객체를 명시적 매핑
    Vacation toEntity(OtherFormDTO dto, Employee employee);




}
