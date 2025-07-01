package cn.lanqiao.blog.mapper;

import cn.lanqiao.blog.model.pojo.Categories;
import cn.lanqiao.blog.model.pojo.Posts;
import cn.lanqiao.blog.model.vo.PostVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoriesMapper extends BaseMapper<Categories> {
    List<Categories> getList();

    List<PostVo> selectPostsByCategoryId(@Param("categoryId") long id);
}
