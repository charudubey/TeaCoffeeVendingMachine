package com.tcvm.service;

import com.tcvm.vo.Product;

public interface ProductDispenserService {
	
	public Double placeOrder(Product product);

	public Double calculateProductCost(Product product);
	
	public void dispense(Product product);
	
}
