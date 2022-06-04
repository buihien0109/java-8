const btn = document.querySelector(".btn");
const imageEl = document.querySelector(".image img");

btn.addEventListener("click", async function() {
    try {
        // B1 : Gọi API
        let res = await axios.get("https://dog.ceo/api/breeds/image/random");
        console.log(res);

        // B2 : Lấy kết quả trả về từ API -> hiển thị
        imageEl.src = res.data.message
    } catch (error) {
        console.log(error);
    }
})