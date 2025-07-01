package cn.lanqiao.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class loginVo {
    private Long id;
    private String userName;
    private String token;

}
