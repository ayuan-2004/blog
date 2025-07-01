package cn.lanqiao.blog;

import cn.lanqiao.blog.controller.commentsController;
import cn.lanqiao.blog.mapper.commentsMapper;
import cn.lanqiao.blog.model.dto.ArticleDto;
import cn.lanqiao.blog.model.pojo.Comments;
import cn.lanqiao.blog.model.pojo.Posts;
import cn.lanqiao.blog.service.ArticleService;
import cn.lanqiao.blog.service.commentService;
import cn.lanqiao.blog.utile.PasswordHashUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private commentService commentService;
    @Test
    void contextLoads() {
    }
    @Test
    void pass() {
        String password="123456";
        String PassHash="$2a$10$xJwL5v5Jz5UZ5Z5Z5Z5Z5e";
        byte[] salt= PasswordHashUtil.generateSalt();
        String passwordHash=PasswordHashUtil.hashPassword(password,salt);
        System.out.println(passwordHash);
        boolean b = PasswordHashUtil.verifyPassword(passwordHash, PassHash, salt);
        System.out.println(b);
    }
    @Test
    void test1(){
//        Posts post = new Posts();
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle("测试标题");
        articleDto.setContent("这是内容");
        articleDto.setAuthorId(1);
        articleDto.setSlug("测试标题");
        articleDto.setStatus("published");
        articleService.addArticle(articleDto);
        System.out.println(articleDto);
    }
    @Test
    void test2(){
//
//        Posts posts = commentService.changeCommentCount(4);
//        System.out.println(posts);
    }
}
