package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/azabook/admin/toggle")
public class AdminToggleController extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        boolean next = (isAdmin == null) ? true : !isAdmin;

        session.setAttribute("isAdmin", next);

        // 토글 후 홈으로
        resp.sendRedirect(req.getContextPath() + "/azabook");
	}
	
    
}
