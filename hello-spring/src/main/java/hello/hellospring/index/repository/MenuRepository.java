package hello.hellospring.index.repository;

import hello.hellospring.index.domain.MainCtgryVO;
import hello.hellospring.index.domain.SubCtgryVO;

import java.util.List;

public interface MenuRepository {
  List<MainCtgryVO> getMainCtgry();

  List<SubCtgryVO> getSubCtgry();
}
