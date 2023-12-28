package hello.hellospring.post.controller;


import hello.hellospring.comment.domain.CommentRegisterVO;
import hello.hellospring.comment.service.CommentService;
import hello.hellospring.member.domain.*;
import hello.hellospring.post.domain.CriteriaVO;
import hello.hellospring.post.domain.RegisterVO;
import hello.hellospring.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequestMapping("/post/*")
@Controller
public class PostController {

  private final PostService postService;
  private final CommentService commentService;

  @Autowired
  public PostController(PostService postService,  CommentService commentService) {
    this.postService = postService;
    this.commentService = commentService;
  }


  // RETURN ADD POST VIEW
  @GetMapping("register")
  public String registerView(
          Model model,
          RegisterVO register) {
    // 공개 옵션과 댓글 옵션의 DEFAULT 값 셋팅
    register.setPublic_option("Y");
    register.setComment_option("Y");
    model.addAttribute("register", register);
    return "/post/register";
  }

 // ADD POST
  @PostMapping("register")
  public String postRegister(
          RegisterVO register,
          HttpServletRequest req ) {
    HttpSession session = req.getSession();
    // 회원의 pk 셋팅
    register.setWriter(((MemberVO) session.getAttribute("loginMember")).getId());
    // 게시글 옵션이 비공개일 경우 'N' 셋팅
    register.setPublic_option(register.getPublic_option() == null ? String.valueOf('N') : String.valueOf('Y'));
    // 댓글 옵션이 없을 경우 'N' 셋팅
    register.setComment_option(register.getComment_option() == null ? String.valueOf('N') : String.valueOf('Y'));

    postService.insertPost(register);
    Long generatedKey = register.getId();

    return "redirect:/post/detail/" + generatedKey;
  }

  // POST DETAIL VIEW
  @GetMapping("detail/{id}")
  public String postDetailView(
          Model model,
          @PathVariable Long id,
          CommentRegisterVO comment,
          HttpServletRequest req,
          HttpServletResponse resp) {

    // 세션이 유효한 경우에만 게시글 조회수 업데이트
    HttpSession session = req.getSession();

    postService.checkCookies(req, resp, id);
    HashMap<String, Object> post = postService.selectPostById(id);

    // 사용자의 좋아요 정보
    if(session.getAttribute("loginMember") == null) {
      model.addAttribute("likeState", false);
      model.addAttribute("member", 0);
    } else {
      // post의 방문횟수 제어
      // 세션에 로그인 된 회원
      MemberVO member = (MemberVO) session.getAttribute("loginMember");

      HashMap<String, Object> likeInfo = new HashMap<>();
      likeInfo.put("id", id);
      likeInfo.put("writer", member.getId());

      Boolean isLikeState = postService.searchLikeByPostId(likeInfo);
      model.addAttribute("likeState", isLikeState);
      model.addAttribute("member", member.getId());

    }

    int totalCommentCount = postService.selectCommentCountByPostId(id);

    // 조회한 게시글
    model.addAttribute("post", post);
    // 댓글 작성 VO
    model.addAttribute("comment", comment);
    // 댓글의 총 개수
    model.addAttribute("commentCount",totalCommentCount);

    return "/post/detail";
  }

  // CATEGORY ID BY POST
  @GetMapping("list/{id}")
  public String postListView(
          Model model,
          @PathVariable Long id){
    List<HashMap<String, Object>> lists = postService.selectPostList(id);
    model.addAttribute("lists", lists); // 게시글 목록
    model.addAttribute("count", postService.countByCtgryId(id)); // 카테고리에 해당하는 총 개시글의 개수
    model.addAttribute("ctgryName", postService.selectCtgryNameById(id)); // 카테고리명
    return "/post/list";
  }

  // RETURN POST VIEW
  @GetMapping("modify/{id}")
  public String modifyView(
          Model model,
          @PathVariable Long id) {
    HashMap<String, Object> register = postService.modifyGetData(id);
    model.addAttribute("register", register);

    return "post/modify" ;
  }

  // UPDATE POST
  @PostMapping("modify/{id}")
  public String modifyPost(
          @PathVariable Long id,
          Long ctgry,
          String prepend,
          String title,
          String content,
          String public_option,
          String comment_option,
          HttpServletRequest req,
          RedirectAttributes rd) {

    // 게시글 옵션이 비공개일 경우 'N' 셋팅
    String newPublicOption = public_option == null ? String.valueOf('N') : String.valueOf('Y');
    // 댓글 옵션이 없을 경우 'N' 셋팅
    String newCommentOption = comment_option == null ? String.valueOf('N') : String.valueOf('Y');
    // 회원의 PK 셋팅
    HttpSession session = req.getSession();
    Long userId = ((MemberVO) session.getAttribute("loginMember")).getId();

    RegisterVO register = new RegisterVO(id, title, content,prepend, newPublicOption, newCommentOption, ctgry, userId);

    postService.modifyPostData(register);
    rd.addFlashAttribute("msg", "게시글이 수정되었습니다.");

    return "redirect:/post/detail/" + id;
  }

  // DELETE POST
  @GetMapping("delete/{ctgry}/{id}")
  public String deletePost(
          @PathVariable Long ctgry,
          @PathVariable Long id,
          RedirectAttributes rd) {

    // 좋아요 삭제
    postService.deleteLikedById(id);
    // 게시글 삭제
    commentService.deletePostToComment(id);
    // 댓글 삭제
    postService.deletePostById(id);
    rd.addFlashAttribute("msg", "게시글이 삭제되었습니다.");

    return "redirect:/post/list/" + ctgry;
  }

  // LIST VIEW PAGINATION
  @GetMapping("/list/{ctgry}/{amount}")
  @ResponseBody
  public List<HashMap<String, Object>> restPostListView(
          @PathVariable Long ctgry,
          @PathVariable Long amount ) {

    CriteriaVO criteria = new CriteriaVO(ctgry, amount);
    return postService.selectRestPostListByCtgryId(criteria);
  }

  // LIST VIEW SEARCH POST
  @GetMapping("/search/{ctgry}/{option}/{value}")
  @ResponseBody
  public List<HashMap<String, Object>> searchPostList(
          @PathVariable Long ctgry,
          @PathVariable String option,
          @PathVariable String value) {

    HashMap<String, Object> requestData = new HashMap<>();
    requestData.put("ctgry", ctgry);
    requestData.put("option", option);
    requestData.put("value", value);

    return  postService.searchPostList(requestData);
  }

  // MEMBER PROFILE PAGINATION
  @GetMapping("member/{id}/{amount}")
  @ResponseBody
  public List<HashMap<String, Object>> getPostListByMemberId(
          @PathVariable Long id,
          @PathVariable Long amount) {

    HashMap<String, Object> requestData = new HashMap<>();
    requestData.put("id", id);
    requestData.put("amount", amount);

    return postService.getPostListByMemberId(requestData);
  }


  // POST LIKE FUNC
  @GetMapping("like/{post}/{member}")
  @ResponseBody
  public void insertPostLike(
          @PathVariable Long post,
          @PathVariable Long member){

    HashMap<String, Object> like = new HashMap<>();
    like.put("post", post);
    like.put("member", member);
    postService.insertPostLike(like);
  }

  // POST UNLIKE FUNC
  @DeleteMapping("delete/{post}/{member}")
  @ResponseBody
  public void deletePostLike(
          @PathVariable Long post,
          @PathVariable Long member) {

    HashMap<String, Object> like = new HashMap<>();
    like.put("post", post);
    like.put("member", member);
    postService.deletePostLike(like);
  }
}
