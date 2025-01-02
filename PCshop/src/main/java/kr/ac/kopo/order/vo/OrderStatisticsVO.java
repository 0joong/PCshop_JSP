package kr.ac.kopo.order.vo;

public class OrderStatisticsVO {
    private String itemNm; // 상품명
    private int sumQty;  // 판매 수량
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public int getSumQty() {
		return sumQty;
	}
	public void setSumQty(int sumQty) {
		this.sumQty = sumQty;
	}


}
