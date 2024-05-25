package com.aurora.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户权限
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleVO {

    /**
     * 用户信息id
     */
    @NotNull(message = "id不能为空")
    private Integer userInfoId;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 角色id集合
     */
    @NotNull(message = "用户角色不能为空")
    private List<Integer> roleIds;

}
