<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <h2>검색 결과</h2>

<c:choose>
  <c:when test="${books == null}">
    <p>검색어를 입력하거나 빈칸으로 검색하면 전체 리스트가 나옵니다.</p>
  </c:when>

  <c:when test="${empty books}">
    <p>검색 결과가 없습니다.</p>
  </c:when>

  <c:otherwise>
    <div class="result-list">
      <c:forEach var="b" items="${books}">
        <div class="result-item">

          <!-- 왼쪽: 표지 -->
          <a class="result-thumb" href="${pageContext.request.contextPath}/azabook/books/detail?isbn=${b.isbn}">
            <c:choose>
              <c:when test="${empty b.imgLink}">
                <div class="img-placeholder">NO IMG</div>
              </c:when>
              <c:otherwise>
                <img class="cover" src="${b.imgLink}" alt="${b.productName}">
              </c:otherwise>
            </c:choose>
          </a>

          <!-- 오른쪽: 정보 -->
          <div class="result-info">
            <a class="result-title" href="${pageContext.request.contextPath}/azabook/books/detail?isbn=${b.isbn}">
              ${b.productName}
            </a>

            <div class="result-meta">
              <span>저자: ${b.author}</span>
            </div>

            <div class="result-meta">
              <span>출판사: ${b.publisher}</span>
              <span class="dot">·</span>
              <span>출판일: ${b.publicationDate}</span>
            </div>

            <div class="result-meta">
              <span>카테고리: ${b.category}</span>
            </div>
            
            <div class="result-meta price">
 			 <span>가격: ${b.price}원</span>
			</div>
			  <c:if test="${sessionScope.isAdmin}">
		        <a class="btn" href="${contextPath}/azabook/admin/books/form?isbn=${b.isbn}">
		          수정
		        </a>
		      </c:if>
          </div>
        </div>
      </c:forEach>
    </div>
  </c:otherwise>
</c:choose>