package com.yash.tcvm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.tcvm.service.ContainerService;
import com.tcvm.service.ContainerServiceImpl;
import com.tcvm.vo.Material;
import com.tcvm.vo.MaterialType;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

public class ContainerServiceImplTest {

	ContainerService containerService = new ContainerServiceImpl();
	
	
	
	@Test
	public void shouldGetOrder(){
		
	}
	
	@Test
	public void shouldReturnTrueIfTeaContentsAreAvaliable(){
		
		Product input = new Product();
		input.setProductType(ProductType.TEA);
		input.setQuantity(5);
		
		boolean actualResult = containerService.checkAvailableQuantity(input);
		Assert.assertEquals(true, actualResult);
	}
	
	@Test
	public void shouldCheckIfBlackTeaContentsAreAvailable(){
		
		Product input = new Product();
		input.setProductType(ProductType.BLACK_TEA);
		input.setQuantity(10);
		
		boolean actualResult = containerService.checkAvailableQuantity(input);
		Assert.assertEquals(true, actualResult);
	}
	
	@Test
	public void shouldCheckIfCoffeeContaintsAreAvailable(){
		
		Product input = new Product();
		input.setProductType(ProductType.COFFEE);
		input.setQuantity(5);
		
		boolean actualResult = containerService.checkAvailableQuantity(input);
		Assert.assertEquals(true, actualResult);
	}
	
	@Test
	public void shouldCheckIfBlackCoffeeContaintsAreAvailable(){
		
		Product input = new Product();
		input.setProductType(ProductType.BLACK_COFFEE);
		input.setQuantity(5);
		
		boolean actualResult = containerService.checkAvailableQuantity(input);
		Assert.assertEquals(true, actualResult);
	}
	
	@Test
	public void shouldReturnFalseIfTeaContentsAreNotAvailable(){
		
		Product input = new Product();
		input.setProductType(ProductType.TEA);
		input.setQuantity(500);
		
		boolean actualResult = containerService.checkAvailableQuantity(input);
		Assert.assertEquals(true, actualResult);
	}
	
	@Test
	public void shouldReturnFalseIfBlackTeaContentsAreNotAvailable(){
		Product input = new Product();
		input.setProductType(ProductType.BLACK_TEA);
		input.setQuantity(500);
		
		boolean actualResult = containerService.checkAvailableQuantity(input);
		Assert.assertEquals(true, actualResult);
	}
	
	@Test
	public void shouldReturnFalseIfCoffeeContentsAreNotAvailable(){

		Product input = new Product();
		input.setProductType(ProductType.COFFEE);
		input.setQuantity(500);
		
		boolean actualResult = containerService.checkAvailableQuantity(input);
		Assert.assertEquals(true, actualResult);
		
	}
	
	@Test
	public void shouldReturnFalseIfBlackCoffeeContaintsAreNotAvailable(){
		
		Product input = new Product();
		input.setProductType(ProductType.BLACK_COFFEE);
		input.setQuantity(500);
		
		boolean actualResult = containerService.checkAvailableQuantity(input);
		Assert.assertEquals(true, actualResult);
		
	}


}
