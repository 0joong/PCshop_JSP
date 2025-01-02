package kr.ac.kopo.component.service;

import java.util.List;

import kr.ac.kopo.component.dao.ComponentDAO;
import kr.ac.kopo.component.vo.CPUInfoVO;
import kr.ac.kopo.component.vo.MainboardInfoVO;
import kr.ac.kopo.product.vo.ComponentVO;

public class ComponentService {
    private final ComponentDAO componentDAO;

    // Constructor Injection
    public ComponentService(ComponentDAO componentDAO) {
        this.componentDAO = componentDAO;
    }

    /**
     * 특정 카테고리의 컴포넌트 목록 가져오기
     * 
     * @param category - 컴포넌트 카테고리 (예: "cpu", "mainboard")
     * @return 카테고리에 해당하는 ComponentVO 리스트
     */
    public List<ComponentVO> getComponentsByCategory(String category) {
        return componentDAO.getComponentsByCategory(category);
    }

    /**
     * 특정 ID로 컴포넌트 조회
     * 
     * @param itemCd - 컴포넌트 ID
     * @return ComponentVO (조회된 컴포넌트)
     */
    public ComponentVO getComponentById(int itemCd) {
        return componentDAO.getComponentById(itemCd);
    }

    /**
     * 새 컴포넌트 추가
     * 
     * @param component - 추가할 컴포넌트 데이터
     * @return itemCd
     */
    public int addComponent(ComponentVO component) {
        return componentDAO.insertComponent(component);
    }

    /**
     * 기존 컴포넌트 수정
     * 
     * @param component - 수정할 컴포넌트 데이터
     * @return 수정 성공 여부 (true: 성공, false: 실패)
     */
    public boolean updateComponent(ComponentVO component) {
        return componentDAO.updateComponent(component);
    }

    /**
     * 컴포넌트 삭제
     * 
     * @param itemCd - 삭제할 컴포넌트 ID
     * @return 삭제 성공 여부 (true: 성공, false: 실패)
     */
    public boolean deleteComponent(int itemCd) {
        return componentDAO.deleteComponent(itemCd);
    }
    
    public List<ComponentVO> getAllComponents() {
        return componentDAO.getAllComponents();
    }
    
    public String getSocketByComponentId(int itemCd, String type) {
    	return componentDAO.getSocketByComponentId(itemCd, type);
    }
    
    public void addCPUInfo(CPUInfoVO cpuInfo) {
        componentDAO.addCPUInfo(cpuInfo);
    }

    public void addMainboardInfo(MainboardInfoVO mainboardInfo) {
        componentDAO.addMainboardInfo(mainboardInfo);
    }
}
