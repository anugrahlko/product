package com.product.in.nfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	//@Autowired
	private ProductRepository productRepository ;
	
	//@Autowired
	//private ProductRepository productDBRepository;
	//@Autowired
	private ProductFileRepository productFileRepository;
	
	
	@Autowired
	public ProductService( ProductFileRepository productFileRepository,ProductRepository productRepository) {
		System.out.println(source);
		if(source.equalsIgnoreCase("file")) {
			this.productRepository = productFileRepository;
		}
		else {
			this.productRepository = productRepository;
		}
	}
	
	public static String source="file";

	public List<Product> getAllProduct() {
		
		List<Product> product = new ArrayList<Product>() ;
		productRepository.findAll().forEach(product::add);
		return product;
	}

	public Product getProductById(String id) {
		return productRepository.findByProdId(id);
	}
	
	public List<Product> getProductByName(String name) {
		return productRepository.findByProductName(name);
	}

	public List<Product> getProductByBrand(String number) {
		return productRepository.findByBrandName(number);
	}

	public String insertProduct(Product product) {
		// TODO Auto-generated method stub
		productRepository.save(product);
		
		return "Saved";
	}

	public String updateProduct(Product product, String id) {
		// TODO Auto-generated method stub
		Product currentProduct = getProductById(id);
		currentProduct.setProductName(product.getProductName());
		currentProduct.setBrandName(product.getBrandName());
		currentProduct.setPrice(product.getPrice());
		currentProduct.setRating(product.getRating());
		productRepository.save(currentProduct);
		return "Updated";
	}

	
	public String deleteProductbyId(String id) {
		Product currentProduct = getProductById(id);
		productRepository.delete(currentProduct);
		return "Deleted";
	}

}
