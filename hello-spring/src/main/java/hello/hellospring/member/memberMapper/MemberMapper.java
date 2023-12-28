package hello.hellospring.member.memberMapper;


import hello.hellospring.member.domain.LoginVO;
import hello.hellospring.member.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface MemberMapper {

  MemberVO getUserInfo(Long id);

  // 회원가입
  void joinMember(MemberVO member);

  // 중복회원 검사
  int duplicateMemberCheck(MemberVO member);

  MemberVO loginMemberInfo(LoginVO loginInfo);

  // 방문 횟수 추가
  void visitedUpdate(MemberVO member);

  // 회원 정보 수정
  void updateInfo(MemberVO member);

  // 로그인 정보 구현 #######################
  int checkEmailInfo(LoginVO loginInfo);

  int checkMemberInfo(LoginVO loginInfo);

  HashMap<String, Object>  selectMemberInfoById(Long id);
}
