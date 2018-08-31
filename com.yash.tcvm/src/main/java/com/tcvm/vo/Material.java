package com.tcvm.vo;

public class Material {

	private MaterialType materialType;
	private Double materialConsumptionQuantity;
	private Double materialWasteQuantity;
	private ProductType productType;
	
	public Material(MaterialType materialType, Double materialConsumptionQuantity, Double materialWasteQuantity,
			ProductType productType) {
		super();
		this.materialType = materialType;
		this.materialConsumptionQuantity = materialConsumptionQuantity;
		this.materialWasteQuantity = materialWasteQuantity;
		this.productType = productType;
	}
	
	public MaterialType getMaterialType() {
		return materialType;
	}
	public Double getMaterialConsumptionQuantity() {
		return materialConsumptionQuantity;
	}
	public Double getMaterialWasteQuantity() {
		return materialWasteQuantity;
	}
	public ProductType getProductType() {
		return productType;
	}
}
