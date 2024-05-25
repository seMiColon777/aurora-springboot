package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.CategoryAdminDTO;
import com.aurora.model.dto.CategoryDTO;
import com.aurora.model.dto.CategoryOptionDTO;
import com.aurora.service.CategoryService;
import com.aurora.model.vo.CategoryVO;
import com.aurora.model.vo.ConditionVO;
import com.aurora.model.dto.PageResultDTO;
import com.aurora.model.vo.ResultVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.*;

/**
 * 分类模块
 */
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有分类
     *
     * @return ResultVO
     */
    @GetMapping("/categories/all")
    public ResultVO<List<CategoryDTO>> listCategories() {
        return ResultVO.ok(categoryService.listCategories());
    }

    /**
     * 查看后台分类列表
     *
     * @param conditionVO conditionVO
     * @return ResultVO
     */
    @GetMapping("/admin/categories")
    public ResultVO<PageResultDTO<CategoryAdminDTO>> listCategoriesAdmin(ConditionVO conditionVO) {
        return ResultVO.ok(categoryService.listCategoriesAdmin(conditionVO));
    }

    /**
     * 搜索文章分类
     *
     * @param conditionVO conditionVO
     * @return ResultVO
     */
    @GetMapping("/admin/categories/search")
    public ResultVO<List<CategoryOptionDTO>> listCategoriesAdminBySearch(ConditionVO conditionVO) {
        return ResultVO.ok(categoryService.listCategoriesBySearch(conditionVO));
    }

    /**
     * 删除分类
     *
     * @param categoryIds categoryIds
     * @return ResultVO
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/categories")
    public ResultVO<?> deleteCategories(@RequestBody List<Integer> categoryIds) {
        categoryService.deleteCategories(categoryIds);
        return ResultVO.ok();
    }

    /**
     * 添加或修改分类
     *
     * @param categoryVO categoryVO
     * @return ResultVO
     */
    @OptLog(optType = SAVE_OR_UPDATE)
    @PostMapping("/admin/categories")
    public ResultVO<?> saveOrUpdateCategory(@Valid @RequestBody CategoryVO categoryVO) {
        categoryService.saveOrUpdateCategory(categoryVO);
        return ResultVO.ok();
    }


}
