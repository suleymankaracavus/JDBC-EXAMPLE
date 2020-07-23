package com.proje.serviceImplement;

import java.util.List;

import com.proje.Repository.BrandRepository;
import com.proje.Repository.implement.BrandRepositoryImplement;
import com.proje.Service.BrandService;
import com.proje.model.Brand;

public class BrandServiceImplement implements BrandService {
	
private BrandRepository brandRepository=new BrandRepositoryImplement();

@Override
public Brand saveBrand(Brand brand) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Brand> findBrands() {
	// TODO Auto-generated method stub
	return brandRepository.findBrands();
}

@Override
public Brand findBrandById(int id) {
	return brandRepository.findBrandById(id);
}



}
