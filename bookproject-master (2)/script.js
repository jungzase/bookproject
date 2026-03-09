// [Model] 데이터를 담는 배열
let books = [];

// [Controller] 서버에서 데이터를 가져오는 함수
async function loadBooks() {
    try {
        // 실제 서버 API 주소에 맞게 수정 필요 (예: /api/books)
        const response = await fetch('/api/books'); 
        const data = await response.json();
        books = data; 
        renderBooks(); 
    } catch (error) {
        console.error("데이터 로드 실패:", error);
        // 서버 연결 전 테스트용 더미 데이터
        books = [
            { isbn: '978-1', productName: 'MVC 패턴의 이해', author: '김코딩', price: 25000, publisher: 'A출판사' },
            { isbn: '978-2', productName: 'HTML 가이드', author: '이개발', price: 18000, publisher: 'B출판사' }
        ];
        renderBooks();
    }
}

// script.js 내의 renderBooks 함수 수정본
function renderBooks() {
    const listUI = document.getElementById('bookListUI');
    listUI.innerHTML = ''; 

    books.forEach((book) => {
        const li = document.createElement('li');
        li.className = 'book-item';
        
        // CSV의 imageLink를 img 태그의 src로 사용
        li.innerHTML = `
            <div class="book-info">
                <img src="${book.imageLink}" alt="${book.productName}" style="width:50px; height:auto; margin-right:10px;">
                <span><strong>${book.productName}</strong> - ${book.author}</span>
            </div>
            <button onclick="handleDelete('${book.isbn}', event)">삭제</button>
        `;

        li.onclick = () => showDetail(book);
        listUI.appendChild(li);
    });
}

// [View] 상세보기 정보를 화면에 표시
function showDetail(book) {
    const detailArea = document.getElementById('bookDetail');
    const content = document.getElementById('detailContent');

    detailArea.style.display = 'block'; // 숨겨진 영역 보이기
    content.innerHTML = `
        <strong>ISBN:</strong> ${book.isbn}<br>
        <strong>저자:</strong> ${book.author}<br>
        <strong>출판사:</strong> ${book.publisher || '정보 없음'}<br>
        <strong>가격:</strong> ${book.price}원
    `;
}

// [Controller] 삭제 기능 (Admin 입장)
function handleDelete(isbn, event) {
    event.stopPropagation(); // 리스트 클릭 이벤트(상세보기)가 발생하지 않도록 방지
    if (confirm("이 도서를 삭제하시겠습니까?")) {
        books = books.filter(b => b.isbn !== isbn);
        renderBooks();
        document.getElementById('bookDetail').style.display = 'none';
    }
}

// 페이지 로드 시 실행
window.onload = loadBooks;