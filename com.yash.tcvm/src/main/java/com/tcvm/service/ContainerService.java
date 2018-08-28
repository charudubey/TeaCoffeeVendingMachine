package com.tcvm.service;

import com.tcvm.vo.Material;
import com.tcvm.vo.Product;

public interface ContainerService {

	public Boolean checkAvailableQuantity(Product product);
}
