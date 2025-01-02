package kr.ac.kopo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.order.dao.OrderDAOImpl;
import kr.ac.kopo.order.service.OrderService;
import kr.ac.kopo.order.vo.OrderStatisticsVO;

public class AdminOrderStatisticsController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			request.setAttribute("errMsg", "잘못된 접근입니다.");
			return "/index.jsp";
		}
		
		List<OrderStatisticsVO> salesList = new ArrayList<>();
		OrderService orderService = new OrderService(new OrderDAOImpl());
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String category = request.getParameter("category");
		
		if(startDate  == null || endDate == null || category == null) {
			return "/admin/orderStatistics.jsp";
		}
		
		if (LocalDate.parse(startDate).isAfter(LocalDate.parse(endDate))) {
            request.setAttribute("errMsg", "시작 날짜는 종료 날짜보다 빠를 수 없습니다.");
            return "/admin/orderStatistics.jsp";
        }
		
		salesList = orderService.getOrderStatistics(startDate, endDate, category);
		
		request.setAttribute("salesList", salesList);
		
		return "/admin/orderStatistics.jsp";
	}

}
