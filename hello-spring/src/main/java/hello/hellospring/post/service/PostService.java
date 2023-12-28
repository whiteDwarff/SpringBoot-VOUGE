package hello.hellospring.post.service;

import hello.hellospring.post.domain.CriteriaVO;
import hello.hellospring.post.domain.RegisterVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public interface PostService {
  void insertPost(RegisterVO register);

  HashMap<String, Object> selectPostById(Long id);

  List<HashMap<String, Object>> selectPostList(Long id);

  String selectCtgryNameById(Long id);

  void checkCookies(HttpServletRequest req, HttpServletResponse resp, Long id);

  void addPostCookie(HttpServletResponse resp, Long id);

  HashMap<String, Object> modifyGetData(Long id);

  void modifyPostData(RegisterVO register);

  void deletePostById(Long postId);

  int selectCommentCountByPostId(Long id);

  List<HashMap<String, Object>> selectRestPostListByCtgryId(CriteriaVO criteria);

  Long countByCtgryId(Long id);

  List<HashMap<String, Object>> searchPostList (HashMap<String, Object> requestData);

  Long searchPostListCount(HashMap<String, Object> requestData);

  List<HashMap<String, Object>> getPostListByMemberId(HashMap<String, Object> requestData);

  Boolean searchLikeByPostId(HashMap<String, Object> info);

  void insertPostLike(HashMap<String, Object> liked);

  void deletePostLike(HashMap<String, Object> liked);

  List<HashMap<String, Object>> indexPostList (int id);

  void deleteLikedById(Long id);


}
