package com.tcvm.service;

import java.util.Map;

import com.tcvm.dao.MaterialDao;
import com.tcvm.dao.MaterialDaoImpl;
import com.tcvm.vo.Container;
import com.tcvm.vo.ContainerType;
import com.tcvm.vo.Material;
import com.tcvm.vo.MaterialType;
import com.tcvm.vo.Product;

public class ContainerServiceImpl implements ContainerService {

	MaterialDao materialDao;
	
	public ContainerServiceImpl() {
		materialDao = new MaterialDaoImpl();
	}
	
	/*public ContainerServiceImpl(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}*/
	
	 
	@Override
	public Boolean checkAvailableQuantity(Product product) {
		
		Map<MaterialType, Material> materialMap = materialDao.getMaterial(product.getProductType());
		
		Double availableTeaCapacity = updatedTeaCapacity(materialMap, product.getQuantity());
		
		Double availableCoffeeCapacity = updatedCoffeeCapacity(materialMap, product.getQuantity());
		
		Double availableMilkCapacity = updatedMilkCapacity(materialMap, product.getQuantity());
		
		Double availableWaterCapacity = updatedWaterCapacity(materialMap, product.getQuantity());
		
		Double availableSugarCapacity = updatedSugarCapacity(materialMap, product.getQuantity());

        if(availableTeaCapacity >=0 && availableCoffeeCapacity >= 0 && 
        		availableMilkCapacity >=0 && availableWaterCapacity >= 0 && availableSugarCapacity >= 0)
              return true;
        else
        	throw new RuntimeException("Insufficient Material, Please Try Later!");
		 
	}
	
	@Override
	public void updateContainerCapacity(Product product) {
		
		Map<MaterialType, Material> materialMap = materialDao.getMaterial(product.getProductType());
		
		Container.availableTeaCapacity = updatedTeaCapacity(materialMap, product.getQuantity());
		
		Container.availableCoffeeCapacity = updatedCoffeeCapacity(materialMap, product.getQuantity());
		
		Container.availableMilkCapacity = updatedMilkCapacity(materialMap, product.getQuantity());
		
		Container.availableWaterCapacity = updatedWaterCapacity(materialMap, product.getQuantity());
		
		Container.availableSugarCapacity = updatedSugarCapacity(materialMap, product.getQuantity());
		
		updateWasteCapacity(materialMap, product.getQuantity());
		 
	}
	
	
	private Double updatedTeaCapacity(Map<MaterialType, Material> materialMap, Integer quantity){
		return Container.availableTeaCapacity - materialMap.get(MaterialType.TEA).getMaterialConsumptionQuantity() * quantity;
	}
	
	private Double updatedCoffeeCapacity(Map<MaterialType, Material> materialMap, Integer quantity){
		
		return Container.availableCoffeeCapacity - 
				materialMap.get(MaterialType.COFFEE).getMaterialConsumptionQuantity() * quantity;
	}
	
	private Double updatedMilkCapacity(Map<MaterialType, Material> materialMap, Integer quantity){
		
		return Container.availableMilkCapacity - 
				materialMap.get(MaterialType.MILK).getMaterialConsumptionQuantity() * quantity;
	}
	
	private Double updatedWaterCapacity(Map<MaterialType, Material> materialMap, Integer quantity){
		
		return Container.availableWaterCapacity - 
				materialMap.get(MaterialType.WATER).getMaterialConsumptionQuantity() * quantity;
	}
	
	private Double updatedSugarCapacity(Map<MaterialType, Material> materialMap, Integer quantity){
		
		return Container.availableWaterCapacity - 
				materialMap.get(MaterialType.SUGAR).getMaterialConsumptionQuantity() * quantity;
	}

	@Override
	public void updateWasteCapacity(Map<MaterialType, Material> materialMap, Integer quantity) {
		
		Container.coffeeWasteMaterial += materialMap.get(MaterialType.COFFEE).getMaterialWasteQuantity() * quantity;
		Container.teaWasteMaterial += materialMap.get(MaterialType.TEA).getMaterialWasteQuantity() * quantity;
		Container.sugarWasteMaterial += materialMap.get(MaterialType.SUGAR).getMaterialWasteQuantity() * quantity;
		Container.waterWasteMaterial += materialMap.get(MaterialType.WATER).getMaterialWasteQuantity() * quantity;
		Container.milkWasteMaterial += materialMap.get(MaterialType.MILK).getMaterialWasteQuantity() * quantity;
		
	}

	@Override
	public void checkContainerStatus() {
		
		System.out.println("Available Capacity in Containers are: ");
		System.out.println("***************************************");
		System.out.println("Water Capacity: " + Container.availableWaterCapacity + " ml");
		System.out.println("Milk Capacity: " + Container.availableMilkCapacity + " ml");
		System.out.println("Sugar Capacity: " + Container.availableSugarCapacity + " grams");
		System.out.println("Tea Capacity: " + Container.availableTeaCapacity + " grams");
		System.out.println("Coffee Capacity: " + Container.availableCoffeeCapacity + " grams");
		
		
	}

	@Override
	public Boolean refillContainer(ContainerType containerType, Double refillAmount) {

		Boolean refillStatus = false; 
		
		if(containerType.equals(ContainerType.Milk)){
			if(Container.totalMilkCapacity - Container.availableMilkCapacity >= refillAmount){
				Container.availableMilkCapacity += refillAmount; 
				Container.refillCounterForMilkContainer++;
				refillStatus = true;
			}
				 
		} 
		
		if(containerType.equals(ContainerType.Water)){
			if(Container.totalWaterCapacity - Container.availableWaterCapacity >= refillAmount){
				Container.availableWaterCapacity += refillAmount; 
				Container.refillCounterForWaterContainer++;
				refillStatus = true;
			}
				
		}
		
		if(containerType.equals(ContainerType.Sugar)){
			if(Container.totalSugarCapacity - Container.availableSugarCapacity >= refillAmount){
				Container.availableSugarCapacity += refillAmount; 
				Container.refillCounterForSugarContainer++;
				refillStatus = true;
			}
		}
		
		if(containerType.equals(ContainerType.Coffee)){
			if(Container.totalCoffeeCapacity - Container.availableCoffeeCapacity >= refillAmount){
				Container.availableCoffeeCapacity += refillAmount; 
				Container.refillCounterForCoffeeContainer++;
				refillStatus = true;
			}
		}
		
		if(containerType.equals(ContainerType.Tea)){
			if(Container.totalTeaCapacity - Container.availableTeaCapacity >= refillAmount){
				Container.availableTeaCapacity += refillAmount;
				Container.refillCounterForMilkContainer++;
				refillStatus = true;
			}
		}
		
		return refillStatus;
	}

	@Override
	public void resetContainers() {
	
		System.out.println("Resetting Containers... ");
		
		Container.availableCoffeeCapacity = Container.totalCoffeeCapacity;
		Container.availableMilkCapacity = Container.totalMilkCapacity;
		Container.availableSugarCapacity = Container.totalSugarCapacity;
		Container.availableTeaCapacity = Container.totalTeaCapacity;
		Container.availableWaterCapacity = Container.totalWaterCapacity;
		
		System.out.println("Updated Capacity in Containers are: ");
		System.out.println("***************************************");
		System.out.println("Water Capacity: " + Container.availableWaterCapacity + " ml");
		System.out.println("Milk Capacity: " + Container.availableMilkCapacity + " ml");
		System.out.println("Sugar Capacity: " + Container.availableSugarCapacity + " grams");
		System.out.println("Tea Capacity: " + Container.availableTeaCapacity + " grams");
		System.out.println("Coffee Capacity: " + Container.availableCoffeeCapacity + " grams");
		
	}
	

}
