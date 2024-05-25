package com.aurora.controller;


import com.aurora.annotation.AccessLimit;
import com.aurora.annotation.OptLog;
import com.aurora.model.dto.*;
import com.aurora.service.UserAuthService;
import com.aurora.model.vo.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.UPDATE;

/**
 * 用户账号模块
 */
@RestController
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    /**
     * 发送邮箱验证码
     *
     * @param username 用户名
     * @return
     */
    @AccessLimit(seconds = 60,maxCount = 1)
    @GetMapping("/users/code")
    public ResultVO<?> sendCode(String username) {
        userAuthService.sendCode(username);
        return ResultVO.ok();
    }

    /**
     * 获取用户区域分布
     *
     * @param conditionVO
     * @return
     */
    @GetMapping("/admin/users/area")
    public ResultVO<List<UserAreaDTO>> listUserAreas(ConditionVO conditionVO) {
        return ResultVO.ok(userAuthService.listUserAreas(conditionVO));
    }

    /**
     * 查询后台用户列表
     *
     * @param conditionVO
     * @return
     */
    @GetMapping("/admin/users")
    public ResultVO<PageResultDTO<UserAdminDTO>> listUsers(ConditionVO conditionVO) {
        return ResultVO.ok(userAuthService.listUsers(conditionVO));
    }

    /**
     * 用户注册
     *
     * @param userVO
     * @return
     */
    @PostMapping("/users/register")
    public ResultVO<?> register(@Valid @RequestBody UserVO userVO) {
        userAuthService.register(userVO);
        return ResultVO.ok();
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/users/password")
    public ResultVO<?> updatePassword(@Valid @RequestBody UserVO user) {
        userAuthService.updatePassword(user);
        return ResultVO.ok();
    }

    /**
     * 修改管理员密码
     *
     * @param passwordVO
     * @return
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/admin/users/password")
    public ResultVO<?> updateAdminPassword(@Valid @RequestBody PasswordVO passwordVO) {
        userAuthService.updateAdminPassword(passwordVO);
        return ResultVO.ok();
    }

    /**
     * 用户登出
     *
     * @return
     */
    @PostMapping("/users/logout")
    public ResultVO<UserLogoutStatusDTO> logout() {
        return ResultVO.ok(userAuthService.logout());
    }

    /**
     * qq登录
     *
     * @param qqLoginVO
     * @return
     */
    @PostMapping("/users/oauth/qq")
    public ResultVO<UserInfoDTO> qqLogin(@Valid @RequestBody QQLoginVO qqLoginVO) {
        return ResultVO.ok(userAuthService.qqLogin(qqLoginVO));
    }

}
