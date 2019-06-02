package com.example.newcoder.model;

public class Book {
	private String id;
	private String name;
	private int price;
	private int pnum;
	private String category;
	private String description;
	private String img_url;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public Book(String id, String name, int price, int pnum, String category, String description, String img_url) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.pnum = pnum;
		this.category = category;
		this.description = description;
		this.img_url = img_url;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", pnum=" + pnum + ", category=" + category
				+ ", description=" + description + ", img_url=" + img_url + "]";
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
