package kr.co.groupworks.util.mapper;

import kr.co.groupworks.dto.kah.OtherFormDTO;
import kr.co.groupworks.dto.kah.SickFormDTO;
import kr.co.groupworks.dto.kah.VacationFileDTO;
import kr.co.groupworks.entity.kah.CalendarAttachment;
import kr.co.groupworks.entity.kah.Vacation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public interface CalendarAttachmentMapper {


    @Mapping(target = "calendar", source = "vacation")
    CalendarAttachment toEntity(String fileFullName, String fileName, Vacation vacation);

    VacationFileDTO toDto(CalendarAttachment calendarAttachment);

//    @Mapping(target = "filePath", source = "dto.otherFilePath")
//    @Mapping(target = "fileName", source = "dto.otherFileName")
//    @Mapping(target = "calendar", source = "vacation")
//    CalendarAttachment toEntity(OtherFormDTO dto, Vacation vacation);
}
