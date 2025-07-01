package cn.lanqiao.blog.service.Impl;

import cn.lanqiao.blog.mapper.BulletinMapper;
import cn.lanqiao.blog.mapper.UserMapper;
import cn.lanqiao.blog.model.dto.comment;
import cn.lanqiao.blog.model.pojo.BulletinComment;
import cn.lanqiao.blog.model.pojo.Bulletins;
import cn.lanqiao.blog.model.pojo.Users;
import cn.lanqiao.blog.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulletinServiceImpl implements BulletinService {
    @Autowired
    private BulletinMapper bulletinMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Bulletins> getList() {
        return bulletinMapper.selectAllBulletins();
    }

    @Override
    public Bulletins getById(long id) {
        return bulletinMapper.selectById(id);
    }

    @Override
    public Bulletins view(long id) {
        bulletinMapper.incrementViewCount(id);
        return bulletinMapper.selectById(id);
    }

    @Override
    public List<BulletinComment> getComment(long id) {
        return bulletinMapper.selectCommentsByBulletinId(id);
    }

    @Override
    public BulletinComment addComment(comment comment) {
        BulletinComment bulletinComment = new BulletinComment();
        bulletinComment.setBulletinId(comment.getBulletinId());
        bulletinComment.setContent(comment.getContent());
        bulletinComment.setAuthorName(comment.getAuthorName());

        // 根据作者名查找用户ID
        Users byUsername = userMapper.findByUsername(comment.getAuthorName());
        if (byUsername == null) {
            throw new RuntimeException("用户不存在");
        }
        bulletinComment.setUser(byUsername);
        bulletinComment.setUserId(byUsername.getUserId());

        // 插入数据库
        bulletinMapper.insertBulletinComment(bulletinComment);

        // 返回新插入的评论对象
        return bulletinComment;
    }

    @Override
    public Bulletins commentCount(long id) {
        Bulletins bulletins = new Bulletins();
        bulletins.setCommentCount(bulletinMapper.commentCount(id));
        return bulletins;
    }
}
