package com.yash.tcvm.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.sun.corba.se.impl.presentation.rmi.ExceptionHandler;
import com.tcvm.service.ContainerService;
import com.tcvm.service.ContainerServiceImpl;
import com.tcvm.vo.Material;
import com.tcvm.vo.MaterialType;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

@RunWith(MockitoJUnitRunner.class)
public class ContainerServiceImplTest {

	ContainerService containerService = new ContainerServiceImpl();
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	
	@Test
	public void shouldGetOrder(){
		
	}
	
	@Test
	public void shouldReturnTrueWhenTeaContentsAreAvaliable(){
		
		Product input = new Product();
		input.setProductType(ProductType.TEA);
		input.setQuantity(5);
		
		boolean actual = containerService.checkAvailableQuantity(input);
		Assert.assertEquals(true, actual);
	}
	
	@Test
	public void shouldThrowExceptionWhenTeaContentsAreUnavaliable(){
		
		Product input = new Product();
		input.setProductType(ProductType.TEA);
		input.setQuantity(5000);
		
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Insufficient Material, Please Try Later!");
		containerService.checkAvailableQuantity(input);
		
	} 
	
	@Test
	public void shouldUpdateContainerCapacity(){
		
		Product input = new Product();
		input.setProductType(ProductType.BLACK_COFFEE);
		input.setQuantity(5);
		
		containerService.updateContainerCapacity(input);
	} 

	@Test
	public void shouldUpdateWasteCapacity(){
		
		Map<MaterialType, Material> input = new HashMap<MaterialType, Material>();
		input.put(MaterialType.COFFEE, new Material(MaterialType.COFFEE, 4.0, 1.0, ProductType.COFFEE));
		input.put(MaterialType.WATER, new Material(MaterialType.WATER, 20.0, 3.0, ProductType.COFFEE));
		input.put(MaterialType.MILK, new Material(MaterialType.MILK, 80.0, 8.0, ProductType.COFFEE));
		input.put(MaterialType.SUGAR, new Material(MaterialType.SUGAR, 15.0, 2.0, ProductType.COFFEE));
		input.put(MaterialType.TEA, new Material(MaterialType.TEA, 0.0, 0.0, ProductType.COFFEE));
		
		containerService.updateWasteCapacity(input, 4);
	} 
	
	/*@Test
	public void shouldDisplayContainerStatus(){
		
		
	} */


}
