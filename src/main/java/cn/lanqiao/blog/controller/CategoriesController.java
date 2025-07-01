package cn.lanqiao.blog.controller;

import cn.lanqiao.blog.model.pojo.Categories;
import cn.lanqiao.blog.model.pojo.Posts;
import cn.lanqiao.blog.model.vo.PostVo;
import cn.lanqiao.blog.service.CategorieService;
import cn.lanqiao.blog.utile.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private CategorieService categorieService;
    @GetMapping("/list")
    public Result<List<Categories>> list(){
        return Result.success(categorieService.list());
    }
    //通过分类的id去查询文章
    @GetMapping("/list/{categoryId}")
    public Result<List<PostVo>> listById(@PathVariable("categoryId") long categoryId){
        return Result.success(categorieService.listById(categoryId));
    }
}
