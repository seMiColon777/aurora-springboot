package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.model.dto.PhotoAlbumAdminDTO;
import com.aurora.model.dto.PhotoAlbumDTO;
import com.aurora.enums.FilePathEnum;
import com.aurora.model.vo.ResultVO;
import com.aurora.service.PhotoAlbumService;
import com.aurora.strategy.context.UploadStrategyContext;
import com.aurora.model.vo.ConditionVO;
import com.aurora.model.dto.PageResultDTO;
import com.aurora.model.vo.PhotoAlbumVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.*;

/**
 * 相册模块
 */
@RestController
public class PhotoAlbumController {

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private PhotoAlbumService photoAlbumService;


    /**
     * 上传相册封面
     *
     * @param file 相册封面
     * @return
     */
    @OptLog(optType = UPLOAD)
    @PostMapping("/admin/photos/albums/upload")
    public ResultVO<String> savePhotoAlbumCover(MultipartFile file) {
        return ResultVO.ok(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.PHOTO.getPath()));
    }

    /**
     * 保存或更新相册
     *
     * @param photoAlbumVO
     * @return
     */
    @OptLog(optType = SAVE_OR_UPDATE)
    @PostMapping("/admin/photos/albums")
    public ResultVO<?> saveOrUpdatePhotoAlbum(@Valid @RequestBody PhotoAlbumVO photoAlbumVO) {
        photoAlbumService.saveOrUpdatePhotoAlbum(photoAlbumVO);
        return ResultVO.ok();
    }

    /**
     * 查看后台相册列表
     *
     * @param conditionVO
     * @return
     */
    @GetMapping("/admin/photos/albums")
    public ResultVO<PageResultDTO<PhotoAlbumAdminDTO>> listPhotoAlbumBacks(ConditionVO conditionVO) {
        return ResultVO.ok(photoAlbumService.listPhotoAlbumsAdmin(conditionVO));
    }

    /**
     * 获取后台相册列表信息
     *
     * @return
     */
    @GetMapping("/admin/photos/albums/info")
    public ResultVO<List<PhotoAlbumDTO>> listPhotoAlbumBackInfos() {
        return ResultVO.ok(photoAlbumService.listPhotoAlbumInfosAdmin());
    }

    /**
     * 根据id获取后台相册信息
     *
     * @param albumId 相册id
     * @return
     */
    @GetMapping("/admin/photos/albums/{albumId}/info")
    public ResultVO<PhotoAlbumAdminDTO> getPhotoAlbumBackById(@PathVariable("albumId") Integer albumId) {
        return ResultVO.ok(photoAlbumService.getPhotoAlbumByIdAdmin(albumId));
    }

    /**
     * 根据id删除相册
     *
     * @param albumId 相册id
     * @return
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/photos/albums/{albumId}")
    public ResultVO<?> deletePhotoAlbumById(@PathVariable("albumId") Integer albumId) {
        photoAlbumService.deletePhotoAlbumById(albumId);
        return ResultVO.ok();
    }

    /**
     * 获取相册列表
     *
     * @return
     */
    @GetMapping("/photos/albums")
    public ResultVO<List<PhotoAlbumDTO>> listPhotoAlbums() {
        return ResultVO.ok(photoAlbumService.listPhotoAlbums());
    }

}
