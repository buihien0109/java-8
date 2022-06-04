const btn = document.querySelector("#btn");
const imageEl = document.querySelector(".image img");
const selectEl = document.querySelector("#breed-list");

// API lấy giống loài chính
async function getBreedList() {
    try {
        let res = await axios.get("https://dog.ceo/api/breeds/list/all");
        console.log(res);

        // Hiển thị danh sách giống loài chính
        displayBreedList(res.data.message);
    } catch (error) {
        console.log(error);
    }
}

// Hiển thị danh sách giống loài chính
function displayBreedList(obj) {
    // Lấy ra mảng key của đối tượng
    let keys = Object.keys(obj);
    
    let html = ""
    for (let i = 0; i < keys.length; i++) {
        html += `<option value="${keys[i]}">${keys[i]}</option>`
    }

    // Chèn lại nội dung cho phần tử
    selectEl.innerHTML = html;
}

// window.onload = getBreedList;
