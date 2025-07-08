package cn.lanqiao.blog.model.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Posts {

  private long postId;
  private String title;
  private String slug;
  private String content;
  private String excerpt;
  private long authorId;

  private String status;
  private Long viewCount;
  private String keyword;
  private Long commentCount;
  private LocalDateTime createdAt;
  //热度
  private Double hotScore;

  private LocalDateTime  updatedAt;
  private LocalDateTime publishedAt;

  // 关联属性
  private Users user;
  private List<Categories> categories;
  private List<Tags> tags;
  private List<PostImages> postImages;

  @TableField(exist = false)
  private List<Long> categoryIds;

  @TableField(exist = false)
  private List<Long> tagIds;

  // 添加JSON注解处理字段别名
  @JsonProperty("featuredImage")
  private String featuredImage;
}
