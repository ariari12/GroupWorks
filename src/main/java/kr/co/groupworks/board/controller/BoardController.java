package kr.co.groupworks.board.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    // 리다이렉트 URL
    private static final String REDIRECT_URL = "redirect:/main?alert=invalid_department";

    // 게시판 리스트
    @GetMapping("{departmentId}/list")
    public String board(@PathVariable("departmentId") String boardType,
                        @RequestParam(required = false) String type,
                        @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO,
                        @RequestParam(value = "page", defaultValue = "1")int page,
                        @RequestParam(value = "keyword", defaultValue = "")String keyword,
                        @RequestParam(value = "keywordtype", defaultValue = "")String keywordType,
                        Model model) {

        // 페이지당 글 수
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        Page<BoardDTO> allPosts;
        List<BoardDTO> noticePosts = Collections.emptyList();

        // 게시판 초기화 및 접근권한 검사
        Long departmentId = BoardUtils.initializeBoard(boardType, sessionEmployeeDTO, "리스트", model);
        if (departmentId == null && !boardType.equals("notice"))
            return REDIRECT_URL;

        // 게시글
        if (type == null && !boardType.equals("notice")) {
            // 부서 게시판
            noticePosts = boardService.getTopNotices(departmentId);
            allPosts = boardService.getBoardList(departmentId, pageable, keyword, keywordType);
            // 공지사항 게시글 날짜 설정.
            BoardUtils.setDate(noticePosts);
        } else {
            // 공지 게시판
            allPosts = boardService.getNotices(departmentId, pageable, keyword, keywordType);
        }
        // 모든 게시글 날짜 설정.
        BoardUtils.setDate(allPosts);

        // 페이지네이션
        int totalPages = allPosts.getTotalPages();
        int startPage = Math.max(1, (page - 1) / 10 * 10 + 1);
        int endPage = Math.min(totalPages, startPage + 9);

        model.addAttribute("allPosts", allPosts);
        model.addAttribute("noticePosts", noticePosts);
        model.addAttribute("type", type != null ? type : "");
        model.addAttribute("currentPage", page);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("keywordType", keywordType);
        return "board/list";
    }

    // 게시판 글쓰기 (GET)
    @GetMapping("{departmentId}/write")
    public String write(@PathVariable("departmentId") String boardType, Model model,
                        @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {

        // 게시판 초기화 및 접근권한 검사
        Long departmentId = BoardUtils.initializeBoard(boardType, sessionEmployeeDTO, "글쓰기", model);
        if (departmentId == null && !boardType.equals("notice"))
            return REDIRECT_URL;

        return "board/write";
    }

    // 게시판 글쓰기 (POST)
    @PostMapping("{departmentId}/write")
    public String write(@PathVariable("departmentId") String boardType,
                            @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO,
                            @ModelAttribute("boardDTO") BoardDTO dto,
                            HttpServletRequest request) {

        log.info("전달 받은 DTO : {}", dto);
        Long departmentId = boardType.equals("notice") ? null : Long.parseLong(boardType);
        dto.setDepartmentId(departmentId);
        dto.setIpAddress(request.getRemoteAddr());
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        log.info("설정한 DTO : {}", dto);
        boardService.register(dto);
        return "redirect:/board/" + boardType + "/list";
    }

    // 게시판 상세보기
    @GetMapping("{departmentId}/detail/{no}")
    public String detail(@PathVariable("departmentId") String boardType,
                         @PathVariable("no") Long no, Model model,
                         @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {

        // 게시판 초기화 및 접근권한 검사
        Long departmentId = BoardUtils.initializeBoard(boardType, sessionEmployeeDTO, "", model);
        if (departmentId == null && !boardType.equals("notice"))
            return REDIRECT_URL;

        // 게시판 상세데이터.
        BoardDTO boardDTO = boardService.getDetail(no, departmentId);

        // 댓글
        List<CommentDTO> commentDTO = commentService.getComments(no);
        Long commentCount = commentService.countAllComments(no);

        // 상세데이터가 없으면 리스트로 돌려보냄.
        if (boardDTO.getBoardId() == null) {
            return "redirect:/board/" + boardType + "/list";
        }

        // 조회수 증가
        if (sessionEmployeeDTO.getEmployeeId() != boardDTO.getEmployee().getEmployeeId()) {
            boardService.increaseHits(no);
            boardDTO.setHits(boardDTO.getHits() + 1);
        }

        log.info("comment : {}", commentDTO);

        model.addAttribute("board", boardDTO);
        model.addAttribute("comment", commentDTO);
        model.addAttribute("commentCount", commentCount);
        return "board/detail";
    }

    // 게시판 수정
    @GetMapping("{departmentId}/edit/{no}")
    public String edit(@PathVariable("departmentId") String boardType,
                       @PathVariable("no") Long no,  Model model,
                       @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {

        // 게시판 초기화 및 접근권한 검사
        Long departmentId = BoardUtils.initializeBoard(boardType, sessionEmployeeDTO, "글수정", model);
        if (departmentId == null && !boardType.equals("notice"))
            return REDIRECT_URL;

        BoardDTO dto = boardService.getDetail(no, departmentId);

        // 상세데이터가 없으면 리스트로 돌려보냄.
        if (dto.getBoardId() == null) {
            return "redirect:/board/" + boardType + "/list";
        }
        model.addAttribute("board", dto);
        return "board/edit";
    }

    // 게시판 수정 (put)
    @ResponseBody
    @PutMapping("/edit/{no}")
    public ResponseEntity<Void> edit(@PathVariable("no") Long no,
                                     @RequestBody BoardDTO dto) {
        boardService.editBoard(no, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 게시글삭제.
    @ResponseBody
    @PostMapping("{departmentId}/delete/{no}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("departmentId") String boardType,
                                                      @PathVariable("no") Long no,
                                                      @SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {

        Long employeeId = sessionEmployeeDTO.getEmployeeId();
        String result = boardService.deleteBoard(no, employeeId);
        Map<String, String> response = new HashMap<>();
        response.put("msg", result);
        return ResponseEntity.ok(response);
    }
}
