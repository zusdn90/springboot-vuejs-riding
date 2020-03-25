package com.riding.springboot.service.board;


import com.riding.springboot.domain.entity.board.Board;
import com.riding.springboot.domain.repository.BoardRepository;
import com.riding.springboot.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    private static final int BLOCK_PAGE_NUM_CONUT = 5;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4;       // 한 페이지에 존재하는 게시글 수

    @Transactional
    public BoardDto getPost(Long id) {
        Optional<Board> boardEntityWrapper = boardRepository.findById(id);
        Board boardEntity = boardEntityWrapper.get();

        BoardDto boardDto = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();

        return boardDto;
    }


    @Transactional
    public List<BoardDto> getBoardList(Integer pageNum) {
        Page<Board> apge = boardRepository.findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createDate")));

        List<Board> boardEntities = boardRepository.findAll() ;
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }
        return  boardDtoList;
    }
    /**
     * 게시판 검색(Repository에서 검색 결과를 받아와 비즈니스 로직을 실행하는 함수)
     * @param keyword
     * @return
     */
    public List<BoardDto> searchPosts(String keyword) {
        List<Board> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if(boardEntities.isEmpty()) return  boardDtoList;

        for(Board boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }
        return boardDtoList;
    }

    /**
     * Entity를 Dto로 변환하는 작업 수행
     * @param boardEntity
     * @return
     */
    private BoardDto convertEntityToDto(Board boardEntity) {
        return BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();
    }



    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    /**
     * 전체 게시글 갯수 조회
     * @param
     * @return
     */
    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    public Integer [] getPageList(Integer curPageNum){
        Integer [] pageList = new Integer[BLOCK_PAGE_NUM_CONUT];

        //총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)Math.ceil(postsTotalCount / PAGE_POST_COUNT);

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_CONUT)
                ? curPageNum + BLOCK_PAGE_NUM_CONUT
                : totalLastPageNum;

        //페이지 시작 번호 조정
        curPageNum = (curPageNum <=3) ? 1 : curPageNum -2;

        // 페이지 번호 할당
        for(int val = curPageNum, idx=0; val <= blockLastPageNum; val++, idx++){
            pageList[idx] = val;
        }

        return pageList;
    }
}
