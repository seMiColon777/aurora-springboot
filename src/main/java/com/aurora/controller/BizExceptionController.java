package com.aurora.controller;

import com.aurora.exception.BizException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常处理模块
 */
@RestController
public class BizExceptionController {

    /**
     * 处理BizException
     *
     * @param request request
     */
    @SneakyThrows
    @RequestMapping("/bizException")
    public void handleBizException(HttpServletRequest request) {
        if (request.getAttribute("bizException") instanceof BizException) {
            System.out.println(request.getAttribute("bizException"));
            throw ((BizException) request.getAttribute("bizException"));
        } else {
            throw new Exception();
        }
    }

}
