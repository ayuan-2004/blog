package cn.lanqiao.blog.service.Impl;

import cn.lanqiao.blog.mapper.ArticleMapper;
import cn.lanqiao.blog.mapper.UserMapper;
import cn.lanqiao.blog.mapper.commentsMapper;
import cn.lanqiao.blog.model.pojo.Comments;
import cn.lanqiao.blog.model.pojo.Posts;
import cn.lanqiao.blog.model.pojo.Users;
import cn.lanqiao.blog.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commentServiceImpl implements commentService {
    @Autowired
    private commentsMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public List<Comments> getByPostId(long postId) {
        return commentMapper.getByPostId(postId);
    }

    @Override
    public Comments addComments(Comments comments) {
        Users byUsername = userMapper.findByUsername(comments.getAuthorName());
        comments.setAuthorName(byUsername.getDisplayName());
        comments.setAuthorEmail(byUsername.getEmail());
        comments.setUserId(byUsername.getUserId());
        comments.setAuthorUrl(byUsername.getAvatarUrl());
        // 检查 parentId 是否为 null 或者 0
        Long parentId = comments.getParentId();
        if (parentId != null && parentId == 0L) {
            comments.setParentId(null);
        }
        commentMapper.addComments(comments);
        return commentMapper.getByPostId(comments.getPostId()).get(0);
    }

    @Override
    public void deleteComments(long commentId) {
        commentMapper.deleteByCommenrId(commentId);
    }

    @Override
    public List<Comments> getByUserId(long userId) {
        return commentMapper.getByUserId(userId);
    }

}
