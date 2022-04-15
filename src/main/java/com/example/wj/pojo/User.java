package com.example.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "用户名不能为空")
    private String username;

    private String password;
    private String name;
    private String salt;
    private String phone;

    @Email(message = "请输入正确的邮箱")
    private String email;
    private boolean enabled;

    // 用于存储当前用户拥有的角色的瞬态属性。
    @Transient
    private List<AdminRole> roles;

}
