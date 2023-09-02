package com.getinfy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="AllProducts_tbl")
public class ProductEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private Double price;

}
