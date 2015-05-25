/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;


import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.NewsHeadline;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Map;

public class TKNewsArticlesSearchResponse extends TKResponse {

    private String error;

    private NewsHeadline[] articles;

    @JsonSetter("articles")
    public void setArticles(Map articlesResponse) throws Exception {
        this.articles = (NewsHeadline[]) this.extractArray(NewsHeadline[].class, articlesResponse, "article");
    }


    public String getError() {
        return error;
    }

    public NewsHeadline[] getArticles() {
        return articles;
    }
}
