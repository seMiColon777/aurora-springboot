package com.aurora.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 审核
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewVO {

    /**
     * id列表
     */
    @NotNull(message = "id不能为空")
    private List<Integer> ids;

    /**
     * 删除状态
     */
    @NotNull(message = "状态值不能为空")
    private Integer isReview;

}
