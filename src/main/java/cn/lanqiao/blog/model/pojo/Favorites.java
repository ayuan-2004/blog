package cn.lanqiao.blog.model.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class Favorites {

  private long favoriteId;
  private long userId;
  private long postId;
  private Date createdAt;

}
