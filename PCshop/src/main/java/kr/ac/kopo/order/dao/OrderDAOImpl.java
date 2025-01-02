package kr.ac.kopo.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.ac.kopo.mybatis.MyConfig;
import kr.ac.kopo.order.vo.OrderItemVO;
import kr.ac.kopo.order.vo.OrderStatisticsVO;
import kr.ac.kopo.order.vo.OrderVO;

public class OrderDAOImpl implements OrderDAO {

	private final SqlSession sqlSession;

	public OrderDAOImpl() {
		sqlSession = new MyConfig().getInstance();
	}

	@Override
	public void createOrder(OrderVO order) {
		sqlSession.clearCache();
		sqlSession.insert("kr.ac.kopo.order.dao.OrderDAO.createOrder", order);
	}

	@Override
	public OrderVO getOrderById(String orderId) {
		sqlSession.clearCache();
		return sqlSession.selectOne("kr.ac.kopo.order.dao.OrderDAO.getOrderById", orderId);
	}

	@Override
	public List<OrderVO> getOrdersByUserId(String userId) {
		sqlSession.clearCache();
		return sqlSession.selectList("kr.ac.kopo.order.dao.OrderDAO.getOrdersByUserId", userId);
	}

	@Override
	public void addOrderItem(OrderItemVO orderItem) {
		sqlSession.clearCache();
		sqlSession.insert("kr.ac.kopo.order.dao.OrderDAO.addOrderItem", orderItem);
	}

	@Override
	public String getLastOrderId(String userId) {
		return sqlSession.selectOne("kr.ac.kopo.order.dao.OrderDAO.getLastOrderId", userId);
	}

	@Override
	public List<OrderItemVO> getOrderItemsByOrderId(String orderId) {
		return sqlSession.selectList("kr.ac.kopo.order.dao.OrderDAO.getOrderItemsByOrderId", orderId);
	}

	@Override
	public List<OrderVO> findAllOrders() {
		return sqlSession.selectList("kr.ac.kopo.order.dao.OrderDAO.findAllOrders");
	}

	@Override
	public void updateTrackingNumber(String orderId, String itemCd, String trackingNo) {
		sqlSession.update("kr.ac.kopo.order.dao.OrderDAO.updateTrackingNo",
				Map.of("orderId", orderId, "trackingNo", trackingNo, "itemCd", itemCd));
	}

	@Override
	public void updateOrderStatus(String orderId, String status) {
		sqlSession.update("kr.ac.kopo.order.dao.OrderDAO.updateOrderStatus",
				Map.of("orderId", orderId, "status", status));
	}
	
	@Override
	public List<OrderStatisticsVO> getOrderStatistics(String startDate, String endDate, String category) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("startDate", startDate);
	    params.put("endDate", endDate);
	    params.put("category", category);
	    System.out.println(params);

	    return sqlSession.selectList("kr.ac.kopo.order.dao.OrderDAO.getOrderStatistics", params);
	}

}
