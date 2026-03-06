<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>도서 상세</h2>

<c:choose>
  <c:when test="${book == null}">
    <p>해당 도서를 찾을 수 없습니다.</p>
    <a href="${pageContext.request.contextPath}/azabook/books/search">검색으로 돌아가기</a>
  </c:when>

  <c:otherwise>
    <div style="display:flex; gap:18px; align-items:flex-start;">

  <!-- 이미지 영역 -->
  <div style="width:220px;">
    <c:choose>
      <c:when test="${empty book.imgLink}">
        <div class="img-placeholder" style="width:100%; aspect-ratio:3/4;">NO IMG</div>
      </c:when>
      <c:otherwise>
        <img class="cover" src="${book.imgLink}" alt="${book.productName}" />
      </c:otherwise>
    </c:choose>
  </div>

  <!-- 정보 + 버튼 영역 -->
  <div style="flex:1; display:flex; justify-content:space-between; gap:20px;">

    <!-- 왼쪽: 정보 -->
    <div>
      <h3 style="margin-top:0;">${book.productName}</h3>
      <p>저자: ${book.author}</p>
      <p>출판사: ${book.publisher}</p>
      <p>출판일: ${book.publicationDate}</p>
      <p>가격: ${book.price}원</p>
      <p>카테고리: ${book.category}</p>
      <p>ISBN: ${book.isbn}</p>
    </div>

    <!-- 오른쪽: 버튼(세로) -->
    <div style="display:flex; flex-direction:column; gap:10px; min-width:120px;">
      <a class="btn" href="javascript:history.back()">← 뒤로</a>

      <c:if test="${sessionScope.isAdmin}">
        <a class="btn" href="${contextPath}/azabook/admin/books/form?isbn=${b.isbn}">
          수정
        </a>
      </c:if>
    </div>

  </div>
</div>
  </c:otherwise>
</c:choose>