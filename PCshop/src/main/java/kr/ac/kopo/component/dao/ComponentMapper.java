package kr.ac.kopo.component.dao;

import java.util.List;
import kr.ac.kopo.product.vo.ComponentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ComponentMapper {
	
	List<ComponentVO> getAllComponents();

    // 카테고리별 부품 조회
    List<ComponentVO> getComponentsByCategory(String category);

    // 특정 부품 조회
    ComponentVO getComponentById(int itemCd);

    // 부품 삽입
    int insertComponent(ComponentVO component);

    // 부품 수정
    int updateComponent(ComponentVO component);

    // 부품 삭제
    int deleteComponent(int itemCd);
    
    // CPU의 소켓 정보를 가져오기
    String getCpuSocketByItemId(@Param("itemCd") int itemCd);

    // 메인보드의 소켓 정보를 가져오기
    String getMainboardSocketByItemId(@Param("itemCd") int itemCd);
}
