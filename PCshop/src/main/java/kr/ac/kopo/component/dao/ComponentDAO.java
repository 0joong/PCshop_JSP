package kr.ac.kopo.component.dao;

import java.util.List;

import kr.ac.kopo.component.vo.CPUInfoVO;
import kr.ac.kopo.component.vo.MainboardInfoVO;
import kr.ac.kopo.product.vo.ComponentVO;

public interface ComponentDAO {
	
    List<ComponentVO> getComponentsByCategory(String category); // 카테고리별 조회
    
    ComponentVO getComponentById(int itemCd); // ID로 조회
    
    int insertComponent(ComponentVO component); // 데이터 삽입 (성공 여부 반환)
    
    boolean updateComponent(ComponentVO component); // 데이터 수정 (성공 여부 반환)
    
    boolean deleteComponent(int itemCd); // 데이터 삭제 (성공 여부 반환)
    
    List<ComponentVO> getAllComponents();
    
    String getSocketByComponentId(int itemCd, String type);
    
    // CPU 정보 추가
    void addCPUInfo(CPUInfoVO cpuInfo);

    // Mainboard 정보 추가
    void addMainboardInfo(MainboardInfoVO mainboardInfo);

}
