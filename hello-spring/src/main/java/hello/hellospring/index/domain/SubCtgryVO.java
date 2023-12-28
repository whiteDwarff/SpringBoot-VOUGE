package hello.hellospring.index.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubCtgryVO {
  private Long id;
  private String name;
  private Long parent;
}
