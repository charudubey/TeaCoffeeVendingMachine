package com.tcvm.service;

import java.util.Map;

import com.tcvm.dao.ProductDao;
import com.tcvm.dao.ProductDaoImpl;
import com.tcvm.vo.Product;

public class ProductDispenserServiceImpl implements ProductDispenserService{

	ContainerService containerService;
	ProductDao productDao;
	
	public ProductDispenserServiceImpl() {
		containerService = new ContainerServiceImpl();
		productDao = new ProductDaoImpl();
	}
	
	/*public ProductDispenserServiceImpl(ContainerService containerService, ProductDao productDao) {
		this.containerService = containerService;
		this.productDao = productDao;
	}*/
	
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
		
		containerService.updateContainerCapacity(product);
		updateItemsSoldCount(product);
		
	}

	@Override
	public void updateItemsSoldCount(Product product) {

		if(Product.totalItemsSold.containsKey(product.getProductType()))
			Product.totalItemsSold.put(product.getProductType(),Product.totalItemsSold.get(product.getProductType()) + product.getQuantity());
		else
			Product.totalItemsSold.put(product.getProductType(), product.getQuantity());
		
	}
	
	
	

}
