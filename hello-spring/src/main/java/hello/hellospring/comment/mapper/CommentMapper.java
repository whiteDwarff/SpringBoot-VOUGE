package hello.hellospring.comment.mapper;


import hello.hellospring.comment.domain.CommentRegisterVO;
import hello.hellospring.comment.domain.CommentRequestVO;
import hello.hellospring.comment.domain.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CommentMapper {
  void insertCommentByPostId(CommentRegisterVO comment);

  List<HashMap<String, Object>> selectCommentByPostId(CommentRequestVO comment);

  void commentUpdateById(CommentVO comment);

  void commentDeleteById(Long id);

  List<HashMap<String , Object>> getCommentListByMemberId(HashMap<String, Object> requestData);

  void deletePostToComment(Long id);
}
