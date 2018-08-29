package com.tcvm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tcvm.vo.Material;
import com.tcvm.vo.MaterialType;
import com.tcvm.vo.ProductType;

public class MaterialDaoImpl implements MaterialDao{

	@Override
	public List<Material> materialList() {
		
		List<Material> materialList = new ArrayList<Material>();
		
		materialList.add(new Material(MaterialType.TEA, 5.0, 1.0, ProductType.TEA));
		materialList.add(new Material(MaterialType.WATER, 60.0, 5.0, ProductType.TEA));
		materialList.add(new Material(MaterialType.MILK, 40.0, 4.0, ProductType.TEA));
		materialList.add(new Material(MaterialType.SUGAR, 15.0, 2.0, ProductType.TEA));
		materialList.add(new Material(MaterialType.COFFEE, 0.0, 0.0, ProductType.TEA));

		materialList.add(new Material(MaterialType.TEA, 3.0, 0.0, ProductType.BLACK_TEA));
		materialList.add(new Material(MaterialType.WATER, 100.0, 12.0, ProductType.BLACK_TEA));
		materialList.add(new Material(MaterialType.SUGAR, 15.0, 2.0, ProductType.BLACK_TEA));
		materialList.add(new Material(MaterialType.COFFEE, 0.0, 0.0, ProductType.BLACK_TEA));
		materialList.add(new Material(MaterialType.MILK, 0.0, 0.0, ProductType.BLACK_TEA));
		
		
		materialList.add(new Material(MaterialType.COFFEE, 4.0, 1.0, ProductType.COFFEE));
		materialList.add(new Material(MaterialType.WATER, 20.0, 3.0, ProductType.COFFEE));
		materialList.add(new Material(MaterialType.MILK, 80.0, 8.0, ProductType.COFFEE));
		materialList.add(new Material(MaterialType.SUGAR, 15.0, 2.0, ProductType.COFFEE));
		materialList.add(new Material(MaterialType.TEA, 0.0, 0.0, ProductType.COFFEE));
		
		materialList.add(new Material(MaterialType.COFFEE, 3.0, 1.0, ProductType.BLACK_COFFEE));
		materialList.add(new Material(MaterialType.WATER, 100.0, 12.0, ProductType.BLACK_COFFEE));
		materialList.add(new Material(MaterialType.SUGAR, 15.0, 2.0, ProductType.BLACK_COFFEE));
		materialList.add(new Material(MaterialType.MILK, 0.0, 0.0, ProductType.BLACK_COFFEE));
		materialList.add(new Material(MaterialType.TEA, 0.0, 0.0, ProductType.BLACK_COFFEE));
		
		
		return materialList;
	}

	@Override
	public Map<MaterialType,Material> getMaterial(ProductType productType) {
		return materialList().stream()
				.filter(n->n.getProductType().equals(productType))
				.collect(Collectors.toMap(Material:: getMaterialType, n->n));
	}

}
