package cn.lanqiao.blog.mapper;

import cn.lanqiao.blog.model.dto.ArticleDto;
import cn.lanqiao.blog.model.pojo.Categories;
import cn.lanqiao.blog.model.pojo.PostImages;
import cn.lanqiao.blog.model.pojo.Posts;
import cn.lanqiao.blog.model.pojo.Tags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper{

    List<Posts> selectArticle( ArticleDto articleDto);
    List<PostImages> selectImagesByPostId(long postId);

    List<Categories> selectCategoriesByPostId(@Param("postId") Long postId);

    List<Tags> selectTagsByPostId(@Param("postId") Long postId);

    Posts getByPostId(Long postId);

    List<Posts> getByUserId(@Param("userId") Long userId);

    void insertPost(Posts post);

    void insertPostCategory(@Param("postId") long postId, @Param("categoryId") long categoryId);

    void insertPostTag(@Param("postId") long postId, @Param("tagId") long tagId);

    void updateArticle(Posts posts);

    void updateView(Long postId);

    void delete(Long postId);

    List<Posts> getHotPage();
}
