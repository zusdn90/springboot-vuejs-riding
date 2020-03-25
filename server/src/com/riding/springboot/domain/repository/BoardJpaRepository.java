package com.riding.springboot.domain.repository;

import com.riding.springboot.domain.entity.board.BoardEnitity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<BoardEnitity, Long> {
    BoardEnitity findByName(String name);
}
