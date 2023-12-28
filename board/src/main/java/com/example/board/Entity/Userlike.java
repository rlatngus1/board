package com.example.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "Userlike")
public class Userlike {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Column(name = "userlikeid")
    private long userlikeid;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userid", nullable = false, referencedColumnName = "userid")
    private Account account;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "articleid", referencedColumnName = "articleid")
    private Articles articleid;

}

