package com.example.board.Repository;

import com.example.board.Entity.Userlike;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserlikeRepository extends JpaRepository <Userlike, Long> {

     @Query(value = "SELECT COUNT(*) from userlike where articleid =:articleid and userid = :userid", nativeQuery = true)
    int cntbyarticleid_userid(@Param("articleid")long articleid, @Param("userid")String userid);

    @Modifying
    @Transactional
     @Query(value = "INSERT INTO userlike (articleid, userid) VALUES (:articleid, :userid)", nativeQuery = true)
    void newlikecnt(@Param("articleid")long articleid, @Param("userid")String userid);


}
