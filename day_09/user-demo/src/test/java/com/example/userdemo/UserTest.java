package com.example.userdemo;

import com.example.userdemo.entity.Student;
import com.example.userdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class UserTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void insertUserTest() {
        Student student = Student.builder().name("Bùi Hiên").email("hien@gmail.com").build();
        testEntityManager.persist(student);
    }
}
