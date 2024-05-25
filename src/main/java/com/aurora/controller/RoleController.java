package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.RoleDTO;
import com.aurora.model.dto.UserRoleDTO;
import com.aurora.model.vo.ResultVO;
import com.aurora.service.RoleService;
import com.aurora.model.vo.ConditionVO;
import com.aurora.model.dto.PageResultDTO;
import com.aurora.model.vo.RoleVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.*;

/**
 * 角色模块
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询用户角色选项
     *
     * @return
     */
    @GetMapping("/admin/users/role")
    public ResultVO<List<UserRoleDTO>> listUserRoles() {
        return ResultVO.ok(roleService.listUserRoles());
    }


    /**
     * 查询角色列表
     *
     * @param conditionVO
     * @return
     */
    @GetMapping("/admin/roles")
    public ResultVO<PageResultDTO<RoleDTO>> listRoles(ConditionVO conditionVO) {
        return ResultVO.ok(roleService.listRoles(conditionVO));
    }

    /**
     * 保存或更新角色
     *
     * @param roleVO
     * @return
     */
    @OptLog(optType = SAVE_OR_UPDATE)
    @PostMapping("/admin/role")
    public ResultVO<?> saveOrUpdateRole(@RequestBody @Valid RoleVO roleVO) {
        roleService.saveOrUpdateRole(roleVO);
        return ResultVO.ok();
    }

    /**
     * 删除角色
     *
     * @param roleIdList
     * @return
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/roles")
    public ResultVO<?> deleteRoles(@RequestBody List<Integer> roleIdList) {
        roleService.deleteRoles(roleIdList);
        return ResultVO.ok();
    }
}
