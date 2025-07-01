package cn.lanqiao.blog.mapper;

import cn.lanqiao.blog.model.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from users where username = #{username} and status = 'active'")
    Users findByUsername(String username);

    // 添加用户
    int addUser(Users users);

    void updateUser(Users users);
}
