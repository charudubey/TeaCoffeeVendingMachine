package com.tcvm.vo;

public enum ProductType {

		  TEA("Tea"),
		  COFFEE("Coffee"),
		  BLACK_COFFEE("Black Coffee"),
		  BLACK_TEA("Black Tea");
		 
		  private String type;
		 
		  private ProductType(String type) {
		    this.type = type;
		  }
		 
		  public String getType() {
		    return type;
		  }
}
