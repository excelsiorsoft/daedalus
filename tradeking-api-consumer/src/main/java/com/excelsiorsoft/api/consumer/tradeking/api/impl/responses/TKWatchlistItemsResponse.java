/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.watchlist.WatchlistItem;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class TKWatchlistItemsResponse extends TKResponse {

    private String error;

    private WatchlistItem[] watchListsItems;

    public String getError() {
        return error;
    }

    public WatchlistItem[] getWatchListItems() {
        return watchListsItems;
    }

    @JsonSetter("watchlists")
    @SuppressWarnings("unchecked")
    public void setWatchLists(Map<String, LinkedHashMap> watchListsResponse) {

        //manually deserialize as we need to un-nest some unnecessary stuff
        if (null == watchListsResponse.get("watchlist")) return;

        List<LinkedHashMap> items = new ArrayList<>();

        Object itemsContainer = watchListsResponse.get("watchlist").get("watchlistitem");

        //api does not wrap a single result
        if (itemsContainer.getClass() == ArrayList.class) {
            //we know it's ArrayList because of condition
            items = (ArrayList) itemsContainer;
        } else {
            items.add((LinkedHashMap) watchListsResponse.get("watchlist").get("watchlistitem"));
        }

        List<WatchlistItem> resultList = new ArrayList<>();

        for (Object item : items) {

            Map itemEntries = (LinkedHashMap) item;
            double costBasis = Double.parseDouble((String) itemEntries.get("costbasis"));
            double quantity = Double.parseDouble((String) itemEntries.get("qty"));
            Map instrument = (LinkedHashMap) itemEntries.get("instrument");
            String ticker = (String) instrument.get("sym");

            WatchlistItem itemObject = new WatchlistItem(costBasis, quantity, ticker);
            resultList.add(itemObject);

        }

        this.watchListsItems = new WatchlistItem[resultList.size()];
        this.watchListsItems = resultList.toArray(this.watchListsItems);

    }
}
