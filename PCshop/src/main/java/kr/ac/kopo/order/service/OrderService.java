package kr.ac.kopo.order.service;

import java.util.List;

import kr.ac.kopo.order.dao.OrderDAO;
import kr.ac.kopo.order.vo.OrderItemVO;
import kr.ac.kopo.order.vo.OrderStatisticsVO;
import kr.ac.kopo.order.vo.OrderVO;

public class OrderService {
	private final OrderDAO orderDAO;

	public OrderService(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public void addOrder(OrderVO order) {
		orderDAO.createOrder(order);
	}

	public OrderVO getOrderById(String orderId) {
		return orderDAO.getOrderById(orderId);
	}

	public List<OrderVO> getOrdersByUserId(String userId) {
		return orderDAO.getOrdersByUserId(userId);
	}

	public void addOrderItems(List<OrderItemVO> orderItems) {
		for (OrderItemVO item : orderItems) {
			orderDAO.addOrderItem(item);
		}
	}

	public String createOrderAndGetId(OrderVO order) {
		orderDAO.createOrder(order);
		return orderDAO.getLastOrderId(order.getUserId());
	}

	public List<OrderItemVO> getOrderItemsByOrderId(String orderId) {
		return orderDAO.getOrderItemsByOrderId(orderId);
	}

	public OrderVO getOrderDetails(String orderId) {
		// 주문 정보 가져오기 (OrderVO)
		OrderVO order = orderDAO.getOrderById(orderId);

		// 주문 항목 정보 가져오기 (OrderItemVO 리스트)
		List<OrderItemVO> orderItems = orderDAO.getOrderItemsByOrderId(orderId);

		// 주문에 아이템 리스트 설정
		order.setItems(orderItems);

		return order;
	}

	public List<OrderVO> getAllOrders() {
		return orderDAO.findAllOrders();
	}

	public void updateTrackingNumber(String orderId, String itemCd, String trackingNumber) {
		orderDAO.updateTrackingNumber(orderId, itemCd, trackingNumber);
	}

	public void updateOrderStatus(String orderId, String status) {
		orderDAO.updateOrderStatus(orderId, status);
	}
	
	public List<OrderStatisticsVO> getOrderStatistics(String startDate, String endDate, String category) {
	    return orderDAO.getOrderStatistics(startDate, endDate, category);
	}
}
