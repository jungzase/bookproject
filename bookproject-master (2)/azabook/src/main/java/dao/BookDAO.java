package dao;

import java.util.List;

import controller.Book;

public interface BookDAO {
	public List<Book> recommend3();
	public List<Book> search(String keyword, String category);
	public Book findByIsbn(String isbn);
	public void insert(Book book);
	public void update(Book book);
	public void delete(String isbn);
}
