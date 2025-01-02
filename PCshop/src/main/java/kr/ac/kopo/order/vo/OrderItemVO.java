package kr.ac.kopo.order.vo;

public class OrderItemVO {
    private String orderId; // 주문 ID
    private int itemCd; // 상품 코드
    private int qty; // 수량
    private int price; // 상품 1개당 가격
    private String trackingNo; // 송장 번호
    private String shippingStatus; // 배송 상태
    private String name;
    
    
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Getter & Setter
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getItemCd() {
        return itemCd;
    }

    public void setItemCd(int itemCd) {
        this.itemCd = itemCd;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
}
