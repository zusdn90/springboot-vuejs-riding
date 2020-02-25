package com.riding.springboot.domain.repository;

import com.riding.springboot.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
