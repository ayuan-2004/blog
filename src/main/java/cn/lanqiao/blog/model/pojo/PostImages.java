package cn.lanqiao.blog.model.pojo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostImages {

  private long imageId;
  private long postId;
  private String imageUrl;
  private String altText;
  private long displayOrder;
  private LocalDateTime createdAt;
}
