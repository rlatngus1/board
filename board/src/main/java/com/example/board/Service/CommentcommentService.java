package com.example.board.Service;
import com.example.board.Entity.Commentcomments;
import org.springframework.stereotype.Service;
import com.example.board.Repository.CommentCommentRepository;

import java.util.List;

@Service
public class CommentcommentService {

    private final CommentCommentRepository repository;

    public CommentcommentService(CommentCommentRepository repository) {
        this.repository = repository;
    }

    public void write_commentcomment(String writer, String commenttxt, long originalcommentid, long articleid) {
        
        repository.write_commentcomment(writer, commenttxt, originalcommentid,articleid);
    }

    public List<Commentcomments> findbyarticleid(long articleid){
        return repository.findByArticleid_Articleid(articleid);
    }


}
