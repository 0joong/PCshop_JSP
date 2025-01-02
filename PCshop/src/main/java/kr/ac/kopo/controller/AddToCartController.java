package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.cart.service.CartService;
import kr.ac.kopo.cart.vo.CartVO;
import kr.ac.kopo.member.vo.MemberVO;

public class AddToCartController implements Controller {

	private CartService cartService;

	public AddToCartController() {
		this.cartService = new CartService();
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO) session.getAttribute("user");

		if (user == null) {
			request.setAttribute("errMsg", "로그인 후 이용해주세요.");
			return "/board/estimate/list.do";
		}

		String userId = user.getId(); 

		String cpuId = request.getParameter("cpuId");
		String mainboardId = request.getParameter("mainboardId");
		
		if (cpuId != null && !cpuId.isEmpty()) {
			CartVO cpuItem = new CartVO();
			cpuItem.setUserId(userId);
			cpuItem.setItemCd(cpuId); // 여기서는 CPU ID를 담음
			cpuItem.setQuantity(1); // 기본 수량을 1로 설정
			System.out.println("추가 부품 : " + cpuItem);
			cartService.addToCart(cpuItem);
		}
		

		// 동일한 방식으로 메인보드도 추가
		if (mainboardId != null && !mainboardId.isEmpty()) {
			CartVO mainboardItem = new CartVO();
			mainboardItem.setUserId(userId);
			mainboardItem.setItemCd(mainboardId); // 메인보드 ID를 담음
			mainboardItem.setQuantity(1);
			System.out.println("추가 부품 : " + mainboardItem);
			cartService.addToCart(mainboardItem);
		}

		return "/cart/cart.do";
	}
}