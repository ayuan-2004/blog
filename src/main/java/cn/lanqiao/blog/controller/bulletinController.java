package cn.lanqiao.blog.controller;

import cn.lanqiao.blog.mapper.UserMapper;
import cn.lanqiao.blog.model.dto.comment;
import cn.lanqiao.blog.model.pojo.BulletinComment;
import cn.lanqiao.blog.model.pojo.Bulletins;
import cn.lanqiao.blog.service.BulletinService;
import cn.lanqiao.blog.utile.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bulletin")
@Slf4j
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class bulletinController {
    @Autowired
    private BulletinService bulletinService;

    @GetMapping
    public Result getBulletin() {
        List<Bulletins> bulletins=bulletinService.getList();
        return Result.success(bulletins);
    }
    //获取公告详情
    @GetMapping("/{id}")
    public Result<Bulletins> getBulletinById(@PathVariable long id) {
        Bulletins bulletins=bulletinService.getById(id);
        return Result.success(bulletins);
    }
    //浏览次数
    @PutMapping("/{id}/view")
    public Result<Bulletins> viewBulletin(@PathVariable long id) {
        return Result.success( bulletinService.view(id));
    }
    //获取评论
    @GetMapping("/{id}/comment")
    public Result<List<BulletinComment>> getComment(@PathVariable long id) {
        return Result.success(bulletinService.getComment(id));
    }
    //添加评论
    @PostMapping("/comment")
    public Result<BulletinComment> addComment(@RequestBody comment comment) {
        return Result.success(bulletinService.addComment(comment));
    }
}
