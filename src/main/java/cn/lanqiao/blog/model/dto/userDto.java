package cn.lanqiao.blog.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userDto {
    private String username;
    private String password;
    private String email;
    private String avatarUrl;
}
