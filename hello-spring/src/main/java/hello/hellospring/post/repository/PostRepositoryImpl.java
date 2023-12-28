package hello.hellospring.post.repository;

import hello.hellospring.post.domain.CriteriaVO;
import hello.hellospring.post.domain.RegisterVO;
import hello.hellospring.post.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {

  private final PostMapper postMapper;

  @Autowired
  public PostRepositoryImpl(PostMapper postMapper) {
    this.postMapper = postMapper;
  }

  @Override
  public void insertPost(RegisterVO register) {
    postMapper.insertPost(register);
  }

  @Override
  public HashMap<String, Object> selectPostById(Long id) {
    return postMapper.selectPostById(id);
  }

  @Override
  public List<HashMap<String, Object>> selectPostList(Long id) {
    return postMapper.selectPostList(id);
  }

  @Override
  public String selectCtgryNameById(Long id) {
    return postMapper.selectCtgryNameById(id);
  }

  @Override
  public void updateHitById(Long id) {
    postMapper.updateHitById(id);
  }

  @Override
  public HashMap<String, Object> modifyGetData(Long id) {
    return postMapper.modifyGetData(id);
  }

  @Override
  public void modifyPostData(RegisterVO register) {
    postMapper.modifyPostData(register);
  }

  @Override
  public void deletePostById(Long postId) {
    postMapper.deletePostById(postId);
  }

  @Override
  public int selectCommentCountByPostId(Long id) {
    return postMapper.selectCommentCountByPostId(id);
  }

  @Override
  public List<HashMap<String, Object>> selectRestPostListByCtgryId(CriteriaVO criteria) {
    return postMapper.selectRestPostListByCtgryId(criteria);
  }

  @Override
  public Long countByCtgryId(Long id){
    return postMapper.countByCtgryId(id);
  }

  @Override
  public List<HashMap<String, Object>> searchPostList(HashMap<String, Object> requestData) {
    return postMapper.searchPostList(requestData);
  }

  @Override
  public Long searchPostListCount(HashMap<String, Object> requestData) {
    return postMapper.searchPostListCount(requestData);
  }

  @Override
  public List<HashMap<String, Object>> getPostListByMemberId(HashMap<String, Object> requestData) {
    return postMapper.getPostListByMemberId(requestData);
  }

  @Override
  public Boolean searchLikeByPostId(HashMap<String, Object> info) {
    return postMapper.searchLikeByPostId(info) > 0;
  }

  @Override
  public void insertPostLike(HashMap<String, Object> liked) {
    postMapper.insertPostLike(liked);
  }

  @Override
  public void deletePostLike(HashMap<String, Object> liked) {
    postMapper.deletePostLike(liked);
  }

  @Override
  public List<HashMap<String, Object>> indexPostList(int id) {
    return postMapper.indexPostList(id);
  }

  @Override
  public void deleteLikedById(Long id) {
    postMapper.deleteLikedById(id);
  }
}
