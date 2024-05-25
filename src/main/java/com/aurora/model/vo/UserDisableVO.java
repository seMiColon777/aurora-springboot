package com.aurora.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用户禁用状态
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDisableVO {

    @NotNull(message = "用户id不能为空")
    private Integer id;

    @NotNull(message = "用户禁用状态不能为空")
    private Integer isDisable;

}
