package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.JobDTO;
import com.aurora.model.dto.PageResultDTO;
import com.aurora.service.JobService;
import com.aurora.model.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.*;

/**
 * 定时任务模块
 */
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 添加定时任务
     *
     * @param jobVO
     * @return
     */
    @OptLog(optType = SAVE)
    @PostMapping("/admin/jobs")
    public ResultVO<?> saveJob(@RequestBody JobVO jobVO) {
        jobService.saveJob(jobVO);
        return ResultVO.ok();
    }

    /**
     * 修改定时任务
     *
     * @param jobVO
     * @return
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/admin/jobs")
    public ResultVO<?> updateJob(@RequestBody JobVO jobVO) {
        jobService.updateJob(jobVO);
        return ResultVO.ok();
    }

    /**
     * 删除定时任务
     *
     * @param jobIds
     * @return
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/jobs")
    public ResultVO<?> deleteJobById(@RequestBody List<Integer> jobIds) {
        jobService.deleteJobs(jobIds);
        return ResultVO.ok();
    }

    /**
     * 根据id获取任务
     *
     * @param jobId
     * @return
     */
    @GetMapping("/admin/jobs/{id}")
    public ResultVO<JobDTO> getJobById(@PathVariable("id") Integer jobId) {
        return ResultVO.ok(jobService.getJobById(jobId));
    }

    /**
     * 获取任务列表
     *
     * @param jobSearchVO
     * @return
     */
    @GetMapping("/admin/jobs")
    public ResultVO<PageResultDTO<JobDTO>> listJobs(JobSearchVO jobSearchVO) {
        return ResultVO.ok(jobService.listJobs(jobSearchVO));
    }

    /**
     * 更改任务的状态
     *
     * @param jobStatusVO
     * @return
     */
    @PutMapping("/admin/jobs/status")
    public ResultVO<?> updateJobStatus(@RequestBody JobStatusVO jobStatusVO) {
        jobService.updateJobStatus(jobStatusVO);
        return ResultVO.ok();
    }

    /**
     * 执行某个任务
     *
     * @param jobRunVO
     * @return
     */
    @PutMapping("/admin/jobs/run")
    public ResultVO<?> runJob(@RequestBody JobRunVO jobRunVO) {
        jobService.runJob(jobRunVO);
        return ResultVO.ok();
    }

    /**
     * 获取所有job分组
     *
     * @return
     */
    @GetMapping("/admin/jobs/jobGroups")
    public ResultVO<List<String>> listJobGroup() {
        return ResultVO.ok(jobService.listJobGroups());
    }
}
