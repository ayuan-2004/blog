package cn.lanqiao.blog.model.dto;

import lombok.Data;

@Data
public class comment {
    private long bulletinId;
    private String content;
    private String authorName;
}
