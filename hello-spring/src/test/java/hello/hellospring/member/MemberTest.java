package hello.hellospring.member;


import hello.hellospring.member.memberRepository.MemberRepository;
import hello.hellospring.member.memberService.MemberService;
import hello.hellospring.member.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@Slf4j
@SpringBootTest
public class MemberTest {

  @Autowired
  MemberService memberService;
  @Autowired
  MemberRepository memberRepository;

  @Test
  @DisplayName("회원가입 중복 검사")
  void joinTest() {
    MemberVO member = new MemberVO();

    member.setEmail("test1");
    member.setPassword("test1");
    member.setNickname("test1");

    boolean result = memberService.duplicateMemberCheck(member);
    assertThat(result).isEqualTo(true);
  }

  @Test
  @DisplayName("회원 정보 조회")
  void loginTest() {
    MemberVO member = memberRepository.getUserInfo(10L);
    System.out.println(member.toString());
  }

  @Test
  @DisplayName("로그인 정보 확인")
  void checkLoginInfo() {
    LoginVO loginInfo = new LoginVO();
    loginInfo.setEmail("moneycoludy@naver.com");
    loginInfo.setPassword("Zkfps135!");

    int result = memberService.checkIfLoginIsSuccessful(loginInfo);
    System.out.println(result);
    assertThat(result).isEqualTo(1);
  }

  @Test
  void test() {
//    "moneycoludy@naver.com", "SHA2(Zkfps135!, 256)"
    LoginVO loginInfo = new LoginVO( "moneycoludy@naver.com", "SHA2(Zkfps1355!, 256)");

    int i = memberService.checkIfLoginIsSuccessful(loginInfo);
    log.info(String.valueOf(i));
    assertThat(i).isEqualTo(1);

  }

}
