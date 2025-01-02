package kr.ac.kopo.address.vo;

import java.sql.Timestamp;

public class AddressVO {
    private int addressId;       // 주소 고유 ID
    private String userId;       // 사용자 ID
    private String name;         // 수신자 이름
    private String phone;        // 수신자 전화번호
    private String roadAddr;     // 도로명 주소
    private String detailAddr;   // 상세 주소
    private String postalCode;   // 우편번호
    private String extraAddr;    // 참고 항목
    private String alias;        // 주소 별칭
    private String isDefault;    // 기본 배송지 여부 (Y/N)
    private Timestamp createdAt; // 생성 시각
    private Timestamp updatedAt; // 수정 시각

    
    public int getAddressId() {
		return addressId;
	}


	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getRoadAddr() {
		return roadAddr;
	}


	public void setRoadAddr(String roadAddr) {
		this.roadAddr = roadAddr;
	}


	public String getDetailAddr() {
		return detailAddr;
	}


	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public String getExtraAddr() {
		return extraAddr;
	}


	public void setExtraAddr(String extraAddr) {
		this.extraAddr = extraAddr;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getIsDefault() {
		return isDefault;
	}


	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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
		return "AddressVO [addressId=" + addressId + ", userId=" + userId + ", name=" + name + ", phone=" + phone
				+ ", roadAddr=" + roadAddr + ", detailAddr=" + detailAddr + ", postalCode=" + postalCode
				+ ", extraAddr=" + extraAddr + ", alias=" + alias + ", isDefault=" + isDefault + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
}