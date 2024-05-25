package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.enums.FilePathEnum;
import com.aurora.model.dto.*;
import com.aurora.model.vo.*;
import com.aurora.service.ArticleService;
import com.aurora.strategy.context.ArticleImportStrategyContext;
import com.aurora.strategy.context.UploadStrategyContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.*;

/**
 * 文章模块
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private ArticleImportStrategyContext articleImportStrategyContext;

    /**
     * 获取置顶和推荐文章
     *
     * @return ResultVO
     */
    @GetMapping("/articles/topAndFeatured")
    public ResultVO<TopAndFeaturedArticlesDTO> listTopAndFeaturedArticles() {
        return ResultVO.ok(articleService.listTopAndFeaturedArticles());
    }

    /**
     * 获取所有文章
     *
     * @return ResultVO
     */
    @GetMapping("/articles/all")
    public ResultVO<PageResultDTO<ArticleCardDTO>> listArticles() {
        return ResultVO.ok(articleService.listArticles());
    }

    /**
     * 根据分类id获取文章
     *
     * @param categoryId categoryId
     * @return ResultVO
     */
    @GetMapping("/articles/categoryId")
    public ResultVO<PageResultDTO<ArticleCardDTO>> getArticlesByCategoryId(@RequestParam Integer categoryId) {
        return ResultVO.ok(articleService.listArticlesByCategoryId(categoryId));
    }

    /**
     * 根据id获取文章
     *
     * @param articleId articleId
     * @return ResultVO
     */
    @GetMapping("/articles/{articleId}")
    public ResultVO<ArticleDTO> getArticleById(@PathVariable("articleId") Integer articleId) {
        return ResultVO.ok(articleService.getArticleById(articleId));
    }

    /**
     * 校验文章访问密码
     *
     * @param articlePasswordVO articlePasswordVO
     * @return ResultVO
     */
    @PostMapping("/articles/access")
    public ResultVO<?> accessArticle(@Valid @RequestBody ArticlePasswordVO articlePasswordVO) {
        articleService.accessArticle(articlePasswordVO);
        return ResultVO.ok();
    }

    /**
     * 根据标签id获取文章
     *
     * @param tagId tagId
     * @return ResultVO
     */
    @GetMapping("/articles/tagId")
    public ResultVO<PageResultDTO<ArticleCardDTO>> listArticlesByTagId(@RequestParam Integer tagId) {
        return ResultVO.ok(articleService.listArticlesByTagId(tagId));
    }

    /**
     * 获取所有文章归档
     *
     * @return ResultVO
     */
    @GetMapping("/archives/all")
    public ResultVO<PageResultDTO<ArchiveDTO>> listArchives() {
        return ResultVO.ok(articleService.listArchives());
    }

    /**
     * 获取后台文章
     *
     * @param conditionVO conditionVO
     * @return ResultVO
     */
    @GetMapping("/admin/articles")
    public ResultVO<PageResultDTO<ArticleAdminDTO>> listArticlesAdmin(ConditionVO conditionVO) {
        return ResultVO.ok(articleService.listArticlesAdmin(conditionVO));
    }

    /**
     * 保存和修改文章
     *
     * @param articleVO articleVO
     * @return ResultVO
     */
    @OptLog(optType = SAVE_OR_UPDATE)
    @PostMapping("/admin/articles")
    public ResultVO<?> saveOrUpdateArticle(@Valid @RequestBody ArticleVO articleVO) {
        articleService.saveOrUpdateArticle(articleVO);
        return ResultVO.ok();
    }

    /**
     * 修改文章是否置顶和推荐
     *
     * @param articleTopFeaturedVO articleTopFeaturedVO
     * @return ResultVO
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/admin/articles/topAndFeatured")
    public ResultVO<?> updateArticleTopAndFeatured(@Valid @RequestBody ArticleTopFeaturedVO articleTopFeaturedVO) {
        articleService.updateArticleTopAndFeatured(articleTopFeaturedVO);
        return ResultVO.ok();
    }

    /**
     * 删除或者恢复文章
     *
     * @param deleteVO deleteVO
     * @return ResultVO
     */
    @PutMapping("/admin/articles")
    public ResultVO<?> updateArticleDelete(@Valid @RequestBody DeleteVO deleteVO) {
        articleService.updateArticleDelete(deleteVO);
        return ResultVO.ok();
    }

    /**
     * 物理删除文章
     *
     * @param articleIds articleIds
     * @return ResultVO
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/articles/delete")
    public ResultVO<?> deleteArticles(@RequestBody List<Integer> articleIds) {
        articleService.deleteArticles(articleIds);
        return ResultVO.ok();
    }

    /**
     * 上传文章图片
     *
     * @param file 文章图片
     * @return ResultVO
     */
    @OptLog(optType = UPLOAD)
    @PostMapping("/admin/articles/images")
    public ResultVO<String> saveArticleImages(MultipartFile file) {
        return ResultVO.ok(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.ARTICLE.getPath()));
    }

    /**
     * 根据id查看后台文章
     *
     * @param articleId 文章id
     * @return ResultVO
     */
    @GetMapping("/admin/articles/{articleId}")
    public ResultVO<ArticleAdminViewDTO> getArticleBackById(@PathVariable("articleId") Integer articleId) {
        return ResultVO.ok(articleService.getArticleByIdAdmin(articleId));
    }

    /**
     * 导入文章
     *
     * @param file 文件
     * @param type 类型
     * @return ResultVO
     */
    @OptLog(optType = UPLOAD)
    @PostMapping("/admin/articles/import")
    public ResultVO<?> importArticles(MultipartFile file, @RequestParam(required = false) String type) {
        articleImportStrategyContext.importArticles(file, type);
        return ResultVO.ok();
    }

    /**
     * 导出文章
     *
     * @param articleIds 文章id
     * @return ResultVO
     */
    @OptLog(optType = EXPORT)
    @PostMapping("/admin/articles/export")
    public ResultVO<List<String>> exportArticles(@RequestBody List<Integer> articleIds) {
        return ResultVO.ok(articleService.exportArticles(articleIds));
    }

    /**
     * 搜索文章
     *
     * @param condition 搜索条件
     * @return ResultVO
     */
    @GetMapping("/articles/search")
    public ResultVO<List<ArticleSearchDTO>> listArticlesBySearch(ConditionVO condition) {
        return ResultVO.ok(articleService.listArticlesBySearch(condition));
    }

}
