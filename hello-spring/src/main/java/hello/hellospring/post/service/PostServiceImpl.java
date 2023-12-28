package hello.hellospring.post.service;

import hello.hellospring.post.domain.CriteriaVO;
import hello.hellospring.post.domain.RegisterVO;
import hello.hellospring.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  @Autowired
  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public void insertPost(RegisterVO register) {
    postRepository.insertPost(register);
  }

  @Override
  public HashMap<String, Object> selectPostById(Long id) {
    return postRepository.selectPostById(id);
  }

  @Override
  public List<HashMap<String, Object>> selectPostList(Long id) {
    return postRepository.selectPostList(id);
  }

  @Override
  public String selectCtgryNameById(Long id) {
    return postRepository.selectCtgryNameById(id);
  }

  @Override
  public void checkCookies(HttpServletRequest req, HttpServletResponse resp, Long id) {
    Cookie[] cookies = req.getCookies();

    if (cookies == null) addPostCookie(resp, id);
    else {
      for (Cookie cookie : cookies)
        if (cookie.getName().equals("PostCookie" + id)) return;
      addPostCookie(resp, id);
    }
  }

  @Override
  public void addPostCookie(HttpServletResponse resp, Long id) {
    postRepository.updateHitById(id);
    Cookie cookie = new Cookie("PostCookie" + id, String.valueOf(id));
    cookie.setMaxAge(24 * 60 * 60);
    resp.addCookie(cookie);
  }

  @Override
  public HashMap<String, Object> modifyGetData(Long id) {
    return postRepository.modifyGetData(id);
  }

  @Override
  public void modifyPostData(RegisterVO register) {
    postRepository.modifyPostData(register);
  }

  @Override
  public void deletePostById(Long postId) {
    postRepository.deletePostById(postId);
  }

  @Override
  public int selectCommentCountByPostId(Long id) {
    return postRepository.selectCommentCountByPostId(id);
  }

  @Override
  public List<HashMap<String, Object>> selectRestPostListByCtgryId(CriteriaVO criteria) {
    return postRepository.selectRestPostListByCtgryId(criteria);
  }

  @Override
  public Long countByCtgryId(Long id) {
    return postRepository.countByCtgryId(id);
  }

  @Override
  public List<HashMap<String, Object>> searchPostList(HashMap<String, Object> requestData) {
    return postRepository.searchPostList(requestData);
  }

  @Override
  public Long searchPostListCount(HashMap<String, Object> requestData) {
    return postRepository.searchPostListCount(requestData);
  }

  @Override
  public List<HashMap<String, Object>> getPostListByMemberId(HashMap<String, Object> requestData) {
    return postRepository.getPostListByMemberId(requestData);
  }

  @Override
  public Boolean searchLikeByPostId(HashMap<String, Object> info) {
    return postRepository.searchLikeByPostId(info);
  }

  @Override
  public void insertPostLike(HashMap<String, Object> liked) {
    postRepository.insertPostLike(liked);
  }

  @Override
  public void deletePostLike(HashMap<String, Object> liked) {
    postRepository.deletePostLike(liked);
  }

  @Override
  public List<HashMap<String, Object>> indexPostList(int id) {
    return postRepository.indexPostList(id);
  }

  @Override
  public void deleteLikedById(Long id) {
    postRepository.deleteLikedById(id);
  }


}
