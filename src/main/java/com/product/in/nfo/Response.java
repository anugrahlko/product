package com.product.in.nfo;

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
