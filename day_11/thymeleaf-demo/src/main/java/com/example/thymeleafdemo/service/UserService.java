package com.example.thymeleafdemo.service;

import com.example.thymeleafdemo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
        users.add(new User(1, "Nguyễn Văn A", "a@gmail.com"));
        users.add(new User(2, "Trần Văn B", "b@gmail.com"));
        users.add(new User(3, "Ngô Thị C", "c@gmail.com"));
    }

    // Lấy ra tất cả user
    public List<User> getUsers() {
        return users;
    }

    // Lấy user theo id
    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
