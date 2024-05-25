package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.PageResultDTO;
import com.aurora.model.dto.UserInfoDTO;
import com.aurora.model.dto.UserOnlineDTO;
import com.aurora.service.UserInfoService;
import com.aurora.model.vo.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.aurora.constant.OptTypeConstant.DELETE;
import static com.aurora.constant.OptTypeConstant.UPDATE;

/**
 * 用户信息模块
 */
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 更新用户信息
     *
     * @param userInfoVO userInfoVO
     * @return ResultVO
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/users/info")
    public ResultVO<?> updateUserInfo(@Valid @RequestBody UserInfoVO userInfoVO) {
        userInfoService.updateUserInfo(userInfoVO);
        return ResultVO.ok();
    }

    /**
     * 更新用户头像
     *
     * @param file 用户头像
     * @return ResultVO
     */
    @OptLog(optType = UPDATE)
    @PostMapping("/users/avatar")
    public ResultVO<String> updateUserAvatar(MultipartFile file) {
        return ResultVO.ok(userInfoService.updateUserAvatar(file));
    }

    /**
     * 绑定用户邮箱
     *
     * @param emailVO emailVO
     * @return ResultVO
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/users/email")
    public ResultVO<?> saveUserEmail(@Valid @RequestBody EmailVO emailVO) {
        userInfoService.saveUserEmail(emailVO);
        return ResultVO.ok();
    }

    /**
     * 修改用户的订阅状态
     *
     * @param subscribeVO subscribeVO
     * @return ResultVO
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/users/subscribe")
    public ResultVO<?> updateUserSubscribe(@RequestBody SubscribeVO subscribeVO) {
        userInfoService.updateUserSubscribe(subscribeVO);
        return ResultVO.ok();
    }

    /**
     * 修改用户角色
     *
     * @param userRoleVO userRoleVO
     * @return ResultVO
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/admin/users/role")
    public ResultVO<?> updateUserRole(@Valid @RequestBody UserRoleVO userRoleVO) {
        userInfoService.updateUserRole(userRoleVO);
        return ResultVO.ok();
    }

    /**
     * 修改用户禁用状态
     *
     * @param userDisableVO userDisableVO
     * @return ResultVO
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/admin/users/disable")
    public ResultVO<?> updateUserDisable(@Valid @RequestBody UserDisableVO userDisableVO) {
        userInfoService.updateUserDisable(userDisableVO);
        return ResultVO.ok();
    }

    /**
     * 查看在线用户
     *
     * @param conditionVO conditionVO
     * @return ResultVO
     */
    @GetMapping("/admin/users/online")
    public ResultVO<PageResultDTO<UserOnlineDTO>> listOnlineUsers(ConditionVO conditionVO) {
        return ResultVO.ok(userInfoService.listOnlineUsers(conditionVO));
    }

    /**
     * 下线用户
     *
     * @param userInfoId userInfoId
     * @return ResultVO
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/users/{userInfoId}/online")
    public ResultVO<?> removeOnlineUser(@PathVariable("userInfoId") Integer userInfoId) {
        userInfoService.removeOnlineUser(userInfoId);
        return ResultVO.ok();
    }

    /**
     * 根据id获取用户信息
     *
     * @param userInfoId userInfoId
     * @return ResultVO
     */
    @GetMapping("/users/info/{userInfoId}")
    public ResultVO<UserInfoDTO> getUserInfoById(@PathVariable("userInfoId") Integer userInfoId) {
        return ResultVO.ok(userInfoService.getUserInfoById(userInfoId));
    }

}
