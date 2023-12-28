package hello.hellospring.index.service;

import hello.hellospring.index.domain.MainCtgryVO;
import hello.hellospring.index.domain.SubCtgryVO;

import java.util.List;

public interface MenuService {

  List<MainCtgryVO> setMainCategory();

  List<SubCtgryVO> setSubCategory();

}
