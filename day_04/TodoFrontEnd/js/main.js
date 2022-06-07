// Định nghĩa URL API root
const URL_API = "http://localhost:8080/api/v1";

// Lưu lại danh sách todos sau khi gọi API
let todos = [];

let isUpdate = false;
let idUpdate = null;

// Truy cập vào các thành phần
const todoListEl = document.querySelector(".todo-list");
const todoOptionItemsEl = document.querySelectorAll(".todo-option-item input");

const todoInputEl = document.getElementById("todo-input");
const btnAdd = document.getElementById("btn-add");

// Danh sách API
// API lấy danh sách todo theo trạng thái
const getTodoAPI = (status) => {
    switch (status) {
        case "all": {
            return axios.get(`${URL_API}/todos`);
        }
        case "unactive": {
            return axios.get(`${URL_API}/todos?status=false`);
        }
        case "active": {
            return axios.get(`${URL_API}/todos?status=true`);
        }
        default: {
            return axios.get(`${URL_API}/todos`);
        }
    }
};

// API xóa todo
const deleteTodoAPI = (id) => {
    return axios.delete(`${URL_API}/todos/${id}`);
};

// API cập nhật todo (theo title, status)
const updateTodoAPI = (todo) => {
    return axios.put(`${URL_API}/todos/${todo.id}`, {
        title: todo.title,
        status: todo.status,
    });
};

// API tạo todo
const createTodoAPI = (title) => {
    return axios.post(`${URL_API}/todos`, {
        title: title,
    });
};

// Các hàm xử lý
// Hàm xử lý việc thêm
async function createTodo(title) {
    try {
        // Gọi API tạo todo
        const res = await createTodoAPI(title);

        // Khi có kết quả trả về từ server thì thêm vào trong mảng todos
        todos.push(res.data);

        // Render ra ngoài giao diện
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
}

// Lấy danh sách todo
const getTodos = async (status = "") => {
    try {
        // Gọi API
        let res = await getTodoAPI(status);

        // Lưu lại kết quả trả về từ API
        todos = res.data;

        // Hiển thị danh sách lên giao diện
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
};

// Function có chức năng hiển thị todo lên giao diện
const renderTodo = (arr) => {
    // Xóa hết nội dung đang có trước khi render
    todoListEl.innerHTML = "";

    // Kiểm tra xem có todo nào trong danh sách hay không
    if (arr.length == 0) {
        todoListEl.innerHTML = "Không có công việc nào trong danh sách";
        return;
    }

    // Tạo chuỗi html
    let html = "";
    for (let i = 0; i < arr.length; i++) {
        const t = arr[i];
        html += `
            <div class="todo-item ${t.status ? "active-todo" : ""}">
                <div class="todo-item-title">
                    <input 
                        type="checkbox" ${t.status ? "checked" : ""}
                        onclick="toggleStatus(${t.id})"
                    />
                    <p>${t.title}</p>
                </div>
                <div class="option">
                    <button class="btn btn-update" onclick="updateTitle(${t.id})">
                        <img src="./img/pencil.svg" alt="icon" />
                    </button>
                    <button class="btn btn-delete" onclick="deleteTodo(${t.id})">
                        <img src="./img/remove.svg" alt="icon" />
                    </button>
                </div>
            </div>
        `;
    }
    todoListEl.innerHTML = html;
};

// Cập nhật tiêu đề todo
function updateTitle(id) {
    let title;

   // Tìm kiếm công việc muốn cập nhật và lưu lại giá trị title
    todos.forEach((todo) => {
        if (todo.id == id) {
            title = todo.title;
        }
    });

   // Đổi tên "THÊM" -> "CẬP NHẬT"
    btnAdd.innerText = "CẬP NHẬT";

   // Hiển thị tiêu đề cần cập nhật lên ô input
    todoInputEl.value = title;
    todoInputEl.focus();

   // Lưu lại id của công việc cần cập nhật và cho phép cập nhật
    idUpdate = id;
    isUpdate = true;
}

btnAdd.addEventListener("click", function () {
    // Lấy tiêu đề trong ô input
     let todoTitle = todoInputEl.value;
 
    // Kiểm tra xem tiêu đề có trống hay không
     if (todoTitle == "") {
         alert("Nội dung không được để trống!");
         return;
     }
 
    // Nếu isUpdate == true thì cho phép cập nhật
    // Ngược lại isUpdate == false thì cho phép thêm
     if (isUpdate) {
        // Tìm công viêc có id = idUpdate
         let todo = todos.find((todo) => todo.id == idUpdate);
 
        // Sửa lại tiêu đề của công việc đó = nội dung trong ô input
         todo.title = todoTitle;
 
        // Thực hiện cập nhật
         updateTodo(todo);
     } else {
 
        // Thực hiện thêm công việc
         createTodo(todoTitle);
     }
 
     todoInputEl.value = "";
 });


 async function updateTodo(todoUpdate) {
    try {
       // Gọi API cập nhật
        let res = await updateTodoAPI(todoUpdate);

       // Cập nhật lại trong mảng todos
        todos.forEach((todo, index) => {
            if (todo.id == todoUpdate.id) {
                todos[index] = res.data;
            }
        });

       // Thay đổi giao diện về ban đầu
        btnAdd.innerText = "Thêm";

       // Reset lại biến sau khi đã cập nhật thành công
        isUpdate = false;
        idUpdate = null;

        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
}

// Chức năng xóa công việc
const deleteTodo = async (id) => {
    try {
        // Xác nhận xem người dùng có muốn xóa hay không
        let isConfirm = confirm("Bạn có muốn xóa không?");

        // Nếu ok -> Xóa
        if (isConfirm) {
            // Xóa trên server
            await deleteTodoAPI(id);

            // Xóa trong mảng todos
            todos.forEach((todo, index) => {
                if (todo.id == id) {
                    todos.splice(index, 1);
                }
            });

            // Render lại giao diện
            renderTodo(todos);
        }
    } catch (error) {
        console.log(error);
    }
};

// Chức năng thay đổi trạng thái công việc
const toggleStatus = async (id) => {
    try {
        // Lấy ra công việc cần update trong mảng todos
        let todo = todos.find((todo) => todo.id == id);

        // Thay đổi trạng thái công việc
        todo.status = !todo.status;

        // Gọi API
        let res = updateTodoAPI(todo);

        // Render lại giao diện
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
};

// Lọc công việc theo trạng thái
// Lắng nghe sự kiện trên các ô input
todoOptionItemsEl.forEach((input) => {
    input.addEventListener("change", function () {
        // Nếu ô input vào đang được chọn --> lấy ra value
        let status = input.value;

        // Gọi API để lấy công việc theo trạng thái --> hiển thị
        getTodos(status);
    });
});

getTodos();
