package com.example.demo.dto;

import com.example.demo.vo.ResponseOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String pwd;
    private String name;
    private String userId;
    private Date createAt;
    private List<ResponseOrder> orders;

    private String encryptedPwd;
}
