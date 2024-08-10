package kr.co.groupworks.calendar.controller;

import kr.co.groupworks.calendar.dto.*;
import kr.co.groupworks.calendar.entity.VacationStatus;
import kr.co.groupworks.calendar.entity.VacationType;
import kr.co.groupworks.calendar.entity.AmPm;
import kr.co.groupworks.calendar.service.VacationService;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/vacation")
@RequiredArgsConstructor
public class VacationController {
    private final VacationService vacationService;

    // enum 상수 값 지속적으로 넘겨주기 위함
    @ModelAttribute("amPms")
    public AmPm[] amPms(){
        return AmPm.values();
    }

    public static List<VacationType> vacationStatusList(){
        ArrayList<VacationType> list = new ArrayList<>();
        list.add(VacationType.ANNUAL);
        list.add(VacationType.HALF);
        list.add(VacationType.OTHER);
        list.add(VacationType.SICK);
        return list;
    }

    @GetMapping("")
    public String vacationMain(@SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO,
                               Model model) {
        log.info("VacationController - vacationMain");

        Long employeeId = sessionEmployeeDTO.getEmployeeId();
        log.info("세션 값 : "+employeeId);

        // 휴가 신청 내역 조회
        List<VacationRequestDTO> vacationRequestList = vacationService.findAllByEmployeeId(employeeId);
        log.info("휴가 신청 내역 조회 : {}",vacationRequestList);

        // 휴가 보유 사용현황 조회
        List<VacationMyHistoryDTO> vacationHistoryList = vacationService.findVacationHistory(employeeId);
        log.info("휴가 보유 사용현황 조회 : {}",vacationHistoryList);


        // 검증 validation th:filed 속성 추가하기 위한 dto
        model.addAttribute("annualForm",new AnnualFormDTO());
        model.addAttribute("halfForm",new HalfFormDTO());
        model.addAttribute("otherForm",new OtherFormDTO());
        model.addAttribute("sickForm",new SickFormDTO());
        model.addAttribute("vacationRequestList",vacationRequestList);
        model.addAttribute("vacationHistoryList",vacationHistoryList);
        // header title 넘겨주기
        model.addAttribute("title", "내 휴가");
        model.addAttribute("subtitle", "휴가 신청내역");
        return "calendar/vacationMain";
    }
    // 휴가 신청 수정 폼
    @GetMapping("/modify/{calendarId}")
    public String modifyForm(@PathVariable("calendarId") Long calendarId, Model model,
                             @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO){
        log.info("VacationController - modifyForm");
        VacationModifyFormDTO modifyFormDTO =vacationService.findCalendarByIdAndEmployee(calendarId, sessionEmployeeDTO.getEmployeeId());
        log.info("{}",modifyFormDTO);
        model.addAttribute("title", "휴가 신청 수정");
        model.addAttribute("modifyForm",modifyFormDTO);
        model.addAttribute("vacationStatusList",vacationStatusList());


        return "calendar/vacationModifyForm";
    }

    // 휴가 수정
    @PostMapping("/modify/{calendarId}")
    public String modifyVacation(@PathVariable("calendarId") Long calendarId,
                                 @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO,
                                 @ModelAttribute VacationModifyFormDTO dto,
                                 @RequestParam("fileUpload") MultipartFile[] files,
                                 Model model){
        log.info("VacationController - modifyVacation");
        vacationService.modifyVacation(calendarId,dto,sessionEmployeeDTO.getEmployeeId(), files);
        model.addAttribute("title", "휴가 신청 수정");
        model.addAttribute("vacationStatusList",vacationStatusList());

        return "redirect:/vacation";
    }


    // 휴가 신청 취소
    @GetMapping("/delete/{calendarId}")
    public String vacationDelete(@PathVariable Long calendarId,
                                 @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO) {
        log.info("VacationController - vacationDelete");
        vacationService.deleteRequest(calendarId, sessionEmployeeDTO.getEmployeeId());
        return "redirect:/vacation";
    }


    // 구성원 휴가 신청 내역
    @GetMapping(value = "/team") //page=0 부터 시작 size 를 정하면 된다
    public String vacationTeam(Model model, @PageableDefault(size = 10) Pageable pageable,
                               @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO) {
        log.info("VacationController - vacationTeam");

        Long employeeId = sessionEmployeeDTO.getEmployeeId();
        log.info("세션 값 : "+employeeId);

        // 휴가 신청 내역 조회
        Page<VacationRequestDTO> vacationRequestList = vacationService.findAllTeamSearchPending(employeeId, pageable);
        log.info("휴가 신청 내역 조회 : {}",vacationRequestList);

        // header title 넘겨주기
        model.addAttribute("title", "구성원 휴가");
        model.addAttribute("vacationRequestList",vacationRequestList);
        return "calendar/vacationTeam";
    }

    //휴가 신청 세부내역
    @GetMapping(value = "/team/detail")
    public String vacationTeam(Model model, @RequestParam(value = "id") Long id,
                               @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO) {
        log.info("VacationController - vacationTeamDetail");
        Long employeeId = sessionEmployeeDTO.getEmployeeId();

        // 휴가 신청 내역 조회
        VacationModifyFormDTO modifyFormDTO = vacationService.findCalendarByIdAndEmployee(id, employeeId);


        // header title 넘겨주기
        model.addAttribute("modifyForm",modifyFormDTO);
        model.addAttribute("vacationStatusList",vacationStatusList());
        return "calendar/vacationDetail";
    }
}
