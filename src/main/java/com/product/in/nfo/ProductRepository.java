package com.product.in.nfo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

	//List<Contact> findByFirstname(String firstname);
	Product findByProdId(String id);
	List<Product> findByProductName(String name);
	List<Product> findByBrandName(String name);
	
	//Product findByEmail(String lastname);
	

}
