package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.OperationLogDTO;
import com.aurora.model.vo.ResultVO;
import com.aurora.service.OperationLogService;
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
 * 操作日志模块
 */
@RestController
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 查看操作日志
     *
     * @param conditionVO conditionVO
     * @return ResultVO
     */
    @GetMapping("/admin/operation/logs")
    public ResultVO<PageResultDTO<OperationLogDTO>> listOperationLogs(ConditionVO conditionVO) {
        return ResultVO.ok(operationLogService.listOperationLogs(conditionVO));
    }

    /**
     * 删除操作日志
     *
     * @param operationLogIds operationLogIds
     * @return ResultVO
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/operation/logs")
    public ResultVO<?> deleteOperationLogs(@RequestBody List<Integer> operationLogIds) {
        operationLogService.removeByIds(operationLogIds);
        return ResultVO.ok();
    }

}
