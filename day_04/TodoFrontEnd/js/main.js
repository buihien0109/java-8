const URL_API = "http://localhost:8080/api/v1"

let todos = [];

// Truy cập vào các thành phần
const todoListEl = document.querySelector(".todo-list");
const todoOptionItemsEl = document.querySelectorAll(".todo-option-item input");

// Danh sách API
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

const deleteTodoAPI = id => {
    return axios.delete(`${URL_API}/todos/${id}`);
}

const updateTodoAPI = todo => {
    return axios.put(`${URL_API}/todos/${todo.id}`, {
        title : todo.title,
        status : todo.status
    })
}

const createTodoAPI = title => {
    return axios.put(`${URL_API}/todos/`, {
        title : title
    })
}

// Các hàm xử lý
const getTodos = async (status = "") => {
    try {
        let res = await getTodoAPI(status);

        todos = res.data;

        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
}

const renderTodo = arr => {
    todoListEl.innerHTML = "";

    if (arr.length == 0) {
        todoListEl.innerHTML = "Không có công việc nào trong danh sách";
        return;
    }

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

const deleteTodo = async (id) => {
    try {
        let isConfirm = confirm("Bạn có muốn xóa không?");
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
todoOptionItemsEl.forEach(input => {
    input.addEventListener("change", function() {
        // Lấy ra value
        let status = input.value;
        getTodos(status);
    })
})

getTodos();