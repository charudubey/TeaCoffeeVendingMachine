package com.tcvm.vo;

import java.util.HashMap;
import java.util.Map;

public class Product {

	//private Integer productId;
	private ProductType productType;
	//private Double price;
	private Integer quantity;
	
	public static Map<ProductType, Integer> totalItemsSold = new HashMap<ProductType, Integer>();
	
	public Product() {
		
	}
	
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	/*public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}*/
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
