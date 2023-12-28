package hello.hellospring.comment.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRegisterVO {

  private String content;
  private Long writer;
  private Long post;
}
