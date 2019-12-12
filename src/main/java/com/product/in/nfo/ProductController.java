package com.product.in.nfo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	 //@Autowired
	 private ProductService productService;
	
	//public String source = "file";
	
	@Autowired
	public ProductController( ProductService productService) {	//ProductService productService) {
		this.productService= productService;
		//this.productService.source = "File";
		System.out.println("In PC constructor");
	}
	 
	
	@Secured({"ROLE_USER","ROLE_ADMIN","ROLE_MGMT"})
	@RequestMapping(method = RequestMethod.GET,value="/product")				// Get All Contact
	public Response<?, ?> getAllContact(){
		try {
			Response<List<Product>, String> response2= new Response<List<Product>, String>() ;
			response2.setData(productService.getAllProduct());
			response2.setMessage( HttpStatus.OK.toString());
			return response2;
		} catch (Exception e) {
			Response<String, String> response1 =new Response<String, String>();
			response1.setData("Somthing Went Wrong "+e.getMessage());
			response1.setMessage(e.getLocalizedMessage());
			return response1;
		}
		
	}
	
	
	@Secured({"ROLE_USER","ROLE_ADMIN","ROLE_MGMT"})
	@RequestMapping(method = RequestMethod.GET,value="/product/id/{id}")		// Get Contact By First Name
	public Product getContactById(@PathVariable String id){
		return productService.getProductById(id);
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN","ROLE_MGMT"})
	@RequestMapping(method = RequestMethod.GET,value="/product/name/{name}")		// Get Contact By First Name
	public List<Product> getContactByName(@PathVariable String name){
		return productService.getProductByName(name);
	}
	@Secured({"ROLE_USER","ROLE_ADMIN","ROLE_MGMT"})
	@RequestMapping(method = RequestMethod.GET,value="/product/brand/{name}")		// Get Contact By Last Name
	public List<Product> getProductByBrand(@PathVariable String name){
		return productService.getProductByBrand(name);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_MGMT"})
	@RequestMapping(method = RequestMethod.POST,value= "/product")	// Post Contact
	@ResponseStatus(HttpStatus.CREATED)
	public Response<String, String> insertProduct(@RequestBody Product product ){
		Response<String, String> response1= new Response<String, String>() ;
		try {
			
			response1.setData(HttpStatus.CREATED.toString());
			response1.setMessage(productService.insertProduct(product));
			return response1;
		} catch (Exception e) {
			response1.setData("Somthing Went Wrong");
			response1.setMessage( "Creation Failed");
			return response1;
		}
	}
	@Secured({"ROLE_ADMIN","ROLE_MGMT"})
	@RequestMapping(method = RequestMethod.PUT,value= "/product/{id}")	// Put Contact
	@ResponseStatus(HttpStatus.CREATED)
	public  Response<String, String>  updateProduct(@RequestBody Product product, @PathVariable String id ){
		Response<String, String> response1= new Response<String, String>() ;
		try {
			response1.setData(HttpStatus.CREATED.toString());
			response1.setMessage(productService.updateProduct(product,id));
			return response1;
		} catch (Exception e) {
			response1.setData("Somthing Went Wrong");
			response1.setMessage( "Creation Failed");
			return response1;
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE,value= "/product/{id}")	// Delete Contact
	public Response<?, String> deleteProduct(@PathVariable String id ){
		Response<String, String> response1= new Response<String, String>() ;
		try {
			
			response1.setData(HttpStatus.OK.toString());
			response1.setMessage( productService.deleteProductbyId(id));
			return response1;
		} catch (Exception e) {
			response1.setData("Somthing Went Wrong");
			response1.setMessage( "Deletion Failed");
			return response1;
		}
	}
	@ExceptionHandler({RuntimeException.class})
	@ResponseBody	
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return new ResponseEntity<String>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}
