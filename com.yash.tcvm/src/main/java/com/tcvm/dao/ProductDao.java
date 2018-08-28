package com.tcvm.dao;

import java.util.Map;

import com.tcvm.vo.ProductType;

public interface ProductDao {

	public Map<ProductType, Double> getProductPrice();
	
}
