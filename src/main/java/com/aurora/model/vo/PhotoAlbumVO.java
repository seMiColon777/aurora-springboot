package com.aurora.model.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 相册
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoAlbumVO {

    /**
     * 相册id
     */
    private Integer id;

    /**
     * 相册名
     */
    @NotBlank(message = "相册名不能为空")
    private String albumName;

    /**
     * 相册描述
     */
    private String albumDesc;

    /**
     * 相册封面
     */
    @NotBlank(message = "相册封面不能为空")
    private String albumCover;

    /**
     * 状态值
     */
    private Integer status;

}
