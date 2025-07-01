package cn.lanqiao.blog.model.pojo;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Users {

  private long userId;
  private String username;
  private String email;
  private String passwordHash;
  private long sex;
  private String displayName;
  private String avatarUrl;
  private String bio;
  private String status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private long followerCount;
  private long followingCount;
  private long postCount;
  private long likeCount;

}
