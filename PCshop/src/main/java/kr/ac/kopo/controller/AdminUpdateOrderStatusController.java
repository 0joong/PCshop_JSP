package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.order.dao.OrderDAOImpl;
import kr.ac.kopo.order.service.OrderService;

public class AdminUpdateOrderStatusController implements Controller {

	@Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OrderService orderService = new OrderService(new OrderDAOImpl());
        String orderId = request.getParameter("orderId");
        String status = request.getParameter("status");

        if (orderId == null || orderId.isEmpty()) {
            throw new IllegalArgumentException("Order ID is required");
        }

        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Order status is required");
        }

        orderService.updateOrderStatus(orderId, status);

        return "redirect:/admin/orderDetail.do?orderId=" + orderId;
    }

}
