package cn.lanqiao.blog.service.Impl;

import cn.lanqiao.blog.mapper.TagsMapper;
import cn.lanqiao.blog.model.pojo.Categories;
import cn.lanqiao.blog.model.pojo.PostImages;
import cn.lanqiao.blog.model.pojo.Tags;
import cn.lanqiao.blog.utile.EntityFillUtil;
import cn.lanqiao.blog.mapper.ArticleMapper;
import cn.lanqiao.blog.model.dto.ArticleDto;
import cn.lanqiao.blog.model.pojo.Posts;
import cn.lanqiao.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagsMapper tagsMapper;
    //自动填充
    private Posts convertToPost(ArticleDto dto) {
        //将ArticleDto对象转换为Posts对象
        Posts post = new Posts();
        //设置标题
        post.setTitle(dto.getTitle());
        //设置状态
        post.setStatus(dto.getStatus());
        //设置内容
        post.setContent(dto.getContent());
        //设置摘要
        post.setExcerpt(dto.getExcerpt());
        //设置特色图片
        post.setFeaturedImage(dto.getFeaturedImage());
        //设置作者ID
        post.setAuthorId(dto.getAuthorId());
        //设置别名
        post.setSlug(dto.getSlug());
        //设置关键词
        post.setKeyword(dto.getKeyword());
        //设置浏览次数
        post.setViewCount(dto.getViewCount());
        //设置评论次数
        post.setCommentCount(dto.getCommentCount());
        //设置创建时间
        post.setCreatedAt(dto.getCreatedAt());
        //设置更新时间
        post.setUpdatedAt(dto.getUpdatedAt());
        //设置发布时间
        post.setPublishedAt(dto.getPublishedAt());
        if (dto.getCategories() != null) {
            post.setCategories(new ArrayList<>(dto.getCategories()));
        }
        if (dto.getTags() != null) {
            post.setTags(new ArrayList<>(dto.getTags()));
        }
        if (dto.getSlug() != null && !dto.getSlug().isEmpty()) {
            post.setSlug(dto.getSlug());
        } else {
            post.setSlug(generateSlug(dto.getTitle()));
        }
        //返回Posts对象
        return post;
    }
    private String generateSlug(String title) {
        return Normalizer.normalize(title, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9\\s]", "")
                .trim()
                .toLowerCase()
                .replaceAll("\\s+", "-");
    }
    @Override
    public List<Posts> selectList(ArticleDto articleDto) {
        List<Posts> posts = articleMapper.selectArticle(articleDto);
        return posts;
    }

    @Override
    public Posts getByPostId(Long postId) {
        Posts posts=articleMapper.getByPostId(postId);
        return posts;
    }

    @Override
    public List<Posts> selectByUserId(Long userId) {
        List<Posts> post=articleMapper.getByUserId(userId);
        return post;
    }

    @Override
    public void addArticle(ArticleDto articleDto) {
        //将ArticleDto转换为Posts对象
        Posts post = convertToPost(articleDto);
        articleMapper.insertPost(post);
        long postId = post.getPostId(); // MyBatis 会自动填充自增主键
        // Step 3: 插入分类关联
        if (articleDto.getCategories() != null && !articleDto.getCategories().isEmpty()) {
            for (Categories category : articleDto.getCategories()) {
                articleMapper.insertPostCategory(postId, category.getCategoryId());
            }
        }

        // Step 4: 插入标签关联
        if (articleDto.getTags() != null && !articleDto.getTags().isEmpty()) {
            for (Tags tag : articleDto.getTags()) {
                articleMapper.insertPostTag(postId, tag.getTagId());
            }
        }

    }

    @Override
    // 重写updateArticle方法，用于更新文章
    public void updateArticle(Posts posts) {
        // 根据文章ID获取文章
        Posts byPostId = articleMapper.getByPostId(posts.getPostId());
        // 处理更新操作
        EntityFillUtil.handleUpdate(posts,byPostId);
        // 更新文章
        articleMapper.updateArticle(posts);
    }

    @Override
    public void deleteArticle(Long postId) {
        articleMapper.delete(postId);
    }


    @Override
    public List<Tags> selectTags() {
        return tagsMapper.selectList();
    }

    @Override
    public Posts updateView(Long postId) {
        articleMapper.updateView(postId);
        return articleMapper.getByPostId(postId);
    }

    @Override
    public List<Posts> getHotPage() {
        return articleMapper.getHotPage();
    }
}
