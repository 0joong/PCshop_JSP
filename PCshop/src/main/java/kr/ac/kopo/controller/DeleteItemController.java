package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import kr.ac.kopo.cart.service.CartService;
import kr.ac.kopo.member.vo.MemberVO;

public class DeleteItemController implements Controller {

    private CartService cartService;

    public DeleteItemController() {
        this.cartService = new CartService();
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션에서 로그인 정보를 확인
        HttpSession session = request.getSession();
        MemberVO user = (MemberVO) session.getAttribute("user");

        // 사용자가 로그인되어 있지 않으면 메인 페이지로 리다이렉트
        if (user == null) {
            request.setAttribute("errMsg", "잘못된 접근입니다.");
            return "/index.jsp";
        }

        // 삭제할 아이템의 아이템 코드 가져오기
        String itemCd = request.getParameter("itemCd");

        // 로그인된 사용자 ID 가져오기
        String userId = user.getId();
        
        System.out.println("deleteCartItem");
        System.out.println(userId + itemCd);

        // 장바구니에서 해당 항목 삭제
        cartService.deleteCartItem(userId, itemCd);

        // 장바구니 페이지로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/cart/cart.do");
        return null;
    }
}
