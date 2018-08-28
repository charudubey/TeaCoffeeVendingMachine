package com.tcvm.service;

import com.tcvm.vo.Product;

public interface ProductDispenserService {
	
	public Boolean placeOrder(Product product);

	public Double calculateProductCost(Product product);
	
	public Double verifyAndReturnFunds(Double Price);
	
	public Boolean dispense(Product product);
	
}
