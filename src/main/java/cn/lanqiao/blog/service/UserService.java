package cn.lanqiao.blog.service;

import cn.lanqiao.blog.model.dto.loginDto;
import cn.lanqiao.blog.model.dto.userDto;
import cn.lanqiao.blog.model.pojo.Users;

public interface UserService {
    Users login(loginDto loginDto);

    void register(userDto users);

    Users selectUser(String username);

    Users updateUser(Users users);

    boolean updatePass(String username, String password);
}
