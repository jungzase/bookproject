<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${book != null}">
        <h2>책 수정</h2>
    </c:when>
    <c:otherwise>
        <h2>책 추가</h2>
    </c:otherwise>
</c:choose>

<form method="post" action="${pageContext.request.contextPath}/azabook/admin/books/save">
    
    <c:if test="${book != null}">
        <input type="hidden" name="isUpdate" value="true">
    </c:if>

    <p>ISBN</p>
    <input type="text" name="isbn" value="${book != null ? book.isbn : ''}" ${book != null ? 'readonly' : ''} />
    <br/><br/>

    <p>제목</p>
    <input type="text" name="productName" value="${book != null ? book.productName : ''}"  />
    <br/><br/>

    <p>저자</p>
    <input type="text" name="author" value="${book != null ? book.author : ''}"  />
    <br/><br/>

    <p>출판사</p>
    <input type="text" name="publisher" value="${book != null ? book.publisher : ''}"  />
    <br/><br/>

    <p>출판일</p>
    <input type="date" name="publicationDate" value="${book != null ? book.publicationDate : ''}"  />
    <br/><br/>

    <p>가격</p>
    <input type="number" name="price" value="${book != null ? book.price : ''}" />
    <br/><br/>

    <p>카테고리</p>
    <input type="text" name="category" value="${book != null ? book.category : ''}" />
    <br/><br/>

    <p>이미지 링크</p>
    <input type="text" name="imgLink" value="${book != null ? book.imgLink :''}" />
    <br/><br/>

    <div style="margin-top: 20px;">
        <button type="submit" style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; cursor: pointer;">
            저장
        </button>
        
        <!-- 삭제 버튼 만들긴했는데 ai돌려도 헷갈려서 버튼만 만들어놓음 -->
        <c:if test="${book != null}">
            <button type="button" 
                    onclick="if(confirm('정말 삭제하시겠습니까?')) location.href='${pageContext.request.contextPath}/azabook/admin/books/delete?isbn=${book.isbn}'" 
                    style="background-color: #f44336; color: white; padding: 10px 20px; border: none; cursor: pointer; margin-left: 10px;">
                삭제	
            </button> 
        </c:if>
        
        <button type="button" onclick="history.back()" style="padding: 10px 20px; margin-left: 10px; cursor: pointer;">
            취소
        </button>
    </div>
</form>