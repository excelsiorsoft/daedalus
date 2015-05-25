/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */
package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TKAllWatchListsResponse extends TKResponse {


    private String error;

    private String[] watchLists;

    public String getError() {
        return error;
    }

    public String[] getWatchLists() {
        return watchLists;
    }

    @JsonSetter("watchlists")
    @SuppressWarnings("unchecked")
    public void setWatchLists(Map<String, Object> watchListsResponse) {

        List<String> resultList = new ArrayList<>();

        Object list = watchListsResponse.get("watchlist");

        List<Map<String, String>> itemList = new ArrayList<>();

        if (list.getClass() == ArrayList.class) {
            //we know from condition this is right
            itemList = (ArrayList) list;
        } else {
            itemList.add((LinkedHashMap) watchListsResponse.get("watchlist"));
        }

        resultList.addAll(itemList.stream().map(item -> item.get("id")).collect(Collectors.toList()));

        this.watchLists = new String[resultList.size()];
        this.watchLists = resultList.toArray(this.watchLists);

    }
}
