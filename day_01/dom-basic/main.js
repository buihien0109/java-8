const heading = document.getElementById("heading");
console.log(heading);

const para2 = document.querySelector(".para");
console.log(para2);

// .para:nth-child(4)
const para3 = document.querySelector(".para + .para");
console.log(para3);

// const para3 = para2.nextElementSibling
// console.log(para3);

const ul1 = document.querySelector(".box ul");
console.log(ul1);

const ul2 = document.querySelector("body > ul");
console.log(ul2);

const paras = document.querySelectorAll("p");
console.log(paras);

// Sử dụng for qua Nodelist
// for (let i = 0; i < paras.length; i++) {
//     paras[i].style.color = "red";
//     paras[i].style.backgroundColor = "black";
// }

Array.from(paras).map(e => {
    e.style.color = "red";
    e.style.backgroundColor = "black";
})

// Lấy ra nội dung của phần tử
console.log(heading.innerHTML);
console.log(heading.innerText);
console.log(heading.textContent);

console.log(ul1.innerHTML);
console.log(ul1.innerText);
console.log(ul1.textContent);

heading.innerHTML = "Xin chào";
heading.innerHTML = "<span>Xin chào các bạn</span>";
heading.innerText = "Hello";

// Tạo ra phần tử DOM
const newPara = document.createElement("p");
newPara.innerText = "New Para";

console.log(newPara);

// Chèn vào vị trí đầu
// document.body.prepend(newPara)

// Chèn vào vị trí cuối
// document.body.appendChild(newPara);

// document.body.insertBefore(newPara, para2);

const box = document.querySelector(".box");

// document.body.insertBefore(newPara, box)

// targetElement.insertAdjacentElement
// para3.insertAdjacentElement("afterend", newPara);
// box.insertAdjacentElement("beforebegin", newPara)

box.insertAdjacentHTML("beforebegin", "<p>Thẻ para mới</p>")

// Xóa phần tử
// Xóa heading
// document.body.removeChild(heading)
heading.parentElement.removeChild(heading);

// Thay thế
// Ví dụ : Thay thế thẻ para2 bằng thẻ a, link đến trang google.com
const link = document.createElement("a");
link.innerText = "Link google";
link.href = "https://google.com";

document.body.replaceChild(link, para2)

// Sao chép phần tử
const boxCopy1 = box.cloneNode(true);
const boxCopy2 = box.cloneNode(false);

console.log(boxCopy1);
console.log(boxCopy2);

document.body.appendChild(boxCopy1)

// ClassList
console.log(box.classList);

// Thêm class
box.classList.add("text-big", "text-bold");
box.classList.remove("text-big")

console.log(box.classList.contains("box"));
console.log(box.classList.contains("text-big"));

// setInterval : Lặp đi lặp lại 1 cv sau 1 khoảng thời gian nhất định
// setInterval(function() {
//     box.classList.toggle("text-big")
// }, 1500)
