package kr.ac.kopo.product.vo;

import com.google.gson.annotations.SerializedName;

public class ComponentVO {
	@SerializedName("itemCd")
    private int itemCd;         // ITEM CD
    private String category;       // CATEGORY
    @SerializedName("name")
    private String name;           // NAME
    private String brand;          // BRAND
    private double price;          // PRICE
    private int stockQty;          // STOCK QTY
    private String thumbImgUrl;    // THUMB IMG URL
    private String detailImgUrl;   // DETAIL IMG URL
    private String description;    // DESCRIPTION

    // Getters and Setters
    public int getItemCd() {
        return itemCd;
    }

    public void setItemCd(int itemCd) {
        this.itemCd = itemCd;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public String getThumbImgUrl() {
        return thumbImgUrl;
    }

    public void setThumbImgUrl(String thumbImgUrl) {
        this.thumbImgUrl = thumbImgUrl;
    }

    public String getDetailImgUrl() {
        return detailImgUrl;
    }

    public void setDetailImgUrl(String detailImgUrl) {
        this.detailImgUrl = detailImgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ComponentVO [itemCd=" + itemCd + ", category=" + category + ", name=" + name + ", brand=" + brand
                + ", price=" + price + ", stockQty=" + stockQty + ", thumbImgUrl=" + thumbImgUrl
                + ", detailImgUrl=" + detailImgUrl + ", description=" + description + "]";
    }
}
