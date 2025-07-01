package cn.lanqiao.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class loginDto {
    private String username;
    private String password;
    private String newPassword;
}
