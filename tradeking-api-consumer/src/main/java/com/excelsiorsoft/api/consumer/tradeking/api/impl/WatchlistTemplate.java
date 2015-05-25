/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl;

import com.excelsiorsoft.api.consumer.tradeking.api.WatchlistOperations;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.watchlist.WatchlistItem;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.responses.TKAllWatchListsResponse;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.responses.TKWatchlistItemsResponse;
import com.excelsiorsoft.api.consumer.tradeking.connect.TradeKingServiceProvider;

import org.springframework.http.ResponseEntity;
import org.springframework.social.ApiException;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


public class WatchlistTemplate extends BaseTemplate implements WatchlistOperations {

    private static final String URL_WATCHLIST_LIST = "watchlists.json";
    private static final String URL_WATCHLIST_LIST_DELETE = "watchlists/%s.json";
    private static final String URL_WATCHLIST_LIST_EDIT = "watchlists/%s/symbols.json";
    private static final String URL_WATCHLIST_LIST_DELETE_TICKERS = "watchlists/%s/symbols/%s.json";
    private static final String URL_WATCHLIST_GET_ITEMS = "watchlists/%s.json";

    WatchlistTemplate(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public String[] getAllLists() {

        URI url = this.buildUri(URL_WATCHLIST_LIST);
        ResponseEntity<TKAllWatchListsResponse> response = this.getRestTemplate().getForEntity(url, TKAllWatchListsResponse.class);

        if (response.getBody().getError() != null)
            throw new ApiException(TradeKingServiceProvider.PROVIDER_ID, response.getBody().getError());

        return response.getBody().getWatchLists();

    }

    @Override
    public String[] addList(String watchlistName, String[] tickers) {

        Assert.notNull(tickers);

        URI url = this.buildUri(URL_WATCHLIST_LIST);
        MultiValueMap<String, Object> requestObject = new LinkedMultiValueMap<>();
        requestObject.add("id", watchlistName);
        requestObject.add("symbols", this.buildCommaSeparatedParameterValue(tickers));

        ResponseEntity<TKAllWatchListsResponse> response = this.getRestTemplate().postForEntity(url, requestObject, TKAllWatchListsResponse.class);

        if (response.getBody().getError() != null)
            throw new ApiException(TradeKingServiceProvider.PROVIDER_ID, response.getBody().getError());

        return response.getBody().getWatchLists();
    }

    @Override
    public void deleteListById(String watchlistName) {
        URI url = this.buildUri(String.format(URL_WATCHLIST_LIST_DELETE, watchlistName));
        this.getRestTemplate().delete(url);
    }

    @Override
    public String[] addSymbolsToList(String watchlistName, String[] tickers) {
        URI url = this.buildUri(String.format(URL_WATCHLIST_LIST_EDIT, watchlistName));
        MultiValueMap<String, Object> requestObject = new LinkedMultiValueMap<>();
        requestObject.add("symbols", this.buildCommaSeparatedParameterValue(tickers));

        ResponseEntity<TKAllWatchListsResponse> response = this.getRestTemplate().postForEntity(url, requestObject, TKAllWatchListsResponse.class);

        if (response.getBody().getError() != null)
            throw new ApiException(TradeKingServiceProvider.PROVIDER_ID, response.getBody().getError());

        return response.getBody().getWatchLists();
    }

    @Override
    public void deleteSymbolFromList(String watchlistName, String[] tickers) {
        String tickersString = this.buildCommaSeparatedParameterValue(tickers);
        URI url = this.buildUri(String.format(URL_WATCHLIST_LIST_DELETE_TICKERS, watchlistName, tickersString));
        this.getRestTemplate().delete(url);
    }

    @Override
    public WatchlistItem[] getItems(String watchlistName) {

        URI url = this.buildUri(String.format(URL_WATCHLIST_GET_ITEMS, watchlistName));

        ResponseEntity<TKWatchlistItemsResponse> response = this.getRestTemplate().getForEntity(url, TKWatchlistItemsResponse.class);

        if (response.getBody().getError() != null && !"Success".equals(response.getBody().getError()))
            throw new ApiException(TradeKingServiceProvider.PROVIDER_ID, response.getBody().getError());

        return response.getBody().getWatchListItems();

    }

}
