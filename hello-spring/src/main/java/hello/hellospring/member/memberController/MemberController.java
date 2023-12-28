package hello.hellospring.member.memberController;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.hellospring.member.domain.*;
import hello.hellospring.member.memberService.MemberService;
import hello.hellospring.member.memberService.S3UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {

  private final MemberService memberService;

  private final S3UploadService s3UploadService;


  @Autowired
  public MemberController(MemberService memberService, S3UploadService s3UploadService) {
    this.memberService = memberService;
    this.s3UploadService = s3UploadService;
  }


  // JOIN REQUEST
  @PostMapping("join")
  public String join(
          @RequestParam String email,
          @RequestParam String password,
          @RequestParam String nickname,
          RedirectAttributes rd) {

    MemberVO member = new MemberVO();
    member.setEmail(email);
    member.setPassword(password);
    member.setNickname(nickname);

    Boolean result = memberService.duplicateMemberCheck(member);

    if (result) {
      rd.addFlashAttribute("msg", "회원가입이 완료되었습니다.");
    } else {
      rd.addFlashAttribute("msg", "중복된 이메일 및 닉네임이 존재합니다.");
    }
    return "redirect:/member/";
  }
  // RETURN LOGIN VIEW
  @GetMapping
  public String login(Model model, LoginVO loginInfo) {
    model.addAttribute("loginInfo", loginInfo);
    return "/member/login";
  }

  // LOGIN REQUEST
  @PostMapping("login")
  public String login(
          @RequestParam String email,
          @RequestParam String password,
          HttpServletRequest req,
          HttpServletResponse resp,
          RedirectAttributes rd) {

    LoginVO loginInfo = new LoginVO(email, password);
    int result = memberService.checkIfLoginIsSuccessful(loginInfo);

    if(result == 1) {
      MemberVO member = memberService.loginMember(loginInfo);

      // Cookie 정보 조회 후 방문횟수 제어
      Boolean isCookieState = memberService.checkCookies(req, resp, member);
      if(isCookieState) member.setId(member.getId()+1);

      log.info(member.toString());

      // 세션 객체 생성
      HttpSession session = req.getSession();
      // 세션 객체에 member 객체 저장
      session.setAttribute("loginMember", member);

      return "redirect:/";
    } else if(result == 2) {
      rd.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
    } else if(result == 3){
      rd.addFlashAttribute("msg", "가입된 회원정보가 존재하지 않습니다.");
    }
    return "redirect:/member/";
  }

  // RETURN MEMBER INFO VIEw
  @GetMapping("info")
  public String profile() {
    return "/member/info";
  }

  // UPDATE MEMBER INFO REQUEST
  @PostMapping("info")
  public String updateProfile(
          @RequestParam("file") MultipartFile file,
          @RequestParam String nickname,
          @RequestParam String password,
          HttpSession httpSession,
          RedirectAttributes rd,
          HttpServletRequest request) {

    try {
      MemberVO orgMemberInfo = (MemberVO) httpSession.getAttribute("loginMember");
      MemberVO changeMemberInfo = memberService.getUserInfo(orgMemberInfo.getId());
      if(file != null) {
        String profile = s3UploadService.saveFile(file);
        changeMemberInfo.setProfile(profile);
      }
      changeMemberInfo.setNickname(nickname);
      changeMemberInfo.setPassword(password);

      memberService.updateMemberInfo(changeMemberInfo);


      HttpSession session = request.getSession();
      session.setAttribute("loginMember", memberService.loginMember(new LoginVO(changeMemberInfo.getEmail(), changeMemberInfo.getPassword())));
      rd.addFlashAttribute("result", "회원정보 수정이 완료되었습니다.");

    } catch (IOException e) {
      rd.addFlashAttribute("result", "회원정보 수정에 실패하였습니다.");
    }
    return "redirect:/member/info";
  }

  // LOGOUT
  @GetMapping("/logout")
  public String logout(
          HttpSession session,
          RedirectAttributes rd) {
    session.invalidate();
    rd.addFlashAttribute("msg", "로그아웃 되었습니다.");
    return "redirect:/";
  }

  // RETURN MEMBER PROFILE VIEW
  @GetMapping("profile/{id}")
  public String memberProfile(
          @PathVariable Long id,
          Model model) {

    HashMap<String, Object> memberInfo = memberService.selectMemberInfoById(id);
    model.addAttribute("memberInfo", memberInfo);

    log.info(memberInfo.toString());

    return "/member/profile";
  }
}
