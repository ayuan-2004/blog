package cn.lanqiao.blog.utile;


import cn.lanqiao.blog.model.pojo.Posts;
import cn.lanqiao.blog.model.pojo.Users;

import java.time.LocalDateTime;

public class EntityFillUtil {

    // 处理插入操作的填充逻辑
    public static void handleInsert(Object entity) {
        LocalDateTime now = LocalDateTime.now();

        // 公共字段填充
        if (entity instanceof Posts) {
            Posts post = (Posts) entity;
            post.setCreatedAt(now);
            post.setUpdatedAt(now);
            // 文章表特定字段
            if (post.getViewCount() == null) post.setViewCount(0L);
            if (post.getCommentCount() == null) post.setCommentCount(0L);
            if ("published".equals(post.getStatus()) && post.getPublishedAt() == null) {
                post.setPublishedAt(now);
            }
        }
        else if (entity instanceof Users) {
            Users user = (Users) entity;
            user.setCreatedAt(now);
            user.setUpdatedAt(now);
            // 用户表特定字段
            if (user.getStatus() == null) user.setStatus("active");
        }
        // 其他实体类型可继续扩展
    }

    // 处理更新操作的填充逻辑
    public static void handleUpdate(Object entity, Object originalEntity) {
        if (entity == null || originalEntity == null) {
            throw new IllegalArgumentException("Entity or Original Entity cannot be null");
        }

        LocalDateTime now = LocalDateTime.now();

        // 公共字段填充
        if (entity instanceof Posts) {
            Posts post = (Posts) entity;
            Posts original = (Posts) originalEntity;
            post.setUpdatedAt(now);

            // 确保日期字段不会被置为字符串空值
            if (post.getStatus() != null && !post.getStatus().equals(original.getStatus())) {
                if ("published".equals(post.getStatus())) {
                    // 保持原逻辑
                } else if ("draft".equals(post.getStatus())) {
                    // 明确设置为 null 而不是空字符串
                    post.setPublishedAt(null);
                    //post.setDeletedAt(null);
                }
            }
        }
        // 其他实体类型可继续扩展
    }
}