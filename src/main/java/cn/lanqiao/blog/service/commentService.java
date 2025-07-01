package cn.lanqiao.blog.service;

import cn.lanqiao.blog.model.pojo.Comments;
import cn.lanqiao.blog.model.pojo.Posts;

import java.util.List;

public interface commentService {
    List<Comments> getByPostId(long postId);

    Comments addComments(Comments comments);

    void deleteComments(long commentId);

    List<Comments> getByUserId(long userId);
}
