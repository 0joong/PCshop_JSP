package kr.ac.kopo.cart.service;

import java.util.List;

import kr.ac.kopo.cart.dao.CartDAO;
import kr.ac.kopo.cart.dao.CartDAOImpl;
import kr.ac.kopo.cart.vo.CartVO;

public class CartService {

    private CartDAO cartDAO;

    public CartService() {
        this.cartDAO = new CartDAOImpl();
    }
    
    public CartService(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    // 장바구니에 항목 추가
    public void addToCart(CartVO cartItem) {
        // 이미 장바구니에 존재하는지 확인
        CartVO existingItem = cartDAO.selectCartItem(cartItem.getUserId(), cartItem.getItemCd());
        if (existingItem != null) {
            // 기존 항목이 있으면 수량 증가
            cartDAO.updateQuantity(cartItem.getUserId(), cartItem.getItemCd(), existingItem.getQuantity());
        } else {
            // 기존 항목이 없으면 새로 추가
            cartDAO.insertCartItem(cartItem);
        }
    }

    // 장바구니에 있는 특정 항목 조회
    public CartVO getCartItem(String userId, String itemCd) {
        return cartDAO.selectCartItem(userId, itemCd);
    }

    // 장바구니 항목 수량 업데이트
    public void updateCartItemQuantity(String userId, String itemCd, int quantity) {
        cartDAO.updateQuantity(userId, itemCd, quantity);
    }
    
    // 장바구니 항목 삭제
    public void removeCartItem(String userId, String itemCd) {
        cartDAO.deleteCartItem(userId, itemCd);
    }

    // 사용자별 장바구니 전체 항목 삭제
    public void removeAllCartItemsByUserId(String userId) {
        cartDAO.deleteAllCartItemsByUserId(userId);
    }
    
    public void deleteCartItem(String userId, String itemCd) {
        cartDAO.deleteCartItem(userId, itemCd);
    }
    
    public void updateQuantity(String userId, String itemCd, int quantity) {
        cartDAO.updateQuantity(userId, itemCd, quantity);
    }

 // 사용자별 장바구니 조회 추가
    public List<CartVO> getCartItemsByUserId(String userId) {
        return cartDAO.selectCartItemsByUserId(userId);
    }

}
