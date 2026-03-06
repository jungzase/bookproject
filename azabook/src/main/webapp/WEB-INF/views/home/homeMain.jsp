<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  .rec-wrap{
    display:flex;
    justify-content:center;   /* 가운데 정렬 */
    margin: 24px 0;
  }
  .rec-grid{
    display:grid;
    grid-template-columns: repeat(3, 220px); /* 3칸 고정 + 고정폭 */
    gap: 18px;
  }
/* 카드 폭(너가 원하는대로 조절) */
.book-card{
  width: 280px;          /* 여기 숫자만 바꾸면 카드 전체 크기 조절됨 */
  display: flex;
  flex-direction: column;
  gap: 8px;
}

/* 썸네일 영역 */
.thumb{
  display:block;
  width: 100%;
}

/* 표지 이미지: 책 비율 고정 + 잘라내기 */
.cover{
  width: 100%;
  aspect-ratio: 3 / 4;   /* 책 표지 느낌 */
  object-fit: cover;     /* 비율 유지하면서 꽉 채우고 넘치는 부분은 잘림 */
  border-radius: 12px;
  display:block;
}

.rec-wrap{
  display:flex;
  justify-content:center;
}

.rec-grid{
  display:grid;
  grid-template-columns: repeat(3, 280px); /* 위 book-card width랑 동일 */
  gap: 18px;
}

  .img-placeholder{
    width:100%;
    height:160px;            /* 이미지 영역 고정 */
    border-radius:10px;
    background:#f3f3f3;
    display:flex;
    align-items:center;
    justify-content:center;
    margin-bottom:10px;
    font-size:12px;
    color:#777;
  }
  .title{
    display:block;
    font-weight:700;
    margin: 6px 0 8px;
    text-decoration:none;
    color:#111;
    overflow:hidden;
    white-space:nowrap;
    text-overflow:ellipsis;  /* 제목 길면 ... */
  }
  .meta{
    font-size:12px;
    color:#555;
    overflow:hidden;
    white-space:nowrap;
    text-overflow:ellipsis;
  }
  .empty-card{
    border:1px dashed #ddd;
    background:#fafafa;
  }
</style>

<h2 style="text-align:center;">추천 도서</h2>

<c:choose>
  <c:when test="${empty recommend}">
    <p style="text-align:center;">추천 도서가 없습니다.</p>
  </c:when>
  <c:otherwise>
    <div class="rec-wrap">
      <div class="rec-grid">

        <%-- 1) 추천 도서 최대 3개만 출력 --%>
        <c:forEach var="b" items="${recommend}" varStatus="st">
          <c:if test="${st.count <= 3}">
            <div class="book-card">
              <a class="thumb" href="${pageContext.request.contextPath}/azabook/books/detail?isbn=${b.isbn}">
                <c:choose>
				  <c:when test="${empty b.imgLink}">
				    <div class="img-placeholder">NO IMG</div>
				  </c:when>
				  <c:otherwise>
				    <img class="cover" src="${b.imgLink}" alt="${b.productName}" />
				  </c:otherwise>
				</c:choose>
              </a>

              <a class="title" href="${pageContext.request.contextPath}/azabook/books/detail?isbn=${b.isbn}">
                ${b.productName}
              </a>

              <div class="meta">저자: ${b.author}</div>
              <div class="meta">카테고리: ${b.category}</div>
            </div>
          </c:if>
        </c:forEach>

        <%-- 2) 3칸 고정: 부족하면 빈 카드로 채우기 --%>
        <c:set var="cnt" value="${recommend.size()}" />
        <c:forEach begin="1" end="${3 - (cnt > 3 ? 3 : cnt)}" var="i">
          <div class="book-card empty-card"></div>
        </c:forEach>

      </div>
    </div>
  </c:otherwise>
</c:choose>