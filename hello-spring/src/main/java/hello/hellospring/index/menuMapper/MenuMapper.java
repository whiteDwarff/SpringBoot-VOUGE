package hello.hellospring.index.menuMapper;

import hello.hellospring.index.domain.MainCtgryVO;
import hello.hellospring.index.domain.SubCtgryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

  List<MainCtgryVO> getMainCtgry();

  List<SubCtgryVO> getSubCtgry();
}
