package com.tcvm.contoller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tcvm.controller.MenuContoller;
import com.tcvm.service.ProductDispenserService;
import com.tcvm.util.InputOutput;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

@RunWith(MockitoJUnitRunner.class)
public class MenuControllerTest {

	@Mock
	InputOutput input;
	
	@Mock
	ProductDispenserService productDispenserService;
	
	@InjectMocks
	MenuContoller menuContoller;
	
	@Test
	public void shouldReturnMenuOption(){
		
		when(input.getInputString()).thenReturn("2");
		
		String actual = menuContoller.callMenu();
		
		assertEquals("2", actual);
		verify(input).getInputString();
	}
	
	@Test
	public void shouldGetBlackTeaBeverageOption(){
		
		when(input.getInputString()).thenReturn("2");
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		
	}

	@Test
	public void shouldGetTeaBeverageOption(){
		
		when(input.getInputString()).thenReturn("1");
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		
	}

	@Test
	public void shouldGetBlackCoffeeBeverageOption(){
		
		when(input.getInputString()).thenReturn("4");
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		
	}

	@Test
	public void shouldGetCoffeeBeverageOption(){
		
		when(input.getInputString()).thenReturn("3");
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		
	}

	
	
	@Test
	public void shouldGetOrderQuantityWhenBeverageIsSelected(){
		
		when(input.getInputInteger()).thenReturn(3);
		
		Integer actual = menuContoller.getOrderQuantityForBeverage(2);
		
		assertEquals(new Integer(3), actual);
		verify(input).getInputInteger();
	}
	
	@Test
	public void shouldNotGetQuantityWhenBeverageIsNotSelected(){
		
		when(input.getInputString()).thenReturn("6");
		when(input.getInputInteger()).thenReturn(3);
		menuContoller.callMenuOption();
		
		verify(input, never()).getInputInteger();
		
	}
	
	@Test
	public void shouldDispenseBlackCoffeeBeverage(){
		Product order = new Product();
		order.setProductType(ProductType.BLACK_COFFEE);
		order.setQuantity(new Integer(2));
		
		when(productDispenserService.calculateProductCost(any(Product.class))).thenReturn(20.0);
		when(input.getInputString()).thenReturn("1");
		when(input.getInputInteger()).thenReturn(3);
		when(input.getInputDouble()).thenReturn(20.0);
		Boolean actual = menuContoller.dispenseBeverage(order);
		
		assertTrue(actual);
	}
	
	@Test
	public void shouldReturnInvalidExceptionWhenAmountIsLessWhileDispenseBeverage(){
		Product order = new Product();
		order.setProductType(ProductType.BLACK_COFFEE);
		order.setQuantity(new Integer(2));
		
		when(productDispenserService.calculateProductCost(order)).thenReturn(new Double(20.0));
		when(input.getInputString()).thenReturn("1");
		when(input.getInputInteger()).thenReturn(3);
		when(input.getInputDouble()).thenReturn(new Double(20.0));
		Boolean actual = menuContoller.dispenseBeverage(order);
		
		assertTrue(actual);
	}

}
