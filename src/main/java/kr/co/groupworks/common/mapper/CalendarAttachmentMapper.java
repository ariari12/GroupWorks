package kr.co.groupworks.common.mapper;

import kr.co.groupworks.calendar.dto.VacationFileDTO;
import kr.co.groupworks.calendar.entity.CalendarAttachment;
import kr.co.groupworks.calendar.entity.Vacation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CalendarAttachmentMapper {


    @Mapping(target = "calendar", source = "vacation")
    CalendarAttachment toEntity(String filePath, String fileName, Vacation vacation);

    VacationFileDTO toDto(CalendarAttachment calendarAttachment);

//    @Mapping(target = "filePath", source = "dto.otherFilePath")
//    @Mapping(target = "fileName", source = "dto.otherFileName")
//    @Mapping(target = "calendar", source = "vacation")
//    CalendarAttachment toEntity(OtherFormDTO dto, Vacation vacation);
}
