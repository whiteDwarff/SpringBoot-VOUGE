package hello.hellospring.comment.controller;


import hello.hellospring.comment.domain.CommentRegisterVO;
import hello.hellospring.comment.domain.CommentRequestVO;
import hello.hellospring.comment.domain.CommentVO;
import hello.hellospring.comment.service.CommentService;
import hello.hellospring.member.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/comment/*")
public class CommentController {

  private final CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }
  // 댓글 등록
  @PostMapping("insert/{id}")
  public String insertComment(
          @PathVariable Long id,
          CommentRegisterVO comment,
          HttpServletRequest req,
          RedirectAttributes rd) {

    HttpSession session = req.getSession();
    comment.setPost(id);
    comment.setWriter(((MemberVO) session.getAttribute("loginMember")).getId());

    log.info("######################");
    log.info(((MemberVO) session.getAttribute("loginMember")).getId().toString());
    log.info("######################");

    commentService.insertCommentByPostId(comment);

    rd.addFlashAttribute("msg", "댓글이 등록되었습니다.");

    return "redirect:/post/detail/" + id;
  }

  @GetMapping("member/{id}/{amount}")
  @ResponseBody
  public List<HashMap<String, Object>> getCommentListByMemberId(
          @PathVariable Long id,
          @PathVariable Long amount) {
    HashMap<String, Object> requestData = new HashMap<>();
    requestData.put("id", id);
    requestData.put("amount", amount);

    List<HashMap<String, Object>> aa = commentService.getCommentListByMemberId(requestData);



    return commentService.getCommentListByMemberId(requestData);
  }

  @GetMapping("getComment/{id}/{sortOption}/{requestCount}")
  @ResponseBody
  public List<HashMap<String, Object>> selectCommentByPostId(
          @PathVariable Long id,
          @PathVariable String sortOption,
          @PathVariable Long requestCount) {

    CommentRequestVO comment = new CommentRequestVO(id, sortOption, requestCount);
    List<HashMap<String, Object>> aa = commentService.selectCommentByPostId(comment);
    return commentService.selectCommentByPostId(comment);
  }

  @PatchMapping("/update/{id}")
  @ResponseBody
  public void updateComment(@PathVariable Long id, @RequestBody CommentVO comment) {
    CommentVO newComment = new CommentVO();
    newComment.setId(id);
    newComment.setContent(comment.getContent());

    commentService.commentUpdateById(newComment);
  }

  @DeleteMapping("/delete/{id}")
  @ResponseBody
  public void deleteComment(@PathVariable Long id) {
    commentService.commentDeleteById(id);
  }

}
