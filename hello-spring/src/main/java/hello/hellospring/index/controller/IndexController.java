package hello.hellospring.index.controller;


import hello.hellospring.index.domain.MainCtgryVO;
import hello.hellospring.index.domain.SubCtgryVO;
import hello.hellospring.index.service.MenuService;
import hello.hellospring.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller

public class IndexController {

  private final MenuService menuService;

  private final PostService postService;

  @Autowired
  public IndexController(MenuService menuService, PostService postService) {
    this.menuService = menuService;
    this.postService = postService;
  }

  @GetMapping("/")
  public String index(
          HttpServletRequest req,
          Model model){
    // 애플리케이션 컨텍스트에 메뉴 리스트 저장
    javax.servlet.ServletContext application = req.getServletContext();

    List<MainCtgryVO> mainCategory = menuService.setMainCategory();
    List<SubCtgryVO> subCategory = menuService.setSubCategory();

    application.setAttribute("mainCtgrys", mainCategory);
    application.setAttribute("subCtgrys", subCategory);

    model.addAttribute("life", postService.indexPostList(2));
    model.addAttribute("notice", postService.indexPostList(1));
    model.addAttribute("fashion", postService.indexPostList(6));
    model.addAttribute("promotion", postService.indexPostList(2));

    return "/index";
  }

}
