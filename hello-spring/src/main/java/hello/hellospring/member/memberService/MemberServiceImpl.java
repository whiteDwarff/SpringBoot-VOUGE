package hello.hellospring.member.memberService;


import hello.hellospring.member.memberRepository.MemberRepository;
import hello.hellospring.member.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;

  }

  // 회원 가입 시 중복 이메일과 닉네임 여부 반환
  @Override
  public Boolean duplicateMemberCheck(MemberVO member) {
    if (memberRepository.duplicateMemberCheck(member) == 1) {
      return false;
    }
    memberRepository.joinMember(member);
    return true;
  }

  // 회원 정보 수정
  @Override
  public void updateMemberInfo(MemberVO member) {
    memberRepository.updateSuccessful(member);
  }

  // PK로 조회하여 회원 한명의 모든 정보 반환
  @Override
  public MemberVO getUserInfo(Long id) {
    return memberRepository.getUserInfo(id);
  }

  // 아이디와 비밀번호가 일치할 경우  로그인한 회원 정보 반환
  @Override
  public MemberVO loginMember(LoginVO loginInfo) {
    return memberRepository.loginMember(loginInfo);
  }

  // 로그인 정보 확인
  @Override
  public int checkIfLoginIsSuccessful(LoginVO loginInfo) {
    // 가입된 회원 정보가 있을 경우
    if (memberRepository.checkLoginInfo(loginInfo) == 1)
      // 아이디와 비밀번호가 모두 일치하면 1 반환, 비밀번호가 일치하지 않으면 2 반환
      return memberRepository.checkMemberInfo(loginInfo) == 1 ? 1 : 2;
    // 가입된 이메일이 없는 경우
    return 3;
  }

  // --------------------------------------------------------
  @Override
  public HashMap<String, Object> selectMemberInfoById(Long id) {
    return memberRepository.selectMemberInfoById(id);
  }
  // ------------------ Cookie 확인 후 방문 횟수 제어 ------------------
  @Override
  public Boolean checkCookies(HttpServletRequest req, HttpServletResponse resp, MemberVO member) {
    Cookie[] cookies = req.getCookies();
    if (cookies == null) {
      addMemberCookie(resp, member);
      return true;
    }
    else {
      for (Cookie cookie : cookies)
        if (cookie.getName().equals("memberCookie" + member.getId())) return false;
      addMemberCookie(resp, member);
      return true;
    }
  }

  public void addMemberCookie(HttpServletResponse resp, MemberVO member) {
    memberRepository.addVisitedDate(member);
    Cookie cookie = new Cookie("memberCookie" + member.getId(), String.valueOf(member.getId()));
    cookie.setMaxAge(24 * 60 * 60);
    resp.addCookie(cookie);
  }
}
