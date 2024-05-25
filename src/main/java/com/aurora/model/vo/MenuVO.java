package com.aurora.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 菜单名
     */
    @NotBlank(message = "菜单名不能为空")
    private String name;

    /**
     * 菜单icon
     */
    @NotBlank(message = "菜单icon不能为空")
    private String icon;

    /**
     * 路径
     */
    @NotBlank(message = "路径不能为空")
    private String path;

    /**
     * 组件
     */
    @NotBlank(message = "组件不能为空")
    private String component;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer orderNum;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 是否隐藏
     */
    private Integer isHidden;

}
