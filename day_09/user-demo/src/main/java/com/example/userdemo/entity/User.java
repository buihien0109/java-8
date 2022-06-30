package com.example.userdemo.entity;

import com.example.userdemo.dto.UserDto;
import lombok.*;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "userInfo",
        classes = @ConstructorResult(
                targetClass = UserDto.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "email")
                }
        )
)
@NamedNativeQuery(
        name = "getUserInfo",
        resultSetMapping = "userInfo",
        query = "SELECT user.id, user.name, user.email " +
                "FROM user " +
                "WHERE user.email = ?1"
)
@Entity
@Table(name = "user")
@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "password", columnDefinition = "varchar(255) DEFAULT '111'")
    private String password;
}