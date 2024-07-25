package kr.co.groupworks.service.kah;

import jakarta.persistence.EntityNotFoundException;
import kr.co.groupworks.dto.kah.*;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.CalendarAttachment;
import kr.co.groupworks.entity.kah.Vacation;
import kr.co.groupworks.entity.kah.VacationHistory;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.repository.kah.CalendarAttachmentRepository;
import kr.co.groupworks.repository.kah.VacationHistoryRepository;
import kr.co.groupworks.repository.kah.VacationRepository;
import kr.co.groupworks.util.mapper.CalendarAttachmentMapper;
import kr.co.groupworks.util.mapper.VacationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService{
    @Value("${file.upload-dir}")
    private String uploadDirectory;
    private final CalendarAttachmentRepository calendarAttachmentRepository;
    private final CalendarAttachmentMapper calendarAttachmentMapper;
    private final VacationRepository vacationRepository;
    private final EmployeeRepository employeeRepository;
    private final VacationHistoryRepository vacationHistoryRepository;
    private final VacationMapper vacationMapper;




    // 연차 저장
    @Override
    public Long save(AnnualFormDTO dto) {
        // 사원 엔티티 반환
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + dto.getEmployeeId()));
        VacationHistory vacationHistory =
                vacationHistoryRepository
                        .findByEmployee(employee)
                        .orElseThrow(() -> new EntityNotFoundException("휴가내역을 찾을 수 없습니다 "));
        // 연차 일수 증가
        vacationHistory.updateAnnualDaysUsed(dto.getStartDate(), dto.getEndDate());
        vacationHistory = vacationHistoryRepository.save(vacationHistory);
        //employee.updateAnnualDaysUsed(dto.getStartDate(), dto.getEndDate());
        log.info("employee = {}",employee);
        log.info("vacationHistory = {}",vacationHistory);

        // 기간이 겹치는 휴가가 있는지 확인
        List<Vacation> overlappingVacations = vacationRepository.findOverlappingVacations(
                dto.getEmployeeId(), String.valueOf(dto.getStartDate()), String.valueOf(dto.getEndDate()));

        if (!overlappingVacations.isEmpty()) {
            throw new IllegalArgumentException("겹치는 휴가기간이 있습니다.");
        }

        // 사원 업데이트
        employee = employeeRepository.save(employee);
        // 휴가 엔티티 변환
        Vacation vacation = vacationMapper.toEntity(dto,employee);
        return vacationRepository.save(vacation).getCalendarId();
    }

    // 반차 저장
    @Override
    public Long save(HalfFormDTO dto) {
        // 사원 엔티티 반환
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + dto.getEmployeeId()));
        // 반차 일수 증가
        VacationHistory vacationHistory =
                vacationHistoryRepository
                        .findByEmployee(employee)
                        .orElseThrow(() -> new EntityNotFoundException("휴가내역을 찾을 수 없습니다 "));

        vacationHistory.updateHalfDaysUsed();
        vacationHistory = vacationHistoryRepository.save(vacationHistory);
        log.info("employee = {}",employee);
        log.info("vacationHistory = {}",vacationHistory);


        // 기간이 겹치는 휴가가 있는지 확인
        List<Vacation> overlappingVacations = vacationRepository.findOverlappingVacations(
                dto.getEmployeeId(), String.valueOf(dto.getHalfStartDate()), String.valueOf(dto.getHalfStartDate()));

        if (!overlappingVacations.isEmpty()) {
            throw new IllegalArgumentException("겹치는 휴가기간이 있습니다.");
        }

        //사원 업데이트
        employee=employeeRepository.save(employee);
        // 휴가 엔티티 변환
        Vacation vacation = vacationMapper.toEntity(dto,employee);
        return vacationRepository.save(vacation).getCalendarId();
    }

    // 병가 저장
    @Override
    public Long save(SickFormDTO dto, MultipartFile[] files) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + dto.getEmployeeId()));
        // 병가 일수 증가
        VacationHistory vacationHistory =
                vacationHistoryRepository
                        .findByEmployee(employee)
                        .orElseThrow(() -> new EntityNotFoundException("휴가내역을 찾을 수 없습니다 "));

        vacationHistory.updateSickDaysUsed(dto.getSickStartDate(), dto.getSickEndDate());
        vacationHistory = vacationHistoryRepository.save(vacationHistory);
        log.info("employee = {}",employee);
        log.info("vacationHistory = {}",vacationHistory);

        // 기간이 겹치는 휴가가 있는지 확인
        List<Vacation> overlappingVacations = vacationRepository.findOverlappingVacations(
                dto.getEmployeeId(), String.valueOf(dto.getSickStartDate()), String.valueOf(dto.getSickEndDate()));

        if (!overlappingVacations.isEmpty()) {
            throw new IllegalArgumentException("겹치는 휴가기간이 있습니다.");
        }

        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        //사원 업데이트
        employee=employeeRepository.save(employee);
        Vacation vacation = vacationMapper.toEntity(dto, employee);

        Arrays.asList(files).forEach(file -> {
            String fileFullName = UUID.randomUUID().toString()+"-"+file.getOriginalFilename();
            String filePath = uploadDir+ "/"+ fileFullName;
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
                CalendarAttachment calendarAttachment =
                        calendarAttachmentMapper.toEntity(fileFullName, file.getOriginalFilename(), vacation);
                calendarAttachmentRepository.save(calendarAttachment);

            } catch (IOException e) {
                log.error("파일 저장 중 오류 발생: {}", e.getMessage());
                throw new RuntimeException("파일 저장 중 오류가 발생했습니다. 다시 시도해주세요.");
            }
        });


        return vacationRepository.save(vacation).getCalendarId();

    }

    // 기타 휴가 신청
    @Override
    public Long save(OtherFormDTO dto, MultipartFile[] files) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + dto.getEmployeeId()));


        // 기타 휴가 일수 증가
        VacationHistory vacationHistory =
                vacationHistoryRepository
                        .findByEmployee(employee)
                        .orElseThrow(() -> new EntityNotFoundException("휴가내역을 찾을 수 없습니다 "));

        vacationHistory.updateSickDaysUsed(dto.getOtherStartDate(), dto.getOtherEndDate());
        vacationHistory = vacationHistoryRepository.save(vacationHistory);
        log.info("employee = {}",employee);
        log.info("vacationHistory = {}",vacationHistory);

        // 기간이 겹치는 휴가가 있는지 확인
        List<Vacation> overlappingVacations = vacationRepository.findOverlappingVacations(
                dto.getEmployeeId(), String.valueOf(dto.getOtherStartDate()), String.valueOf(dto.getOtherEndDate()));

        if (!overlappingVacations.isEmpty()) {
            throw new IllegalArgumentException("겹치는 휴가기간이 있습니다.");
        }



        // 사원 업데이트
        employee=employeeRepository.save(employee);
        Vacation vacation = vacationMapper.toEntity(dto, employee);

        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        Arrays.asList(files).forEach(file -> {
            String fileFullName = UUID.randomUUID().toString()+"-"+file.getOriginalFilename();
            String filePath = uploadDir+ "/"+ fileFullName;
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
                CalendarAttachment calendarAttachment =
                        calendarAttachmentMapper.toEntity(fileFullName, file.getOriginalFilename(), vacation);
                calendarAttachmentRepository.save(calendarAttachment);

            } catch (IOException e) {
                log.error("파일 저장 중 오류 발생: {}", e.getMessage());
                throw new RuntimeException("파일 저장 중 오류가 발생했습니다. 다시 시도해주세요.");
            }
        });

        return vacationRepository.save(vacation).getCalendarId();
    }

    @Override
    public List<VacationMyHistoryDTO> findVacationHistory(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));
        return vacationHistoryRepository.findVacationMyHistoryDTO(employee.getEmployeeId());
    }


    // 연차 수정
    @Override
    public Long save(AnnualModifyFormDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + dto.getEmployeeId()));
        Vacation vacation = vacationMapper.toEntity(dto);

        return vacationRepository.save(vacation).getCalendarId();
    }


    // 사원의 휴가신청 내역 모두 조회
    @Override
    @Transactional(readOnly = true)
    public List<VacationMyRequestDTO> findAllByEmployeeId(Long employeeId) {
        List<Vacation> vacationList = vacationRepository.findAllByEmployeeId(employeeId);
        // 휴가 번호로 첨부파일 조회
        return vacationList.stream()
                .map(vacation ->
                        VacationMyRequestDTO.builder()
                                .startDate(vacation.getStartDate())
                                .endDate(vacation.getEndDate() != null ? vacation.getEndDate() : vacation.getStartDate())
                                .vacationType(vacation.getVacationType())
                                .fileList(vacation.getAttachmentList())
                                .contents(vacation.getContents())
                                .status(vacation.getStatus()).build())
                .toList();
    }


}
