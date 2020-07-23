package com.proje.Repository;

import java.util.List;

import com.proje.model.Brand;

public interface BrandRepository {
	
	Brand saveBrand(Brand brand);
	List<Brand> findBrands();
	Brand findBrandById(int id);
	
	
	

	


}
