package com.getinfy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.getinfy.entity.ProductEntity;
import com.getinfy.repo.ProductRepo;
import com.getinfy.serviceimpl.ProductServiceImpl;

@SpringBootTest
public class ProductServiceImplTest {

	@Mock
	ProductRepo productrepo;

	@InjectMocks
	ProductServiceImpl productservice;

	@Test
	void saveTest() {

		ProductEntity entity = new ProductEntity(2, "Mouse", 230.0);

		productservice.saveProduct(entity);
		verify(productrepo).save(entity);
	}

	@Test
	void saveNegativeTest() {

		ProductEntity entity = new ProductEntity(2, "Mouse", 230.0);

		productservice.saveProduct(null);
	}

	@Test
	void getProducts() {
		List<ProductEntity> products = new ArrayList<>();

		products.add(new ProductEntity(3, "Mouse", 240.0));
		products.add(new ProductEntity(4, "Pen", 345.0));
		ProductEntity p2 = new ProductEntity(4, "Pen", 280.0);
		when(productservice.getProducts()).thenReturn(products);

	}
	
	@Test
	void NegativeProductsTest() {
		List<ProductEntity> products = new ArrayList<>();
		when(productservice.getProducts()).thenReturn(products);
		
		
	}
	
	@Test
	void UpdateTest() {

		ProductEntity entity = new ProductEntity(2, "Mouse", 230.0);

		productservice.updateProduct(entity);
		verify(productrepo).save(entity);
	}
	@Test
	void deleteProduct() {
		ProductEntity entity = new ProductEntity(2, "Mouse", 230.0);
		productservice.deleteProduct(entity.getId());
	}
	
	@Test
	void getProduct() {
		int id=2;
		ProductEntity entity = new ProductEntity(2, "Mouse", 230.0);
		ProductEntity product = productservice.getProduct(id);
		Optional<ProductEntity> findById = productrepo.findById(id);
		ProductEntity productEntity = findById.get();
		assertEquals(entity, productEntity);
	}
	
	
}
