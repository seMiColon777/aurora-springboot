package com.aurora.model.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 友链
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendLinkVO {

    /**
     * 友链id
     */
    private Integer id;

    /**
     * 友链名
     */
    @NotBlank(message = "链接名不能为空")
    private String linkName;

    /**
     * 友链头像
     */
    @NotBlank(message = "链接头像不能为空")
    private String linkAvatar;

    /**
     * 友链头像
     */
    @NotBlank(message = "链接地址不能为空")
    private String linkAddress;

    /**
     * 友链头像
     */
    @NotBlank(message = "链接介绍不能为空")
    private String linkIntro;

}
