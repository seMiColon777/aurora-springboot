package com.aurora.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoAlbumAdminDTO {

    private Long id;

    private String albumName;

    private String albumDesc;

    private String albumCover;

    private Long photoCount;

    private Long status;

}
