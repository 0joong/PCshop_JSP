package kr.ac.kopo.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.cart.dao.CartDAOImpl;
import kr.ac.kopo.cart.service.CartService;
import kr.ac.kopo.cart.vo.CartVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.order.dao.OrderDAOImpl;
import kr.ac.kopo.order.service.OrderService;
import kr.ac.kopo.order.vo.OrderItemVO;
import kr.ac.kopo.order.vo.OrderVO;
import kr.ac.kopo.payment.service.KakaoPayService;
import kr.ac.kopo.payment.vo.KakaoPayApprovalVO;

public class PaymentApprovalController implements Controller {

	public PaymentApprovalController() {
		
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String addressId = request.getParameter("addressId"); // addressId 받기
		OrderService orderService = new OrderService(new OrderDAOImpl());
		CartService cartService = new CartService(new CartDAOImpl());
		
		// 기존 세션 가져오기 (쿠키에서 JSESSIONID 읽어오기)
	    HttpSession session = request.getSession(false);
	    if (session == null) {
	        String sessionId = null;
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if ("JSESSIONID".equals(cookie.getName())) {
	                    sessionId = cookie.getValue();
	                    break;
	                }
	            }
	        }

	        if (sessionId != null) {
	            // 기존 세션 ID로 세션 복구
	            session = request.getSession(true); // 새 세션 생성
	            session.setAttribute("JSESSIONID", sessionId); // 세션 ID를 새 세션에 설정
	        }
	    }

	    // 사용자 정보 확인
	    MemberVO user = (MemberVO) session.getAttribute("user");
	    if (user == null) {
	        System.out.println("사용자 정보가 세션에 없습니다.");
	        response.sendRedirect(request.getContextPath() + "/member/loginForm.jsp");
	        return null;
	    }

		String pgToken = request.getParameter("pg_token");
		String userId = user.getId();
	    System.out.println("결제 승인 사용자 ID: " + userId);

		// KakaoPayService 호출
		KakaoPayService kakaoPayService = KakaoPayService.getInstance();
		KakaoPayApprovalVO approvalVO = kakaoPayService.approvePayment(pgToken, userId);

		if (approvalVO != null) {
			// 주문 데이터 생성
			OrderVO order = new OrderVO();
			order.setUserId(userId);
			order.setTid(approvalVO.getTid());
			order.setTotalPrice(approvalVO.getAmount().getTotal());
			order.setAddressId(Integer.parseInt(addressId));
			order.setPaymentMethod("KAKAO_PAY");
			order.setStatus("결제완료");

			// 주문 저장
			//orderService.addOrder(order);
			String orderId = orderService.createOrderAndGetId(order);
			System.out.println("생성된 ORDER_ID: " + orderId);
			order.setOrderId(orderId);

			// 주문 항목 정보 저장
			List<OrderItemVO> orderItems = getOrderItemsFromCart(userId, order.getOrderId());
			orderService.addOrderItems(orderItems);
			
			// 장바구니 비우기
	        cartService.removeAllCartItemsByUserId(userId);

			// 결제 승인 정보를 JSP에 전달
			request.setAttribute("approvalVO", approvalVO);
			request.setAttribute("orderVO", order);

			return "/order/paymentSuccess.jsp"; // 성공 페이지로 포워드
		} else {
			request.setAttribute("errMsg", "결제 승인에 실패했습니다.");
			return "/order/paymentFail.jsp"; // 실패 페이지로 포워드
		}
	}
	// Helper method to extract items from cart
	private List<OrderItemVO> getOrderItemsFromCart(String userId, String orderId) {
		CartService cartService = new CartService(new CartDAOImpl());
	    // Retrieve cart items and convert them to OrderItemVO
	    List<CartVO> cartItems = cartService.getCartItemsByUserId(userId);
	    return cartItems.stream().map(cartItem -> {
	        OrderItemVO orderItem = new OrderItemVO();
	        orderItem.setOrderId(orderId);
	        orderItem.setItemCd(Integer.parseInt(cartItem.getItemCd()));
	        orderItem.setQty(cartItem.getQuantity());
	        orderItem.setPrice(cartItem.getPrice());
	        return orderItem;
	    }).collect(Collectors.toList());
	}
}
