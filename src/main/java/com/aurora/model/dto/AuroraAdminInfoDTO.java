package com.aurora.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuroraAdminInfoDTO {

    private Long viewsCount;

    private Long messageCount;

    private Long userCount;

    private Long articleCount;

    private List<CategoryDTO> categoryDTOs;

    private List<TagDTO> tagDTOs;

    private List<ArticleStatisticsDTO> articleStatisticsDTOs;

    private List<UniqueViewDTO> uniqueViewDTOs;

    private List<ArticleRankDTO> articleRankDTOs;

}
