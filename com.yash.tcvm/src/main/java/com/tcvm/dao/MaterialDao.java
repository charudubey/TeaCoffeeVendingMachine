package com.tcvm.dao;

import java.util.List;
import java.util.Map;

import com.tcvm.vo.Material;
import com.tcvm.vo.MaterialType;
import com.tcvm.vo.ProductType;

public interface MaterialDao {

	public List<Material> materialList();
	
	public Map<MaterialType, Material> getMaterial(ProductType productType);
	
}
