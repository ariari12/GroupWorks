package kr.co.groupworks.board.service;

import kr.co.groupworks.board.dto.BoardDTO;
import kr.co.groupworks.board.entity.Board;
import kr.co.groupworks.board.entity.BoardStatus;
import kr.co.groupworks.board.repository.BoardRepository;
import kr.co.groupworks.common.mapper.BoardMapper;
import kr.co.groupworks.entity.cis.Department;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.repository.cis.DepartmentRepository;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getAllBoards(Long departmentId) {

        // DESC
        Sort sort = Sort.by(Sort.Order.desc("createDate"));
        List<Board> board = boardRepository.findByDepartmentIdOrNoDepartmentIdAndStatusActive(departmentId, sort);
        log.info("board {}", board);

        return IntStream.range(0, board.size())
                .mapToObj(i -> {
                    Board b = board.get(i);
                    BoardDTO boardDTO = boardMapper.toDTO(b);
                    // 오래된 게시글이 1번부터 시작하도록 번호 매기기
                    boardDTO.setRankNum(b.getBoardId());
                    boardDTO.setBoardId((long) (board.size() - i)); // 번호 매기기 (1부터 시작)
                    return boardDTO;
                })
                .collect(Collectors.toList());
    }

    // 부서게시판 글쓰기.
    @Override
    public Long register(BoardDTO dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId()).get();
        Department department = departmentRepository.findById(dto.getDepartmentId()).get();

        dto.setEmployee(employee);
        dto.setDepartment(department);
        dto.setStatus(BoardStatus.ACTIVE);

        Board entity = boardMapper.toEntity(dto);
        Board save = boardRepository.save(entity);
        return save.getBoardId();
    }

    @Override
    public BoardDTO getDetail(Long no, Long departmentId) {
        Department department = departmentRepository.findById(departmentId).get();
        Board board = boardRepository.findByBoardIdAndDepartment(no, department).get();
        BoardDTO boardDTO = boardMapper.toDTO(board);
        log.info("board : {}", boardDTO);
        return boardDTO;
    }
}


