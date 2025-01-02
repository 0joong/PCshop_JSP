package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import kr.ac.kopo.cart.service.CartService;
import kr.ac.kopo.member.vo.MemberVO;

public class UpdateQuantityController implements Controller {

    private CartService cartService;

    public UpdateQuantityController() {
        this.cartService = new CartService();
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션에서 로그인 정보를 확인
        HttpSession session = request.getSession();
        MemberVO user = (MemberVO) session.getAttribute("user");

        // 사용자가 로그인되어 있지 않으면 메인 페이지로 리다이렉트
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return null;
        }

        // 업데이트할 아이템의 아이템 코드와 수량 가져오기
        String itemCd = request.getParameter("itemCd");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // 로그인된 사용자 ID 가져오기
        String userId = user.getId();

        // 장바구니 항목 수량 업데이트
        cartService.updateQuantity(userId, itemCd, quantity);

        // 업데이트 성공 메시지 반환
        response.setContentType("text/plain");
        response.getWriter().write("success");
        
        System.out.println("user id: " + userId + "\nitem cd: " + itemCd +"\nquan: " +quantity);
        return null;
    }
}
