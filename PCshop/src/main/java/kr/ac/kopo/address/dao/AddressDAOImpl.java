package kr.ac.kopo.address.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.ac.kopo.address.vo.AddressVO;
import kr.ac.kopo.mybatis.MyConfig;

public class AddressDAOImpl implements AddressDAO {

    private SqlSession sqlSession;

    public AddressDAOImpl() {
        this.sqlSession = new MyConfig().getInstance();
    }

    @Override
    public List<AddressVO> selectAddressesByUserId(String userId) {
    	sqlSession.clearCache();
        return sqlSession.selectList("kr.ac.kopo.address.dao.AddressDAO.selectAddressesByUserId", userId);
    }

    @Override
    public boolean insertAddress(AddressVO address) {
    	sqlSession.clearCache();
    	System.out.println(address.toString());
        int result = sqlSession.insert("kr.ac.kopo.address.dao.AddressDAO.insertAddress", address);
        return result > 0; // 삽입된 행이 1개 이상이면 true 반환
    }

    @Override
    public void updateAddress(AddressVO address) {
    	sqlSession.clearCache();
        sqlSession.update("kr.ac.kopo.address.dao.AddressDAO.updateAddress", address);
    }

    @Override
    public void deleteAddress(int addressId) {
        sqlSession.delete("kr.ac.kopo.address.dao.AddressDAO.deleteAddress", addressId);
    }

    @Override
    public void resetDefaultAddress(String userId) {
        sqlSession.update("kr.ac.kopo.address.dao.AddressDAO.resetDefaultAddress", userId);
    }

}
