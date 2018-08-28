package com.tcvm.vo;

public enum MaterialType {

	  TEA("Tea"),
	  COFFEE("Coffee"),
	  WATER("Water"),
	  MILK("Milk"),
	  SUGAR("Sugar");
	 
	  private String type;
	 
	  private MaterialType(String type) {
	    this.type = type;
	  }
	 
	  public String getType() {
	    return type;
	  }
	
}
