package com.tcvm.contoller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tcvm.controller.AppLauncher;
import com.tcvm.controller.CustomScanner;
//import com.tcvm.controller.AppLauncher;
import com.tcvm.controller.MenuController;
import com.tcvm.service.ContainerService;
import com.tcvm.service.ProductDispenserService;
import com.tcvm.service.ReportService;
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
	
	@Mock 
	ReportService reportService;
	
	@InjectMocks
	MenuController menuContoller;
	
	
	
	@Test
	public void shouldGetBlackTeaBeverageOption(){
		
		when(input.getInputString()).thenReturn("2");
		when(input.getInputDouble()).thenReturn(5.0);
		when(input.getInputInteger()).thenReturn(1).thenReturn(1);
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		
	}
	
	@Test
	public void shouldCallDefaultMethod(){
		
		menuContoller = new MenuController();
		AppLauncher appLauncher = new AppLauncher();
		CustomScanner customScanner = new CustomScanner();
	}
 
	@Test
	public void shouldGetTeaBeverageOption(){
		
		when(input.getInputString()).thenReturn("1");
		when(input.getInputInteger()).thenReturn(2).thenReturn(1);
		when(input.getInputDouble()).thenReturn(20.0);
		
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		
	}

	@Test
	public void shouldGetBlackCoffeeBeverageOption(){
		
		when(input.getInputString()).thenReturn("4");
		when(input.getInputDouble()).thenReturn(40.0);
		when(input.getInputInteger()).thenReturn(4).thenReturn(1);
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		
	}

	@Test
	public void shouldGetCoffeeBeverageOption(){
		
		when(input.getInputString()).thenReturn("3");
		when(input.getInputDouble()).thenReturn(45.0);
		when(input.getInputInteger()).thenReturn(3).thenReturn(1);
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
		
		when(input.getInputString()).thenReturn("12");
		when(input.getInputInteger()).thenReturn(1);
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		verify(input).getInputInteger();
		
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
		
		when(productDispenserService.placeOrder(order)).thenReturn(new Double(20.0));
		when(input.getInputString()).thenReturn("1");
		when(input.getInputInteger()).thenReturn(3);
		when(input.getInputDouble()).thenReturn(new Double(10.0));
		Boolean actual = menuContoller.dispenseBeverage(order);
		assertFalse(actual);
		
	}
	
	@Test
	public void shouldReturnFalseWhenAmountIsNullWhileDispenseBeverage(){
		Product order = new Product();
		order.setProductType(ProductType.BLACK_COFFEE);
		order.setQuantity(new Integer(2));
		
		when(productDispenserService.placeOrder(order)).thenReturn(null);
		when(input.getInputString()).thenReturn("1");
		when(input.getInputInteger()).thenReturn(3);
		when(input.getInputDouble()).thenReturn(new Double(10.0));
		Boolean actual = menuContoller.dispenseBeverage(order);
		assertFalse(actual);
		
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
	public void shouldCallMenuOptionsTwice(){
		
		when(input.getInputString()).thenReturn("8");
		when(input.getInputInteger()).thenReturn(0).thenReturn(1);
		doNothing().when(containerService).resetContainers();
		menuContoller.callMenuOption();
		
		verify(input, times(2)).getInputString();
		verify(input, times(2)).getInputInteger();
		verify(containerService, times(2)).resetContainers();
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
	public void shouldExitTheSystemIfCalledExit(){
		
		when(input.getInputString()).thenReturn("9");
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
	}
	
	@Test
	public void shouldCallRefillContainerWhenSendingValidEntry(){
		
		Container.availableTeaCapacity = 500.0;
		when(input.getInputString()).thenReturn("5");
		when(input.getInputInteger()).thenReturn(4);
		when(input.getInputDouble()).thenReturn(200.0);
		when(containerService.refillContainer(any(ContainerType.class), anyDouble())).thenReturn(true);
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		verify(input, times(2)).getInputInteger();
		verify(input).getInputDouble();
		verify(containerService).refillContainer(any(ContainerType.class), anyDouble());
	}
	
	@Test
	public void shouldReturnUnsuccessfullMessageWhenRefillContainerIsCalled(){
		
		Container.availableTeaCapacity = 500.0;
		when(input.getInputString()).thenReturn("5");
		when(input.getInputInteger()).thenReturn(4);
		when(input.getInputDouble()).thenReturn(200.0);
		when(containerService.refillContainer(any(ContainerType.class), anyDouble())).thenReturn(false);
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		verify(input, times(2)).getInputInteger();
		verify(input).getInputDouble();
		verify(containerService).refillContainer(any(ContainerType.class), anyDouble());
	}
	
	@Test
	public void shouldCallReportsMenu(){
		
		when(input.getInputString()).thenReturn("6");
		when(input.getInputInteger()).thenReturn(1);
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
	}

	@Test
	public void shouldCallInvalidOptionForReportsMenu(){
		
		when(input.getInputString()).thenReturn("6");
		when(input.getInputInteger()).thenReturn(5).thenReturn(1);
		doNothing().when(reportService).generateTeaCoffeeReportDrinkwise();
		menuContoller.callMenuOption(); 
		
		verify(input).getInputString();
		verify(input, times(4)).getInputInteger();
		verify(reportService).generateTeaCoffeeReportDrinkwise();
	}

	
	@Test
	public void shouldCallDrinkwiseSoldReport(){
		
		when(input.getInputString()).thenReturn("6");
		when(input.getInputInteger()).thenReturn(1);
		doNothing().when(reportService).generateTeaCoffeeReportDrinkwise();
		
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		verify(input, times(2)).getInputInteger();
		verify(reportService).generateTeaCoffeeReportDrinkwise();
	}
	
	@Test
	public void shouldCallAllCupsAndPriceReport(){
		
		when(input.getInputString()).thenReturn("6");
		when(input.getInputInteger()).thenReturn(2);
		doNothing().when(reportService).generateTotalTeaCoffeeReport();
		
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		verify(input, times(2)).getInputInteger();
		verify(reportService).generateTotalTeaCoffeeReport();
	}
	
	@Test
	public void shouldCallContainersStatusReport(){
		
		when(input.getInputString()).thenReturn("6");
		when(input.getInputInteger()).thenReturn(3);
		doNothing().when(reportService).containerStatusReport();
		
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		verify(input, times(2)).getInputInteger();
		verify(reportService).containerStatusReport();
	}
	
	@Test
	public void shouldCallRefillContainerStatusReport(){
		
		when(input.getInputString()).thenReturn("6");
		when(input.getInputInteger()).thenReturn(4);
		doNothing().when(reportService).refillingCounterStatus();
		
		menuContoller.callMenuOption();
		
		verify(input).getInputString();
		verify(input, times(2)).getInputInteger();
		verify(reportService).refillingCounterStatus();
	}
	

}
