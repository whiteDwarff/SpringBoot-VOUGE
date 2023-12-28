package hello.hellospring.post;

import hello.hellospring.post.domain.CriteriaVO;
import hello.hellospring.post.domain.RegisterVO;
import hello.hellospring.post.mapper.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
@Transactional
public class PostTest {
  @Autowired
  PostMapper mapper;

  @Test
  public void list() {
    List<HashMap<String, Object>> result = mapper.selectPostList(9L);

    for (HashMap<String, Object> row : result) {
      for (Map.Entry<String, Object> entry : row.entrySet()) {
        String key = entry.getKey();
        Object value = entry.getValue();
        System.out.println("Key: " + key + ", Value: " + value);
      }
      System.out.println("------------------------");
    }
    }

    @Test
    public void getCtgryName() {
      String name = mapper.selectCtgryNameById(3L);
      log.info(name);
    }

    @Test
    public void modifyGetData() {
      HashMap<String, Object> regster = mapper.modifyGetData(18L);
      log.info(regster.toString());
    }

    @Test
    public void modifyPostData() {
      RegisterVO register = new RegisterVO();
      register.setCtgry(7L);
      register.setPrepend("공지");
      register.setTitle("발매!!");
      register.setContent("진짜?");
      register.setPublic_option(null);
      register.setComment_option("Y");
      register.setWriter(1L);

      mapper.modifyPostData(register);
      log.info(register.toString());

    }

    @Test
    public void restPostList() {
      Long id = 6L;
      Long amount= 0L;
        CriteriaVO ct = new CriteriaVO(id, amount);
        log.info("##############################");
        log.info(ct.toString());
        log.info("##############################");
        List<HashMap<String, Object>>  lists = mapper.selectRestPostListByCtgryId(ct);
        for(HashMap<String, Object> list : lists) {
          log.info(list.toString());
        }
    }
    @Test
    public void countByCtgryId() {
      Long result = mapper.countByCtgryId(6L);
      log.info(String.valueOf(result));
    }

//    @Test
//    public void searchPostList() {
//      HashMap<String, Object> option = new HashMap<>();
//      option.put("option", "p.title");
//      option.put("value", "휠라");
//      option.put("ctgry", 6L);
//      List<HashMap<String, Object>> results = mapper.searchPostList(option);
//
//        for(HashMap<String, Object> result : results)
//          log.info(result.values().toString());
//    }
//
//    @Test
//    public void searchPostListCount() {
//      HashMap<String, Object> option = new HashMap<>();
//      option.put("option", "p.title");
//      option.put("value", "휠라");
//      option.put("ctgry", 6L);
//
//      Long result = mapper.searchPostListCount(option);
//      log.info(result.toString());
//    }



  }

