package kr.ac.kopo.order.dao;

import java.util.List;

import kr.ac.kopo.order.vo.OrderItemVO;
import kr.ac.kopo.order.vo.OrderStatisticsVO;
import kr.ac.kopo.order.vo.OrderVO;

public interface OrderDAO {

	void createOrder(OrderVO order);

	OrderVO getOrderById(String orderId);

	List<OrderVO> getOrdersByUserId(String userId);

	void addOrderItem(OrderItemVO orderItem);

	String getLastOrderId(String userId); // 최신 주문 ID 조회

	List<OrderItemVO> getOrderItemsByOrderId(String orderId);

	List<OrderVO> findAllOrders();

	void updateTrackingNumber(String orderId, String itemCd, String trackingNumber);

	void updateOrderStatus(String orderId, String status);
	
	List<OrderStatisticsVO> getOrderStatistics(String startDate, String endDate, String category);
}
