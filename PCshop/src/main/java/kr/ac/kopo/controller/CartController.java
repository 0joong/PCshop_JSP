package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import kr.ac.kopo.cart.service.CartService;
import kr.ac.kopo.cart.vo.CartVO;
import kr.ac.kopo.member.vo.MemberVO;

public class CartController implements Controller {

	private CartService cartService;

	public CartController() {
		this.cartService = new CartService();
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에서 로그인 정보를 확인
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO) session.getAttribute("user"); // 로그인 시 저장된 사용자 정보 확인 (user라는 이름으로 저장된 객체)

		if (user == null) {
			// 로그인하지 않은 경우, 메인 페이지로 리다이렉트
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return null;
		}
		// 로그인된 상태이므로 사용자 ID를 사용하여 장바구니 정보를 조회
		String userId = user.getId();
		List<CartVO> cartItems = cartService.getCartItemsByUserId(userId);

		// 장바구니 항목을 request 영역에 저장하여 JSP에서 접근 가능하도록 설정
		request.setAttribute("cartItems", cartItems);
		System.out.println("장바구니 항목 수: " + cartItems.size());
		for (CartVO cart : cartItems) {
			System.out.println(cart.toString());
		}

		// 장바구니 페이지로 포워드
		return "/cart/cart.jsp";

	}
}
