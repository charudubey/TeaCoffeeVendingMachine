package com.tcvm.controller;

import java.util.Scanner;

import com.tcvm.service.ProductDispenserService;
import com.tcvm.service.ProductDispenserServiceImpl;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

public class MenuController {

	public static void main(String[] args) {
		callOption();
	}
	
	private static String viewMenu(){
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
		
		Scanner scanner = new Scanner(System.in);
		String inputChoice = scanner.next();
		return inputChoice;
	}
	
	private static void callOption(){
		System.out.println("Please select option from the following");
		ProductDispenserService productDispenserService = new ProductDispenserServiceImpl();
		
		String inputChoice = viewMenu();
		Product order = new Product();
		
		if(validateInputOption(inputChoice)){
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter Quantity ");
			try {
				int q = scanner.nextInt();
				order.setQuantity(q);
			} catch(NumberFormatException e){
				throw new NumberFormatException("Please Enter Valid Quantity");
			} finally {
				scanner.close();
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

				break;

			case "6":

				break;

			case "7":

				break;

			case "8":

				break;

			case "9":

				break;

			default:
				System.out.println("Please select valid choice");
				callOption();
				break;
			}
			
			if(validateInputOption(inputChoice)){
				Boolean status = productDispenserService.placeOrder(order);
				if(status)
					callOption();
			}
	}
	
	private static Boolean validateInputOption(String inputChoice){
		
		if(Integer.parseInt(inputChoice)<=4 && Integer.parseInt(inputChoice)>0){
			return true;
		}
		
		return false;
	}

	
}
