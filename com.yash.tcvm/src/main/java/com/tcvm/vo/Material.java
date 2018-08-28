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
	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}
	public Double getMaterialConsumptionQuantity() {
		return materialConsumptionQuantity;
	}
	public void setMaterialConsumptionQuantity(Double materialConsumptionQuantity) {
		this.materialConsumptionQuantity = materialConsumptionQuantity;
	}
	public Double getMaterialWasteQuantity() {
		return materialWasteQuantity;
	}
	public void setMaterialWasteQuantity(Double materialWasteQuantity) {
		this.materialWasteQuantity = materialWasteQuantity;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
	@Override
	public String toString() {
		return "Material [materialType=" + materialType + ", materialConsumptionQuantity=" + materialConsumptionQuantity
				+ ", materialWasteQuantity=" + materialWasteQuantity + ", productType=" + productType + "]";
	}

	
}
