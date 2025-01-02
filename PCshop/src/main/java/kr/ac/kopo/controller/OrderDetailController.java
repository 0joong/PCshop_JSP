package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.order.dao.OrderDAOImpl;
import kr.ac.kopo.order.service.OrderService;
import kr.ac.kopo.order.vo.OrderItemVO;
import kr.ac.kopo.order.vo.OrderVO;

import java.util.List;

public class OrderDetailController implements Controller {

	public OrderDetailController() {
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/loginForm.jsp");
			return null;
		}

		OrderService orderService = new OrderService(new OrderDAOImpl());

		// 주문 ID 파라미터 확인
		String orderId = request.getParameter("orderId");
		if (orderId == null || orderId.isEmpty()) {
			request.setAttribute("errMsg", "유효하지 않은 주문입니다.");
			return "/index.jsp"; // 에러 페이지로 이동
		}

		// 주문 상세 정보와 항목 정보 가져오기
		OrderVO order = orderService.getOrderDetails(orderId);

		// 주문 정보가 없을 경우
		if (order == null) {
			request.setAttribute("errMsg", "주문 정보를 찾을 수 없습니다.");
			return "/index.jsp"; // 에러 페이지로 이동
		}
		System.out.println(order);
		System.out.println(order.getAddress());

		// JSP에서 사용할 데이터 설정
		request.setAttribute("order", order);

		// 주문 상세 페이지로 이동
		return "/order/orderDetail.jsp";
	}
}
