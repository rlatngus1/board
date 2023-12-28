package com.example.board.Service;
import com.example.board.Repository.UserDislikeRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDislikeService {

    private final UserDislikeRepository repository;

    public UserDislikeService(UserDislikeRepository repository) {
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

    public void newdislikecnt(long articleid, String userid){

        repository.newdislikecnt(articleid, userid);
    }


}
