package hello.hellospring.post.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterVO {

  private Long id;
  private String title;
  private String content;
  private String prepend;
  private String public_option;
  private String comment_option;
  private Long ctgry;
  private Long writer;


}
