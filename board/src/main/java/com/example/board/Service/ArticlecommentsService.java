package com.example.board.Service;

import com.example.board.Entity.Articlecomments;
import com.example.board.Repository.ArticlecommentsRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlecommentsService {

    private final ArticlecommentsRepository repository;

    public ArticlecommentsService(ArticlecommentsRepository repository) {
        this.repository = repository;
    }

    public void commentwrite(Long articleid, String commentwriter, String commenttxt){

        repository.commentwrite(articleid, commentwriter, commenttxt);
    }

    public List<Articlecomments> findcommentsbyarticleid(long articleid){
        return repository.findAllByArticleid_Articleid(articleid);
    }

    public List<Articlecomments> findByCommentwriter(String userid){
        return repository.findByCommentwriter(userid);
    }

}
