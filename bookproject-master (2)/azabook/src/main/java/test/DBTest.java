package test;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DB;

//@WebServlet("/dbtest")
public class DBTest extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain; charset=UTF-8");
		
		try(Connection conn = DB.getConnection()) {
			resp.getWriter().println("H2 연결 성공!");
            resp.getWriter().println("DB: " + conn.getMetaData().getURL());
        } catch (Exception e) {
            resp.getWriter().println("H2 연결 실패!");
            resp.getWriter().println("원인: " + e.getClass().getName());
            resp.getWriter().println(e.getMessage());
        }
	}
}
