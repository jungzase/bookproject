package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BookService;

@WebServlet("/azabook/books/*")
public class BookController extends HttpServlet{
	private BookService service = new BookService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getPathInfo();
		if(cmd == null) cmd = "/search";
		String page = "";
		try {
			switch(cmd) {
			case "/search" : {
				page = "books/searchMain.jsp";
				req.setAttribute("mainpage", page);
				String keyword = req.getParameter("keyword");
                String category = req.getParameter("category");
				boolean hasAnyParam = (keyword != null || category != null);
				
				if (hasAnyParam) {
                    // keyword가 빈칸이면 전체 나오게 DAO에서 처리
                    req.setAttribute("books", service.search(keyword, category));
                    req.setAttribute("keyword", keyword);
                    req.setAttribute("category", category);
                }
				
				// layout으로 렌더링
                req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
                break;
			}
			case "/detail" :
				page = "books/detail.jsp";
				req.setAttribute("mainpage", page);
				
				String isbn = req.getParameter("isbn");
				Book book = service.detail(isbn);
				req.setAttribute("book", book);
				
				req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
				break;
			default:
				break;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
