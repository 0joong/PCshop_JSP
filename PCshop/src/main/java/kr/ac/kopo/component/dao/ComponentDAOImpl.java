package kr.ac.kopo.component.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.ac.kopo.component.vo.CPUInfoVO;
import kr.ac.kopo.component.vo.MainboardInfoVO;
import kr.ac.kopo.mybatis.MyConfig;
import kr.ac.kopo.product.vo.ComponentVO;

public class ComponentDAOImpl implements ComponentDAO {
	private final ComponentMapper componentMapper;
	private final SqlSession sqlSession;

	public ComponentDAOImpl() {
		sqlSession = new MyConfig().getInstance();
		this.componentMapper = sqlSession.getMapper(ComponentMapper.class); // 매퍼 주입
	}

	@Override
	public List<ComponentVO> getComponentsByCategory(String category) {
		sqlSession.clearCache();
		return componentMapper.getComponentsByCategory(category);
	}

	@Override
	public ComponentVO getComponentById(int itemCd) {
		sqlSession.clearCache();
		return componentMapper.getComponentById(itemCd);
	}

	@Override
	public int insertComponent(ComponentVO component) {
		//int result = componentMapper.insertComponent(component);
		//return result > 0; // 삽입 성공 여부 반환
		sqlSession.clearCache();
		return componentMapper.insertComponent(component);
	}

	@Override
	public boolean updateComponent(ComponentVO component) {
		sqlSession.clearCache();
		int result = componentMapper.updateComponent(component);
		return result > 0; // 수정 성공 여부 반환
	}

	@Override
	public boolean deleteComponent(int itemCd) {
		sqlSession.clearCache();
		int result = componentMapper.deleteComponent(itemCd);
		return result > 0; // 삭제 성공 여부 반환
	}

	@Override
	public List<ComponentVO> getAllComponents() {
		sqlSession.clearCache();
		return componentMapper.getAllComponents();
	}

	@Override
	public String getSocketByComponentId(int itemCd, String type) {
		sqlSession.clearCache();
		if ("cpu".equalsIgnoreCase(type)) {
			System.out.println("getSocketByComponentId : cpu");
			return componentMapper.getCpuSocketByItemId(itemCd);
		} else if ("mainboard".equalsIgnoreCase(type)) {
			System.out.println("getSocketByComponentId : mainboard");
			return componentMapper.getMainboardSocketByItemId(itemCd);
		} else {
			System.out.println("getSocketByComponentId : invalid type");
		}

		return null; // 유효하지 않은 타입의 경우 null 반환
	}

	@Override
	public void addCPUInfo(CPUInfoVO cpuInfo) {
		sqlSession.clearCache();
		sqlSession.insert("kr.ac.kopo.component.dao.ComponentMapper.addCPUInfo", cpuInfo);
	}

	@Override
	public void addMainboardInfo(MainboardInfoVO mainboardInfo) {
		sqlSession.clearCache();
		sqlSession.insert("kr.ac.kopo.component.dao.ComponentMapper.addMainboardInfo", mainboardInfo);
	}
}