package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.ExceptionLogDTO;
import com.aurora.model.vo.ResultVO;
import com.aurora.service.ExceptionLogService;
import com.aurora.model.vo.ConditionVO;
import com.aurora.model.dto.PageResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.DELETE;

/**
 * 异常日志模块
 */
@RestController
public class ExceptionLogController {

    @Autowired
    private ExceptionLogService exceptionLogService;

    /**
     * 获取异常日志
     *
     * @param conditionVO conditionVO
     * @return ResultVO
     */
    @GetMapping("/admin/exception/logs")
    public ResultVO<PageResultDTO<ExceptionLogDTO>> listExceptionLogs(ConditionVO conditionVO) {
        return ResultVO.ok(exceptionLogService.listExceptionLogs(conditionVO));
    }

    /**
     * 删除异常日志
     *
     * @param exceptionLogIds exceptionLogIds
     * @return ResultVO
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/exception/logs")
    public ResultVO<?> deleteExceptionLogs(@RequestBody List<Integer> exceptionLogIds) {
        exceptionLogService.removeByIds(exceptionLogIds);
        return ResultVO.ok();
    }

}
