package com.product.in.nfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
 
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prodId", updatable = false, nullable = false)
	private String prodId;
	
	@Column(name = "productName", nullable = false)
	private String productName;
	
	@Column(name = "brandName", nullable = false)
	private String brandName;
	
	@Column(name = "price", nullable = false)
	private String price;
	
	@Column(name = "rating")// create one different class for it
	private String rating;
	
	@Column(name = "offer", nullable = false)
	private boolean offer;

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public boolean isOffer() {
		return offer;
	}

	public void setOffer(boolean offer) {
		this.offer = offer;
	}
	
	
}
