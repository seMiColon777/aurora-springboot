package com.aurora.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 照片信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoInfoVO {

    /**
     * 照片id
     */
    @NotNull(message = "照片id不能为空")
    private Integer id;

    /**
     * 照片名
     */
    @NotBlank(message = "照片名不能为空")
    private String photoName;

    /**
     * 照片描述
     */
    private String photoDesc;

}
