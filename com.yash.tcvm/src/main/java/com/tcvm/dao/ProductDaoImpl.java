package com.tcvm.dao;

import java.util.HashMap;
import java.util.Map;

import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

public class ProductDaoImpl implements ProductDao{

	
	@Override
	public Double getProductPrice(Product product) {

		Map<ProductType, Double> priceList = getPriceList();
		
		return (priceList.get(product.getProductType()) * product.getQuantity());
	}

	@Override
	public Map<ProductType, Double> getPriceList() {

		Map<ProductType, Double> priceList = new HashMap<>();

		priceList.put(ProductType.TEA, 10.0);
		priceList.put(ProductType.BLACK_TEA, 5.0);
		priceList.put(ProductType.COFFEE, 15.0);
		priceList.put(ProductType.BLACK_COFFEE, 10.0);

		return priceList;
	}

}
