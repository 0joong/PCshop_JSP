package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import kr.ac.kopo.address.service.AddressService;
import kr.ac.kopo.address.vo.AddressVO;
import kr.ac.kopo.cart.service.CartService;
import kr.ac.kopo.cart.vo.CartVO;
import kr.ac.kopo.member.vo.MemberVO;

public class CheckoutController implements Controller {

    private CartService cartService;
    private AddressService addressService;

    public CheckoutController() {
        this.cartService = new CartService();
        this.addressService = new AddressService();
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        MemberVO user = (MemberVO)session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/loginForm.do");
            return null;
        }

        // 사용자 ID 가져오기
        String userId = user.getId();

        // 장바구니 데이터 가져오기
        List<CartVO> cartItems = cartService.getCartItemsByUserId(userId);

        // 배송지 정보 가져오기
        List<AddressVO> userAddresses = addressService.getAddressesByUserId(userId);
        
        System.out.println("가져온 addressVO 정보");
        for(AddressVO a : userAddresses) {
        	System.out.println(a.toString());
        }

        // 데이터 공유영역에 저장
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("userAddresses", userAddresses);

        // checkout.jsp로 포워드
        return "/order/checkout.jsp";
    }
}
