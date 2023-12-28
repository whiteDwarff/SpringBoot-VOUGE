package hello.hellospring.comment.repository;

import hello.hellospring.comment.domain.CommentRegisterVO;
import hello.hellospring.comment.domain.CommentRequestVO;
import hello.hellospring.comment.domain.CommentVO;
import hello.hellospring.comment.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository{

  private final CommentMapper commentMapper;

  @Autowired
  public CommentRepositoryImpl(CommentMapper commentMapper) {
    this.commentMapper = commentMapper;
  }

  @Override
  public void insertCommentByPostId(CommentRegisterVO comment) {
    commentMapper.insertCommentByPostId(comment);
  }

  @Override
  public List<HashMap<String, Object>> selectCommentByPostId(CommentRequestVO comment) {
    return commentMapper.selectCommentByPostId(comment);
  }

  @Override
  public void commentUpdateById(CommentVO comment) {
    commentMapper.commentUpdateById(comment);
  }

  @Override
  public void commentDeleteById(Long id) {
    commentMapper.commentDeleteById(id);
  }

  @Override
  public List<HashMap<String, Object>> getCommentListByMemberId(HashMap<String, Object> requestData) {
    return commentMapper.getCommentListByMemberId(requestData);
  }

  @Override
  public void deletePostToComment(Long id) {
    commentMapper.deletePostToComment(id);
  }
}
