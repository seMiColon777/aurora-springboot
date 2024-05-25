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
     * @return ResultVO
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
     * @param conditionVO conditionVO
     * @return ResultVO
     */
    @GetMapping("/admin/users/area")
    public ResultVO<List<UserAreaDTO>> listUserAreas(ConditionVO conditionVO) {
        return ResultVO.ok(userAuthService.listUserAreas(conditionVO));
    }

    /**
     * 查询后台用户列表
     *
     * @param conditionVO conditionVO
     * @return ResultVO
     */
    @GetMapping("/admin/users")
    public ResultVO<PageResultDTO<UserAdminDTO>> listUsers(ConditionVO conditionVO) {
        return ResultVO.ok(userAuthService.listUsers(conditionVO));
    }

    /**
     * 用户注册
     *
     * @param userVO userVO
     * @return ResultVO
     */
    @PostMapping("/users/register")
    public ResultVO<?> register(@Valid @RequestBody UserVO userVO) {
        userAuthService.register(userVO);
        return ResultVO.ok();
    }

    /**
     * 修改密码
     *
     * @param user user
     * @return ResultVO
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
     * @param passwordVO passwordVO
     * @return ResultVO
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
     * @return ResultVO
     */
    @PostMapping("/users/logout")
    public ResultVO<UserLogoutStatusDTO> logout() {
        return ResultVO.ok(userAuthService.logout());
    }

    /**
     * qq登录
     *
     * @param qqLoginVO qqLoginVO
     * @return ResultVO
     */
    @PostMapping("/users/oauth/qq")
    public ResultVO<UserInfoDTO> qqLogin(@Valid @RequestBody QQLoginVO qqLoginVO) {
        return ResultVO.ok(userAuthService.qqLogin(qqLoginVO));
    }

}
