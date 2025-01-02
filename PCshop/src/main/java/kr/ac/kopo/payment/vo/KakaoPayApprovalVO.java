package kr.ac.kopo.payment.vo;

public class KakaoPayApprovalVO {
    private String aid;            // 요청 고유 번호
    private String tid;            // 결제 고유 번호
    private String cid;            // 가맹점 코드
    private String partner_order_id; // 가맹점 주문 번호
    private String partnerUserId;  // 가맹점 회원 ID
    private String paymentMethodType; // 결제 수단
    private AmountVO amount;       // 결제 금액 정보
    private String item_name;       // 상품 이름
    private String itemCode;       // 상품 코드
    private int quantity;          // 상품 수량
    private String createdAt;      // 결제 준비 요청 시간
    private String approvedAt;     // 결제 승인 시간
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPartnerOrderId() {
		return partner_order_id;
	}
	public void setPartnerOrderId(String partnerOrderId) {
		this.partner_order_id = partnerOrderId;
	}
	public String getPartnerUserId() {
		return partnerUserId;
	}
	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}
	public String getPaymentMethodType() {
		return paymentMethodType;
	}
	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}
	public AmountVO getAmount() {
		return amount;
	}
	public void setAmount(AmountVO amount) {
		this.amount = amount;
	}
	public String getItemName() {
		return item_name;
	}
	public void setItemName(String itemName) {
		this.item_name = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getApprovedAt() {
		return approvedAt;
	}
	public void setApprovedAt(String approvedAt) {
		this.approvedAt = approvedAt;
	}
	@Override
	public String toString() {
		return "KakaoPayApprovalVO [aid=" + aid + ", tid=" + tid + ", cid=" + cid + ", partnerOrderId=" + partner_order_id
				+ ", partnerUserId=" + partnerUserId + ", paymentMethodType=" + paymentMethodType + ", amount=" + amount
				+ ", itemName=" + item_name + ", itemCode=" + itemCode + ", quantity=" + quantity + ", createdAt="
				+ createdAt + ", approvedAt=" + approvedAt + "]";
	}
	
	public class AmountVO {
	    private int total;   // 총 금액
	    private int taxFree; // 비과세 금액
	    private int vat;     // 부가세 금액
	    private int discount; // 할인 금액
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public int getTaxFree() {
			return taxFree;
		}
		public void setTaxFree(int taxFree) {
			this.taxFree = taxFree;
		}
		public int getVat() {
			return vat;
		}
		public void setVat(int vat) {
			this.vat = vat;
		}
		public int getDiscount() {
			return discount;
		}
		public void setDiscount(int discount) {
			this.discount = discount;
		}
		@Override
		public String toString() {
			return "AmountVO [total=" + total + ", taxFree=" + taxFree + ", vat=" + vat + ", discount=" + discount + "]";
		}
	}
}

