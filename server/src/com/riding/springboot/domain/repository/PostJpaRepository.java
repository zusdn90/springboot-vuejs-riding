package com.riding.springboot.domain.repository;

import com.riding.springboot.domain.entity.board.BoardEnitity;
import com.riding.springboot.domain.entity.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    List<Post> findByBoardEnitity(BoardEnitity boardEnitity);
}
