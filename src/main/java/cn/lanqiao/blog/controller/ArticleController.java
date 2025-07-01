package cn.lanqiao.blog.controller;


import cn.lanqiao.blog.model.dto.ArticleDto;
import cn.lanqiao.blog.model.pojo.Posts;
import cn.lanqiao.blog.service.ArticleService;
import cn.lanqiao.blog.utile.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 文章管理
 * */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    /*
    * 查询所有文章
    * */
    @GetMapping("/page")
    public Result selectList(ArticleDto articleDto){
        List<Posts> posts = articleService.selectList(articleDto);
        return Result.success(posts);
    }
    /*
    * 通过id查询文章详情
    * */
    @GetMapping("/{postId}")
    public Result<Posts> selectById(@PathVariable("postId") Long postId){
        return Result.success(articleService.getByPostId(postId));
    }
    /*
    * 查询个人的文章列表
    * */
    @GetMapping("/page/{userId}")
    public Result selectByUserId(@PathVariable long userId){
        return Result.success(articleService.selectByUserId(userId));
    }
    /*
    * 新增文章
    * */
    @PostMapping
    public Result add(@RequestBody ArticleDto articleDto){
        // 打印文章信息
        //System.out.println(articleDto);
        // 调用文章服务新增文章
        articleService.addArticle(articleDto);
        // 返回成功结果
        return Result.success();
    }
    /*
    * 修改文章
    * */
    @PutMapping
    public Result update(@RequestBody Posts posts){
        articleService.updateArticle(posts);
        return Result.success();
    }
    /*
    * 删除文章
    * */
    @DeleteMapping("/{postId}")
    public Result delete(@PathVariable("postId")Long postId){
        articleService.deleteArticle(postId);
        return Result.success();
    }
    /*
    * 标签
    * */
    @GetMapping("/tags")
    public Result selectTags(){
        return Result.success(articleService.selectTags());
    }
    /**
     * 改变浏览次数
     * */
    @PutMapping("/view/{postId}")
    public Result updateView(@PathVariable("postId")Long postId){
        return Result.success(articleService.updateView(postId));
    }
}
