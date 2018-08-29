package com.yash.tcvm.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.tcvm.dao.MaterialDao;
import com.tcvm.dao.MaterialDaoImpl;
import com.tcvm.vo.Material;
import com.tcvm.vo.MaterialType;
import com.tcvm.vo.ProductType;

public class MaterialDaoImplTest {

	@Test
	public void shouldReturnMaterialList() {
		MaterialDao materialDao = new MaterialDaoImpl();
		
		List<Material> actual = materialDao.materialList();
		
		assertEquals(20, actual.size());
	}
	
	@Test
	public void shouldReturnMaterialListForSpecificProductType() {
		MaterialDao materialDao = new MaterialDaoImpl();
		Map<MaterialType, Material> expected = new HashMap<>();
		expected.put(MaterialType.TEA, new Material(MaterialType.TEA, 3.0, 0.0, ProductType.BLACK_TEA));
		expected.put(MaterialType.WATER, new Material(MaterialType.WATER, 100.0, 12.0, ProductType.BLACK_TEA));
		expected.put(MaterialType.SUGAR, new Material(MaterialType.SUGAR, 15.0, 2.0, ProductType.BLACK_TEA));
		expected.put(MaterialType.COFFEE, new Material(MaterialType.COFFEE, 0.0, 0.0, ProductType.BLACK_TEA));
		expected.put(MaterialType.MILK, new Material(MaterialType.MILK, 0.0, 0.0, ProductType.BLACK_TEA));
		
		
		Map<MaterialType, Material> actual = materialDao.getMaterial(ProductType.BLACK_TEA);

        assertEquals(expected.size(), actual.size());
	}

}
