package cn.lanqiao.blog.service.Impl;
import cn.lanqiao.blog.utile.EntityFillUtil;
import cn.lanqiao.blog.mapper.UserMapper;
import cn.lanqiao.blog.model.dto.loginDto;
import cn.lanqiao.blog.model.dto.userDto;
import cn.lanqiao.blog.model.pojo.Users;
import cn.lanqiao.blog.service.UserService;
import cn.lanqiao.blog.utile.PasswordHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper usersMapper;
    @Override
    public Users login(loginDto loginDto) {
        Users users=usersMapper.findByUsername(loginDto.getUsername());
        byte[] salt= PasswordHashUtil.generateSalt();
        boolean b = PasswordHashUtil.verifyPassword(loginDto.getPassword(), users.getPasswordHash(), salt);
        if(!b){
            return null;
        }
        return users;
    }

    @Override
    public void register(userDto users) {
        byte[] salt= PasswordHashUtil.generateSalt();
        String passHash=PasswordHashUtil.hashPassword(users.getPassword(),salt);
        Users user = new Users();
        user.setUsername(users.getUsername());
        user.setPasswordHash(passHash);
        user.setEmail(users.getEmail());
        user.setAvatarUrl(users.getAvatarUrl());
        EntityFillUtil.handleInsert(user);
        usersMapper.addUser(user);
    }

    @Override
    public Users selectUser(String username) {
        Users users=usersMapper.findByUsername(username);
        return users;
    }

    @Override
    public Users updateUser(Users users) {
        Users byUsername = usersMapper.findByUsername(users.getUsername());
        EntityFillUtil.handleUpdate(users,byUsername);
        usersMapper.updateUser(users);
        return usersMapper.findByUsername(users.getUsername());
    }

    @Override
    public boolean updatePass(String username, String newPassword) {
        // 根据用户名查找用户
        Users byUsername = usersMapper.findByUsername(username);
        // 生成盐
        byte[] salt= PasswordHashUtil.generateSalt();
        // 对密码进行哈希
        String passHash=PasswordHashUtil.hashPassword(newPassword,salt);
        // 获取旧密码
        String oldPass=byUsername.getPasswordHash();
        //获取用户id
        long id=byUsername.getUserId();
        // 如果新密码和旧密码不相等
        if (!oldPass.equals(passHash)){
            Users users = new Users();
            users.setUserId(id);
            users.setPasswordHash(passHash);
            usersMapper.updateUser(users);
            return true;
        }
        return false;
    }
}
