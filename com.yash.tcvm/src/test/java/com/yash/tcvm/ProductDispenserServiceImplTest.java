package com.yash.tcvm;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.tcvm.service.ContainerService;
import com.tcvm.service.ContainerServiceImpl;
import com.tcvm.service.ProductDispenserService;
import com.tcvm.service.ProductDispenserServiceImpl;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

public class ProductDispenserServiceImplTest {

	@Mock
	ContainerService containerService = new ContainerServiceImpl();
	
	@InjectMocks
	ProductDispenserService productService =  new ProductDispenserServiceImpl();
	
	@Test
	public void shouldReturnTrueWhenMaterialQuantityIsAvailable() {
		
		Product input = new Product();
		input.setProductType(ProductType.BLACK_COFFEE);
		input.setQuantity(1);
		
		productService.placeOrder(input);
	}

}
