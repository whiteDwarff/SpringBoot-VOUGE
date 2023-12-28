package hello.hellospring.member.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberVO {
  private Long id;
  private String email;
  private String password;
  private String nickname;
  private String profile;
  private String grade;
  private Date createdAt;
  private Long visitedDate;
  private Long commentCount;
  private Long postCount;
}
