<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
  <c:when test="${book != null}">
    <h2>책 수정</h2>
  </c:when>
  <c:otherwise>
    <h2>책 추가</h2>
  </c:otherwise>
</c:choose>

<form>
  <p>ISBN</p>
  <input name="isbn" value="${book != null ? book.isbn : ''}" ${book != null ? 'readonly' : ''}/>
  <br/><br/>

  <p>제목</p>
  <input name="productName" value="${book != null ? book.productName : ''}" />
  <br/><br/>

  <p>저자</p>
  <input name="author" value="${book != null ? book.author : ''}" />
  <br/><br/>

  <p>출판사</p>
  <input name="publisher" value="${book != null ? book.publisher : ''}" />
  <br/><br/>

  <p>출판일</p>
  <input name="publicationDate" value="${book != null ? book.publicationDate : ''}" />
  <br/><br/>

  <p>가격</p>
  <input name="price" value="${book != null ? book.price : ''}" />
  <br/><br/>

  <p>카테고리</p>
  <input name="category" value="${book != null ? book.category : ''}" />
  <br/><br/>

  <p>이미지 링크</p>
  <input name="imgLink" value="${book != null ? book.imgLink : ''}" />
  <br/><br/>

  <!-- 저장은 다른 사람이 구현 -->
  <button type="button" disabled>저장(구현 예정)</button>
</form>