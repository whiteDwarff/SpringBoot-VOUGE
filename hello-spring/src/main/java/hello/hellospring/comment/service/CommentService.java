package hello.hellospring.comment.service;

import hello.hellospring.comment.domain.CommentRegisterVO;
import hello.hellospring.comment.domain.CommentRequestVO;
import hello.hellospring.comment.domain.CommentVO;

import java.util.HashMap;
import java.util.List;

public interface CommentService {

  void insertCommentByPostId(CommentRegisterVO comment);

  List<HashMap<String, Object>> selectCommentByPostId(CommentRequestVO comment);

  void commentUpdateById(CommentVO comment);

  void commentDeleteById(Long id);

  List<HashMap<String , Object>> getCommentListByMemberId(HashMap<String, Object> requestData);

  void deletePostToComment(Long id);
}
