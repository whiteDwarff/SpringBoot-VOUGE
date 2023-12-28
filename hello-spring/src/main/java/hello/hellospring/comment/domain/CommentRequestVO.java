package hello.hellospring.comment.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRequestVO {

  private Long id;
  private String sortOption;
  private Long requestCount;

}
