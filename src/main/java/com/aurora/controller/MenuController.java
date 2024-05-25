package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.LabelOptionDTO;
import com.aurora.model.dto.MenuDTO;
import com.aurora.model.dto.UserMenuDTO;
import com.aurora.model.vo.ResultVO;
import com.aurora.service.MenuService;
import com.aurora.model.vo.ConditionVO;
import com.aurora.model.vo.IsHiddenVO;
import com.aurora.model.vo.MenuVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.*;

/**
 * 菜单模块
 */
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查看菜单列表
     *
     * @param conditionVO
     * @return
     */
    @GetMapping("/admin/menus")
    public ResultVO<List<MenuDTO>> listMenus(ConditionVO conditionVO) {
        return ResultVO.ok(menuService.listMenus(conditionVO));
    }

    /**
     * 新增或修改菜单
     *
     * @param menuVO
     * @return
     */
    @OptLog(optType =SAVE_OR_UPDATE)
    @PostMapping("/admin/menus")
    public ResultVO<?> saveOrUpdateMenu(@Valid @RequestBody MenuVO menuVO) {
        menuService.saveOrUpdateMenu(menuVO);
        return ResultVO.ok();
    }

    /**
     * 修改目录是否隐藏
     *
     * @param isHiddenVO
     * @return
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/admin/menus/isHidden")
    public ResultVO<?> updateMenuIsHidden(@RequestBody IsHiddenVO isHiddenVO) {
        menuService.updateMenuIsHidden(isHiddenVO);
        return ResultVO.ok();
    }

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/menus/{menuId}")
    public ResultVO<?> deleteMenu(@PathVariable("menuId") Integer menuId) {
        menuService.deleteMenu(menuId);
        return ResultVO.ok();
    }

    /**
     * 查看角色菜单选项
     *
     * @return
     */
    @GetMapping("/admin/role/menus")
    public ResultVO<List<LabelOptionDTO>> listMenuOptions() {
        return ResultVO.ok(menuService.listMenuOptions());
    }

    /**
     * 查看当前用户菜单
     *
     * @return
     */
    @GetMapping("/admin/user/menus")
    public ResultVO<List<UserMenuDTO>> listUserMenus() {
        return ResultVO.ok(menuService.listUserMenus());
    }
}
