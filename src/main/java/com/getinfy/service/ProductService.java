package com.getinfy.service;

import java.util.List;

import com.getinfy.entity.ProductEntity;

public interface ProductService {

	public boolean saveProduct(ProductEntity entity);

	public List<ProductEntity> getProducts();

	public ProductEntity getProduct(Integer id);

	public boolean deleteProduct(Integer id);
	
	public boolean updateProduct(ProductEntity entity);
	
	

}
