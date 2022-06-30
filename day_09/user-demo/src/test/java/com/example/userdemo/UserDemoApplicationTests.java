package com.example.userdemo;

import com.example.userdemo.dto.UserDto;
import com.example.userdemo.entity.User;
import com.example.userdemo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class UserDemoApplicationTests {
    @Autowired
    private UserRepository userRepository;


    @Test
    void insertUserTest() {
        User user = User.builder().name("Nguyễn Văn A").email("aaaa@gmail.com").password("222").build();
        User user1 = User.builder().name("Trần Văn B").email("bbbb@gmail.com").password("333").build();
        User user2 = User.builder().name("Ngô Thị C").email("cccc@gmail.com").build();

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    void countByNameContainsIgnoreCaseTest() {
        long count = userRepository.countByNameContainsIgnoreCase("bùi");
        Assertions.assertThat(count).isEqualTo(1L);
    }

    @Test
    void sortNameDescTest() {
        List<User> users = userRepository.findByOrderByNameDesc(Sort.by("name").descending());
        users.forEach(user -> System.out.println(user.getName()));

        Assertions.assertThat(users.get(0).getName()).isEqualTo("Phương Loan");
    }

    @Test
    void findByOrderByNameAscTest() {
        List<User> users = userRepository.findByOrderByNameAsc(PageRequest.of(0, 2));
        users.forEach(user -> System.out.println(user.getName()));
    }

    @Test
    void findByEmailReturnDtoTest() {
        UserDto userDto = userRepository.findByEmail("hien@gmail.com");
        Assertions.assertThat(userDto.getName()).isEqualTo("Bùi Hiên");
    }
}
