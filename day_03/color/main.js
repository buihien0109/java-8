const btnRandomColorName = document.querySelector(".btn-random-color-name");

// btnRandomColorName.addEventListener("click", async function() {
//     try {
//         let res = await axios.post("http://localhost:8080/bmi-post", {
//             height : 1.7,
//             weight : 60
//         });
//         console.log(res);

//     } catch (error) {
//         console.log(error.response);
//         // alert(error.response.data.message)
//     }
// })


$(".btn-random-color-name").click(function () {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/bmi-post",
        data : JSON.stringify({
            height : 1.7,
            weight : 60
        }),
        headers : {
            "Content-type" : "application/json"
        },
        success: function (response) {
            console.log(response);
        },
        error: function (error) {
            console.log(error);
        }
    });
})

// AOP