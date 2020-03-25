package com.riding.springboot.service.board;

import com.riding.springboot.advice.exception.CNotOwnerException;
import com.riding.springboot.advice.exception.CResourceNotExistException;
import com.riding.springboot.advice.exception.CUserExistException;
import com.riding.springboot.common.CacheKey;
import com.riding.springboot.domain.entity.board.BoardEnitity;
import com.riding.springboot.domain.entity.board.Post;
import com.riding.springboot.domain.entity.user.User;
import com.riding.springboot.domain.repository.BoardJpaRepository;
import com.riding.springboot.domain.repository.PostJpaRepository;
import com.riding.springboot.domain.repository.UserRepository;
import com.riding.springboot.dto.ParamPost;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardPostService {
    private final BoardJpaRepository boardJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final UserRepository userRepository;

    // 게시판 이름으로 게시판을 조회. 없을경우 CResourceNotExistException 처리
    @Cacheable(value = CacheKey.BOARD, key = "#boardName", unless = "#result == null")  // 단일 데이터 캐시 처리
    public BoardEnitity findBoard(String boardName) {
        return Optional.ofNullable(boardJpaRepository.findByName(boardName)).orElseThrow(CResourceNotExistException::new);
    }

    // 게시판 이름으로 게시물 리스트 조회.
    @Cacheable(value = CacheKey.POSTS, key = "#boardName", unless = "#result == null")
    public List<Post> findPosts(String boardName) {
        return postJpaRepository.findByBoardEnitity(findBoard(boardName));
    }

    // 게시물 ID로 게시물 단건 조회. 없을경우 CResourceNotExistException 처리
    public Post getPost(long postId) {
        return postJpaRepository.findById(postId).orElseThrow(CResourceNotExistException::new);
    }

    // 게시물을 등록합니다. 게시물의 회원UID가 조회되지 않으면 CUserNotFoundException 처리합니다.
    @CacheEvict(value = CacheKey.POSTS, key = "#boardName") // 게시글 등록 후 캐시 삭제
    public Post writePost(String uid, String boardName, ParamPost paramPost) {
        BoardEnitity boardEnitity = findBoard(boardName);
        Post post = new Post(userRepository.findByUid(uid).orElseThrow(CUserExistException::new),boardEnitity, paramPost.getAuthor(), paramPost.getTitle(), paramPost.getContent());

        return postJpaRepository.save(post);
    }

    // 게시물을 수정합니다. 게시물 등록자와 로그인 회원정보가 틀리면 CNotOwnerException 처리합니다.
    @CachePut(value = CacheKey.POST, key = "#postId")
    public Post updatePost(long postId, String uid, ParamPost paramPost) {
        Post post = getPost(postId);
        User user = post.getUser();

        if(!uid.equals(user.getUid()))
            throw new CNotOwnerException();

        // 영속성 컨텍스트의 변경감지(dirty checking) 기능에 의해 조회한 Post내용을 변경만 해도 Update쿼리가 실행됩니다.
        post.setUpdate(paramPost.getAuthor(), paramPost.getTitle(), paramPost.getContent());
        return post;
    }

    // 게시물을 삭제합니다. 게시물 등록자와 로그인 회원정보가 클리면 CNotOwnerException 처리합니다.
    public boolean deletePost(long postId, String uid) {
        Post post = getPost(postId);
        User user = post.getUser();

        if(!uid.equals(user.getUid()))
            throw new CNotOwnerException();
        postJpaRepository.delete(post);
        return true;
    }
}
