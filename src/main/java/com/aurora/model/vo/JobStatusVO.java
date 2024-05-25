package com.aurora.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobStatusVO {

    /**
     * 任务id
     */
    private Integer id;

    /**
     * 任务状态
     */
    private Integer status;
}
