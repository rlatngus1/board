package com.example.board.Repository;

import com.example.board.Entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository <Account, String> {

    Optional<Account> findByUserid(String userid);

    @Modifying
    @Transactional
    @Query(value = "insert into account (userid, userpw) VALUES(:userid, :userpw)", nativeQuery = true)
    void joinmember(@Param("userid")String userid, @Param("userpw")String userpw);

}
