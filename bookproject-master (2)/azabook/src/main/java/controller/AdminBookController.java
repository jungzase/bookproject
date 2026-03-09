package controller;

import java.io.IOException;
import java.sql.Date; // Date 변환을 위해 필요

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BookService;

@WebServlet("/azabook/admin/books/*")
public class AdminBookController extends HttpServlet {
    
    private BookService service = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // 관리자 모드 아닐 때 접근 막기
        Boolean isAdmin = (Boolean) req.getSession().getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            resp.sendRedirect(req.getContextPath() + "/azabook");
            return;
        }

        String cmd = req.getPathInfo();
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
            } 
            else if ("/delete".equals(cmd)) {
                String isbn = req.getParameter("isbn");
                if (isbn != null && !isbn.isBlank()) {
                    service.delete(isbn); // DB에서 책 삭제
                }
                // 삭제 완료 후 도서 검색(리스트) 페이지로 이동
                resp.sendRedirect(req.getContextPath() + "/azabook/books/search");
            }
            else {
                resp.sendError(404);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        Boolean isAdmin = (Boolean) req.getSession().getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            resp.sendRedirect(req.getContextPath() + "/azabook");
            return;
        }

        String cmd = req.getPathInfo();

        try {

            if ("/save".equals(cmd)) {
                
                String isbn = req.getParameter("isbn");
                String productName = req.getParameter("productName");
                String author = req.getParameter("author");
                String publisher = req.getParameter("publisher");
                String publicationDate = req.getParameter("publicationDate");
                String price = req.getParameter("price");
                String category = req.getParameter("category");
                String imgLink = req.getParameter("imgLink");
                
                String isUpdate = req.getParameter("isUpdate"); 

                Book book = new Book();
                book.setIsbn(isbn);
                book.setProductName(productName);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setCategory(category);
                book.setImgLink(imgLink);
                
                // 날짜와 숫자는 형변환 필요
                if (publicationDate != null && !publicationDate.isEmpty()) {
                    book.setPublicationDate(Date.valueOf(publicationDate)); 
                }
                if (price != null && !price.isEmpty()) {
                    book.setPrice(Integer.parseInt(price)); 
                }

                // 3. Service를 통해 DB 처리
                if ("true".equals(isUpdate)) {
                    service.update(book); // 수정 (Update)
                } else {
                    service.insert(book); // 새로 추가 (Insert)
                }


                resp.sendRedirect(req.getContextPath() + "/azabook/books/detail?isbn=" + isbn);
                
            } else {
                resp.sendError(404);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}