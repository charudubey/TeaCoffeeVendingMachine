package com.tcvm.controller;

import java.util.InputMismatchException;

import com.tcvm.service.ContainerService;
import com.tcvm.service.ContainerServiceImpl;
import com.tcvm.service.ProductDispenserService;
import com.tcvm.service.ProductDispenserServiceImpl;
import com.tcvm.util.CustomScanner;
import com.tcvm.vo.ContainerType;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

public class MenuContoller {

	CustomScanner customScanner;
	ProductDispenserService productDispenserService;
	ContainerService containerService;
	 
	
	public MenuContoller() {
		
		customScanner = new CustomScanner();
		productDispenserService = new ProductDispenserServiceImpl();
		containerService = new ContainerServiceImpl();
		
	}
	
	public MenuContoller(CustomScanner customScanner, ProductDispenserService productDispenserService, ContainerService containerService) {
		this.productDispenserService = productDispenserService;
		this.containerService = containerService;
		this.customScanner = customScanner;
	}

	public String callMenu() {
		
		String selectedOption;
		System.out.println("Please select an option from the following");
		
		System.out.println(
				  "1. Tea\n"
				+ "2. Black Tea\n"
				+ "3. Coffee\n"
				+ "4. Black Coffee\n"
				+ "5. Refill Container\n"
				+ "6. Check Total Sale\n"
				+ "7. Container Status\n"
				+ "8. Reset Container\n"
				+ "9. Exit TCVM");
		
		selectedOption = customScanner.getInputString();
		
		return selectedOption;
	}

	public void callMenuOption() {

		Product order = new Product();
		
		String inputChoice = callMenu();
		
		Integer quantity = getOrderQuantityForBeverage(Integer.parseInt(inputChoice));
		
		if(quantity!=null){
			order.setQuantity(quantity);
		}
		
		callSelectedOption(order, inputChoice);
	}

	public Boolean dispenseBeverage(Product order) {
		
		Boolean isDispensed = false; 
		
		Double costOfProduct = productDispenserService.placeOrder(order);
		 
		if(costOfProduct!=null){
			System.out.println("Please enter amount: Rs. " + costOfProduct);
			Double enteredAmount = customScanner.getInputDouble();
			
			if(enteredAmount < costOfProduct){
				System.out.println("Please enter correct Amount: Rs. " + costOfProduct);
			} else {
				
				if(enteredAmount > costOfProduct)
					System.out.println("Please collect Rs. " + (enteredAmount - costOfProduct));
				
				productDispenserService.dispense(order);
				isDispensed = true;
			}
		} 
		
		return isDispensed;
	}

	public Integer getOrderQuantityForBeverage(int inputChoice) {
		
		Integer quantity = null;
		
		if(validateInputOption(inputChoice)){
			System.out.println("Please enter Quantity ");
				quantity = customScanner.getInputInteger();
		}
		
		return quantity;
	}
	
	private Boolean validateInputOption(Integer inputChoice){
		
		if(inputChoice<=4 && inputChoice>0){
			return true;
		}
		
		return false;
	}
	
	public Integer selectRefillContainer(){
		
		Integer inputChoice = 0; 
		
		System.out.println("Please select container type: ");
		System.out.println(
				  "1. Water Container\n"
				+ "2. Milk Container\n"
				+ "3. Coffee Container\n"
				+ "4. Tea Container\n"
				+ "5. SugarContainer\n");
		
			inputChoice = customScanner.getInputInteger();
			
			if(inputChoice<=5 && inputChoice>0)
				return inputChoice;
			else{
				System.out.println("Please provide valid input!");
				goToMenu();
			}
			
		return inputChoice;
	}
	
	public void refillContainer(ContainerType containerType){
		
		ContainerService containerService = new ContainerServiceImpl();
		
		System.out.println("Please enter refill quantity");
		Double getRefillAmount = customScanner.getInputDouble();
		
		Boolean refillStatus = containerService.refillContainer(containerType, getRefillAmount);
		if(refillStatus) {
			System.out.println("Container Refilled Successfully!");
		} else {
			System.out.println("Refill unsuccessfull! Please try later!");
		}
		goToMenu();
		
	}

	private void callSelectedOption(Product order, String inputChoice) {
		
		switch (inputChoice) {
		case "1":
			order.setProductType(ProductType.TEA);
			break;

		case "2":
			order.setProductType(ProductType.BLACK_TEA);
			break;

		case "3":
			order.setProductType(ProductType.COFFEE);
			break;

		case "4":
			order.setProductType(ProductType.BLACK_COFFEE);
			break;

		case "5":
			Integer containerType = selectRefillContainer();
			refillContainer(ContainerType.getById(containerType));
			break;

		case "6":

			break;

		case "7":
			containerService.checkContainerStatus();
			goToMenu();
			break;

		case "8":
			containerService.resetContainers();
			goToMenu();
			break;

		case "9":
			break;

		default:
			System.out.println("Please select valid choice");
			goToMenu();
			break;
		}
		
		if(validateInputOption(Integer.parseInt(inputChoice))){
			Boolean isDispensed = dispenseBeverage(order);
			if(isDispensed) {
				System.out.println("Dispensing " + order.getProductType().getType());
				goToMenu();
			}
				
		}
	}
	
	private void goToMenu() {
		System.out.println("Please press '0' to go to menu!");
		Integer menuInput = customScanner.getInputInteger();
		if(menuInput==0)
			callMenuOption();
	}
	
	
}
