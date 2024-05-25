package com.aurora.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {

    /**
     * 回复用户id
     */
    private Integer replyUserId;

    /**
     * 主题id
     */
    private Integer topicId;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    private String commentContent;

    /**
     * 评论父id
     */
    private Integer parentId;

    /**
     * 评论类型
     */
    @NotNull(message = "评论类型不能为空")
    private Integer type;
}
