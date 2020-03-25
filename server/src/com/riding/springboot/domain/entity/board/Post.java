package com.riding.springboot.domain.entity.board;

import com.riding.springboot.domain.entity.common.CommonDateEntity;
import com.riding.springboot.domain.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends CommonDateEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    @Column(nullable = false, length = 50)
    private String author;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEnitity boardEnitity;  //게시글 - 게시판의 관례 N:1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msrl")
    private User user;  // 게시글 - 회원의 관계 - N:1

    // Join 테이블이 Json결과에 표시되지 않도록 처리
    @JsonIgnore
    protected BoardEnitity getBoard() {
        return boardEnitity;
    }

    // 생성자
    public Post(User user, BoardEnitity boardEnitity, String author, String title, String content){
        this.user = user;
        this.boardEnitity = boardEnitity;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    // 수정 시 데이터 처리
    public Post setUpdate(String author, String title, String content){
        this.author = author;
        this.title = title;
        this.content =content;
        return  this;
    }
}
