package cn.lanqiao.blog.model.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class Likes {

  private long likeId;
  private long userId;
  private String contentType;
  private long contentId;
  private Date createdAt;

}
