package com.tcvm.service;

import com.tcvm.vo.Product;

public class ProductDispenserServiceImpl implements ProductDispenserService{

	ContainerService containerService = new ContainerServiceImpl();
	
	@Override
	public Boolean placeOrder(Product product) {
		
		if(containerService.checkAvailableQuantity(product)){
			return true;
		}
		
		return false;
	}

	@Override
	public Double calculateProductCost(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double verifyAndReturnFunds(Double Price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean dispense(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

}
