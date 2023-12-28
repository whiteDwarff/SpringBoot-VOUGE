package hello.hellospring.comment.service;

import hello.hellospring.comment.domain.CommentRegisterVO;
import hello.hellospring.comment.domain.CommentRequestVO;
import hello.hellospring.comment.domain.CommentVO;
import hello.hellospring.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

  private final CommentRepository commentRepository;

  @Autowired
  public CommentServiceImpl(CommentRepository commentRepository){
    this.commentRepository = commentRepository;
  }

  @Override
  public void insertCommentByPostId(CommentRegisterVO comment) {
    commentRepository.insertCommentByPostId(comment);
  }

  @Override
  public List<HashMap<String, Object>> selectCommentByPostId(CommentRequestVO comment) {
    return commentRepository.selectCommentByPostId(comment);
  }

  @Override
  public void commentUpdateById(CommentVO comment) {
    commentRepository.commentUpdateById(comment);
  }

  @Override
  public void commentDeleteById(Long id) {
    commentRepository.commentDeleteById(id);
  }

  @Override
  public List<HashMap<String, Object>> getCommentListByMemberId(HashMap<String, Object> requestData) {
    return commentRepository.getCommentListByMemberId(requestData);
  }

  @Override
  public void deletePostToComment(Long id) {
    commentRepository.deletePostToComment(id);
  }
}
