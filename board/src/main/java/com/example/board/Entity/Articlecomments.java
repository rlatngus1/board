package com.example.board.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
@Setter
@Getter
@Entity(name = "Articlecomments")   //본문 댓글
public class Articlecomments {

    @Column(name = "commentid")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentid;

    @JoinColumn(name = "articleid", referencedColumnName = "articleid")
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Articles articleid;

    @Column(name = "commentwriter", nullable = false)
    private String commentwriter;

    @Column(name = "commenttxt", nullable = false)
    private String commenttxt;

    @Column(name = "likecount", columnDefinition = "smallint DEFAULT 0")
    private Integer likecount;      //좋아요 받은 횟수


    @Column(name = "dislikecount", columnDefinition = "smallint DEFAULT 0")
    private Integer dislikecount;   //싫어요 받은 횟수


}
