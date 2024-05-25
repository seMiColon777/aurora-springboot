package com.aurora.strategy.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.aurora.model.dto.ArticleSearchDTO;
import com.aurora.strategy.SearchStrategy;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.highlight.Highlight;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightParameters;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.aurora.constant.CommonConstant.*;
import static com.aurora.enums.ArticleStatusEnum.PUBLIC;

@Log4j2
@Service("esSearchStrategyImpl")
public class EsSearchStrategyImpl implements SearchStrategy {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public List<ArticleSearchDTO> searchArticle(String keywords) {
        if (StringUtils.isBlank(keywords)) {
            return new ArrayList<>();
        }
        return search(buildQuery(keywords));
    }

    private NativeQueryBuilder buildQuery(String keywords) {
        NativeQueryBuilder nativeQueryBuilder = new NativeQueryBuilder();
        Query articleTitle = QueryBuilders.match().field("articleTitle").query(keywords).build()._toQuery();
        Query articleContent = QueryBuilders.match().field("articleContent").query(keywords).build()._toQuery();
        Query isDelete = QueryBuilders.term().field("isDelete").value(FALSE).build()._toQuery();
        Query status = QueryBuilders.term().field("status").value(PUBLIC.getStatus()).build()._toQuery();
        Query boolQuery = QueryBuilders.bool().must(Lists.newArrayList(articleTitle, articleContent, isDelete, status)).build()._toQuery();
        return nativeQueryBuilder.withQuery(boolQuery);
    }

    private List<ArticleSearchDTO> search(NativeQueryBuilder nativeQueryBuilder) {
        HighlightField articleTitle = new HighlightField("articleTitle");
        HighlightParameters highlightParameters = HighlightParameters.builder().withPreTags(PRE_TAG).withPostTags(POST_TAG).build();
        HighlightField articleContent = new HighlightField("articleContent");
        Highlight highlight = new Highlight(highlightParameters, Lists.newArrayList(articleTitle, articleContent));
        nativeQueryBuilder.withHighlightQuery(new HighlightQuery(highlight, ArticleSearchDTO.class));
        try {
            SearchHits<ArticleSearchDTO> search = elasticsearchTemplate.search(nativeQueryBuilder.build(), ArticleSearchDTO.class);
            return search.getSearchHits().stream().map(hit -> {
                ArticleSearchDTO article = hit.getContent();
                List<String> titleHighLightList = hit.getHighlightFields().get("articleTitle");
                if (CollectionUtils.isNotEmpty(titleHighLightList)) {
                    article.setArticleTitle(titleHighLightList.get(0));
                }
                List<String> contentHighLightList = hit.getHighlightFields().get("articleContent");
                if (CollectionUtils.isNotEmpty(contentHighLightList)) {
                    article.setArticleContent(contentHighLightList.get(contentHighLightList.size() - 1));
                }
                return article;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ArrayList<>();
    }

}

