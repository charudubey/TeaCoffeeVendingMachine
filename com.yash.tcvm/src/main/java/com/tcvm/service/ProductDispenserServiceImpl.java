package com.tcvm.service;

import com.tcvm.dao.ProductDao;
import com.tcvm.dao.ProductDaoImpl;
import com.tcvm.vo.Product;

public class ProductDispenserServiceImpl implements ProductDispenserService{

	ContainerService containerService = new ContainerServiceImpl();
	ProductDao productDao = new ProductDaoImpl();
	
	@Override
	public Double placeOrder(Product product) {
		
		if(containerService.checkAvailableQuantity(product)){
			return calculateProductCost(product);
		}
		
		return null;
	}

	@Override
	public Double calculateProductCost(Product product) {
		return productDao.getProductPrice(product);
	}

	@Override
	public void dispense(Product product) {
		
		System.out.println("Dispensing " + product.getProductType().getType());
		containerService.updateContainerCapacity(product);
		
	}
	

}
