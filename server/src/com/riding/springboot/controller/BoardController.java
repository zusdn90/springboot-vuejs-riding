package com.riding.springboot.controller;

import com.riding.springboot.dto.BoardDto;
import com.riding.springboot.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value="/api/board")
public class BoardController {
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDto> boardList = boardService.getBoardList();

        model.addAttribute("boardList", boardList);
        return "게시판 리스트 페이지";
    }

    @GetMapping("/post")
    public String write() {
        return "게시판 작성 페이지";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }
}
