package cn.lanqiao.blog.model.pojo;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserFollows {

  private long followId;
  private long followerId;
  private long followingId;
  private LocalDateTime createdAt;

}
