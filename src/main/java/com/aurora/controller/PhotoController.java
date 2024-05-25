package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.enums.FilePathEnum;
import com.aurora.model.dto.PageResultDTO;
import com.aurora.model.dto.PhotoAdminDTO;
import com.aurora.model.dto.PhotoDTO;
import com.aurora.service.PhotoService;
import com.aurora.model.vo.*;
import com.aurora.strategy.context.UploadStrategyContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.aurora.constant.OptTypeConstant.*;

/**
 * 照片模块
 */
@RestController
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    /**
     * 上传照片
     *
     * @param file 照片
     * @return
     */
    @OptLog(optType = UPLOAD)
    @PostMapping("/admin/photos/upload")
    public ResultVO<String> savePhotoAlbumCover(MultipartFile file) {
        return ResultVO.ok(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.PHOTO.getPath()));
    }

    /**
     * 根据相册id获取照片列表
     *
     * @param conditionVO
     * @return
     */
    @GetMapping("/admin/photos")
    public ResultVO<PageResultDTO<PhotoAdminDTO>> listPhotos(ConditionVO conditionVO) {
        return ResultVO.ok(photoService.listPhotos(conditionVO));
    }

    /**
     * 更新照片信息
     *
     * @param photoInfoVO
     * @return
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/admin/photos")
    public ResultVO<?> updatePhoto(@Valid @RequestBody PhotoInfoVO photoInfoVO) {
        photoService.updatePhoto(photoInfoVO);
        return ResultVO.ok();
    }

    /**
     * 保存照片
     *
     * @param photoVO
     * @return
     */
    @OptLog(optType = SAVE)
    @PostMapping("/admin/photos")
    public ResultVO<?> savePhotos(@Valid @RequestBody PhotoVO photoVO) {
        photoService.savePhotos(photoVO);
        return ResultVO.ok();
    }

    /**
     * 移动照片相册
     *
     * @param photoVO
     * @return
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/admin/photos/album")
    public ResultVO<?> updatePhotosAlbum(@Valid @RequestBody PhotoVO photoVO) {
        photoService.updatePhotosAlbum(photoVO);
        return ResultVO.ok();
    }

    /**
     * 更新照片删除状态
     *
     * @param deleteVO
     * @return
     */
    @OptLog(optType = UPDATE)
    @PutMapping("/admin/photos/delete")
    public ResultVO<?> updatePhotoDelete(@Valid @RequestBody DeleteVO deleteVO) {
        photoService.updatePhotoDelete(deleteVO);
        return ResultVO.ok();
    }

    /**
     * 删除照片
     *
     * @param photoIds
     * @return
     */
    @OptLog(optType = DELETE)
    @DeleteMapping("/admin/photos")
    public ResultVO<?> deletePhotos(@RequestBody List<Integer> photoIds) {
        photoService.deletePhotos(photoIds);
        return ResultVO.ok();
    }

    /**
     * 根据相册id查看照片列表
     *
     * @param albumId
     * @return
     */
    @GetMapping("/albums/{albumId}/photos")
    public ResultVO<PhotoDTO> listPhotosByAlbumId(@PathVariable("albumId") Integer albumId) {
        return ResultVO.ok(photoService.listPhotosByAlbumId(albumId));
    }

}
