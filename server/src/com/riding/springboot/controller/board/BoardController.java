package com.riding.springboot.controller.board;

import com.riding.springboot.dto.BoardDto;
import com.riding.springboot.service.board.BoardService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"2.Board API"})
@CrossOrigin("http://192.168.11.37:8081")
@AllArgsConstructor
@RestController
@RequestMapping(value="/api")
public class BoardController {
    private BoardService boardService;

    /**
     * 게시판 목록
     * @param
     * @return
     */
    @GetMapping("/board/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getBoardList(pageNum);
        Integer [] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);
        return "게시판 리스트 페이지~~~";
    }

    /**
     * 게시글 작성 페이지
     * @param
     * @return
     */
    @GetMapping("/board/post")
    public String write() {
        return "게시판 작성 페이지";
    }

    /**
     * 게시글 저장
     * @param
     * @return
     */
    @PostMapping("/board/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    /**
     * 게시글 상세페이지
     * @param
     * @return
     */
    @GetMapping("/board/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model){
        BoardDto boardDto = boardService.getPost(no);

        model.addAttribute("boardDto", boardDto);
        return "게시판 상세 페이지";
    }

    /**
     * 게시글 수정 페이지
     * @param
     * @return
     */
    @GetMapping("/board/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "게시판 수정 페이지";
    }

    /**
     * 게시글 수정
     * @param
     * @return
     */
    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/";
    }

    /**
     * 게시판 삭제
     * @param
     * @return
     */
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/";
    }

    /**
     * 게시판 찾기 기능
     * @param keyword
     * @return
     */
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);

        return "게시판 리스트 페이지";
    }
}
