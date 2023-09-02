package com.getinfy.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.getinfy.entity.ProductEntity;
import com.getinfy.service.ProductService;

@RestController
public class ProductRest {

	@Autowired
	ProductService productService;

	@PostMapping("/save")
	public ResponseEntity<String> saveProduct(@RequestBody ProductEntity entity) {

		boolean saveProduct = productService.saveProduct(entity);

		if (saveProduct) {

			return new ResponseEntity<String>("Product Saved", HttpStatus.CREATED);

		}

		return new ResponseEntity<String>("Product not Saved", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getProducts")
	public ResponseEntity<List<ProductEntity>> getProducts() {

		List<ProductEntity> products = productService.getProducts();
		if (products != null) {
			return new ResponseEntity<>(products, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/getProduct/{id}")
	public ResponseEntity<ProductEntity> getProduct(@PathVariable Integer id) {

		ProductEntity product = productService.getProduct(id);

		return new ResponseEntity<ProductEntity>(product, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProducts(@PathVariable Integer id) {

		productService.deleteProduct(id);

		return new ResponseEntity<String>("Product Deleted", HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<String> updateProducts(@RequestBody ProductEntity entity) {

		productService.updateProduct(entity);

		return new ResponseEntity<String>("Product Upated", HttpStatus.OK);

	}

}
