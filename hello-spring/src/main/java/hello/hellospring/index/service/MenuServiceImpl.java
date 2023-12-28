package hello.hellospring.index.service;

import hello.hellospring.index.domain.MainCtgryVO;
import hello.hellospring.index.domain.SubCtgryVO;
import hello.hellospring.index.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements  MenuService{

  private final MenuRepository menuRepository;


  @Autowired
  public MenuServiceImpl( MenuRepository menuRepository) {
    this.menuRepository = menuRepository;
  }

  @Override
  public List<MainCtgryVO> setMainCategory() {
    return menuRepository.getMainCtgry();
  }

  @Override
  public List<SubCtgryVO> setSubCategory() {
    return menuRepository.getSubCtgry();
  }

}
