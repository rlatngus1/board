package com.example.board.Controller;
import com.example.board.Service.ArticleService;
import com.example.board.Service.UserDislikeService;
import com.example.board.Service.UserlikeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private  final UserlikeService userlikeService;
    private final ArticleService articleService;
    private final UserDislikeService userDislikeService;

    public RestController(UserlikeService userlikeService, ArticleService articleService, UserDislikeService userDislikeService) {
        this.userlikeService = userlikeService;
        this.articleService = articleService;
        this.userDislikeService = userDislikeService;
    }



    @PostMapping("/likecntplus")
    @ResponseBody
    public String likecntplus(@RequestBody Map<String, Object> data){

        String loginid = data.get("loginid").toString();
        String articleid_str = data.get("articleid").toString();
        long articleid = Long.parseLong(articleid_str);

        if(userlikeService.cntbyarticleid_userid(articleid, loginid)){
            //좋아요 누른 적 없는 경우 1. 해당 게시물 좋아요 +1 // 2. 좋아요,유저 아이디 입력
            int updated_likecnt = articleService.update_likecnt(articleid); // 좋아요 증가!
            userlikeService.newlikecnt(articleid, loginid); //좋아요 테이블에 입력
            return String.valueOf(updated_likecnt);

        }else{ //좋아요 누른 적 있는 경우
            return "false";
        }

    }

    @PostMapping("/dislikecntplus")
    @ResponseBody
    public String dislikecntplus(@RequestBody Map<String, Object> data){

        String loginid = data.get("loginid").toString();
        String articleid_str = data.get("articleid").toString();
        long articleid = Long.parseLong(articleid_str);

        if(userDislikeService.cntbyarticleid_userid(articleid, loginid)){
            int updated_dislikecnt = articleService.update_dislikecnt(articleid);
            userDislikeService.newdislikecnt(articleid, loginid);
            return String.valueOf(updated_dislikecnt);

        }else{
            return "false";
        }

    }






}
