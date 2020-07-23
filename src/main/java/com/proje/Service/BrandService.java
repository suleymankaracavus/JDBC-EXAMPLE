package com.proje.Service;

import java.util.List;

import com.proje.model.Brand;

public interface BrandService {
	Brand saveBrand(Brand brand);
	
	List<Brand> findBrands();

	Brand findBrandById(int id);

}
