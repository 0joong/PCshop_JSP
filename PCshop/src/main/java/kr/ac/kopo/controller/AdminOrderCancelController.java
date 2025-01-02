package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.order.service.OrderService;
import kr.ac.kopo.order.dao.OrderDAOImpl;

public class AdminOrderCancelController implements Controller {

    private final OrderService orderService;

    public AdminOrderCancelController() {
        // OrderService를 초기화합니다.
        this.orderService = new OrderService(new OrderDAOImpl());
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 주문 ID 가져오기
        String orderId = request.getParameter("orderId");

        if (orderId == null || orderId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "주문 ID가 유효하지 않습니다.");
            return null;
        }

        try {
            // 주문 상태를 취소로 업데이트
        	orderService.updateOrderStatus(orderId, "취소");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "주문 취소 처리 중 예외가 발생했습니다.");
        }

        return "/admin/orderManagement.do";
    }
}
