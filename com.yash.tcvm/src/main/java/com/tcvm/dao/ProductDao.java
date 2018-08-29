package com.tcvm.dao;

import java.util.Map;

import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

public interface ProductDao {

	public Double getProductPrice(Product product);
	
	public Map<ProductType, Double> getPriceList();
	
}
