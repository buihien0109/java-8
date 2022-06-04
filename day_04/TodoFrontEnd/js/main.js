// Định nghĩa URL API root
const URL_API = "http://localhost:8080/api/v1"

// Lưu lại danh sách todos sau khi gọi API
let todos = [];

// Truy cập vào các thành phần
const todoListEl = document.querySelector(".todo-list");
const todoOptionItemsEl = document.querySelectorAll(".todo-option-item input");

// Danh sách API
// API lấy danh sách todo theo trạng thái
const getTodoAPI = status => {
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
}

// API xóa todo
const deleteTodoAPI = id => {
    return axios.delete(`${URL_API}/todos/${id}`);
}

// API cập nhật todo (theo title, status)
const updateTodoAPI = todo => {
    return axios.put(`${URL_API}/todos/${todo.id}`, {
        title : todo.title,
        status : todo.status
    })
}

// API tạo todo
const createTodoAPI = title => {
    return axios.put(`${URL_API}/todos`, {
        title : title
    })
}

// Các hàm xử lý
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
}

// Function có chức năng hiển thị todo lên giao diện
const renderTodo = arr => {
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
                    <button class="btn btn-update">
                        <img src="./img/pencil.svg" alt="icon" />
                    </button>
                    <button class="btn btn-delete" onclick="deleteTodo(${t.id})">
                        <img src="./img/remove.svg" alt="icon" />
                    </button>
                </div>
            </div>
        `
    }
    todoListEl.innerHTML = html;
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
            })

            // Render lại giao diện
            renderTodo(todos);
        }

    } catch (error) {
        console.log(error);
    }
}

// Chức năng thay đổi trạng thái công việc
const toggleStatus = async (id) => {
    try {
        // Lấy ra công việc cần update trong mảng todos
        let todo = todos.find(todo => todo.id == id);

        // Thay đổi trạng thái công việc
        todo.status = !todo.status;

        // Gọi API
        let res = updateTodoAPI(todo);

        // Render lại giao diện
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
}

// Lọc công việc theo trạng thái
// Lắng nghe sự kiện trên các ô input
todoOptionItemsEl.forEach(input => {
    input.addEventListener("change", function() {
        // Nếu ô input vào đang được chọn --> lấy ra value
        let status = input.value;

        // Gọi API để lấy công việc theo trạng thái --> hiển thị
        getTodos(status);
    })
})

getTodos();