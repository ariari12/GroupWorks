package kr.co.groupworks.board.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.groupworks.board.dto.BoardDTO;
import kr.co.groupworks.board.entity.Board;
import kr.co.groupworks.board.entity.BoardStatus;
import kr.co.groupworks.board.repository.BoardRepository;
import kr.co.groupworks.comment.repository.CommentRepository;
import kr.co.groupworks.common.mapper.BoardMapper;
import kr.co.groupworks.department.entity.Department;
import kr.co.groupworks.department.repository.DepartmentRepository;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final CommentRepository commentRepository;

    // 게시판 리스트 (페이징)
    @Override
    public Page<BoardDTO> getBoardList(Long departmentId, Pageable pageable, String keyword, String keywordType) {
        Page<Board> boardPage = boardRepository.findBoardList(departmentId, pageable, keyword,keywordType);

        int totalElements = (int) boardPage.getTotalElements();
        return boardPage.map(board -> {
            BoardDTO boardDTO = boardMapper.toDTO(board);
            boardDTO.setRankNum((long) (totalElements - boardPage.getContent().indexOf(board) - pageable.getPageNumber() * pageable.getPageSize()));
            return boardDTO;
        });
    }

    // 게시판 리스트
    @Override
    public List<BoardDTO> getBoardList(Long departmentId) {
        List<Board> board = boardRepository.findBoardList(departmentId);
        return IntStream.range(0, board.size())
                .mapToObj(i -> {
                    Board b = board.get(i);
                    BoardDTO boardDTO = boardMapper.toDTO(b);
                    // 오래된 게시글이 1번부터 시작하도록 번호 매기기
                    boardDTO.setRankNum((long) (board.size() - i));
                    return boardDTO;
                })
                .collect(Collectors.toList());
    }

    // 부서게시판 공지 5개 리스트 가져오기.
    @Override
    public List<BoardDTO> getTopNotices(Long departmentId) {
        List<Board> board = boardRepository.findTopBoardNotice(departmentId,5);
        return board.stream()
                .map(boardMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 공지게시판 리스트 (페이징)
    @Override
    public Page<BoardDTO> getNotices(Long departmentId, Pageable pageable, String keyword, String keywordType) {
        Page<Board> boardPage = boardRepository.findBoardNotice(departmentId, pageable, keyword, keywordType);
        int totalElements = (int) boardPage.getTotalElements();
        return boardPage.map(board -> {
            BoardDTO boardDTO = boardMapper.toDTO(board);
            boardDTO.setRankNum((long) (totalElements - boardPage.getContent().indexOf(board) - pageable.getPageNumber() * pageable.getPageSize()));
            return boardDTO;
        });
    }

    // 조회수 증가
    @Override
    @Transactional
    public void increaseHits(Long boardId) {
        boardRepository.updateViewCount(boardId);
    }

    // 게시판 수정
    @Override
    public void editBoard(Long no, BoardDTO dto) {
        Board target = boardRepository.findById(no)
                .orElseThrow(() -> new EntityNotFoundException("Board Not Found"));
        target.updateBoard(dto.getContent(), dto.getSubject(), dto.getTitle());
    }

    // 공지게시판 리스트
    @Override
    public List<BoardDTO> getNotices(Long departmentId) {
        List<Board> board = boardRepository.findBoardNotice(departmentId);
        return IntStream.range(0, board.size())
                .mapToObj(i -> {
                    Board b = board.get(i);
                    BoardDTO boardDTO = boardMapper.toDTO(b);
                    // 오래된 게시글이 1번부터 시작하도록 번호 매기기
                    boardDTO.setRankNum((long) (board.size() - i));
                    return boardDTO;
                })
                .collect(Collectors.toList());
    }

    // 게시판 글쓰기.
    @Override
    public Long register(BoardDTO dto) {
        Department department = dto.getDepartmentId() == null ? null : departmentRepository.findById(dto.getDepartmentId()).get();
        dto.setDepartment(department);
        Employee employee = employeeRepository.findById(dto.getEmployeeId()).get();
        dto.setEmployee(employee);
        dto.setStatus(BoardStatus.ACTIVE);
        Board entity = boardMapper.toEntity(dto);
        Board save = boardRepository.save(entity);
        return save.getBoardId();
    }

    // 게시판 상세보기
    @Override
    public BoardDTO getDetail(Long no, Long departmentId) {
        Department department = departmentId == null ? null : departmentRepository.findById(departmentId).get();
        Board board = boardRepository.findByBoardIdAndDepartment(no, department).orElse(null);
        BoardDTO boardDTO = board != null ? boardMapper.toDTO(board) : new BoardDTO();
        log.info("board : {}", boardDTO);
        return boardDTO;
    }

    // 게시글 삭제
    @Override
    @Transactional
    public String deleteBoard(Long no, Long employeeId) {
        Board board = boardRepository.findById(no).orElse(null);

        if (board == null) {
            return "게시글을 찾을수없습니다.";
        }
        if (!board.getEmployee().getEmployeeId().equals(employeeId)) {
            return "게시글을 삭제할 권한이 없습니다.";
        }

        commentRepository.deleteByBoardBoardId(no);
        boardRepository.delete(board);
        return "Success";
    }
}


