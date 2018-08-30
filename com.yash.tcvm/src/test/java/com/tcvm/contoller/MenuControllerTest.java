package com.tcvm.contoller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
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

import com.tcvm.controller.MenuContoller;
import com.tcvm.service.ContainerService;
import com.tcvm.service.ProductDispenserService;
import com.tcvm.util.CustomScanner;
import com.tcvm.vo.Container;
import com.tcvm.vo.ContainerType;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

@RunWith(MockitoJUnitRunner.class)
public class MenuControllerTest {

	@Mock
	CustomScanner input;
	
	@Mock
	ProductDispenserService productDispenserService;
	
	@Mock 
	ContainerService containerService;
	
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
		when(input.getInputDouble()).thenReturn(5.0);
		when(input.getInputInteger()).thenReturn(1);
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		
	}

	@Test
	public void shouldGetTeaBeverageOption(){
		
		when(input.getInputString()).thenReturn("1");
		when(input.getInputDouble()).thenReturn(20.0);
		when(input.getInputInteger()).thenReturn(2);
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		
	}

	@Test
	public void shouldGetBlackCoffeeBeverageOption(){
		
		when(input.getInputString()).thenReturn("4");
		when(input.getInputDouble()).thenReturn(40.0);
		when(input.getInputInteger()).thenReturn(4);
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		
	}

	@Test
	public void shouldGetCoffeeBeverageOption(){
		
		when(input.getInputString()).thenReturn("3");
		when(input.getInputDouble()).thenReturn(45.0);
		when(input.getInputInteger()).thenReturn(3);
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
	
	@Test
	public void shouldReturnInvalidInputWhenMenuIsCalled(){
		
		when(input.getInputString()).thenReturn("11");
		when(input.getInputInteger()).thenReturn(1);
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		verify(input).getInputInteger();
	}
	
	@Test
	public void shouldCallCheckContainerStatus(){
		
		when(input.getInputString()).thenReturn("7");
		when(input.getInputInteger()).thenReturn(1);
		doNothing().when(containerService).checkContainerStatus();
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		verify(input).getInputInteger();
		verify(containerService, times(1)).checkContainerStatus();
	}
	
	@Test
	public void shouldCallResetContainers(){
		
		when(input.getInputString()).thenReturn("8");
		when(input.getInputInteger()).thenReturn(1);
		doNothing().when(containerService).resetContainers();
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		verify(input).getInputInteger();
		verify(containerService, times(1)).resetContainers();
	}
	
	@Test
	public void shouldReturnValidOptionForRefillContainer(){
		
		when(input.getInputInteger()).thenReturn(2);
		Integer actual = menuContoller.selectRefillContainer();
		
		
		assertEquals(new Integer(2), actual);
		verify(input).getInputInteger();
	}
	
	@Test
	public void shouldReturnInvalidOptionMessageForRefillContainer(){
		
		when(input.getInputInteger()).thenReturn(6);
		menuContoller.selectRefillContainer();
		
		verify(input, times(2)).getInputInteger();
	}
	
	@Test
	public void shouldCallRefillContainerWhenSendingValidEntry(){
		
		Container.availableTeaCapacity = 500.0;
		when(input.getInputString()).thenReturn("5");
		when(input.getInputInteger()).thenReturn(4);
		when(input.getInputDouble()).thenReturn(200.0);
		when(containerService.refillContainer(any(ContainerType.class), 200.0)).thenReturn(true);
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		verify(input).getInputInteger();
		verify(input).getInputDouble();
		verify(containerService, times(1)).refillContainer(any(ContainerType.class), 200.0);
	}
	

}
