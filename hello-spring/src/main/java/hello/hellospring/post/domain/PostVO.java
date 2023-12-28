package hello.hellospring.post.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostVO {

  private Long id;
  private String title;
  private String content;
  private String prepend;
  private Long liked;
  private Long hit;
  private Date created_at;
  private String public_option;
  private String comment_option;
  private Long ctgry;
  private Long writer;
}
