// Lấy thông tin id trên url
let params = new URLSearchParams(window.location.search);
let id = params.get("id");

// Truy cập
const addressEl = document.getElementById("address");

// Lấy thông tin user
const getUser = async (id) => {
    try {
        // B1 : Gọi API
        let res = await axios.get(`http://localhost:8080/api/v1/users/${id}`);

        // B2 : Hiển thị lên trên giao diện
        addressEl.value = res.data.address;

    } catch (error) {
        console.log(error);
    }
}



// API : https://provinces.open-api.vn/api/p/
const getProvinces = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/");

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

const init = async () => {
    await getProvinces();
    await getUser(id);
}

init();