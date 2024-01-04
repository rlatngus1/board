package com.example.board.Repository;
import com.example.board.Entity.Articles;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Articles, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into articles (userid, title, article) VALUES(:userid, :title, :article)", nativeQuery = true)
    void write_article(@Param("userid")String userid, @Param("title")String title, @Param("article")String article);

    @Modifying
    @Transactional
    @Query(value = "insert into articles (userid, title, article, articleimgsrc) VALUES(:userid, :title, :article, :articleimgsrc)", nativeQuery = true)
    void write_article_with_img(@Param("userid")String userid, @Param("title")String title, @Param("article")String article, @Param("articleimgsrc")String articleimgsrc);

    List<Articles> findAllByUserid_Userid(String userid);

    @Modifying
    @Transactional
    @Query(value = "update articles set title=:title, article=:article WHERE articleid = :articleid",nativeQuery = true )
    void edit_article(@Param("title")String title, @Param("article")String article, @Param("articleid")long articleid);


    @Modifying
    @Transactional
    void deleteByArticleid(long articleid);

    
    List<Articles> findAllByOrderByArticleidDesc(Pageable pageable);

    long count();

    @Query(value = "select * from articles where exists (select * from userlike where articles.articleid=userlike.articleid and userlike.userid=:userid)", nativeQuery = true)
    List<Articles> find_user_liked_articles(@Param("userid")String userid);

    @Query(value = "select * from articles where exists (select * from userdislike where articles.articleid=userdislike.articleid and userdislike.userid=:userid)", nativeQuery = true)
    List<Articles> find_user_dislike_articles(@Param("userid")String userid);

    @Query(value = "select * from articles where title like %:keyword% or article like %:keyword%", nativeQuery = true)
    List<Articles> search_articles(@Param("keyword")String keyword);


}
