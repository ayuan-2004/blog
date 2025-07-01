package cn.lanqiao.blog.model.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BulletinComment {

  private long commentId;
  private long bulletinId;
  private long userId;
  private String authorName;
  private String content;
  private String status;
  private LocalDateTime createdAt;

  private Users user;
}
