package kr.ac.kopo.cart.vo;

public class CartVO {
    private String userId;       // 사용자 ID
    private String itemCd;       // 상품 코드 (item_cd)
    private int quantity;        // 상품 수량
    private int price;           // 상품 가격 (원화 기준, 소수점 없음)
    private String name;         // 상품 이름
    private String category;     // 상품 카테고리

    // 기본 생성자
    public CartVO() {}

    // 전체 필드를 포함하는 생성자
    public CartVO(String userId, String itemCd, int quantity, int price, String name, String category) {
        this.userId = userId;
        this.itemCd = itemCd;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.category = category;
    }
    
    

    public CartVO(String userId, String itemCd, int quantity) {
		this.userId = userId;
		this.itemCd = itemCd;
		this.quantity = quantity;
	}

	// Getter 및 Setter 메서드
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemCd() {
        return itemCd;
    }

    public void setItemCd(String itemCd) {
        this.itemCd = itemCd;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // toString 메서드 (디버깅을 위한 편의 기능)
    @Override
    public String toString() {
        return "CartVO [userId=" + userId + ", itemCd=" + itemCd + ", quantity=" + quantity + ", price=" + price
                + ", name=" + name + ", category=" + category + "]";
    }
}
