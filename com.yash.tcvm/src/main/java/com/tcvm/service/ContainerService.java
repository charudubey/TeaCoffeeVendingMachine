package com.tcvm.service;

import java.util.Map;

import com.tcvm.vo.Material;
import com.tcvm.vo.MaterialType;
import com.tcvm.vo.Product;

public interface ContainerService {

	public Boolean checkAvailableQuantity(Product product);
	
	public void updateContainerCapacity(Product product);
	
	public void updateWasteCapacity(Map<MaterialType, Material> materialMap, Integer quantity);
	
	public void checkContainerStatus();
	
}
