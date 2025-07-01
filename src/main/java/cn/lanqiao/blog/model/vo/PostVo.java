package cn.lanqiao.blog.model.vo;

import cn.lanqiao.blog.model.dto.CategoryDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostVo {
    private long postId;
    private String title;
    private String slug;
    private String content;
    private String excerpt;
    private long authorId;
    private String status;
    private String featuredImage;
    private Long viewCount;
    private String keyword;
    private Long commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private LocalDateTime deletedAt;

    // 只包含分类的基本信息，如 id 和 name
    private List<CategoryDTO> categories;
}
