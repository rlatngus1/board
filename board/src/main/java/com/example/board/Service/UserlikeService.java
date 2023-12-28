package com.example.board.Service;

import com.example.board.Entity.Userlike;
import com.example.board.Repository.UserlikeRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class UserlikeService {

    private final UserlikeRepository repository;

    public UserlikeService(UserlikeRepository repository) {
        this.repository = repository;
    }

    public boolean cntbyarticleid_userid(long articleid, String userid){

        //이미 좋아요 누른 적 있으면 false return
        if(repository.cntbyarticleid_userid(articleid, userid)==1){
            return false;
        }else{
            return true;
        }

    }

    public void newlikecnt(long articleid, String userid){

        repository.newlikecnt(articleid, userid);
    }




}
