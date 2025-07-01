package cn.lanqiao.blog.model.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("comments")
public class Comments {
  
  private String articleTitle;
  private String articleStatus;
  private long commentId;
  private long postId;
  private long userId;
  private Long parentId;
  private String authorName;
  private String authorEmail;
  private String authorUrl;
  private String content;
  private String status;
  private LocalDate createdAt;
  private LocalDate updatedAt;

}
