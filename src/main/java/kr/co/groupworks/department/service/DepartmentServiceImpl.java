package kr.co.groupworks.department.service;

import kr.co.groupworks.department.dto.DepartmentDTO;
import kr.co.groupworks.department.entity.Department;
import kr.co.groupworks.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentRepository departmentRepository;

    //    전체 부서 목록 가져오기
    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departmentList = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for (Department department : departmentList) {
            toDTO(department);
            departmentDTOList.add(toDTO(department));
        }
        return departmentDTOList;
    }
//    ========================================================================================================

    //    dto to entity
    public Department toEntity(DepartmentDTO departmentDTO) {
        return Department.builder()
                .departmentId(departmentDTO.getDepartmentId())
                .departmentName(departmentDTO.getDepartmentName())
                .location(departmentDTO.getLocation())
                .contactNumber(departmentDTO.getContactNumber())
                .build();
    }

    //  Entity to Dto
    public DepartmentDTO toDTO(Department department) {
        return DepartmentDTO.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .contactNumber(department.getContactNumber())
                .location(department.getLocation())
                .build();
    }
}
