package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import controller.Book;
import controller.DB;

public class H2BookDAO implements BookDAO {
	
	 @Override
	    public List<Book> recommend3() {
	        String sql = "SELECT * FROM books WHERE id IN (1,2,3) ORDER BY id ASC";

	        List<Book> list = new ArrayList<>();

	        try (
	            Connection conn = DB.getConnection();
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery()
	        ) {
	            while (rs.next()) {
	                Book b = new Book();
	                // id 컬럼을 Book에 넣을지 말지는 선택 (Book에 필드가 있으면 넣어)
	                // b.setId(rs.getInt("id"));
	                // 컬럼명 주의: DB는 product_name 같은 스네이크 케이스
	                b.setProductName(rs.getString("product_name"));
	                b.setIsbn(rs.getString("isbn"));
	                b.setAuthor(rs.getString("author"));
	                b.setPublisher(rs.getString("publisher"));
	                b.setPublicationDate(rs.getDate("publication_date"));
	                // 가격은 홈에서 안 쓰더라도 Book에 필드가 있으면 넣어두는 게 좋음
	                // (Book에 price가 없으면 이 줄은 지워)
	                b.setPrice(rs.getInt("price"));
	                b.setCategory(rs.getString("category"));
	                b.setImgLink(rs.getString("img_link"));
	                list.add(b);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	@Override
	public List<Book> search(String keyword, String category) {
		keyword = (keyword == null) ? "" : keyword.trim();
	    category = (category == null) ? "" : category.trim();
	    
	    boolean hasKeyword = !keyword.isEmpty();
	    boolean hasCategory = !category.isEmpty();
	    
	    StringBuilder sql = new StringBuilder("SELECT * FROM books WHERE 1=1");
	    List<Object> params = new ArrayList<>();
	 // 키워드: 제목 or 저자
	    if (hasKeyword) {
	        sql.append(" AND (LOWER(product_name) LIKE ? OR LOWER(author) LIKE ?)");
	        String like = "%" + keyword.toLowerCase() + "%";
	        params.add(like);
	        params.add(like);
	    }

	    // 카테고리
	    if (hasCategory) {
	        sql.append(" AND category = ?");
	        params.add(category);
	    }
	    sql.append(" ORDER BY id ASC");
	    List<Book> list = new ArrayList<Book>();
	    
	    try (Connection conn = DB.getConnection();
	            PreparedStatement ps = conn.prepareStatement(sql.toString())) {

	           for (int i = 0; i < params.size(); i++) {
	               ps.setObject(i + 1, params.get(i));
	           }

	           try (ResultSet rs = ps.executeQuery()) {
	               while (rs.next()) {
	                   Book b = new Book();
	                   b.setId(rs.getInt("id"));
	                   b.setProductName(rs.getString("product_name"));
	                   b.setIsbn(rs.getString("isbn"));
	                   b.setAuthor(rs.getString("author"));
	                   b.setPublisher(rs.getString("publisher"));
	                   b.setPublicationDate(rs.getDate("publication_date"));
	                   b.setPrice(rs.getInt("price"));
	                   b.setCategory(rs.getString("category"));
	                   b.setImgLink(rs.getString("img_link"));
	                   list.add(b);
	               }
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       return list;
	}

	@Override
	public Book findByIsbn(String isbn) {
		String sql = "SELECT * FROM books WHERE isbn = ?";
		Book b = new Book();

        try (
            Connection conn = DB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
 
        ) {
            ps.setString(1, isbn);
            try(ResultSet rs = ps.executeQuery()){
            	if(rs.next()) {
	            	b.setId(rs.getInt("id"));
	                b.setProductName(rs.getString("product_name"));
	                b.setIsbn(rs.getString("isbn"));
	                b.setAuthor(rs.getString("author"));
	                b.setPublisher(rs.getString("publisher"));
	                b.setPublicationDate(rs.getDate("publication_date"));
	                b.setPrice(rs.getInt("price"));
	                b.setCategory(rs.getString("category"));
	                b.setImgLink(rs.getString("img_link"));
	                return b;
            	}
            	return null;
            }      
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }  
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
