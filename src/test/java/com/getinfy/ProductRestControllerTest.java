package com.getinfy;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getinfy.entity.ProductEntity;
import com.getinfy.rest.ProductRest;
import com.getinfy.service.ProductService;

@WebMvcTest(value = ProductRest.class)
public class ProductRestControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	ProductService service;

	public String jsonConverter(ProductEntity entity) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(entity);
		return writeValueAsString;
	}

	@Test
	void saveProduct() throws Exception {

		ProductEntity entity = new ProductEntity(1, "soap", 230.0);
		when(service.saveProduct(entity)).thenReturn(true);
		mvc.perform(MockMvcRequestBuilders.post("/save").contentType(MediaType.APPLICATION_JSON)
				.content(jsonConverter(entity))).andExpect(status().isCreated());

	}

	@Test
	void saveProductNegativeTest() throws Exception {

		ProductEntity entity = new ProductEntity(1, "soap", 230.0);
		when(service.saveProduct(entity)).thenReturn(false);
		mvc.perform(MockMvcRequestBuilders.post("/save").contentType(MediaType.APPLICATION_JSON)
				.content(jsonConverter(entity))).andExpect(status().isBadRequest());

	}

	@Test
	void getProductTest() throws Exception {

		List<ProductEntity> products = new ArrayList<>();
		products.add(new ProductEntity(1, "soap", 230.0));
		products.add(new ProductEntity(2, "Mouse", 450.0));
		when(service.getProducts()).thenReturn(products);
		mvc.perform(MockMvcRequestBuilders.get("/getProducts").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void getProductsTestNegative() throws Exception {

		List<ProductEntity> products = new ArrayList<>();
		when(service.getProducts()).thenReturn(null);
		mvc.perform(MockMvcRequestBuilders.get("/getProducts").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}
	
	@Test
	void getProduct() throws Exception {
		int id=1;
		
		ProductEntity entity = new ProductEntity(1, "soap", 230.0);
		when(service.getProduct(id)).thenReturn(entity);
		mvc.perform(MockMvcRequestBuilders.get("/getProduct/"+id).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		
		
	}
	
	@Test
	void deleteProductTest() throws Exception {
		int id=1;
		
		ProductEntity entity = new ProductEntity(1, "soap", 230.0);
		when(service.deleteProduct(id)).thenReturn(true);
		mvc.perform(MockMvcRequestBuilders.delete("/delete/"+id).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		
		
	}
	
	@Test
	void UpdateProductTest() throws Exception {
		int id=1;
		
		ProductEntity entity = new ProductEntity(1, "soap", 230.0);
		when(service.updateProduct(entity)).thenReturn(true);
		mvc.perform(MockMvcRequestBuilders.put("/update").contentType(MediaType.APPLICATION_JSON)
				.content(jsonConverter(entity))).andExpect(status().isOk());
		
		
		
	}
	@Test
	void UpdateNegativeTest() throws Exception{
		ProductEntity entity = new ProductEntity(1, "soap", 230.0);
		when(service.updateProduct(entity)).thenReturn(false);
		mvc.perform(MockMvcRequestBuilders.put("/update").contentType(MediaType.APPLICATION_JSON)
				.content(jsonConverter(entity))).andExpect(status().isOk());
		
	}
	
	
	

}
