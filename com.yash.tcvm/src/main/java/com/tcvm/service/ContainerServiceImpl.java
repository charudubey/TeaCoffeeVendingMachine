package com.tcvm.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.tcvm.dao.MaterialDao;
import com.tcvm.dao.MaterialDaoImpl;
import com.tcvm.vo.Container;
import com.tcvm.vo.Material;
import com.tcvm.vo.MaterialType;
import com.tcvm.vo.Product;

public class ContainerServiceImpl implements ContainerService {

	@Override
	public Boolean checkAvailableQuantity(Product product) {
		
		MaterialDao materialDao = new MaterialDaoImpl();
		
		Map<MaterialType, Material> materialMap = materialDao.getMaterial(product.getProductType());
		
		Double availableTeaCapacity = Container.availableTeaCapacity - 
				Optional.ofNullable(materialMap.get(MaterialType.TEA).getMaterialConsumptionQuantity()).orElse(0.0) * product.getQuantity();
		
		Double availableCoffeeCapacity = Container.availableCoffeeCapacity - 
				Optional.ofNullable(materialMap.get(MaterialType.COFFEE).getMaterialConsumptionQuantity()).orElse(0.0) * product.getQuantity();
		
		Double availableMilkCapacity = Container.availableMilkCapacity - 
				Optional.ofNullable(materialMap.get(MaterialType.MILK).getMaterialConsumptionQuantity()).orElse(0.0) * product.getQuantity();

		Double availableWaterCapacity = Container.availableWaterCapacity - 
				Optional.ofNullable(materialMap.get(MaterialType.WATER).getMaterialConsumptionQuantity()).orElse(0.0) * product.getQuantity();
		
		Double availableSugarCapacity = Container.availableSugarCapacity - 
				Optional.ofNullable(materialMap.get(MaterialType.SUGAR).getMaterialConsumptionQuantity()).orElse(0.0) * product.getQuantity();

		
        if(availableTeaCapacity >=0 && availableCoffeeCapacity >= 0 && 
        		availableMilkCapacity >=0 && availableWaterCapacity >= 0 && availableSugarCapacity >= 0)
              return true;
		 
		return false;
	}

}
