package kr.ac.kopo.cart.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import kr.ac.kopo.cart.vo.CartVO;
import kr.ac.kopo.mybatis.MyConfig;

public class CartDAOImpl implements CartDAO {

    private SqlSession sqlSession;

    public CartDAOImpl() {
        this.sqlSession = new MyConfig().getInstance();
    }

    @Override
    public void insertCartItem(CartVO cartItem) {
        // 이름(name)과 카테고리(category)는 COMPONENTS 테이블에서 가져오는 정보이므로,
        // CartVO 객체에 미리 세팅된 경우에 사용하고, 필요시 자동으로 조인하여 사용할 수도 있습니다.
        sqlSession.insert("kr.ac.kopo.cart.dao.CartDAO.insertCartItem", cartItem);
    }

    @Override
    public CartVO selectCartItem(String userId, String itemCd) {
    	sqlSession.clearCache();
        return sqlSession.selectOne("kr.ac.kopo.cart.dao.CartDAO.selectCartItem", new CartVO(userId, itemCd, 0, 0, null, null));
    }

    @Override
    public void updateQuantity(String userId, String itemCd, int quantity) {
        CartVO cartItem = new CartVO(userId, itemCd, quantity);
        sqlSession.clearCache();
        sqlSession.update("kr.ac.kopo.cart.dao.CartDAO.updateQuantity", cartItem);
    }

    @Override
    public List<CartVO> selectCartItemsByUserId(String userId) {
    	sqlSession.clearCache();
        return sqlSession.selectList("kr.ac.kopo.cart.dao.CartDAO.selectCartItemsByUserId", userId);
    }

    @Override
    public void deleteCartItem(String userId, String itemCd) {
    	sqlSession.clearCache();
        CartVO cartItem = new CartVO(userId, itemCd, 0);
        sqlSession.delete("kr.ac.kopo.cart.dao.CartDAO.deleteCartItem", cartItem);
    }

    @Override
    public void deleteAllCartItemsByUserId(String userId) {
    	sqlSession.clearCache();
        sqlSession.delete("kr.ac.kopo.cart.dao.CartDAO.deleteAllCartItemsByUserId", userId);
    }
}
