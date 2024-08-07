package kr.co.groupworks.calendar.controller;


import kr.co.groupworks.calendar.dto.AnnualFormDTO;
import kr.co.groupworks.calendar.dto.HalfFormDTO;
import kr.co.groupworks.calendar.dto.OtherFormDTO;
import kr.co.groupworks.calendar.dto.SickFormDTO;
import kr.co.groupworks.calendar.service.VacationService;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/vacation")
public class VacationApiController {
    @Value("file.upload-dir")
    private String uploadDirectory;
    private final VacationService vacationService;

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam String filePath) throws UnsupportedEncodingException {
        // URL 인코딩된 파일 이름을 디코딩
        String decodedFilePath = URLDecoder.decode(filePath, "UTF-8");
        System.out.println("filePath: " + decodedFilePath);


//        File file = new File(uploadDirectory + File.separator + fileName);
        File file = new File(uploadDirectory + "/" + decodedFilePath);
        // 파일이 존재하지 않는다면
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // 여기까지 오면 파일이 존재한다.
        Resource resource = new FileSystemResource(file);
        // 파일의 인코딩
        String encodingFileName = URLEncoder.encode(filePath, "UTF-8").replaceAll("\\+", "%20");
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; fileName=\"" + encodingFileName + "\"");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + encodingFileName + "\"");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));

        return ResponseEntity.ok().headers(headers).body(resource);
    }


    // 연차 신청
    @PostMapping("/annual")
    public ResponseEntity<?> vacationAnnual(@Validated @RequestBody AnnualFormDTO dto,
                                            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO){
        log.info("AnnualFormDTO ={}",dto);
        log.info("sessionEmployeeDTO ={}",sessionEmployeeDTO.getEmployeeId());
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        vacationService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("연차 신청이 성공적으로 처리되었습니다.");
    }

    // 반차 신청
    @PostMapping("/half")
    public ResponseEntity<?> vacationHalf(@Validated @RequestBody HalfFormDTO dto,
                                          @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO){
        log.info("HalfFormDTO ={}",dto);
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        vacationService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("반차 신청이 성공적으로 처리되었습니다.");
    }

    // 병가 신청
    @PostMapping("/sick")
    @ResponseBody
    public ResponseEntity<?> vacationSick(@Validated @RequestPart("jsonData") SickFormDTO dto,
                                          @RequestPart("sickFileUpload") MultipartFile[] files,
                                          @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO){

        log.info("SickFormDTO ={}, fileUpload ={}",dto, files);
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());

        vacationService.save(dto,files);
        return ResponseEntity.status(HttpStatus.OK).body("병가 신청이 성공적으로 처리되었습니다.");
    }


    // 기타 휴가 신청
    @PostMapping("/other")
    @ResponseBody
    public ResponseEntity<?> vacationOther(@Validated @RequestPart("jsonData") OtherFormDTO dto,
                                          @RequestPart(value = "otherFileUpload") MultipartFile[] files,
                                          @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO){

        log.info("SickFormDTO ={}, fileUpload ={}",dto, files);
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());

        vacationService.save(dto,files);
        return ResponseEntity.status(HttpStatus.OK).body("기타 휴가 신청이 성공적으로 처리되었습니다.");
    }

    



}
