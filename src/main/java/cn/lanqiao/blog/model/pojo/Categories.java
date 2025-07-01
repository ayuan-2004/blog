package cn.lanqiao.blog.model.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@TableName("categories")
public class Categories {

  @TableId(value = "category_id", type = IdType.AUTO)
  private long categoryId;
  private String name;
  private String slug;
  private String description;
  private long parentId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
