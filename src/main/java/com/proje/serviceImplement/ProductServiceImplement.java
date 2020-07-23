package com.proje.serviceImplement;

import java.util.List;

import com.proje.Repository.ProductRepository;
import com.proje.Repository.implement.ProductRepositoryImplement;
import com.proje.Service.ProductService;
import com.proje.model.Product;

public class ProductServiceImplement implements ProductService   {
private ProductRepository productRepository=new ProductRepositoryImplement();

@Override
public Product saveProduct(Product product) {
	return productRepository.saveProduct(product);
}

@Override
public boolean saveBatchProduct(List<Product> products) {
	return productRepository.saveBatchProduct(products);
}

@Override
public Product updateProduct(Product product) {
	return productRepository.updateProduct(product);
}

@Override
public boolean removeProduct(int id) {
	return productRepository.removeProduct(id);
}

@Override
public Product findProductById(int id) {
	return productRepository.findProductById(id);
}

@Override
public List<Product> findProducts() {
	return productRepository.findProducts();
} 
}
