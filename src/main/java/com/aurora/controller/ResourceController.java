package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.LabelOptionDTO;
import com.aurora.model.dto.ResourceDTO;
import com.aurora.model.vo.ResultVO;
import com.aurora.service.ResourceService;
import com.aurora.model.vo.ConditionVO;
import com.aurora.model.vo.ResourceVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.*;

/**
 * 资源模块
 */
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 查看资源列表
     *
     * @param conditionVO
     * @return
     */
    @GetMapping("/admin/resources")
    public ResultVO<List<ResourceDTO>> listResources(ConditionVO conditionVO) {
        return ResultVO.ok(resourceService.listResources(conditionVO));
    }

    /**
     * 删除资源
     *
     * @param resourceId
     * @return
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/resources/{resourceId}")
    public ResultVO<?> deleteResource(@PathVariable("resourceId") Integer resourceId) {
        resourceService.deleteResource(resourceId);
        return ResultVO.ok();
    }

    /**
     * 新增或修改资源
     *
     * @param resourceVO
     * @return
     */
    @OptLog(optType = SAVE_OR_UPDATE)
    @PostMapping("/admin/resources")
    public ResultVO<?> saveOrUpdateResource(@RequestBody @Valid ResourceVO resourceVO) {
        resourceService.saveOrUpdateResource(resourceVO);
        return ResultVO.ok();
    }

    /**
     * 查看角色资源选项
     *
     * @return
     */
    @GetMapping("/admin/role/resources")
    public ResultVO<List<LabelOptionDTO>> listResourceOption() {
        return ResultVO.ok(resourceService.listResourceOption());
    }
}
