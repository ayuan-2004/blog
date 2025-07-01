package cn.lanqiao.blog.controller;


import cn.lanqiao.blog.model.pojo.Comments;
import cn.lanqiao.blog.service.commentService;
import cn.lanqiao.blog.utile.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* 回复 评论
* */
@RestController
@RequestMapping("/comments")
public class commentsController {
    @Autowired
    private commentService commentService;
    /*
    *通过文章id来查看所有评论
    * */
    @GetMapping("/{postId}")
    public Result getCommentsByPostId(@PathVariable("postId") long postId){
        List<Comments> commentsList=commentService.getByPostId(postId);
        return Result.success(commentsList);
    }
    /*
    * 新增文章评论
    * */
    @PostMapping()
    public Result<Comments> addComments(@RequestBody Comments comments){
        return Result.success(commentService.addComments(comments));

    }
    /*
    * 删除评论
    * */
    @DeleteMapping("/{commentId}")
    public Result deleteComments(@PathVariable("commentId") long commentId){
        commentService.deleteComments(commentId);
        return Result.success();
    }
    /*
    * 通过用户id来查看该用户的所有评论
    * */
    @GetMapping("/user/{userId}")
    public Result getCommentsByUserId(@PathVariable("userId") long userId){
        List<Comments> commentsList = commentService.getByUserId(userId);
        return Result.success(commentsList);
    }
}
