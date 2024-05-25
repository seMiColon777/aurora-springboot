package com.aurora.model.vo;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 照片
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoVO {

    /**
     * 相册id
     */
    @NotNull(message = "相册id不能为空")
    private Integer albumId;

    /**
     * 照片列表
     */
    private List<String> photoUrls;

    /**
     * 照片id列表
     */
    private List<Integer> photoIds;

}
