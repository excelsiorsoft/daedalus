package com.excelsiorsoft.api.consumer.tradeking.api.impl;


import com.excelsiorsoft.api.consumer.tradeking.api.domain.watchlist.WatchlistItem;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.BaseTemplate;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.ApiException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class WatchlistTemplateTest extends BaseTemplateTest {

    @Test
    public void getAllLists() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "watchlists.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("watchlist/all_lists"), MediaType.APPLICATION_JSON));
        String[] watchlistNames = tradeKing.getWatchlistOperations().getAllLists();

        assertArrayEquals("Watch Lists names do not match", watchlistNames, mockData.watchLists);
    }

    @Test
    public void getAllLists_single_response() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "watchlists.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("watchlist/all_lists_single"), MediaType.APPLICATION_JSON));
        String[] watchlistNames = tradeKing.getWatchlistOperations().getAllLists();

        assertArrayEquals("Watch Lists names do not match", watchlistNames, mockData.watchListsSingle);
    }

    @Test(expected = ApiException.class)
    public void getAllLists_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "watchlists.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));
        tradeKing.getWatchlistOperations().getAllLists();
        mockServer.verify();

    }

    @Test
    public void addList() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "watchlists.json"))
                .andExpect(method(POST))
                .andExpect(content().string("id=some+Name&symbols=TCK1%2CTCK2"))
                .andRespond(withSuccess(jsonResource("watchlist/all_lists"), MediaType.APPLICATION_JSON));

        String[] watchlistNames = tradeKing.getWatchlistOperations().addList("some Name", new String[]{"TCK1", "TCK2"});
        mockServer.verify();

        assertArrayEquals("Watch Lists names do not match", watchlistNames, mockData.watchLists);
    }

    @Test(expected = ApiException.class)
    public void addList_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "watchlists.json"))
                .andExpect(method(POST))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        tradeKing.getWatchlistOperations().addList("some Name", new String[]{"TCK1", "TCK2"});
        mockServer.verify();
    }

    @Test
    public void deleteListById() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "watchlists/DEFAULT.json"))
                .andExpect(method(DELETE))
                .andRespond(withSuccess(jsonResource("watchlist/all_lists"), MediaType.APPLICATION_JSON));

        tradeKing.getWatchlistOperations().deleteListById("DEFAULT");
        mockServer.verify();
    }

    @Test
    public void addSymbolsToList() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "watchlists/DEFAULT/symbols.json"))
                .andExpect(method(POST))
                .andExpect(content().string("symbols=TCKR1%2CTCKR2"))
                .andRespond(withSuccess(jsonResource("watchlist/all_lists"), MediaType.APPLICATION_JSON));

        String[] watchlistNames = tradeKing.getWatchlistOperations().addSymbolsToList("DEFAULT", new String[]{"TCKR1", "TCKR2"});
        mockServer.verify();

        assertArrayEquals("Watch Lists names do not match", watchlistNames, mockData.watchLists);
    }

    @Test(expected = ApiException.class)
    public void addSymbolsToList_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "watchlists/DEFAULT/symbols.json"))
                .andExpect(method(POST))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        tradeKing.getWatchlistOperations().addSymbolsToList("DEFAULT", new String[]{"TCKR1", "TCKR2"});
        mockServer.verify();
    }

    @Test
    public void deleteSymbolFromList() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "watchlists/DEFAULT/symbols/TCKR1,TCKR2.json"))
                .andExpect(method(DELETE))
                .andRespond(withSuccess(jsonResource("watchlist/all_lists"), MediaType.APPLICATION_JSON));

        tradeKing.getWatchlistOperations().deleteSymbolFromList("DEFAULT", new String[]{"TCKR1", "TCKR2"});
        mockServer.verify();
    }

    @Test
    public void getItems() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "watchlists/DEFAULT.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("watchlist/list_contents"), MediaType.APPLICATION_JSON));

        WatchlistItem[] items = tradeKing.getWatchlistOperations().getItems("DEFAULT");
        mockServer.verify();

        assertEquals("First Item Object does not match", items[0], mockData.watchlistItem1);
        assertEquals("Second Item Object does not match", items[1], mockData.watchlistItem2);

    }

    @Test(expected = ApiException.class)
    public void getItems_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "watchlists/DEFAULT.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        tradeKing.getWatchlistOperations().getItems("DEFAULT");
        mockServer.verify();

    }

}
