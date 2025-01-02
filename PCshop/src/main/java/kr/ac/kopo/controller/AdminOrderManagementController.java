package kr.ac.kopo.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.order.dao.OrderDAOImpl;
import kr.ac.kopo.order.service.OrderService;
import kr.ac.kopo.order.vo.OrderVO;

public class AdminOrderManagementController implements Controller {

    public AdminOrderManagementController() {}

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	OrderService orderService = new OrderService(new OrderDAOImpl());
        List<OrderVO> orderList = orderService.getAllOrders();
        
        request.setAttribute("orderList", orderList);
        return "/admin/orderManagement.jsp";
    }
}
