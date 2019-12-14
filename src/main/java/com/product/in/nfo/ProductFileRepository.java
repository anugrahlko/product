package com.product.in.nfo;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.StandardOpenOption;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public interface ProductFileRepository extends CrudRepository<Product, String>,ProductRepository {
	
	
	
	
	@Override
	default List<Product> findAll() {
		
		Product product = new Product();
		System.out.println("All Details are here");
		
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader("C:\\\\Users\\\\anugr\\\\Desktop\\\\output.txt"))
        {
           
            Object obj = jsonParser.parse(reader);
 
            JSONObject product1 = (JSONObject) obj;
            
            String prodId = (String) product1.get("prodId");
            System.out.println(prodId);

            String productName = (String) product1.get("productName");
            System.out.println(productName);

            String brandName = (String) product1.get("brandName");
            System.out.println(brandName);
            
            product.setBrandName(brandName);
            
            String price = (String) product1.get("price");
            System.out.println(price);

            String rating = (String) product1.get("rating");
            System.out.println(rating);

            Boolean offer = (Boolean) product1.get("offer");
            System.out.println(offer);
            
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		
		
		product.setOffer(true);
		product.setPrice("200");
		product.setProdId("1");
		product.setProductName("Denim");
		product.setRating("5");
		List<Product> p = new ArrayList<Product>();
		p.add(product);
		return p;
	}
	
	@Override
	default <S extends Product> S save(S entity) {
		Gson gson = new Gson();
		String filePath="C:\\Users\\anugr\\Desktop\\output.txt";
		try(FileWriter writer = new FileWriter(filePath,true);
              BufferedWriter bw = new BufferedWriter(writer)) {
			System.out.println("Converting object to JSON string");
			String jsonString = gson.toJson(entity);
			System.out.println("Writing to File");
			//Object content;
			///Files.writeString(Paths.get(filePath), content);
			bw.write(jsonString);
			System.out.println("Written"+ entity.getBrandName());
			
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Details are Entered");
		return null;
	}
}
