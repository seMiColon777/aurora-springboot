package com.aurora.model.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * qq登录信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QQLoginVO {

    /**
     * qq openId
     */
    @NotBlank(message = "openId不能为空")
    private String openId;

    /**
     * qq accessToken
     */
    @NotBlank(message = "accessToken不能为空")
    private String accessToken;

}
