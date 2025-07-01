package cn.lanqiao.blog.model.dto;

import cn.lanqiao.blog.model.pojo.Categories;
import cn.lanqiao.blog.model.pojo.PostImages;
import cn.lanqiao.blog.model.pojo.Tags;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private long postId;
    private long authorId;
    private String title;
    private String status;
    private String keyword;
    private String slug;
    private String content;
    private String excerpt;
    private String featuredImage;

    private Long viewCount;


    private Long commentCount;

    private LocalDateTime createdAt;


    private LocalDateTime  updatedAt;

    private LocalDateTime publishedAt;

    private LocalDateTime deletedAt;

    private List<Categories> categories;
    private List<Tags> tags;
    private List<PostImages> postImages;
}
