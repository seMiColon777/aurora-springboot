package com.aurora.model.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceVO {

    /**
     * 资源id
     */
    private Integer id;

    /**
     * 资源名
     */
    @NotBlank(message = "资源名不能为空")
    private String resourceName;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 资源路径
     */
    private String requestMethod;

    /**
     * 父资源id
     */
    private Integer parentId;

    /**
     * 是否匿名访问
     */
    private Integer isAnonymous;

}
