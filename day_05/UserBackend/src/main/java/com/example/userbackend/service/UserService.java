package com.example.userbackend.service;

import com.example.userbackend.dto.UserDto;
import com.example.userbackend.exception.NotFoundException;
import com.example.userbackend.mapper.UserMapper;
import com.example.userbackend.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private List<User> users;

    public UserService() {
        initData();
    }

    public void initData() {
        users = new ArrayList<>();
        users.add(new User(1, "Nguyễn Văn A", "a@gmail.com", "0123456789", "Tỉnh Thái Bình", null, "111"));
        users.add(new User(2, "Trần Văn B", "b@gmail.com", "0123456789", "Tỉnh Nam Định", null, "222"));
        users.add(new User(3, "Ngô Thị C", "c@gmail.com", "0123456789", "Tỉnh Hưng Yên", null, "333"));
    }

    // Lấy danh sách user
    public List<UserDto> getUsers() {
        return users
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Tìm kiếm user theo tên
    public List<UserDto> searchUser(String name) {
        return users
                .stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Xóa user theo id
    public void deleteUser(int id) {
        if(findUser(id).isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }
        users.removeIf(user -> user.getId() == id);
    }

    // HELPER METHOD
    public Optional<User> findUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

}
