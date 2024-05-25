package com.aurora.repo;

import com.aurora.model.dto.ArticleSearchDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @author 花未眠
 * elasticsearch
 */
//@Repository
public interface ElasticsearchMapper extends ElasticsearchRepository<ArticleSearchDTO,Integer> {

}
