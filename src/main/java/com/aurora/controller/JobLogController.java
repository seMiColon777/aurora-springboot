package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.JobLogDTO;
import com.aurora.model.vo.ResultVO;
import com.aurora.service.JobLogService;
import com.aurora.model.vo.JobLogSearchVO;
import com.aurora.model.dto.PageResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.DELETE;

/**
 * 定时任务日志模块
 */
@RestController
public class JobLogController {

    @Autowired
    private JobLogService jobLogService;

    /**
     * 获取定时任务的日志列表
     *
     * @param jobLogSearchVO
     * @return
     */
    @GetMapping("/admin/jobLogs")
    public ResultVO<PageResultDTO<JobLogDTO>> listJobLogs(JobLogSearchVO jobLogSearchVO) {
        return ResultVO.ok(jobLogService.listJobLogs(jobLogSearchVO));
    }

    /**
     * 删除定时任务的日志
     *
     * @param ids
     * @return
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/jobLogs")
    public ResultVO<?> deleteJobLogs(@RequestBody List<Integer> ids) {
        jobLogService.deleteJobLogs(ids);
        return ResultVO.ok();
    }

    /**
     * 清除定时任务的日志
     *
     * @return
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/jobLogs/clean")
    public ResultVO<?> cleanJobLogs() {
        jobLogService.cleanJobLogs();
        return ResultVO.ok();
    }

    /**
     * 获取定时任务日志的所有组名
     *
     * @return
     */
    @GetMapping("/admin/jobLogs/jobGroups")
    public ResultVO<?> listJobLogGroups() {
        return ResultVO.ok(jobLogService.listJobLogGroups());
    }
}
