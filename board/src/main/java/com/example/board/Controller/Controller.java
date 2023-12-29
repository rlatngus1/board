package com.example.board.Controller;
import com.example.board.Entity.Account;
import com.example.board.Service.AccountService;
import com.example.board.Service.ArticleService;
import com.example.board.Service.ArticlecommentsService;
import com.example.board.Service.CommentcommentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;


@org.springframework.stereotype.Controller
public class Controller {

    private final AccountService accountService;
    private final ArticleService articleService;
    private final ArticlecommentsService articlecommentsService;

    private final CommentcommentService commentcommentService;

    public Controller(AccountService accountService, ArticleService articleService, ArticlecommentsService articlecommentsService, CommentcommentService commentcommentService) {
        this.accountService = accountService;
        this.articleService = articleService;
        this.articlecommentsService = articlecommentsService;
        this.commentcommentService = commentcommentService;
    }

    @RequestMapping("/")     //메인화면
    public String main(Model model){

        //Pageable 페이징 처리에 필요한 정보들을 전달하는 인터페이스
        Pageable pageable = PageRequest.of(0, 20);
        model.addAttribute("articles", articleService.orderbywritedate(pageable));

        return "/layouts/main";
    }

    @RequestMapping("/login")   //로그인 화면
    public String loginpage(HttpServletRequest request){
        String prevuri = request.getHeader("Referer");
        request.getSession().setAttribute("prevpage", prevuri);
        return "/layouts/login";
    }

    @PostMapping("/logincheck") //아이디 패스워드 값 전달받고 확인합니다
    public String logincheck(HttpServletRequest request, HttpSession session){

        String userid = request.getParameter("userid");
        String userpw = request.getParameter("userpw");

        Optional<Account> optional = accountService.logincheck(userid);

        if (optional.isEmpty()){

            return "redirect:/login";

        }else{
            String correctpw =optional.get().getUserpw();


            if(userpw.equals(correctpw)){
                session.setAttribute("loginid", userid);
                
                String referer = request.getSession().getAttribute("prevpage").toString();
            
                return "redirect:" + referer;

            }else{
           
                return "redirect:/login";
            }
        }


    }

    @RequestMapping("/join")    //회원가입 화면
    public String joinpage(){
        return "/layouts/join";
    }

    @PostMapping("/join_submit")    //회원가입!!
    public String joinmember(HttpServletRequest request){

        String userid = request.getParameter("userid");
        String userpw = request.getParameter("userpw");

        System.out.println(userid);

        accountService.joinmember(userid, userpw);
        return "/layouts/welcome";
    }


    @GetMapping("/write")   //글 작성 화면
    public String write(HttpServletRequest request){
        
        String prevuri = request.getHeader("Referer");
        request.getSession().setAttribute("prevpage", prevuri);

        return "/layouts/write";
    }

    @PostMapping("/write")
    public String article_write(HttpServletRequest request, HttpSession session, @RequestParam(value ="file", required = false)MultipartFile file){

        String userid = session.getAttribute("loginid").toString();
        String title = request.getParameter("title");
        String article = request.getParameter("article");
        String referer = request.getSession().getAttribute("prevpage").toString();



        if(file!=null){
            String fileRealName = file.getOriginalFilename();  //파일 원래이름

            String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length()); //
            String uploadFolder = "C:\\upload";

            UUID uuid = UUID.randomUUID();
            String[] uuids = uuid.toString().split("-");
            String uniqueName = uuids[0];
            String fileString = uploadFolder+"\\"+uniqueName + fileExtension;  

            File saveFile = new File(fileString);
            try {
                file.transferTo(saveFile); 
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            articleService.write_article_with_img(userid,title,article,uniqueName + fileExtension);

        }else{
            articleService.write_article(userid,title,article);

        }
        return "redirect:" + referer;
    }


    @PostMapping("/search")         //검색 결과 화면으로!!
    public String search_result_page(HttpServletRequest request){
        return "/layouts/search_result";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    //제목, 본문 작성후 작성 버튼 클릭시 동작


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id")long id, Model model){


        model.addAttribute("article", articleService.update_views(id));
        model.addAttribute("comments", articlecommentsService.findcommentsbyarticleid(id));
        model.addAttribute("replies", commentcommentService.findbyarticleid(id));

        return "/layouts/detail";
    }

    @PostMapping("/commentwrite/{id}")
    public String commentwrite(@PathVariable("id")Long id, HttpServletRequest request){

        String commentwriter = request.getParameter("writer");
        String commenttxt = request.getParameter("commenttxt");

        articlecommentsService.commentwrite(id, commentwriter, commenttxt);

        return "redirect:/detail/"+id.toString();
    }


    @RequestMapping("/mypage")
    public String mypage(){

        return "/layouts/mypage";
    }

    @RequestMapping("/myarticles")
    public String myarticles(Model model, HttpSession session){

        String userid = session.getAttribute("loginid").toString();
        model.addAttribute("articles", articleService.findByUserid_Userid(userid));

        return "/layouts/myarticles";

    }

    @RequestMapping("/edit/article/{id}")
    public String edit_article(@PathVariable("id")long id, Model model){

        model.addAttribute("article", articleService.findByArticleId(id).get());

        return "/layouts/edit_article";
    }

    @PostMapping("/article_edit_submit")
    public String article_edit_submit(HttpSession session, HttpServletRequest request){

        String loginid = session.getAttribute("loginid").toString();
        String articleid_str = request.getParameter("articleid").toString();
        long articleid = Long.parseLong(articleid_str);
        String title = request.getParameter("title");
        String article = request.getParameter("article");

        articleService.edit_article(title, article, articleid);

        return "redirect:/mypage";
    }

    @RequestMapping("/delete/article/{id}")
    public String delete_article(@PathVariable("id")long id){

     //   articleService.deleteByArticleid(id);
        System.out.println(id);
        articleService.deleteByArticleid(id);
        return "redirect:/myarticles";
    }

    @ResponseBody
    @PostMapping("/articlelist/pagination")
    public String articlelist_pagination(@RequestBody int data, Model model){

        System.out.println(data);
        System.out.println(data-1);
        data = data-1;
        Pageable pageable = PageRequest.of(data, 20);

        return articleService.orderbywritedate(pageable).toString();


    }

    @RequestMapping("/articles/{page}")
    public String articles(@PathVariable("page")int page, Model model){

        Pageable pageable = PageRequest.of(page-1, 20);
        long total_articles = articleService.articlecount();
        long pagecnt = total_articles/20;


        model.addAttribute("page", pagecnt);
        model.addAttribute("articles", articleService.orderbywritedate(pageable));

        return "/layouts/articles";
    }

    @GetMapping("/mycomments")
    public String mycomments(HttpSession session, Model model){ 

        String loginid = session.getAttribute("loginid").toString();
        model.addAttribute("comments", articlecommentsService.findByCommentwriter(loginid));

        return "/layouts/mycomments";
    }

    @RequestMapping("/fileupload")
    public String fileupload() {

        return "/layouts/fileupload";
    }

    @PostMapping("/write_commentcomment")
    public String write_commentcomment(HttpServletRequest request){

        String comment_writer = request.getParameter("writer");
        String commenttxt = request.getParameter("commenttxt");
        String commentid_str = request.getParameter("commentid");
        String article_id_str = request.getParameter("articleid");

        long originalcommentid = Long.parseLong(commentid_str);
        long articleid = Long.parseLong(article_id_str);

        commentcommentService.write_commentcomment(comment_writer, commenttxt, originalcommentid, articleid);

        return "redirect:/detail/"+article_id_str;
    }


    @GetMapping("/mylike")
    public String mylike(Model model, HttpSession session){

        String loginid = session.getAttribute("loginid").toString();

        model.addAttribute("articles", articleService.find_user_liked_articles(loginid));

        return "/layouts/mylike";
    }


    @GetMapping("/mydislike")
    public String mydislike(Model model, HttpSession session){

        String loginid = session.getAttribute("loginid").toString();

        model.addAttribute("articles", articleService.find_user_dislike_articles(loginid));

        return "/layouts/mylike";
    }

    @GetMapping("/changepw")
    public String changepw(){
        return "";
    }

}
