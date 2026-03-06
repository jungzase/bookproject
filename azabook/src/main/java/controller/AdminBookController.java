package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BookService;

@WebServlet("/azabook/admin/books/*")
public class AdminBookController extends HttpServlet{
	
	private BookService service = new BookService();
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 관리자 모드 아닐 때 접근 막기
        Boolean isAdmin = (Boolean) req.getSession().getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            resp.sendRedirect(req.getContextPath() + "/azabook");
            return;
        }

        String cmd = req.getPathInfo(); // /form
        if (cmd == null) cmd = "/form";

        try {
            if ("/form".equals(cmd)) {
                req.setAttribute("mainpage", "admin/bookform.jsp");

                // 수정 모드면 isbn으로 기존 데이터 조회해서 폼에 채우기
                String isbn = req.getParameter("isbn");
                if (isbn != null && !isbn.isBlank()) {
                    req.setAttribute("book", service.detail(isbn));
                }

                req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
            } else {
                resp.sendError(404);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

}
