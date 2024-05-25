package com.aurora.model.dto;

import lombok.*;

import java.util.List;


/**
 * 文章编辑页码DTO
 *
 * @author 花未眠
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAdminViewDTO {

    private Integer id;

    private String articleTitle;

    private String articleAbstract;

    private String articleContent;

    private String articleCover;

    private String categoryName;

    private List<String> tagNames;

    private Integer isTop;

    private Integer isFeatured;

    private Integer status;

    private Integer type;

    private String originalUrl;

    private String password;

}
