package com.example.board.Service;

import com.example.board.Entity.Articles;
import com.example.board.Repository.ArticleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public List findAll(){
        return repository.findAll();
    }

    public List orderbywritedate(Pageable pageable){

        return repository.findAllByOrderByArticleidDesc(pageable);
    }

    public void write_article(String userid, String title, String article){
       repository.write_article(userid, title, article);
    }

    public void write_article_with_img(String userid, String title, String article, String articleimgsrc){
        repository.write_article_with_img(userid, title, article, articleimgsrc);
    }

    public Articles update_views(Long articleid){

        Optional<Articles> articles = repository.findById(articleid);

        if(articles.isPresent()){
            Articles thisarticle =articles.get();

            thisarticle.setViews(thisarticle.getViews()+1);
            repository.save(thisarticle);
        }

        return articles.get();
    }


    public int update_likecnt(Long articleid){

        Optional<Articles> articles = repository.findById(articleid);

        if(articles.isPresent()){
            Articles thisarticle =articles.get();

            thisarticle.setLikecount(thisarticle.getLikecount()+1);
            repository.save(thisarticle);
        }

        return articles.get().getLikecount();
    }

    public int update_dislikecnt(Long articleid){

        Optional<Articles> articles = repository.findById(articleid);

        if(articles.isPresent()){
            Articles thisarticle =articles.get();

            thisarticle.setDislikecount(thisarticle.getDislikecount()+1);
            repository.save(thisarticle);
        }

        return articles.get().getDislikecount();
    }


    public List<Articles> findByUserid_Userid(String userid){

        return repository.findAllByUserid_Userid(userid);
    }


    public Optional<Articles> findByArticleId(long articleid){
        return repository.findById(articleid);
    }

    public void edit_article(String title, String article, long articleid){
        repository.edit_article(title, article, articleid);

    }

    public void deleteByArticleid(long articleid){
        repository.deleteByArticleid(articleid);
    }

    public long articlecount(){
        return repository.count();
    }


}
