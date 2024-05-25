package com.aurora.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobRunVO {

    /**
     * 任务id
     */
    private Integer id;

    /**
     * 任务组别
     */
    private String jobGroup;
}
