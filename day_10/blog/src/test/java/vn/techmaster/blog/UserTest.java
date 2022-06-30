package vn.techmaster.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blog.dto.UserDto;
import vn.techmaster.blog.entity.User;
import vn.techmaster.blog.projection.UserInfo;
import vn.techmaster.blog.repository.UserRepository;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void get_user_dto_by_id_test() {
        UserDto userDto = userRepository.getUserDto(21);
        System.out.println(userDto);
    }

    @Test
    void get_user_info_by_id_test() {
        UserInfo userInfo = userRepository.getUserById(21, UserInfo.class);
        userInfo.showInfo();

//        UserDto userDto = userRepository.getUserById(21, UserDto.class);
//        System.out.println(userDto);

        User user = userRepository.getUserById(21, User.class);
        System.out.println(user);
    }
}
