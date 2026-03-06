package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BookService;


@WebServlet("/azabook")
public class HomeController extends HttpServlet{
	
	private BookService service = new BookService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page="home/homeMain.jsp";
		req.setAttribute("mainpage", page);
		
		try {
			List<Book> recommend = service.recommend3();
			req.setAttribute("recommend", recommend);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("recommend", java.util.Collections.EMPTY_LIST);
		}
		req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
		
		
	}
}
