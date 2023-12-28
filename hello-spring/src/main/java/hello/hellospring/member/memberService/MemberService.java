package hello.hellospring.member.memberService;


import hello.hellospring.member.domain.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public interface MemberService {

  MemberVO getUserInfo(Long id);

  // 중복 회원 검사
  Boolean duplicateMemberCheck(MemberVO member);

  // 회원 정보 수정
  void updateMemberInfo(MemberVO member);

  // 로그인 검사
  MemberVO loginMember(LoginVO loginInfo);

  // 로그인 성공 여부
  int checkIfLoginIsSuccessful(LoginVO loginInfo);

  // Cookie 확인 후 방문횟수 제어
  Boolean checkCookies(HttpServletRequest req, HttpServletResponse res, MemberVO member);

  HashMap<String, Object> selectMemberInfoById(Long id);
}