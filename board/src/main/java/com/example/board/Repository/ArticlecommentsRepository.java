package com.example.board.Repository;

import com.example.board.Entity.Articlecomments;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticlecommentsRepository extends JpaRepository<Articlecomments, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into articlecomments (articleid, commentwriter, commenttxt) VALUES(:articleid, :commentwriter, :commenttxt)", nativeQuery = true)
    void commentwrite(@Param("articleid")long articleid, @Param("commentwriter")String commentwriter, @Param("commenttxt")String commenttxt);


    List<Articlecomments> findAllByArticleid_Articleid(Long articleid);


    List<Articlecomments> findByCommentwriter(String commentwriter);

}
