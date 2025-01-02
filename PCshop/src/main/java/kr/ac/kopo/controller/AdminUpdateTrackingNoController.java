package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.order.dao.OrderDAOImpl;
import kr.ac.kopo.order.service.OrderService;

public class AdminUpdateTrackingNoController implements Controller{
	
	@Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OrderService orderService = new OrderService(new OrderDAOImpl());
        String orderId = request.getParameter("orderId");
        String itemCd = request.getParameter("itemCd");
        String trackingNumber = request.getParameter("trackingNo");

        if (orderId == null || orderId.isEmpty()) {
            throw new IllegalArgumentException("Order ID is required");
        }

        if (itemCd == null || itemCd.isEmpty()) {
            throw new IllegalArgumentException("Item ID is required");
        }

        if (trackingNumber == null || trackingNumber.isEmpty()) {
            throw new IllegalArgumentException("Tracking number is required");
        }

        orderService.updateTrackingNumber(orderId, itemCd, trackingNumber);

        return "redirect:/admin/orderDetail.do?orderId=" + orderId;
    }
}
