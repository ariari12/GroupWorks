package kr.co.groupworks.comment.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.groupworks.board.entity.Board;
import kr.co.groupworks.board.repository.BoardRepository;
import kr.co.groupworks.comment.dto.CommentDTO;
import kr.co.groupworks.comment.entity.Comment;
import kr.co.groupworks.comment.repository.CommentRepository;
import kr.co.groupworks.common.mapper.CommentMapper;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final EmployeeRepository employeeRepository;
    private final BoardRepository boardRepository;
    private final CommentMapper commentMapper;

    // 댓글
    @Override
    public List<CommentDTO> getComments(Long id) {

        List<Comment> comments = commentRepository.findCommentList(id);
        if (comments == null || comments.isEmpty()) {
            return Collections.emptyList();
        }

        // 댓글 리스트를 계층적으로 정렬합니다.
        List<CommentDTO> sortedComments = convertToCommentTree(comments);
        return sortedComments;
    }

    // 전체글 갯수 가져오기.
    @Override
    public Long countAllComments(Long no) {
        List<Comment> comments = commentRepository.findByBoard_BoardId(no);
        return (long) comments.size();
    }

    // 댓글작성
    @Override
    public void addComment(CommentDTO commentDTO, Long employeeId) {

        Board board = boardRepository.findById(commentDTO.getBoardId())
                .orElseThrow(() -> new RuntimeException("Board not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Comment entity = CommentMapper.INSTANCE.toEntity(commentDTO);
        entity.setBoard(board);
        entity.setEmployee(employee);

        // null 값이 아니면 대댓글.
        entity.setParentComment(commentDTO.getParentCommentId() != null ? entity.getParentComment():null);
        commentRepository.save(entity);
    }

    // 댓글 수정
    @Override
    @Transactional
    public void updateComment(Long id, CommentDTO dto) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));
        target.updateContent(dto.getContent());
    }

    // 게시글 삭제
    @Override
    @Transactional
    public void deleteComment(Long id) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));
        commentRepository.delete(target);
    }

    // 댓글 리스트를 계층적으로 정렬하여 리스트를 생성합니다.
    private List<CommentDTO> convertToCommentTree(List<Comment> comments) {
        // 댓글 ID와 댓글 객체를 매핑합니다.
        Map<Long, Comment> commentMap = new HashMap<>();
        for (Comment comment : comments) {
            commentMap.put(comment.getCommentId(), comment);
        }

        // 댓글 트리를 생성할 리스트를 준비합니다.
        List<CommentDTO> commentTree = new ArrayList<>();

        // 댓글을 순회하면서 자식 댓글을 트리에 추가합니다.
        for (Comment comment : comments) {
            // 부모 댓글이 없는 경우 루트 댓글로 추가합니다.
            if (comment.getParentComment() == null) {
                commentTree.add(toDTO(comment, commentMap));
            }
        }

        return commentTree;
    }
    // 댓글과 자식 댓글을 재귀적으로 처리하여 DTO로 변환합니다.
    private CommentDTO toDTO(Comment comment, Map<Long, Comment> commentMap) {
        CommentDTO commentDTO = commentMapper.toDTO(comment);

        // 자식 댓글이 있는 경우 자식 댓글을 DTO로 변환합니다.
        List<CommentDTO> childDTOs = new ArrayList<>();
        for (Comment child : comment.getChildComments()) {
            childDTOs.add(toDTO(child, commentMap));
        }

        // 자식 댓글 리스트를 설정합니다.
        commentDTO.setChildComments(childDTOs);
        return commentDTO;
    }
}
