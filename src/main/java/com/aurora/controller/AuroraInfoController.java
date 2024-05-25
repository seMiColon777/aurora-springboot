package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.AboutDTO;
import com.aurora.model.dto.AuroraAdminInfoDTO;
import com.aurora.model.dto.AuroraHomeInfoDTO;
import com.aurora.model.dto.WebsiteConfigDTO;
import com.aurora.enums.FilePathEnum;
import com.aurora.model.vo.ResultVO;
import com.aurora.service.AuroraInfoService;
import com.aurora.strategy.context.UploadStrategyContext;
import com.aurora.model.vo.AboutVO;
import com.aurora.model.vo.WebsiteConfigVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.aurora.constant.OptTypeConstant.UPDATE;
import static com.aurora.constant.OptTypeConstant.UPLOAD;

/**
 * aurora信息
 */
@RestController
public class AuroraInfoController {

    @Autowired
    private AuroraInfoService auroraInfoService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    /**
     * 上报访客信息
     *
     * @return ResultVO
     */
    @PostMapping("/report")
    public ResultVO<?> report() {
        auroraInfoService.report();
        return ResultVO.ok();
    }

    /**
     * 获取系统信息
     *
     * @return ResultVO
     */
    @GetMapping("/")
    public ResultVO<AuroraHomeInfoDTO> getBlogHomeInfo() {
        return ResultVO.ok(auroraInfoService.getAuroraHomeInfo());
    }

    /**
     * 获取系统后台信息
     *
     * @return ResultVO
     */
    @GetMapping("/admin")
    public ResultVO<AuroraAdminInfoDTO> getBlogBackInfo() {
        return ResultVO.ok(auroraInfoService.getAuroraAdminInfo());
    }

    /**
     * 更新网站配置
     *
     * @param websiteConfigVO websiteConfigVO
     * @return ResultVO
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/admin/website/config")
    public ResultVO<?> updateWebsiteConfig(@Valid @RequestBody WebsiteConfigVO websiteConfigVO) {
        auroraInfoService.updateWebsiteConfig(websiteConfigVO);
        return ResultVO.ok();
    }

    /**
     * 获取网站配置
     *
     * @return ResultVO
     */
    @GetMapping("/admin/website/config")
    public ResultVO<WebsiteConfigDTO> getWebsiteConfig() {
        return ResultVO.ok(auroraInfoService.getWebsiteConfig());
    }

    /**
     * 查看关于我信息
     *
     * @return ResultVO
     */
    @GetMapping("/about")
    public ResultVO<AboutDTO> getAbout() {
        return ResultVO.ok(auroraInfoService.getAbout());
    }

    /**
     * 修改关于我信息
     *
     * @param aboutVO aboutVO
     * @return ResultVO
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/admin/about")
    public ResultVO<?> updateAbout(@Valid @RequestBody AboutVO aboutVO) {
        auroraInfoService.updateAbout(aboutVO);
        return ResultVO.ok();
    }

    /**
     * 上传博客配置图片
     *
     * @param file 图片
     * @return ResultVO
     */
    @OptLog(optType = UPLOAD)
    @PostMapping("/admin/config/images")
    public ResultVO<String> savePhotoAlbumCover(MultipartFile file) {
        return ResultVO.ok(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.CONFIG.getPath()));
    }

}
