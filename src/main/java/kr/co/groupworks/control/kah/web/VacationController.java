package kr.co.groupworks.control.kah.web;

import kr.co.groupworks.dto.cis.employee.SessionEmployeeDTO;
import kr.co.groupworks.dto.kah.*;
import kr.co.groupworks.entity.kah.AmPm;
import kr.co.groupworks.service.kah.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
    public String vacationMain(@SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO,
                               Model model) {
        log.info("VacationController - vacationMain");

        Long employeeId = sessionEmployeeDTO.getEmployeeId();
        log.info("세션 값 : "+employeeId);

        // 휴가 신청 내역 조회
        List<VacationMyRequestDTO> vacationRequestList = vacationService.findAllByEmployeeId(employeeId);
        log.info("휴가 신청 내역 조회 : {}",vacationRequestList);

        // 휴가 보유 사용현황 조회
        List<VacationMyHistoryDTO> vacationHistoryList = vacationService.findVacationHistory(employeeId);


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
        return "kah/vacationMain";
    }

    @GetMapping(value = "/team")
    public String vacationTeam(Model model) {
        log.info("VacationController - vacationTeam");

        // header title 넘겨주기
        model.addAttribute("title", "구성원 휴가");
        return "kah/vacationTeam";
    }


}
