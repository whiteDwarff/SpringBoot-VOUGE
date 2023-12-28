package hello.hellospring.index.repository;

import hello.hellospring.index.domain.MainCtgryVO;
import hello.hellospring.index.domain.SubCtgryVO;
import hello.hellospring.index.menuMapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepositoryImpl implements MenuRepository{

  private final MenuMapper menuMapper;

  @Autowired
  public MenuRepositoryImpl(MenuMapper menuMapper) {
    this.menuMapper = menuMapper;
  }

  @Override
  public List<MainCtgryVO> getMainCtgry() {
    return menuMapper.getMainCtgry();
  }

  @Override
  public List<SubCtgryVO> getSubCtgry() {
    return menuMapper.getSubCtgry();
  }

}
