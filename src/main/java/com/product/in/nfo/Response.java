package com.product.in.nfo;

import javax.persistence.Entity;
import javax.persistence.Id;



public class Response<T,U> {

	private T data;
	private U message;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public U getMessage() {
		return message;
	}
	public void setMessage(U message) {
		this.message = message;
	}
	
}
