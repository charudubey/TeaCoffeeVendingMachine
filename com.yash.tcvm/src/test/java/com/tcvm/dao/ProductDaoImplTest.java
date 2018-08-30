package com.tcvm.dao;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.tcvm.dao.ProductDao;
import com.tcvm.dao.ProductDaoImpl;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

public class ProductDaoImplTest {

	@Test
	public void shouldReturnProductList() {
		ProductDao productDao = new ProductDaoImpl();
		
		Map<ProductType, Double> actual = productDao.getPriceList();
		
		assertEquals(4, actual.size());
	}
	
	@Test
	public void shouldReturnProductPrice() {
		ProductDao productDao = new ProductDaoImpl();
		Product product = new Product();
		product.setProductType(ProductType.BLACK_COFFEE);
		product.setQuantity(2);
		
		Double actual = productDao.getProductPrice(product);
		
		assertEquals(20.0, actual, 0);
	}
	
	
	

}
