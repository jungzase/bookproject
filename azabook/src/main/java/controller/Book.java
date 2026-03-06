package controller;

import java.sql.Date;

public class Book {
	private int id;
	private String productName;
	private String isbn;
	private String author;
	private String publisher;
	private Date publicationDate;
	private int price;
	private String category;
	private String imgLink;
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(int id, String productName, String isbn, String author, String publisher, Date publicationDate, int price,
			String category,String imgLink) {
		this.id=id;
		this.productName = productName;
		this.isbn = isbn;
		this.author = author;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.price = price;
		this.category = category;
		this.imgLink = imgLink;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", productName=" + productName + ", isbn=" + isbn + ", author=" + author
				+ ", publisher=" + publisher + ", publicationDate=" + publicationDate + ", price=" + price
				+ ", category=" + category + ", imgLink=" + imgLink + "]";
	}

	

}
