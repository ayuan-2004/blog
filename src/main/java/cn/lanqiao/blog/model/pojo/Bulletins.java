package cn.lanqiao.blog.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("bulletins")
public class Bulletins {

  @TableId(value = "bulletin_id", type = IdType.AUTO)
  private long bulletinId;
  private String title;
  private String content;
  private String imageUrl;
  private long viewCount;
  // 添加注解表示该字段不在数据库中存在
  @TableField(exist = false)
  private long commentCount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime publishedAt;
  private String status;

}
