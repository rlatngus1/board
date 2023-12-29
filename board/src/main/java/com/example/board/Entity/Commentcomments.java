package com.example.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@Entity(name = "Commentcomments")   // 댓글댓글
public class Commentcomments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentcommentid;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "articleid", referencedColumnName = "articleid")
    private Articles articleid;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "originalcommentid", referencedColumnName = "commentid")
    private Articlecomments commentid;

    @Column(name = "commentcommentwriter", nullable = false)
    private String commentcommentwriter;

    @Column(name = "commentcommenttxt", nullable = false)
    private String commentcommenttxt;


    @Column(name = "likecount", columnDefinition = "smallint DEFAULT 0")
    private Integer likecount;

    @Column(name = "dislikecount", columnDefinition = "smallint DEFAULT 0")
    private Integer dislikecount;

}

