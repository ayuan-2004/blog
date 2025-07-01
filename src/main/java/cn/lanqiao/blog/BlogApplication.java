package cn.lanqiao.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "cn.lanqiao.blog.controller",
        "cn.lanqiao.blog.service",
        "cn.lanqiao.blog.config"
})
@MapperScan("cn.lanqiao.blog.mapper") // MyBatis的接口扫描包
@EntityScan("cn.lanqiao.blog.model.pojo") // 实体类扫描包（如果需要）
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
