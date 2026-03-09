package service;

import java.util.List;

import controller.Book;
import dao.BookDAO;
import dao.H2BookDAO;

public class BookService {
	private BookDAO dao;
	
	public BookService() {
		this.dao = new H2BookDAO();
	}
	public List<Book> recommend3() {
		return dao.recommend3();
	}
	public List<Book> search(String keyword, String category) {
		return dao.search(keyword, category);
	}
	public Book detail(String isbn) {
		return dao.findByIsbn(isbn);
	}
	public void update(Book book) {
	    dao.update(book);
	}
	public void insert(Book book) {
	    dao.insert(book);
	}

	public void delete(String isbn) {
	    dao.delete(isbn);
	}
}
