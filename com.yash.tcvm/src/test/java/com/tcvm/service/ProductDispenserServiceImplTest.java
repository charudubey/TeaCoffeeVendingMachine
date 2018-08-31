package com.tcvm.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tcvm.dao.ProductDao;
import com.tcvm.service.ContainerService;
import com.tcvm.service.ContainerServiceImpl;
import com.tcvm.service.ProductDispenserService;
import com.tcvm.service.ProductDispenserServiceImpl;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

@RunWith(MockitoJUnitRunner.class)
public class ProductDispenserServiceImplTest {

	@Mock
	ContainerService containerService = new ContainerServiceImpl();
	
	@Mock
	ProductDao productDao;
	
	@InjectMocks
	ProductDispenserService productService =  new ProductDispenserServiceImpl();
	
	@Test
	public void shouldReturnNullWhenMaterialQuantityIsNotSent() {
		
		Product input = new Product();
		
		Double actual = productService.placeOrder(input);
		
		assertNull(actual);
	}
	
	@Test
	public void shouldReturnPriceWhenMaterialQuantityIsSent() {
		
		Product input = new Product();
		input.setProductType(ProductType.BLACK_COFFEE);
		input.setQuantity(5);
		
		when(containerService.checkAvailableQuantity(input)).thenReturn(true);
		when(productDao.getProductPrice(input)).thenReturn(50.0);
		
		Double actual = productService.placeOrder(input);
		
		assertEquals(50.0, actual, 0);
		
	}
	
	@Test
	public void shouldDispenseBeverage() {
		Product.totalItemsSold.put(ProductType.BLACK_COFFEE, 10);
		
		Product input = new Product();
		input.setProductType(ProductType.BLACK_COFFEE);
		input.setQuantity(5);
		
		doNothing().when(containerService).updateContainerCapacity(any(Product.class));
		
		productService.dispense(input);
		
		verify(containerService).updateContainerCapacity(input);
		
	}
	
	@Test
	public void shouldDispenseBeverageAndAddNewProdutType() {
		Product.totalItemsSold.put(ProductType.BLACK_COFFEE, 10);
		
		Product input = new Product();
		input.setProductType(ProductType.TEA);
		input.setQuantity(5);
		
		doNothing().when(containerService).updateContainerCapacity(any(Product.class));
		
		productService.dispense(input);
		
		verify(containerService).updateContainerCapacity(input);
		
	}
	
	@Test
	public void shouldReturnProductCost() {
		Product input = new Product();
		input.setProductType(ProductType.TEA);
		input.setQuantity(5);
		
		when(productDao.getProductPrice(input)).thenReturn(25.0);
		
		Double actual = productService.calculateProductCost(input);
		
		verify(productDao).getProductPrice(input);
		assertEquals(new Double(25.0), actual);
		
	}
	

}
