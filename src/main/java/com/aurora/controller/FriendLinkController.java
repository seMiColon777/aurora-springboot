package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.FriendLinkAdminDTO;
import com.aurora.model.dto.FriendLinkDTO;
import com.aurora.model.vo.ResultVO;
import com.aurora.service.FriendLinkService;
import com.aurora.model.vo.ConditionVO;
import com.aurora.model.vo.FriendLinkVO;
import com.aurora.model.dto.PageResultDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.*;

/**
 * 友链模块
 */
@RestController
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 查看友链列表
     *
     * @return
     */
    @GetMapping("/links")
    public ResultVO<List<FriendLinkDTO>> listFriendLinks() {
        return ResultVO.ok(friendLinkService.listFriendLinks());
    }

    /**
     * 查看后台友链列表
     *
     * @param conditionVO
     * @return
     */
    @GetMapping("/admin/links")
    public ResultVO<PageResultDTO<FriendLinkAdminDTO>> listFriendLinkDTO(ConditionVO conditionVO) {
        return ResultVO.ok(friendLinkService.listFriendLinksAdmin(conditionVO));
    }

    /**
     * 保存或修改友链
     *
     * @param friendLinkVO
     * @return
     */
    @OptLog(optType = SAVE_OR_UPDATE)
    @PostMapping("/admin/links")
    public ResultVO<?> saveOrUpdateFriendLink(@Valid @RequestBody FriendLinkVO friendLinkVO) {
        friendLinkService.saveOrUpdateFriendLink(friendLinkVO);
        return ResultVO.ok();
    }

    /**
     * 删除友链
     *
     * @param linkIdList
     * @return
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/links")
    public ResultVO<?> deleteFriendLink(@RequestBody List<Integer> linkIdList) {
        friendLinkService.removeByIds(linkIdList);
        return ResultVO.ok();
    }
}
