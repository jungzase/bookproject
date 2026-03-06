<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아자북 홈페이지</title>
<style>
    body { margin:0; font-family: Arial, sans-serif; }
    header, footer { padding:16px 24px; background:#111;  }
    header .row { display:flex; align-items:center; justify-content:space-between; gap:12px; }
    main { padding:24px; min-height: calc(100vh - 120px);   }
    .container { max-width: 980px; margin: 0 auto; }
    .searchbar { display:flex; gap:8px; }
    input, select, button { padding:8px 10px; }
    table { width:100%; border-collapse:collapse; margin-top:16px; }
    th, td { border:1px solid #333; padding:10px; }
    a { color:#7ee; text-decoration:none; }
    
.bookcontainer{
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;
  margin-top: 16px;
}

.book{
  border: 1px solid #333;
  padding: 14px;
  border-radius: 12px;
}

.thumb{ display:block; margin-bottom: 10px; }

.img-placeholder{
  width: 100%;
  aspect-ratio: 3 / 4;
  border: 1px dashed #444;
  border-radius: 10px;
  display:flex;
  align-items:center;
  justify-content:center;
}

.title{ display:block; font-weight: bold; margin: 6px 0 8px; }
.meta{ font-size: 13px; margin-bottom: 4px; opacity: 0.9; }

.result-list{
  display:flex;
  flex-direction:column;
  gap:18px;
  margin-top: 18px;
}

.result-item{
  display:flex;
  gap:18px;
  padding:16px;
  border:1px solid #ddd;
  border-radius:14px;
  background:#fff;
}

.result-thumb{
  width:120px;
  flex: 0 0 120px;
  display:block;
}

.cover, .img-placeholder{
  width:100%;
  aspect-ratio: 3 / 4;
  object-fit: cover;
  border-radius:12px;
  display:block;
}

.img-placeholder{
  border:1px dashed #bbb;
  display:flex;
  align-items:center;
  justify-content:center;
  color:#666;
}

.result-info{
  flex:1;
  display:flex;
  flex-direction:column;
  gap:8px;
}

.result-title{
  font-size:20px;
  font-weight:800;
  color:#222;
  text-decoration:none;
}

.result-title:hover{
  text-decoration:underline;
}

.result-meta{
  color:#444;
  font-size:14px;
}

.dot{ margin:0 8px; color:#888; }
.btn{
  display:inline-block;
  padding:10px 12px;
  border:1px solid #ccc;
  border-radius:10px;
  text-decoration:none;
  text-align:center;
  color:#222;
  background:#fff;
}
.btn:hover{
  background:#f3f3f3;
}
  </style>
</head>
<body>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<main>
  <div class="container">
    <!-- main 내용만 바뀌는 자리 -->
    <jsp:include page="/WEB-INF/views/${mainpage}"/>
  </div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>