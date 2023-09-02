package com.getinfy.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getinfy.entity.ProductEntity;
import com.getinfy.repo.ProductRepo;
import com.getinfy.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepo prodRepo;
	
	@Override
	public boolean saveProduct(ProductEntity entity) {
		
		if(entity ==null) {
			return false;
		}
		
		prodRepo.save(entity);
		
		return true;
	}
	
	
	@Override
	public boolean deleteProduct(Integer id) {
		prodRepo.deleteById(id);
		return true;
	}
	
	@Override
	public ProductEntity getProduct(Integer id) {
		
		Optional<ProductEntity> findById = prodRepo.findById(id);
		if(findById.isPresent()) {
			ProductEntity productEntity = findById.get();
			return productEntity;
		}
		return null;
	}
	
	@Override
	public List<ProductEntity> getProducts() {
		List<ProductEntity> products = prodRepo.findAll();
		return products;
	}
	
	@Override
	public boolean updateProduct(ProductEntity entity) {
		
		if(entity.getId()!=null) {
			prodRepo.save(entity);
			return true;
		}
		return false;
	}
	
	
	

}
