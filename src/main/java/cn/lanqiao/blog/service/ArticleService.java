package cn.lanqiao.blog.service;

import cn.lanqiao.blog.model.dto.ArticleDto;
import cn.lanqiao.blog.model.pojo.Posts;
import cn.lanqiao.blog.model.pojo.Tags;

import java.util.List;

public interface ArticleService {
    List<Posts> selectList(ArticleDto articleDto);

    Posts getByPostId(Long postId);

    List<Posts> selectByUserId(Long userId);

    void addArticle(ArticleDto articleDto);

    void updateArticle(Posts posts);

    void deleteArticle(Long postId);


    List<Tags> selectTags();

    Posts updateView(Long postId);

    List<Posts> getHotPage();
}
