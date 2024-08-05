package kr.co.groupworks.calendar.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.groupworks.calendar.dto.*;
import kr.co.groupworks.calendar.entity.CalendarAttachment;
import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.calendar.entity.VacationHistory;
import kr.co.groupworks.calendar.entity.VacationStatus;
import kr.co.groupworks.calendar.repository.CalendarAttachmentRepository;
import kr.co.groupworks.calendar.repository.VacationHistoryRepository;
import kr.co.groupworks.calendar.repository.VacationRepository;
import kr.co.groupworks.common.exception.exhandler.VacationNotPendingException;
import kr.co.groupworks.common.mapper.CalendarAttachmentMapper;
import kr.co.groupworks.common.mapper.VacationMapper;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        //employee = employeeRepository.save(employee);
        // 휴가 엔티티 변환
        Vacation vacation = vacationMapper.toEntity(dto,employee);
        System.out.println("vacation.getEmployee() = " + vacation.getEmployee());
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
        //employee=employeeRepository.save(employee);
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
        //employee=employeeRepository.save(employee);
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

        vacationHistory.updateOtherDaysUsed(dto.getOtherStartDate(), dto.getOtherEndDate());
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
        //employee=employeeRepository.save(employee);
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

    // 휴가신청 삭제
    @Override
    public void deleteRequest(Long calendarId, Long employeeId){
        Vacation vacation = vacationRepository.findById(calendarId)
                .orElseThrow(() -> new EntityNotFoundException("일정을 찾을 수 없습니다. " + calendarId));
        // 휴가신청 상태가 검토중일 경우
        if (vacation.getStatus() == VacationStatus.PENDING){
            vacationRepository.deleteByCalendarId(vacation.getCalendarId());
        }else{
            throw new IllegalStateException("휴가 상태가 검토 단계일 경우만 삭제 가능합니다.");
        }


    }

    // 휴가 일정 검색
    @Override
    public VacationModifyFormDTO findCalendarByIdAndEmployee(Long calendarId, Long employeeId) {
        log.info("service {}",employeeId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));
        Vacation vacation = vacationRepository.findByCalendarIdAndEmployee(calendarId, employee)
                .orElseThrow(() -> new EntityNotFoundException("휴가 일정이 존재하지 않습니다."));
        VacationModifyFormDTO modifyFormDto = vacationMapper.toModifyFormDto(vacation);
        if(!vacation.getAttachmentList().isEmpty()){
            modifyFormDto.setFileList(vacation.getAttachmentList()
                    .stream()
                    .map(calendarAttachmentMapper::toDto).toList());
        }
        return modifyFormDto;
    }

    // 휴가 수정
    @Override
    public void modifyVacation(Long calendarId, VacationModifyFormDTO dto, Long employeeId, MultipartFile[] files) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));
        Vacation vacation = vacationRepository.findByCalendarIdAndEmployee(calendarId, employee)
                .orElseThrow(() -> new EntityNotFoundException("휴가 일정이 존재하지 않습니다."));

        if(vacation.getStatus() != VacationStatus.PENDING){
            throw new VacationNotPendingException("휴가 상태가 검토중이 아닙니다.");
        }


        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        // 기존 첨부 파일 삭제
        calendarAttachmentRepository.deleteByCalendar(vacation);

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


        String[] date = Arrays.stream( dto.getDate().split("~"))
                .map(String::trim)
                .toArray(String[]::new);
        // 배열의 길이가 1인 경우, 배열의 0번째 값을 1번째 값으로 복사하여 새로운 배열 생성
        if (date.length == 1) {
            date = new String[] { date[0], date[0] };
        }
        log.info("date {}", Arrays.toString(date));

        // 업데이트 메서드 호출
        vacation.updateVacation(dto.getVacationType(),
                dto.getContents(), dto.getVacationType().getName(),
                date[0], date[1]);

    }

    // 사원의 휴가신청 내역 모두 조회 DTO 다름
    @Override
    public List<CalendarFormDTO> findAllVacation(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));
        return vacationRepository.findCalendarFormByEmployee(employee);
    }


    // 사원의 휴가신청 내역 모두 조회
    @Override
    @Transactional(readOnly = true)
    public List<VacationRequestDTO> findAllByEmployeeId(Long employeeId) {
        List<Vacation> vacationList = vacationRepository.findAllByEmployeeId(employeeId);
        // 휴가 번호로 첨부파일 조회
        return vacationList.stream()
                .map(vacation ->
                        VacationRequestDTO.builder()
                                .calendarId(vacation.getCalendarId())
                                .startDate(vacation.getStartDate())
                                .endDate(vacation.getEndDate() != null ? vacation.getEndDate() : vacation.getStartDate())
                                .vacationType(vacation.getVacationType())
                                .fileList(vacation.getAttachmentList()
                                        .stream().map(calendarAttachmentMapper::toDto).toList())
                                .contents(vacation.getContents())
                                .status(vacation.getStatus()).build())
                .toList();
    }

    // 구성원 휴가 신청 내역 모두 조회
    @Override
    public Page<VacationRequestDTO> findAllTeamSearchPending(Long employeeId, Pageable pageable) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));
        Page<Vacation> vacationList = vacationRepository.findAllTeam(employee, pageable);
        vacationList.forEach(vacation ->
                log.info("service = {}",vacation)
        );

        // 페이징 처리하기 엔티티 dto 전환
        return vacationList
                .map(vacation ->
                        VacationRequestDTO.builder()
                                .calendarId(vacation.getCalendarId())
                                .employeeId(vacation.getEmployee().getEmployeeId())
                                .name(vacation.getEmployee().getEmployeeName())
                                .startDate(vacation.getStartDate())
                                .endDate(vacation.getEndDate() != null ? vacation.getEndDate() : vacation.getStartDate())
                                .vacationType(vacation.getVacationType())
                                .fileList(vacation.getAttachmentList()
                                        .stream()
                                        .map(calendarAttachmentMapper::toDto)
                                        .toList())
                                .contents(vacation.getContents())
                                .status(vacation.getStatus())
                                .build()
                );
    }

    @Override
    public Long approvalVacation(Long calendarId, VacationStatus status, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));
        Vacation vacation = vacationRepository.findByCalendarIdAndEmployee(calendarId, employee)
                .orElseThrow(() -> new EntityNotFoundException("휴가 일정이 존재하지 않습니다."));
        if (vacation.getStatus().equals(VacationStatus.PENDING)) {
            vacation.approvalStatus(status);
        } else{
            throw new IllegalStateException("검토중인 휴가 신청만 승인/반려가 가능합니다.");
        }
        return vacation.getCalendarId();
    }


}
