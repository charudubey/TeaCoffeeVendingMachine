package com.tcvm.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.tcvm.dao.ProductDao;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceImplTest {
	
	@InjectMocks
	ReportServiceImpl reportServiceImpl = new ReportServiceImpl();
	
	@Mock
	ProductDao productDao;
	
	@Mock
	ContainerService containerService ;
	
	@Test
	public void shouldGenerateTotalTeaCoffeeDrinkwiseReport(){
		Product.totalItemsSold.put(ProductType.COFFEE, 8);
		Product.totalItemsSold.put(ProductType.TEA, 5);
		Product.totalItemsSold.put(ProductType.BLACK_COFFEE, 10);
		Map<String, Integer> mapOfItemAndQuantity = new HashMap<>();
		mapOfItemAndQuantity.put("tea", 5);
		ReportService reportService = new ReportServiceImpl();
		Map<ProductType, Double> allItemCost = new HashMap<>();
		allItemCost.put(ProductType.TEA, 10d);
		allItemCost.put(ProductType.BLACK_TEA, 5d);
		allItemCost.put(ProductType.COFFEE, 15d);
		allItemCost.put(ProductType.BLACK_COFFEE, 10d);
		
		Mockito.when(productDao.getPriceList()).thenReturn(allItemCost);
		reportService.generateTeaCoffeeReportDrinkwise();
	}
	
	@Test
	public void shouldGetRefillStatus(){
		reportServiceImpl.refillingCounterStatus();
	}
	
	@Test
	public void shouldGetContainerStatus(){
		Mockito.doNothing().when(containerService).checkContainerStatus();
		reportServiceImpl.containerStatusReport();
	}
	
	@Test
	public void shouldGenerateTotalTeaCoffeeReport(){
		Product.totalItemsSold.put(ProductType.COFFEE, 8);
		Product.totalItemsSold.put(ProductType.TEA, 5);
		Map<String, Integer> mapOfItemAndQuantity = new HashMap<>();
		mapOfItemAndQuantity.put("tea", 5);
		ReportService reportService = new ReportServiceImpl();
		Map<ProductType, Double> allItemCost = new HashMap<>();
		allItemCost.put(ProductType.TEA, 10d);
		allItemCost.put(ProductType.BLACK_TEA, 5d);
		allItemCost.put(ProductType.COFFEE, 15d);
		allItemCost.put(ProductType.BLACK_COFFEE, 10d);
		Mockito.when(productDao.getPriceList()).thenReturn(allItemCost);
		reportService.generateTotalTeaCoffeeReport();
	}

	
	
}
