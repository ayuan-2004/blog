package cn.lanqiao.blog.service;

import cn.lanqiao.blog.model.dto.comment;
import cn.lanqiao.blog.model.pojo.BulletinComment;
import cn.lanqiao.blog.model.pojo.Bulletins;

import java.util.List;

public interface BulletinService {
    List<Bulletins> getList();

    Bulletins getById(long id);

    Bulletins view(long id);

    List<BulletinComment> getComment(long id);

    BulletinComment addComment(comment comment);

    Bulletins commentCount(long id);
}
