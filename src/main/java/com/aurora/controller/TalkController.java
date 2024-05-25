package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.TalkAdminDTO;
import com.aurora.model.dto.TalkDTO;
import com.aurora.enums.FilePathEnum;
import com.aurora.model.vo.ResultVO;
import com.aurora.service.TalkService;
import com.aurora.strategy.context.UploadStrategyContext;
import com.aurora.model.vo.ConditionVO;
import com.aurora.model.dto.PageResultDTO;
import com.aurora.model.vo.TalkVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.*;

/**
 * 说说模块
 */
@RestController
public class TalkController {

    @Autowired
    private TalkService talkService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    /**
     * 查看说说列表
     *
     * @return ResultVO
     */
    @GetMapping("/talks")
    public ResultVO<PageResultDTO<TalkDTO>> listTalks() {
        return ResultVO.ok(talkService.listTalks());
    }

    /**
     * 根据id查看说说
     *
     * @param talkId 说说id
     * @return ResultVO
     */
    @GetMapping("/talks/{talkId}")
    public ResultVO<TalkDTO> getTalkById(@PathVariable("talkId") Integer talkId) {
        return ResultVO.ok(talkService.getTalkById(talkId));
    }

    /**
     * 上传说说图片
     *
     * @param file 说说图片
     * @return ResultVO
     */
    @OptLog(optType = UPLOAD)
    @PostMapping("/admin/talks/images")
    public ResultVO<String> saveTalkImages(MultipartFile file) {
        return ResultVO.ok(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.TALK.getPath()));
    }

    /**
     * 保存或修改说说
     *
     * @param talkVO talkVO
     * @return ResultVO
     */
    @OptLog(optType = SAVE_OR_UPDATE)
    @PostMapping("/admin/talks")
    public ResultVO<?> saveOrUpdateTalk(@Valid @RequestBody TalkVO talkVO) {
        talkService.saveOrUpdateTalk(talkVO);
        return ResultVO.ok();
    }

    /**
     * 删除说说
     *
     * @param talkIds talkIds
     * @return ResultVO
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/talks")
    public ResultVO<?> deleteTalks(@RequestBody List<Integer> talkIds) {
        talkService.deleteTalks(talkIds);
        return ResultVO.ok();
    }

    /**
     * 查看后台说说
     *
     * @param conditionVO conditionVO
     * @return ResultVO
     */
    @GetMapping("/admin/talks")
    public ResultVO<PageResultDTO<TalkAdminDTO>> listBackTalks(ConditionVO conditionVO) {
        return ResultVO.ok(talkService.listBackTalks(conditionVO));
    }

    /**
     * 根据id查看后台说说
     *
     * @param talkId 说说id
     * @return ResultVO
     */
    @GetMapping("/admin/talks/{talkId}")
    public ResultVO<TalkAdminDTO> getBackTalkById(@PathVariable("talkId") Integer talkId) {
        return ResultVO.ok(talkService.getBackTalkById(talkId));
    }

}
