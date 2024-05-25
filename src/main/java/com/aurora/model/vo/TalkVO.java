package com.aurora.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 说说对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TalkVO {

    /**
     * 说说id
     */
    private Integer id;

    /**
     * 说说内容
     */
    @NotBlank(message = "说说内容不能为空")
    private String content;

    /**
     * 说说图片
     */
    private String images;

    /**
     * 置顶状态
     */
    @NotNull(message = "置顶状态不能为空")
    private Integer isTop;

    /**
     * 说说状态
     */
    @NotNull(message = "说说状态不能为空")
    private Integer status;

}
