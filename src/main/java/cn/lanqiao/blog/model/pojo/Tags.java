package cn.lanqiao.blog.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tags")
public class Tags {

  private long tagId;
  private String name;
  private String slug;
  private String description;
  private Date createdAt;
  private Date updatedAt;




}
