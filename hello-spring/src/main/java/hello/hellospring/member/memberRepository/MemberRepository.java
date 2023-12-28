package hello.hellospring.member.memberRepository;


import hello.hellospring.member.domain.MemberVO;
import hello.hellospring.member.domain.LoginVO;

import java.util.HashMap;

public interface MemberRepository {

  MemberVO getUserInfo(Long id);

  void joinMember(MemberVO member);

  int duplicateMemberCheck(MemberVO member);
  MemberVO loginMember(LoginVO loginInfo);

  void addVisitedDate(MemberVO member);

  void updateSuccessful(MemberVO member);

  int checkLoginInfo(LoginVO loginInfo);

  int checkMemberInfo(LoginVO loginInfo);

  HashMap<String, Object> selectMemberInfoById(Long id);
}
