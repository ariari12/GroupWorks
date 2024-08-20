package kr.co.groupworks.calendar.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.groupworks.calendar.dto.*;
import kr.co.groupworks.calendar.entity.*;
import kr.co.groupworks.calendar.repository.CalendarAttachmentRepository;
import kr.co.groupworks.calendar.repository.VacationHistoryRepository;
import kr.co.groupworks.calendar.repository.VacationRepository;
import kr.co.groupworks.common.exception.custom.MissingFileException;
import kr.co.groupworks.common.exception.custom.NotEnoughLeaveDaysException;
import kr.co.groupworks.common.exception.custom.RankNotSufficientException;
import kr.co.groupworks.common.exception.custom.VacationNotPendingException;
import kr.co.groupworks.common.mapper.CalendarAttachmentMapper;
import kr.co.groupworks.common.mapper.VacationMapper;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import kr.co.groupworks.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private final NotificationService notificationService;

    // 매년 1월 1일에 휴가 내역 초기화
    @Scheduled(cron = "0 0 0 1 1 *")
    public void resetVacationHistoryAnnually() {
        List<VacationHistory> all = vacationHistoryRepository.findAll();
        for (VacationHistory history : all) {
            history.resetAnnual();
        }
    }



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

        if(vacationHistory.getTotalAnnual() == 0){
            throw new NotEnoughLeaveDaysException("잔여 연차가 부족합니다");
        }

        // 기간이 겹치는 휴가가 있는지 확인
        List<Vacation> overlappingVacations = vacationRepository.findOverlappingVacations(
                dto.getEmployeeId(), String.valueOf(dto.getStartDate()), String.valueOf(dto.getEndDate()));

        if (!overlappingVacations.isEmpty()) {
            throw new IllegalArgumentException("겹치는 휴가기간이 있습니다.");
        }

        // 휴가 엔티티 변환
        Vacation vacation = vacationMapper.toEntity(dto,employee);
        // 연차 일수 증가
        vacation.updateUsedVacation(dto.getStartDate(), dto.getEndDate());

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

        if(vacationHistory.getTotalAnnual() == 0){
            throw new NotEnoughLeaveDaysException("잔여 연차가 부족합니다");
        }

        // 기간이 겹치는 휴가가 있는지 확인
        List<Vacation> overlappingVacations = vacationRepository.findOverlappingVacations(
                dto.getEmployeeId(), String.valueOf(dto.getHalfStartDate()), String.valueOf(dto.getHalfStartDate()));

        if (!overlappingVacations.isEmpty()) {
            throw new IllegalArgumentException("겹치는 휴가기간이 있습니다.");
        }

        // 휴가 엔티티 변환
        Vacation vacation = vacationMapper.toEntity(dto,employee);
        vacation.updateHalfDaysUsed();
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

        Vacation vacation = vacationMapper.toEntity(dto, employee);
        vacation.updateUsedVacation(dto.getSickStartDate(), dto.getSickEndDate());

        if (files != null && files.length > 0) {
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
        }

        return vacationRepository.save(vacation).getCalendarId();

    }

    // 기타 휴가 신청
    @Override
    public Long save(OtherFormDTO dto, MultipartFile[] files) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + dto.getEmployeeId()));

        // 기간이 겹치는 휴가가 있는지 확인
        List<Vacation> overlappingVacations = vacationRepository.findOverlappingVacations(
                dto.getEmployeeId(), String.valueOf(dto.getOtherStartDate()), String.valueOf(dto.getOtherEndDate()));

        if (!overlappingVacations.isEmpty()) {
            throw new IllegalArgumentException("겹치는 휴가기간이 있습니다.");
        }
        if(files == null){
            throw new MissingFileException("파일 업로드는 필수입니다.");
        }



        Vacation vacation = vacationMapper.toEntity(dto, employee);
        vacation.updateUsedVacation(dto.getOtherStartDate(), dto.getOtherEndDate());

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
    public VacationHistoryDTO findVacationHistory(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));
        return vacationHistoryRepository.findVacationMyHistoryDTO(employee.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("휴가 내역을 찾을 수 없습니다. "));
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
    @Transactional(readOnly = true)
    public VacationModifyFormDTO
    findCalendarByIdAndEmployee(Long calendarId, Long employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));
        Vacation vacation = vacationRepository.findById(calendarId)
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

        // 업데이트 메서드 호출
        vacation.updateVacation(dto.getVacationType(),
                dto.getContents(), dto.getVacationType().getName(),
                date[0], date[1]);

    }

    // 캘린더 페이지 사원의 휴가신청 내역 모두 조회 DTO 다름 (첨부파일 조회 안함)
    @Override
    @Cacheable(value = "vacationRequestCache", key = "#employeeId", cacheManager = "cacheManager")
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public Page<VacationRequestDTO> findAllTeamSearchPending(Long employeeId,
                                                             @PageableDefault(
                                                                     sort = {"createdDate","startDate"},
                                                                     direction = Sort.Direction.DESC
                                                             ) Pageable pageable, String search) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));
        if(employee.getRole().getGrade()<3){
            throw new RankNotSufficientException("직책이 낮아 해당 작업을 수행할 권한이 없습니다.");
        }
        Page<Vacation> vacationList = vacationRepository.findAllTeamSearchName(employee, pageable, search);

        // 페이징 처리하기 엔티티 dto 전환
        return vacationList
                .map(vacation ->
                        VacationRequestDTO.builder()
                                .calendarId(vacation.getCalendarId())
                                .employeeId(vacation.getEmployee().getEmployeeId())
                                .name(vacation.getEmployee().getEmployeeName())
                                .role(vacation.getEmployee().getRole())
                                .rankName(vacation.getEmployee().getRankName())
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
        Employee senderEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));
        Vacation vacation = vacationRepository.findById(calendarId)
                .orElseThrow(() -> new EntityNotFoundException("휴가 일정이 존재하지 않습니다."));

        if (vacation.getStatus().equals(VacationStatus.PENDING)) {
            Approver approver = Approver.builder().approverId(employeeId)
                    .approverName(senderEmployee.getEmployeeName()).build();
            vacation.approvalStatus(status,approver);

            if(status.equals(VacationStatus.APPROVED)) {
                vacationHistoryUpdate(vacation);
            }
            // sse로 전달할 id
            notificationService.sendVacationApproval(vacation, senderEmployee);
        } else{
            throw new IllegalStateException("검토중인 휴가 신청만 승인/반려가 가능합니다.");
        }
        return vacation.getCalendarId();
    }

    // 구성원 휴가 보유 내역 조회
    @Override
    @Transactional(readOnly = true)
    public Page<VacationHistoryDTO> findAllTeamHistory(Long employeeId, Pageable pageable) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));
        if(employee.getRole().getGrade()<3){
            throw new RankNotSufficientException("직책이 낮아 해당 작업을 수행할 권한이 없습니다.");
        }
        return vacationHistoryRepository.findAllTeamDTO(employee, pageable);
    }

    //캡슐화
    private void vacationHistoryUpdate(Vacation vacation) {
        VacationHistory vacationHistory = vacationHistoryRepository.findByEmployee(vacation.getEmployee())
                .orElseThrow(() -> new EntityNotFoundException("휴가 내역을 찾을 수 없습니다. " +
                        vacation.getEmployee().getEmployeeId()));
        if(vacation.getVacationType().equals(VacationType.ANNUAL)){
            // 연차 일수 증가
            vacationHistory.updateAnnual(vacation.getUsedVacation());
        } else if (vacation.getVacationType().equals(VacationType.HALF)) {
            vacationHistory.updateAnnual(vacation.getUsedVacation());
        } else if (vacation.getVacationType().equals(VacationType.SICK)) {
            vacationHistory.updateSick((int) vacation.getUsedVacation());
        } else if (vacation.getVacationType().equals(VacationType.OTHER))
            vacationHistory.updateOther((int) vacation.getUsedVacation());
    }

}
