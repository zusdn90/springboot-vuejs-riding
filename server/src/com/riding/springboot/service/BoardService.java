package com.riding.springboot.service;

import com.riding.springboot.domain.entity.Board;
import com.riding.springboot.domain.repository.BoardRepository;
import com.riding.springboot.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Transactional
    public List<BoardDto> getBoardList() {
        List<Board> boardEntities = boardRepository.findAll() ;
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board: boardEntities) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .createdDate(board.getCreatedDate())
                    .build();

            boardDtoList.add(boardDto);
        }
        return  boardDtoList;
    }


    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }
}
