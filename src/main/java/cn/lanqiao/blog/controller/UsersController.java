package cn.lanqiao.blog.controller;


import cn.lanqiao.blog.model.dto.loginDto;
import cn.lanqiao.blog.model.dto.userDto;
import cn.lanqiao.blog.model.pojo.Users;
import cn.lanqiao.blog.model.vo.loginVo;
import cn.lanqiao.blog.service.UserService;
import cn.lanqiao.blog.utile.JwtUtil;
import cn.lanqiao.blog.utile.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/users")
@Slf4j
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UsersController {

    @Autowired
    private UserService userService;

    /*
    * 登录
    * */
    @PostMapping("/login")
    public Result login(@RequestBody loginDto loginDto){
        Users users=userService.login(loginDto);
        if (users==null){
            return Result.error("用户名或密码错误");
        }
        //生成token值
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", users.getUsername());
        String token = JwtUtil.generateToken(users.getUsername());
        loginVo loginVo = new loginVo(users.getUserId(),users.getUsername(), token);
        return Result.success(loginVo);
    }
    /*
    * 注册
    * */
    @PostMapping
    public Result register(@RequestBody userDto users){
        System.out.println(users);
        userService.register(users);
        return Result.success();
    }
    /*
    * 退出登录
    * */

    /*
    * 查询个人信息
    * */
    @GetMapping("/selectUser")
    public Result<Users> selectUser(@RequestParam  String username) {
        return Result.success(userService.selectUser(username));
    }

    /*
    * 修改个人信息
    * */
    @PutMapping
    public Result updateUser(@RequestBody Users users) {
        System.out.println(users);
        return Result.success(userService.updateUser(users));
    }

    /*
    * 修改密码
    * */
    @PutMapping("/updatePassword")
    public Result<String> updatePassword(@RequestBody loginDto loginDto) {
        boolean b = userService.updatePass(loginDto.getUsername(), loginDto.getNewPassword());
        if (b) {
            return Result.success();
        }
        return Result.error("新旧密码一致，请重新输入");
        //return null;
    }
}
