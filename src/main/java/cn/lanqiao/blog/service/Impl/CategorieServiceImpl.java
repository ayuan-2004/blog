package cn.lanqiao.blog.service.Impl;

import cn.lanqiao.blog.mapper.CategoriesMapper;
import cn.lanqiao.blog.model.pojo.Categories;
import cn.lanqiao.blog.model.pojo.Posts;
import cn.lanqiao.blog.model.vo.PostVo;
import cn.lanqiao.blog.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {
    @Autowired
    private CategoriesMapper categorieSMapper;
    @Override
    public List<Categories> list() {
        return categorieSMapper.getList();
    }

    @Override
    public List<PostVo> listById(long id) {
        return categorieSMapper.selectPostsByCategoryId(id);
    }
}
