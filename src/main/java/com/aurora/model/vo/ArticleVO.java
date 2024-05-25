package com.aurora.model.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

/**
 * 文章
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {

    /**
     * 文章id
     */
    private Integer id;

    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空")
    private String articleTitle;

    /**
     * 文章内容
     */
    @NotBlank(message = "文章内容不能为空")
    private String articleContent;

    /**
     * 文章摘要
     */
    private String articleAbstract;

    /**
     * 文章缩略图
     */
    private String articleCover;

    /**
     * 文章分类
     */
    private String categoryName;

    /**
     * 文章标签
     */
    private List<String> tagNames;

    /**
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 是否推荐
     */
    private Integer isFeatured;

    /**
     * 文章状态
     */
    private Integer status;

    /**
     * 文章类型
     */
    private Integer type;

    /**
     * 原文链接
     */
    private String originalUrl;

    /**
     * 文章访问密码
     */
    private String password;
}
