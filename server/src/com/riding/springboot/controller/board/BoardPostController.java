package com.riding.springboot.controller.board;

import com.riding.springboot.domain.entity.board.BoardEnitity;
import com.riding.springboot.domain.entity.board.Post;
import com.riding.springboot.dto.ParamPost;
import com.riding.springboot.response.CommonResult;
import com.riding.springboot.response.ListResult;
import com.riding.springboot.response.SingleResult;
import com.riding.springboot.service.ResponseService;
import com.riding.springboot.service.board.BoardPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"2. Board API"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardPostController {
    private final BoardPostService boardPostService;
    private final ResponseService responseService;

    @ApiOperation(value = "게시판 정보 조회", notes = "게시판 정보를 조회한다.")
    @GetMapping(value = "/{boardName}")
    public SingleResult<BoardEnitity> boardInfo(@PathVariable String boardName) {
        return responseService.getSingleResult(boardPostService.findBoard(boardName));
    }

    @ApiOperation(value = "게시판 글 리스트", notes = "게시판 게시글 리스트를 조회한다.")
    @GetMapping(value = "/{boardName}/posts")
    public ListResult<Post> posts(@PathVariable String boardName) {
        return responseService.getListResult(boardPostService.findPosts(boardName));
    }

    @ApiOperation(value = "게시판 글 작성", notes = "게시판 게시글을 작성한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping(value = "/{boardName}")
    public SingleResult<Post> post(@PathVariable String boardName, @Valid @ModelAttribute ParamPost post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(boardPostService.writePost(uid, boardName, post));
    }

    @ApiOperation(value = "게시판 글 상세", notes = "게시판 게시글을 조회한다.")
    @GetMapping(value = "/post/{postId}")
    public SingleResult<Post> post(@PathVariable long postId) {
        return responseService.getSingleResult(boardPostService.getPost(postId));
    }

    @ApiOperation(value = "게시판 글 수정", notes = "게시판 게시글을 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PutMapping(value = "/post/{postId}")
    public SingleResult<Post> post(@PathVariable long postId, @Valid @ModelAttribute ParamPost post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(boardPostService.updatePost(postId, uid, post));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시판 글 삭제", notes = "게시판의 글을 삭제한다.")
    @DeleteMapping(value = "/post/{postId}")
    public CommonResult deletePost(@PathVariable long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        boardPostService.deletePost(postId, uid);

        return responseService.getSuccessResult();
    }
}
