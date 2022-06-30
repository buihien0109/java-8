package com.example.userdemo.repository;

import com.example.userdemo.dto.UserDto;
import com.example.userdemo.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    List<User> getUsersByNameContainsIgnoreCase(String name);

    List<User> findByEmailStartsWithIgnoreCase(String email);

    @Query("select u from User u where upper(u.email) like upper(concat('%', :email))")
    List<User> findEmailEndWith(@Param("email") String email);

    @Query("select count(u) from User u where upper(u.name) like upper(concat('%', :name, '%'))")
    long countByNameContainsIgnoreCase(@Param("name") String name);

    long countByName(String name);

    List<User> findByOrderByNameDesc(Sort sort);

    List<User> findByOrderByNameAsc(Pageable pageable);

    @Query(nativeQuery = true, name = "getUserInfo")
    UserDto findByEmail(String email);
}