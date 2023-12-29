package com.example.board.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.board.Entity.Commentcomments;

import jakarta.transaction.Transactional;

import java.util.List;

public interface CommentCommentRepository extends JpaRepository <Commentcomments, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Commentcomments (commentcommentwriter, commentcommenttxt, originalcommentid, articleid) VALUES (:commentcommentwriter, :commentcommenttxt, :originalcommentid, :articleid)", nativeQuery = true)
    void write_commentcomment(@Param("commentcommentwriter")String commentcommentwriter, @Param("commentcommenttxt")String commentcommenttxt, @Param("originalcommentid")long originalcommentid, @Param("articleid")long articleid);


    List<Commentcomments> findByArticleid_Articleid(long articleid);
}