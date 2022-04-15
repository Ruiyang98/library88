package com.example.wj.dto;

import com.example.wj.dto.base.OutputConverter;
import com.example.wj.pojo.AdminRole;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class UserDTO implements OutputConverter {

    private int id;
    private String username;
    private String name;
    private String phone;
    private String email;
    private boolean enabled;
    private List<AdminRole> roles;
}
