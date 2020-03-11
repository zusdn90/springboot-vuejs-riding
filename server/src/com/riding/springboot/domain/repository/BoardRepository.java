package com.riding.springboot.domain.repository;

import com.riding.springboot.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // JpaRepository에서 메서드명의 By 이후는 SQL의 where 조건 절에 대응되는 것인데, 이렇게 Containing을 붙여주면 Like 검색이 됨.
    // 즉, 해당 함수는 %{keyword}% 이렇게 표현
    List<Board> findByTitleContaining(String keyword);
}
