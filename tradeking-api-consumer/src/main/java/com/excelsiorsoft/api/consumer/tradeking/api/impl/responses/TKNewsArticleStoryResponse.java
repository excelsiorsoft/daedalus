/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.NewsStory;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TKNewsArticleStoryResponse extends TKResponse {

    private String error;

    @JsonProperty("article")
    private NewsStory article;

    public String getError() {
        return error;
    }

    public NewsStory getArticle() {
        return article;
    }

}
