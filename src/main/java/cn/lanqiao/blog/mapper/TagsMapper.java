package cn.lanqiao.blog.mapper;

import cn.lanqiao.blog.model.pojo.Tags;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagsMapper {
    List<Tags> selectList();
    String selectSlug(Integer tagId);
}
