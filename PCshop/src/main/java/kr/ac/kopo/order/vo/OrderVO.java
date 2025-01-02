package kr.ac.kopo.order.vo;

import java.sql.Timestamp;
import java.util.List;

import kr.ac.kopo.address.vo.AddressVO;

public class OrderVO {
    private String orderId; // 주문 고유 ID
    private String userId;  // 주문자 ID
    private String tid;     // Transaction ID
    private int totalPrice; // 총 결제 금액
    private int addressId;  // 배송 주소 ID
    private String paymentMethod; // 결제 수단
    private String status;  // 주문 상태
    private Timestamp createdAt; // 생성 시각
    private Timestamp updatedAt; // 수정 시각
    private AddressVO address;
    private List<OrderItemVO> items;
    

    public List<OrderItemVO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemVO> items) {
		this.items = items;
	}

	public AddressVO getAddress() {
		return address;
	}

	public void setAddress(AddressVO address) {
		this.address = address;
	}

	// Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

	@Override
	public String toString() {
		return "OrderVO [orderId=" + orderId + ", userId=" + userId + ", tid=" + tid + ", totalPrice=" + totalPrice
				+ ", addressId=" + addressId + ", paymentMethod=" + paymentMethod + ", status=" + status
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", address=" + address + ", items=" + items
				+ "]";
	}

	
}