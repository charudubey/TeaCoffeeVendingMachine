package com.tcvm.vo;

public class Container {

	private String containerType;
	private String containerTotalCapacity;
	private String containerAvailableCapacity;
	private Integer refillingCounter;
	
	private final Double totalTeaCapacity = 2000.00;
	private final Double totalCoffeeCapacity = 2000.00;
	private final Double totalSugarCapacity = 8000.00;
	private final Double totalWaterCapacity = 15000.00;
	private final Double totalMilkCapacity = 10000.00;
	
	public static Double availableTeaCapacity = 2000.00;
	public static Double availableCoffeeCapacity = 2000.00;
	public static Double availableSugarCapacity = 8000.00;
	public static Double availableWaterCapacity = 15000.00;
	public static Double availableMilkCapacity = 10000.00;
	
	public static Double teaWasteMaterial = 0.0;
	public static Double coffeeWasteMaterial = 0.0;
	public static Double sugarWasteMaterial = 0.0;
	public static Double waterWasteMaterial = 0.0;
	public static Double milkWasteMaterial = 0.0;
	
	public String getContainerType() {
		return containerType;
	}
	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}
	public String getContainerTotalCapacity() {
		return containerTotalCapacity;
	}
	public void setContainerTotalCapacity(String containerTotalCapacity) {
		this.containerTotalCapacity = containerTotalCapacity;
	}
	public String getContainerAvailableCapacity() {
		return containerAvailableCapacity;
	}
	public void setContainerAvailableCapacity(String containerAvailableCapacity) {
		this.containerAvailableCapacity = containerAvailableCapacity;
	}
	public Integer getRefillingCounter() {
		return refillingCounter;
	}
	public void setRefillingCounter(Integer refillingCounter) {
		this.refillingCounter = refillingCounter;
	}
	
	public Double getTotalTeaCapacity() {
		return totalTeaCapacity;
	}
	public Double getTotalCoffeeCapacity() {
		return totalCoffeeCapacity;
	}
	public Double getTotalSugarCapacity() {
		return totalSugarCapacity;
	}
	public Double getTotalWaterCapacity() {
		return totalWaterCapacity;
	}
	public Double getTotalMilkCapacity() {
		return totalMilkCapacity;
	}
	
	
	
	
	
}
