package cn.lanqiao.blog.mapper;

import cn.lanqiao.blog.model.pojo.BulletinComment;
import cn.lanqiao.blog.model.pojo.Bulletins;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BulletinMapper{
    // 查询所有公告（按发布时间倒序）
    List<Bulletins> selectAllBulletins();
    //根据id查询公告
    Bulletins selectById(@Param("id") long id);
    // 增加公告浏览次数
    int incrementViewCount(@Param("bulletinId") Long bulletinId);
    // 查询公告的评论列表
    List<BulletinComment> selectCommentsByBulletinId(@Param("bulletinId") Long bulletinId);
    // 插入公告评论
    int insertBulletinComment(BulletinComment comment);

    long commentCount(long id);
}
