package com.example.board.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "Account")
public class Account{

    @Id @Column(nullable = false, name = "userid")
    private String userid;

    @Column(name = "userpw")
    private String userpw;

    @Column(name = "authority")
    private String authority;

}
