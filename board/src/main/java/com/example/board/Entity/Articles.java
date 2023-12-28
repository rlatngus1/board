package com.example.board.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Table(name = "Articles")
@Setter
@Getter
@Entity(name = "Articles")
public class Articles {

    @Column(name = "articleid")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long articleid;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    private Account userid;     //userid 참조

    @Column(name = "title", nullable = false)   //글제목
    private String title;

    @Column(name = "article", nullable = false)     //본문
    private String article;

    @Column(name = "views", columnDefinition = "smallint DEFAULT 0")
    private Integer views;     //조회수

    @Column(name = "likecount", columnDefinition = "smallint DEFAULT 0")
    private Integer likecount;      //좋아요 받은 횟수

    @Column(name = "dislikecount", columnDefinition = "smallint DEFAULT 0")
    private Integer dislikecount;   //싫어요 받은 횟수

    @CreatedDate
    @Column(name = "writedate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate writedate;    //작성일

    @Column(name = "articleimgsrc")
    private String articleimgsrc;

}
