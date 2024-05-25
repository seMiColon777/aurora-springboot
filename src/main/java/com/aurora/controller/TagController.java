package com.aurora.controller;


import com.aurora.annotation.OptLog;
import com.aurora.model.dto.TagAdminDTO;
import com.aurora.model.dto.TagDTO;
import com.aurora.model.vo.ResultVO;
import com.aurora.service.TagService;
import com.aurora.model.vo.ConditionVO;
import com.aurora.model.dto.PageResultDTO;
import com.aurora.model.vo.TagVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.*;

/**
 * 标签模块
 */
@RestController
public class TagController {


    @Autowired
    private TagService tagService;

    /**
     * 获取所有标签
     *
     * @return ResultVO
     */
    @GetMapping("/tags/all")
    public ResultVO<List<TagDTO>> getAllTags() {
        return ResultVO.ok(tagService.listTags());
    }

    /**
     * 获取前十个标签
     *
     * @return ResultVO
     */
    @GetMapping("/tags/topTen")
    public ResultVO<List<TagDTO>> getTopTenTags() {
        return ResultVO.ok(tagService.listTopTenTags());
    }

    /**
     * 查询后台标签列表
     *
     * @param conditionVO conditionVO
     * @return ResultVO
     */
    @GetMapping("/admin/tags")
    public ResultVO<PageResultDTO<TagAdminDTO>> listTagsAdmin(ConditionVO conditionVO) {
        return ResultVO.ok(tagService.listTagsAdmin(conditionVO));
    }

    /**
     * 搜索文章标签
     *
     * @param condition condition
     * @return ResultVO
     */
    @GetMapping("/admin/tags/search")
    public ResultVO<List<TagAdminDTO>> listTagsAdminBySearch(ConditionVO condition) {
        return ResultVO.ok(tagService.listTagsAdminBySearch(condition));
    }

    /**
     * 添加或修改标签
     *
     * @param tagVO tagVO
     * @return ResultVO
     */
    @OptLog(optType = SAVE_OR_UPDATE)
    @PostMapping("/admin/tags")
    public ResultVO<?> saveOrUpdateTag(@Valid @RequestBody TagVO tagVO) {
        tagService.saveOrUpdateTag(tagVO);
        return ResultVO.ok();
    }

    /**
     * 删除标签
     *
     * @param tagIdList tagIdList
     * @return ResultVO
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/tags")
    public ResultVO<?> deleteTag(@RequestBody List<Integer> tagIdList) {
        tagService.deleteTag(tagIdList);
        return ResultVO.ok();
    }
}
