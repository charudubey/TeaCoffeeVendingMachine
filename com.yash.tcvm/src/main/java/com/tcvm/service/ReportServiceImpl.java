package com.tcvm.service;

import java.util.Map;

import com.tcvm.dao.ProductDao;
import com.tcvm.dao.ProductDaoImpl;
import com.tcvm.vo.Container;
import com.tcvm.vo.Product;
import com.tcvm.vo.ProductType;

public class ReportServiceImpl implements ReportService {

	ProductDao productDao;
	
	public ReportServiceImpl() {
		productDao = new ProductDaoImpl();
	}

	/*public ReportServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}*/

	
	@Override
	public void generateTeaCoffeeReportDrinkwise() {

		Map<ProductType, Double> allItemCost = productDao.getPriceList();
		System.out.println("***********************************");
		System.out.println("Product        Quantity     Cost");
		if (!Product.totalItemsSold.isEmpty()) {
			for (Map.Entry<ProductType, Integer> item : Product.totalItemsSold.entrySet()) {
				if(allItemCost.containsKey(item.getKey())){
					System.out.println(item.getKey() + "        " + item.getValue() + "       " + allItemCost.get(item.getKey()) * item.getValue());
					
				}
			}
			System.out.println("***********************************");
		}
		
	}

	@Override
	public void generateTotalTeaCoffeeReport() {
		
		int cupQuantity = 0;
		double totalSale = 0.0;
		Map<ProductType, Double> allItemCost = productDao.getPriceList();
		if (!Product.totalItemsSold.isEmpty()) {
			for (Map.Entry<ProductType, Integer> map : Product.totalItemsSold.entrySet()) {
				cupQuantity = cupQuantity + map.getValue();
				if(allItemCost.containsKey(map.getKey())){
					totalSale = totalSale + allItemCost.get(map.getKey())*map.getValue();
					
				}
			}
			System.out.println("\n********** Total Tea-Coffee Sale **********");
			System.out.println("Total cup(s) sold: "+cupQuantity + "\nTotal cost: Rs."+totalSale);
			System.out.println("********************************************");
	}
		
	}
	
	@Override
	public void containerStatusReport() {
		ContainerService containerService = new ContainerServiceImpl();
		containerService.checkContainerStatus();
	}

	@Override
	public void refillingCounterStatus() {
		System.out.println("*************************************");
		System.out.println("Refill status for each of the container is: ");
		System.out.println("Tea container Status: "+Container.refillCounterForTeaContainer);
		System.out.println("Water container Status: "+Container.refillCounterForWaterContainer);
		System.out.println("Sugar container Status: "+Container.refillCounterForSugarContainer);
		System.out.println("Coffee container Status: "+Container.refillCounterForCoffeeContainer);
		System.out.println("Milk container Status: "+Container.refillCounterForMilkContainer);

	}

}
