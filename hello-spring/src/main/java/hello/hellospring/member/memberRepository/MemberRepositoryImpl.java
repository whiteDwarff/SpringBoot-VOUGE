package hello.hellospring.member.memberRepository;


import hello.hellospring.member.memberMapper.MemberMapper;
import hello.hellospring.member.domain.MemberVO;
import hello.hellospring.member.domain.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

  private final MemberMapper memberMapper;

  @Autowired
  public MemberRepositoryImpl(MemberMapper memberMapper) {
    this.memberMapper = memberMapper;
  }

  @Override
  public MemberVO getUserInfo(Long id) {
    return memberMapper.getUserInfo(id);
  }

  // 회원 가입
  @Override
  public void joinMember(MemberVO member) {
    memberMapper.joinMember(member);
  }

  // 중복 회원 체크
  @Override
  public int duplicateMemberCheck(MemberVO member) {
    return memberMapper.duplicateMemberCheck(member);
  }
  // 방문 횟수 추가
  @Override
  public void addVisitedDate(MemberVO member) {
    memberMapper.visitedUpdate(member);
  }

  // 회원 정보 수정
  @Override
  public void updateSuccessful(MemberVO member) {
    memberMapper.updateInfo(member);
  }

  // 이메일을 통해 회원 여부 확인
  @Override
  public int checkLoginInfo(LoginVO loginInfo) {
    return memberMapper.checkEmailInfo(loginInfo);
  }
  // 이메일과 비밀번호를 통해 로그인 정보 확인
  public int checkMemberInfo(LoginVO loginInfo) {
    return memberMapper.checkMemberInfo(loginInfo);
  }

  @Override
  public HashMap<String, Object> selectMemberInfoById(Long id) {
    return memberMapper.selectMemberInfoById(id);
  }

  // 이메일과 비밀번호가 일치하다면 회원의 모든 정보 반환
  @Override
  public MemberVO loginMember(LoginVO loginInfo) {
    return memberMapper.loginMemberInfo(loginInfo);
  }

}
