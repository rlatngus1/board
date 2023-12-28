package com.example.board.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ArticleImage")
public class ArticleImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long articleimageid;

    @ManyToOne
    @JoinColumn(name = "articleid", referencedColumnName = "articleid")
    private Articles articleid;

    @Column(name = "imagename")
    private String imagename;
        
}
