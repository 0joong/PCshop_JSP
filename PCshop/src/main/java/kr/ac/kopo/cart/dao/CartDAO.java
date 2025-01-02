package kr.ac.kopo.cart.dao;

import java.util.List;
import kr.ac.kopo.cart.vo.CartVO;

public interface CartDAO {

    // 장바구니 항목 추가
    void insertCartItem(CartVO cartItem);

    // 장바구니에 있는 특정 항목 조회
    CartVO selectCartItem(String userId, String itemCd);

    // 장바구니 항목 수량 업데이트
    void updateQuantity(String userId, String itemCd, int quantity);

    // 사용자별 장바구니 전체 조회
    List<CartVO> selectCartItemsByUserId(String userId);

    // 장바구니 항목 삭제
    void deleteCartItem(String userId, String itemCd);

    // 장바구니 전체 항목 삭제 (사용자 기준)
    void deleteAllCartItemsByUserId(String userId);
}
