package cn.lanqiao.blog.controller;

import cn.lanqiao.blog.config.QiniuUploader;
import cn.lanqiao.blog.utile.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;
@RestController
@RequestMapping("/file")  // 修改这里，使用 @RequestMapping
public class FileController {  // 修正类名拼写错误(Filde→File)

    @Autowired
    private QiniuUploader qiniuUploader;

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return Result.error("请选择文件上传");
        }

        // 判断文件大小是否超过限制
        if(file.getSize() > 20 * 1024 * 1024) {
            return Result.error("文件大小不超过20MB");
        }


        // 判断文件格式
        Set<String> allowedTypes = Set.of("image/png", "image/jpeg", "image/jpg");
        String contentType = file.getContentType();
        if (contentType == null || !allowedTypes.contains(contentType)) {
            return Result.error("仅能上传 PNG、JPEG、JPG 类型图片");
        }

        try {
            // 生成唯一的文件名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID() + fileExtension;

            // 上传文件到七牛云
            String url = qiniuUploader.upload(file, fileName);
            return Result.success(url);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("文件上传失败: " + e.getMessage());
        }
    }
}