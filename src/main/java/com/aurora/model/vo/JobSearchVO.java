package com.aurora.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSearchVO {

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务组别
     */
    private String jobGroup;

    /**
     * 任务状态
     */
    private Integer status;
}
