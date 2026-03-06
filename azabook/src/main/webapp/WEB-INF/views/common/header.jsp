<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header style="padding:16px 24px; background:#111; color:#7ee;">
  <div style="max-width:980px; margin:0 auto; display:flex; align-items:center; justify-content:space-between; gap:12px;">

    <!-- 로고 -->
    <div>
      <a style="color:#7ee; text-decoration:none; font-weight:bold;"
         href="${pageContext.request.contextPath}/azabook">AZA BOOK</a>
    </div>

    <!-- 검색 -->
    <form style="display:flex; gap:8px;"
          action="${pageContext.request.contextPath}/azabook/books/search" method="get">
      <select name="category">
        <option value="">전체</option>
        <option value="자서전">자서전</option>
        <option value="문제집">문제집</option>
        <option value="동화">동화</option>
        <option value="소설">소설</option>
      </select>
      <input name="keyword" placeholder="제목/저자 검색" value="${param.keyword}" />
      <button type="submit">검색</button>
    </form>

    <!-- 오른쪽 버튼 영역 -->
    <div style="display:flex; gap:10px; align-items:center;">

      <!-- 관리자 모드 토글 -->
      <c:choose>
        <c:when test="${sessionScope.isAdmin}">
          <a href="${pageContext.request.contextPath}/azabook/admin/toggle"
             style="color:#7ee;">관리자 ON</a>
        </c:when>
        <c:otherwise>
          <a href="${pageContext.request.contextPath}/azabook/admin/toggle"
             style="color:#7ee;">관리자 OFF</a>
        </c:otherwise>
      </c:choose>

      <!-- 관리자 모드일 때만 노출 -->
      <c:if test="${sessionScope.isAdmin}">
        <a href="${contextPath}/azabook/admin/books/form"
           style="color:#7ee;">추가</a>

        
      </c:if>
    </div>

  </div>
</header>