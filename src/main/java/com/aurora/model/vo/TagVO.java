package com.aurora.model.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagVO {

    /**
     * 标签id
     */
    private Integer id;

    /**
     * 标签名
     */
    @NotBlank(message = "标签名不能为空")
    private String tagName;

}
