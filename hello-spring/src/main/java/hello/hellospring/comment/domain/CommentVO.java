package hello.hellospring.comment.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentVO {

  private Long id;
  private String content;
  private Date data;
  private Long writer;
  private Long post;
}
