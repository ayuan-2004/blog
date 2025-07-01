package cn.lanqiao.blog.mapper;


import cn.lanqiao.blog.model.pojo.Comments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface commentsMapper extends BaseMapper<Comments> {
    List<Comments> getByPostId(long postId);

    void addComments(Comments comments);

    void deleteByCommenrId(@Param("commentId") long commentId);

    List<Comments> getByUserId(long userId);
}
