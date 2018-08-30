package com.tcvm.controller;

import java.util.logging.Logger;

import com.tcvm.service.ContainerService;
import com.tcvm.service.ContainerServiceImpl;
import com.tcvm.service.ProductDispenserService;
import com.tcvm.service.ProductDispenserServiceImpl;
import com.tcvm.util.InputOutput;
import com.tcvm.vo.ContainerType;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

public class MenuContoller {

	InputOutput input;
	ProductDispenserService productDispenserService;
	ContainerService containerService;
	 
	
	public MenuContoller() {
		
	}
	
	public MenuContoller(InputOutput input, ProductDispenserService productDispenserService, ContainerService containerService) {
		this.productDispenserService = productDispenserService;
		this.containerService = containerService;
		this.input = input;
	}

	public String callMenu() {
		
		String selectedOption = null;
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
		
		selectedOption = input.getInputString();
		
		return selectedOption;
	}

	public void callMenuOption() {

		Product order = new Product();
		
		String inputChoice = callMenu();
		
		Integer quantity = getOrderQuantityForBeverage(Integer.parseInt(inputChoice));
		
		if(quantity!=null){
			order.setQuantity(quantity);
			callSelectedOption(order, inputChoice);
		}
	}

	public Boolean dispenseBeverage(Product order) {
		
		Boolean isDispensed = false; 
		
		Double costOfProduct = productDispenserService.placeOrder(order);
		 
		if(costOfProduct!=null){
			System.out.println("Please enter amount: Rs. " + costOfProduct);
			Double enteredAmount = input.getInputDouble();
			
			if(enteredAmount < costOfProduct){
				System.out.println("Please enter correct Amount: Rs. " + costOfProduct);
			} else {
				
				if(enteredAmount > costOfProduct)
					System.out.println("Please collect Rs. " + (enteredAmount - costOfProduct));
				
				productDispenserService.dispense(order);
				isDispensed = true;
				//callMenuOption();
			}
		} 
		
		return isDispensed;
	}

	public Integer getOrderQuantityForBeverage(int inputChoice) {
		
		Integer quantity = null;
		
		if(validateInputOption(inputChoice)){
			System.out.println("Please enter Quantity ");
				quantity = input.getInputInteger();
		}
		
		return quantity;
	}
	
	private Boolean validateInputOption(Integer inputChoice){
		
		if(inputChoice<=4 && inputChoice>0){
			return true;
		}
		
		return false;
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
			/*Integer containerType = selectRefillContainer();
			refillContainer(ContainerType.getById(containerType));*/
			break;

		case "6":

			break;

		case "7":
			containerService.checkContainerStatus();
			break;

		case "8":

			break;

		case "9":

			break;

		default:
			System.out.println("Please select valid choice");
			callMenuOption();
			break;
		}
		
		if(validateInputOption(Integer.parseInt(inputChoice))){
			Boolean isDispensed = dispenseBeverage(order);
			if(isDispensed)
				System.out.println("Dispensing " + order.getProductType().getType());
		}
			
		
	}
	
	
}
