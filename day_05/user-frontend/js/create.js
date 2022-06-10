// Truy cập
const btnSave = document.getElementById("btn-save");
const addressEl = document.getElementById("address");

// Tạo user
btnSave.addEventListener("click", async function() {
    try {
        // Lấy thông in ở tất cả các ô input

        // Gọi API
        let res = await axios.post("http://localhost:8080/api/v1/users", {
            address : addressEl.value,
            ...
        })

        // Nếu thành công thì điều hướng về trang index để hiển thị
        if(res.data) {
            window.location.href = "/";
        }

    } catch (error) {
        console.log(error);
    }
})

// API : https://provinces.open-api.vn/api/p/
const getProvinces = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        console.log(res);

        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}

const renderProvince = arr => {
    let html = "";
    arr.forEach(province => {
        html += `<option value="${province.name}">${province.name}</option>`
    });
    addressEl.innerHTML = html;
}

getProvinces();