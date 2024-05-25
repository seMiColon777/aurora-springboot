package com.aurora.controller;

import com.aurora.annotation.AccessLimit;
import com.aurora.annotation.OptLog;
import com.aurora.model.dto.CommentAdminDTO;
import com.aurora.model.dto.CommentDTO;
import com.aurora.model.dto.PageResultDTO;
import com.aurora.model.dto.ReplyDTO;
import com.aurora.service.CommentService;
import com.aurora.model.vo.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.*;

/**
 * 评论模块
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     *
     * @param commentVO commentVO
     * @return ResultVO
     */
    @AccessLimit(seconds = 60, maxCount = 3)
    @OptLog(optType = SAVE)
    @PostMapping("/comments/save")
    public ResultVO<?> saveComment(@Valid @RequestBody CommentVO commentVO) {
        commentService.saveComment(commentVO);
        return ResultVO.ok();
    }

    /**
     * 获取评论
     *
     * @param commentVO commentVO
     * @return ResultVO
     */
    @GetMapping("/comments")
    public ResultVO<PageResultDTO<CommentDTO>> getComments(CommentVO commentVO) {
        return ResultVO.ok(commentService.listComments(commentVO));
    }

    /**
     * 根据commentId获取回复
     *
     * @param commentId commentId
     * @return ResultVO
     */
    @GetMapping("/comments/{commentId}/replies")
    public ResultVO<List<ReplyDTO>> listRepliesByCommentId(@PathVariable("commentId") Integer commentId) {
        return ResultVO.ok(commentService.listRepliesByCommentId(commentId));
    }

    /**
     * 获取前六个评论
     *
     * @return ResultVO
     */
    @GetMapping("/comments/topSix")
    public ResultVO<List<CommentDTO>> listTopSixComments() {
        return ResultVO.ok(commentService.listTopSixComments());
    }

    /**
     * 查询后台评论
     *
     * @param conditionVO conditionVO
     * @return ResultVO
     */
    @GetMapping("/admin/comments")
    public ResultVO<PageResultDTO<CommentAdminDTO>> listCommentBackDTO(ConditionVO conditionVO) {
        return ResultVO.ok(commentService.listCommentsAdmin(conditionVO));
    }

    /**
     * 审核评论
     *
     * @param reviewVO reviewVO
     * @return ResultVO
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/admin/comments/review")
    public ResultVO<?> updateCommentsReview(@Valid @RequestBody ReviewVO reviewVO) {
        commentService.updateCommentsReview(reviewVO);
        return ResultVO.ok();
    }

    /**
     * 删除评论
     *
     * @param commentIdList commentIdList
     * @return ResultVO
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/comments")
    public ResultVO<?> deleteComments(@RequestBody List<Integer> commentIdList) {
        commentService.removeByIds(commentIdList);
        return ResultVO.ok();
    }

}
