const text = document.getElementById("text");
const btn1 = document.getElementById("btn-1");
const btn2 = document.getElementById("btn-2");

btn1.addEventListener("click", function() {
    // B1 : Random quote
    let quotes = ["Quote 1", "Quote 2", "Quote 3", "Quote 4"]

    let randomIndex = Math.floor(Math.random() * quotes.length);
    let randomQuote = quotes[randomIndex];

    // B2 : Chèn lại nội dung cho thẻ p
    text.innerText = randomQuote;
})

btn2.addEventListener("click", function() {
    // B1 : Random color
    let hexColor = randomHexColor();

    // B2 : Thay đổi màu của thẻ p
})

function randomHexColor() {
    // Tạo mảng bao gồm 16 ký tự 0 -> 9, a -> f

    // Sử dụng vòng lặp 6 lần, mỗi vòng lặp random ra 1 ký tự (cộng chuỗi)

    // Trả về mã Hex + dấu # ở đầu
}