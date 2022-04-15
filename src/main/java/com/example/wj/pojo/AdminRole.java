package com.example.wj.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "admin_role")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;

    @Column(name = "name_zh")
    private String nameZh;

    private boolean enabled;

    // 用于存储当前角色所拥有的菜单的瞬态属性。
    @Transient
    private List<AdminMenu> menus;

    // 用于存储当前角色所拥有的权限的瞬态属性。
    @Transient
    private List<AdminPermission> perms;
}
