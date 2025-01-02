package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.order.dao.OrderDAOImpl;
import kr.ac.kopo.order.service.OrderService;
import kr.ac.kopo.order.vo.OrderVO;

public class AdminOrderDetailController implements Controller {

    public AdminOrderDetailController() {}

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String orderId = request.getParameter("orderId");
        if (orderId == null || orderId.isEmpty()) {
            throw new IllegalArgumentException("Order ID is required");
        }
        OrderService orderService = new OrderService(new OrderDAOImpl());

        // Retrieve order details
        //OrderVO order = orderService.getOrderById(orderId);
        OrderVO order = orderService.getOrderDetails(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }

        // Check if the request is a POST for updating the tracking number
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String trackingNumber = request.getParameter("trackingNumber");
            if (trackingNumber != null && !trackingNumber.isEmpty()) {
                //orderService.updateTrackingNumber(orderId, trackingNumber);
                orderService.updateOrderStatus(orderId, "배송출발");
            }

            String action = request.getParameter("action");
            if ("cancel".equalsIgnoreCase(action)) {
                orderService.updateOrderStatus(orderId, "취소");
            } else if ("complete".equalsIgnoreCase(action)) {
                orderService.updateOrderStatus(orderId, "배송완료");
            }

            return "redirect:/PCshop/admin/orderManagement.do";
        }

        request.setAttribute("order", order);
        System.out.println(order);
        return "/admin/orderDetail.jsp";
    }
}
