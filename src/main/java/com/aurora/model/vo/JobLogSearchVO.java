package com.aurora.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobLogSearchVO {

    /**
     * 任务Id
     */
    private Integer jobId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务的组别
     */
    private String jobGroup;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;
}
