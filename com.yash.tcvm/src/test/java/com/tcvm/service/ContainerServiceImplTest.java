package com.tcvm.service;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.sun.corba.se.impl.presentation.rmi.ExceptionHandler;
import com.tcvm.dao.MaterialDao;
import com.tcvm.service.ContainerService;
import com.tcvm.service.ContainerServiceImpl;
import com.tcvm.vo.Container;
import com.tcvm.vo.ContainerType;
import com.tcvm.vo.Material;
import com.tcvm.vo.MaterialType;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

@RunWith(MockitoJUnitRunner.class)
public class ContainerServiceImplTest {

	@Mock
	MaterialDao materialDao;
	
	@InjectMocks
	ContainerService containerService = new ContainerServiceImpl();
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	
	@Test
	public void shouldGetOrder(){
		
	}
	
	@Test
	public void shouldReturnTrueWhenTeaContentsAreAvaliable(){
		
		Product input = new Product();
		input.setProductType(ProductType.COFFEE);
		input.setQuantity(2);
		
		Map<MaterialType, Material> materialList = new HashMap<MaterialType, Material>();
		materialList.put(MaterialType.COFFEE, new Material(MaterialType.COFFEE, 4.0, 1.0, ProductType.COFFEE));
		materialList.put(MaterialType.WATER, new Material(MaterialType.WATER, 20.0, 3.0, ProductType.COFFEE));
		materialList.put(MaterialType.MILK, new Material(MaterialType.MILK, 80.0, 8.0, ProductType.COFFEE));
		materialList.put(MaterialType.SUGAR, new Material(MaterialType.SUGAR, 15.0, 2.0, ProductType.COFFEE));
		materialList.put(MaterialType.TEA, new Material(MaterialType.TEA, 0.0, 0.0, ProductType.COFFEE));
		
		Mockito.when(materialDao.getMaterial(input.getProductType())).thenReturn(materialList);
		
		boolean actual = containerService.checkAvailableQuantity(input);
		Assert.assertEquals(true, actual);
	}
	
	@Test
	public void shouldThrowExceptionWhenCoffeeContentsAreUnavaliable(){
		
		Product input = new Product();
		input.setProductType(ProductType.COFFEE);
		input.setQuantity(5000);
		
		Map<MaterialType, Material> materialList = new HashMap<MaterialType, Material>();
		materialList.put(MaterialType.COFFEE, new Material(MaterialType.COFFEE, 4.0, 1.0, ProductType.COFFEE));
		materialList.put(MaterialType.WATER, new Material(MaterialType.WATER, 20.0, 3.0, ProductType.COFFEE));
		materialList.put(MaterialType.MILK, new Material(MaterialType.MILK, 80.0, 8.0, ProductType.COFFEE));
		materialList.put(MaterialType.SUGAR, new Material(MaterialType.SUGAR, 15.0, 2.0, ProductType.COFFEE));
		materialList.put(MaterialType.TEA, new Material(MaterialType.TEA, 0.0, 0.0, ProductType.COFFEE));
		
		Mockito.when(materialDao.getMaterial(input.getProductType())).thenReturn(materialList);
		
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Insufficient Material, Please Try Later!");
		containerService.checkAvailableQuantity(input);
		
	} 
	
	@Test
	public void shouldUpdateContainerCapacity(){
		
		Product input = new Product();
		input.setProductType(ProductType.BLACK_COFFEE);
		input.setQuantity(5);
		
		Map<MaterialType,Material> materialList = new HashMap<>();
		materialList.put(MaterialType.TEA, new Material(MaterialType.TEA, 5.0, 1.0, ProductType.TEA));
		materialList.put(MaterialType.WATER, new Material(MaterialType.WATER, 60.0, 5.0, ProductType.TEA));
		materialList.put(MaterialType.MILK, new Material(MaterialType.MILK, 40.0, 4.0, ProductType.TEA));
		materialList.put(MaterialType.SUGAR, new Material(MaterialType.SUGAR, 15.0, 2.0, ProductType.TEA));
		materialList.put(MaterialType.COFFEE, new Material(MaterialType.COFFEE, 0.0, 0.0, ProductType.TEA));
		
		Mockito.when(materialDao.getMaterial(input.getProductType())).thenReturn(materialList);
		
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
	
	@Test
	public void shouldDisplayContainerStatus(){
		
		containerService.checkContainerStatus();
		
	} 
	
	@Test
	public void shouldResetContainers(){
		
		containerService.resetContainers();
		
	} 
	
	@Test
	public void shouldReturnTrueWhenRefillMilkContainerIsSuccessfull(){
		Container container = new Container();
		Container.availableMilkCapacity = 2000.0;
		Boolean actual = containerService.refillContainer(ContainerType.Milk, 200.00);
		assertTrue(actual);
		
	} 
	
	@Test
	public void shouldReturnTrueWhenRefillWaterContainerIsSuccessfull(){
		Container.availableWaterCapacity = 12000.0;
		Boolean actual = containerService.refillContainer(ContainerType.Water, 2000.00);
		assertTrue(actual);
		
	} 
	
	@Test
	public void shouldReturnTrueWhenRefillSugarContainerIsSuccessfull(){
		Container.availableSugarCapacity = 1500.0;
		Boolean actual = containerService.refillContainer(ContainerType.Sugar, 200.00);
		assertTrue(actual);
		
	}
	
	@Test
	public void shouldReturnTrueWhenRefillCoffeeContainerIsSuccessfull(){
		Container.availableCoffeeCapacity = 1500.0;
		Boolean actual = containerService.refillContainer(ContainerType.Coffee, 200.00);
		assertTrue(actual);
		
	}
	
	@Test
	public void shouldReturnTrueWhenRefillTeaContainerIsSuccessfull(){
		Container.availableTeaCapacity = 1200.0;
		Boolean actual = containerService.refillContainer(ContainerType.Tea, 200.00);
		assertTrue(actual);
		
	}


}
