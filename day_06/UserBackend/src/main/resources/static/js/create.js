// Truy cập vào ô input
const nameEl = document.getElementById("name");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const addressEl = document.getElementById("address");
const avatarEl = document.getElementById("avatar");
const passwordEl = document.getElementById("password");

const btnSave = document.getElementById("btn-save");

// Xử lý quay lại trang index
const btnBack = document.querySelector(".btn-back");
btnBack.addEventListener("click", function () {
    window.location.href = "/";
});

// Xử lý thêm user
btnSave.addEventListener("click", async function () {
    try {
        // Tạo object với dữ liệu đã được cập nhật
        let userNew = {
            name: nameEl.value,
            phone: phoneEl.value,
            email: emailEl.value,
            address: addressEl.value,
            password : passwordEl.value
        };

        console.log(userNew);

        // Gọi API để tạo
        let res = await axios.post("/api/v1/users", userNew);

        if (res.data) {
            window.location.href = "/";
        }
    } catch (error) {
        alert(error.response.data.message);
    }
});

// API lấy danh sách tỉnh - thành phố
async function getProvince() {
    try {
        // Gọi API lấy danh sách tỉnh thành phố
        let res = await axios.get("https://provinces.open-api.vn/api/p/");

        // Render tỉnh thành phố
        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}

function renderProvince(arr) {
    for (let i = 0; i < arr.length; i++) {
        const p = arr[i];
        addressEl.innerHTML += `<option value="${p.name}">${p.name}</option>`
    }
}

getProvince();


