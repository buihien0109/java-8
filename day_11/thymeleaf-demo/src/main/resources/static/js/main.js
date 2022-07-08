// Lấy ra path name hiện tại
let pathName = window.location.pathname;
console.log(pathName);

// Tìm kiếm thẻ a nào có giá trị href = pathName => thêm class "active" cho nó;
// B1 : Truy cập vào tất cả thẻ a
// B2 : Sử dụng vòng lặp => lấy ra giá trị href và so sánh
let links = document.querySelectorAll("a");
links.forEach(link => {
    if(link.getAttribute("href") === pathName) {
        link.classList.add("active");
    }
})