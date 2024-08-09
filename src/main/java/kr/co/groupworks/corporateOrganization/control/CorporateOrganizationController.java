package kr.co.groupworks.corporateOrganization.control;


import io.swagger.v3.oas.annotations.Hidden;
import kr.co.groupworks.department.dto.DepartmentDTO;
import kr.co.groupworks.department.service.DepartmentService;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import kr.co.groupworks.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Hidden
@Controller
@RequestMapping("/corporateOrganization")
@RequiredArgsConstructor
@Slf4j
public class CorporateOrganizationController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

//    조직도 컨트롤러
    @GetMapping("/chart")
    public String chart(Model model, @RequestParam(value = "id", defaultValue = "1") Long departmentId) {
//        부서목록, 부서에 해당하는 사원목록, 직급목록 가져오기
        List<DepartmentDTO> departmentDTOList = departmentService.getAllDepartments();
        List<EmployeeDTO> employeeDTOListByDepartmentId = employeeService.findByDepartmentId(departmentId);
        List<Integer> rankList = employeeService.findRankIdByDepartmentId(departmentId);

        log.info("departmentId: {}", departmentId);
        log.info("employeeDTOListByDepartmentId: {}", employeeDTOListByDepartmentId);
        log.info("rankList: {}", rankList);

        model.addAttribute("departmentDTOList", departmentDTOList);
        model.addAttribute("employeeDTOListByDepartmentId", employeeDTOListByDepartmentId);
        model.addAttribute("rankList", rankList);
        return "corporateOrganization/corporateOrganizationChart";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
//        부서목록 가져오기
        departmentService.getAllDepartments();
        model.addAttribute("departmentDTOList", departmentService.getAllDepartments());

        return "corporateOrganization/corporateOrganizationContact";
    }
}
