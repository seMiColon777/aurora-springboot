package com.aurora.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteVO {

    /**
     * 要删除的id
     */
    @NotNull(message = "id不能为空")
    private List<Integer> ids;

    /**
     * 删除状态
     */
    @NotNull(message = "状态值不能为空")
    private Integer isDelete;
}
