package hello.hellospring.post.mapper;

import hello.hellospring.post.domain.CriteriaVO;
import hello.hellospring.post.domain.RegisterVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PostMapper {
  void insertPost(RegisterVO register);

  HashMap<String, Object> selectPostById(Long id);

  List<HashMap<String, Object>> selectPostList(Long id);

  String selectCtgryNameById(Long id);

  void updateHitById(Long id);

  HashMap<String, Object> modifyGetData(Long id);

  void modifyPostData(RegisterVO register);

  void deletePostById(Long id);

  int selectCommentCountByPostId(Long id);

  List<HashMap<String, Object>> selectRestPostListByCtgryId(CriteriaVO criteria);

  Long countByCtgryId(Long id);

  List<HashMap<String, Object>> searchPostList(HashMap<String, Object> requestData);

  Long searchPostListCount(HashMap<String, Object> requestData);

  List<HashMap<String, Object>> getPostListByMemberId(HashMap<String, Object> requestData);

  Long searchLikeByPostId(HashMap<String, Object> info);

  void insertPostLike(HashMap<String, Object> liked);

  void deletePostLike(HashMap<String, Object> liked);

  List<HashMap<String, Object>> indexPostList (int id);

  void deleteLikedById(Long id);
}
