package com.tcvm.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.tcvm.service.ContainerService;
import com.tcvm.service.ContainerServiceImpl;
import com.tcvm.service.ProductDispenserService;
import com.tcvm.service.ProductDispenserServiceImpl;
import com.tcvm.vo.ContainerType;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

public class MenuController {

	Scanner scanner = new Scanner(System.in);
	ProductDispenserService productDispenserService;
	ContainerService containerService;
	
	public MenuController() {
		productDispenserService = new ProductDispenserServiceImpl();
		containerService = new ContainerServiceImpl();
		//scanner = new Scanner(System.in);
	}
	
	public MenuController(Scanner scanner, ProductDispenserService productDispenserService, ContainerService containerService) {
		productDispenserService = new ProductDispenserServiceImpl();
		containerService = new ContainerServiceImpl();
		scanner = new Scanner(System.in);
	}
	
	private String displayMenuOptions(){
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
		
		String inputChoice = scanner.next();

		return inputChoice;
	}
	
	public void callMenu(){
		
		Product order = new Product();
		System.out.println("Please select option from the following");
		
		
		String inputChoice = displayMenuOptions();
		
		
		if(validateInputOption(inputChoice)){
			System.out.println("Please enter Quantity ");
			try {
				int quantity = scanner.nextInt();
				order.setQuantity(quantity);
			} catch(NumberFormatException e){
				throw new NumberFormatException("Please Enter Valid Quantity");
			}
		}

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
				break;

			case "8":

				break;

			case "9":

				break;

			default:
				System.out.println("Please select valid choice");
				callMenu();
				break;
			}
			
			if(validateInputOption(inputChoice)){
				Double costOfProduct = productDispenserService.placeOrder(order);
				
				if(costOfProduct!=null){
					
					System.out.println("Please enter amount: Rs. " + costOfProduct);
					Double enteredAmount = scanner.nextDouble();
					
					if(enteredAmount < costOfProduct){
						
						System.out.println("Please enter correct Amount: Rs. " + costOfProduct);
						
					} else {
						
						if(enteredAmount > costOfProduct)
							System.out.println("Please collect Rs. " + (enteredAmount - costOfProduct));
						
						productDispenserService.dispense(order);
						
						callMenu();
						
					}
				}
			}
			
	}
	
	public void refillContainer(ContainerType containerType){
		
		ContainerService containerService = new ContainerServiceImpl();
		
		System.out.println("Please enter refill quantity");
		Double getRefillAmount = scanner.nextDouble();
		
		containerService.refillContainer(containerType, getRefillAmount);
		
	}
	
	public Integer selectRefillContainer(){
		
		//Scanner scanner = new Scanner(System.in);
		Integer inputChoice = null; 
		
		System.out.println("Please select container type: ");
		System.out.println(
				  "1. Water Container\n"
				+ "2. Milk Container\n"
				+ "3. Coffee Container\n"
				+ "4. Tea Container\n"
				+ "5. SugarContainer\n");
		
		try{
			inputChoice = scanner.nextInt();
			
			if(inputChoice<=5 && inputChoice>0)
				return inputChoice;
			else{
				System.out.println("Please provide valid input!");
				selectRefillContainer();
			}
			
		} catch(InputMismatchException e){
			System.out.println("Please provide numbers only!");
			selectRefillContainer();
		}
		
		return inputChoice;
		
		
	}
	
	private Boolean validateInputOption(String inputChoice){
		
		if(Integer.parseInt(inputChoice)<=4 && Integer.parseInt(inputChoice)>0){
			return true;
		}
		
		return false;
	}

	
}
