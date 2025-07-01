package cn.lanqiao.blog.service;

import cn.lanqiao.blog.model.pojo.Categories;
import cn.lanqiao.blog.model.pojo.Posts;
import cn.lanqiao.blog.model.vo.PostVo;
import cn.lanqiao.blog.utile.Result;

import java.util.List;

public interface CategorieService {
    List<Categories> list();

    List<PostVo> listById(long id);
}
