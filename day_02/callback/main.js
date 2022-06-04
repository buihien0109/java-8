// Một số công việc thực hiện
// Đi làm : 4s
// Nghỉ trưa : 2s
// Đi nhậu : 3s

// function sum(a, b) {
//     return a + b;
// }

// const sum1 = function(a, b) {
//     return a + b;
// }

// const sum2 = (a, b) => a + b;

function doTask1(taskName, callback) {
    console.log("Bắt đầu công việc hằng ngày");
    console.log(`Thực hiện công việc ${taskName}`);

    setTimeout(function() {
        console.log(`Thực hiện xong công việc ${taskName}`);
        callback();
    }, 4000)
}

function doTask2(taskName, callback) {
    console.log(`Thực hiện công việc ${taskName}`);

    setTimeout(function() {
        console.log(`Thực hiện xong công việc ${taskName}`);
        callback();
    }, 2000)
}

function doTask3(taskName, callback) {
    console.log(`Thực hiện công việc ${taskName}`);

    setTimeout(function() {
        console.log(`Thực hiện xong công việc ${taskName}`);
        callback();
    }, 3000)
}

function finish() {
    console.log(`Kết thúc công việc`);
}

// doTask1("Đi làm", function() {
//     doTask2("Nghỉ trưa", function() {
//         doTask3("Đi nhậu", finish)
//     })
// })

doTask1("Đi làm", finish)
doTask2("Nghỉ trưa", finish)
doTask3("Đi nhậu", finish)

// Gọi API lấy danh sách user
// Lấy thông tin của user theo id
// Lấy danh sách bài viết
// Lấy chi tiết bài viết
// Lấy danh sách comment
// Lấy chi tiêt comment