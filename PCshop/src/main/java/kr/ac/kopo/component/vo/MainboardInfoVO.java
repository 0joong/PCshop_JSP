package kr.ac.kopo.component.vo;

public class MainboardInfoVO {

	private int itemCd;
    private String socket; // 메인보드 소켓
    private String chipset; // 칩셋
    private String formFactor; // 폼팩터
    private int memSlot; // 메모리 슬롯 수
    
    
	public MainboardInfoVO() {}
	
	
	
	public int getItemCd() {
		return itemCd;
	}

	public void setItemCd(int itemCd) {
		this.itemCd = itemCd;
	}

	public String getSocket() {
		return socket;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	public String getChipset() {
		return chipset;
	}
	public void setChipset(String chipset) {
		this.chipset = chipset;
	}
	public String getFormFactor() {
		return formFactor;
	}
	public void setFormFactor(String formFactor) {
		this.formFactor = formFactor;
	}
	public int getMemSlot() {
		return memSlot;
	}
	public void setMemSlot(int memSlot) {
		this.memSlot = memSlot;
	}
	@Override
	public String toString() {
		return "MainboardInfoVO [socket=" + socket + ", chipset=" + chipset + ", formFactor=" + formFactor
				+ ", memSlot=" + memSlot + "]";
	}

    
}