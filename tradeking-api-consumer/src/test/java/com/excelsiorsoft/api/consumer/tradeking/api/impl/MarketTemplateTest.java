package com.excelsiorsoft.api.consumer.tradeking.api.impl;


import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.*;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.BaseTemplate;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.OptionQuoteNotFoundException;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.TimeSalesInterval;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.TopListType;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.ApiException;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class MarketTemplateTest extends BaseTemplateTest {

    @Test
    public void getMarketStatus() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/clock.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/market_status"), MediaType.APPLICATION_JSON));

        MarketStatus status = tradeKing.getMarketOperations().getMarketStatus();
        mockServer.verify();

        assertEquals("MarketStatus Dates are not equal", status.getDate(), mockData.marketStatus.getDate());
        assertEquals("MarketStatus objects are not equal", status, mockData.marketStatus);
    }

    @Test(expected = ApiException.class)
    public void getMarketStatus_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/clock.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        tradeKing.getMarketOperations().getMarketStatus();
        mockServer.verify();
    }

    @Test
    public void getDataPoints_Tick() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/timesales.json?symbols=TCKR&interval=tick&rpp=20&index=21"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/time_sales_quotes"), MediaType.APPLICATION_JSON));

        TimeSalesQuote[] points = tradeKing.getMarketOperations().getDataPoints("TCKR", 2, 20);
        mockServer.verify();

        assertEquals("First Quote Interval Start Time is Not Equal to mock's", points[0].getIntervalStartTime(), mockData.timeSalesQuote1.getIntervalStartTime());
        assertEquals("First Quote is Not Equal to mock", points[0], mockData.timeSalesQuote1);
        assertEquals("Second Quote is Not Equal to mock", points[1], mockData.timeSalesQuote2);
    }

    @Test
    public void getDataPoints_MultiDay() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/timesales.json?symbols=TCKR&interval=5min&startdate=2014-12-15&enddate=2014-12-20"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/time_sales_quotes"), MediaType.APPLICATION_JSON));
        TimeSalesQuote[] points = tradeKing.getMarketOperations().getDataPoints("TCKR", new LocalDate(2014, 12, 15), new LocalDate(2014, 12, 20), TimeSalesInterval.MINUTES_5);

        mockServer.verify();
        assertEquals("First Quote Interval Start Time is Not Equal to mock's", points[0].getIntervalStartTime(), mockData.timeSalesQuote1.getIntervalStartTime());
        assertEquals("First Quote is Not Equal to mock", points[0], mockData.timeSalesQuote1);
        assertEquals("Second Quote is Not Equal to mock", points[1], mockData.timeSalesQuote2);
    }

    @Test
    public void getDataPoints_SinglePoint() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/timesales.json?symbols=TCKR&interval=tick&rpp=20&index=21"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/time_sales_quote_single"), MediaType.APPLICATION_JSON));

        TimeSalesQuote[] points = tradeKing.getMarketOperations().getDataPoints("TCKR", 2, 20);
        mockServer.verify();

        assertEquals("First Quote Interval Start Time is Not Equal to mock's", points[0].getIntervalStartTime(), mockData.timeSalesQuote1.getIntervalStartTime());
        assertEquals("First Quote is Not Equal to mock", points[0], mockData.timeSalesQuote1);
    }

    @Test(expected = ApiException.class)
    public void getDataPoints_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/timesales.json?symbols=TCKR&interval=tick&rpp=20&index=21"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        tradeKing.getMarketOperations().getDataPoints("TCKR", 2, 20);
        mockServer.verify();
    }

    @Test
    public void getTopList() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/toplists/topactive.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/toplist"), MediaType.APPLICATION_JSON));

        TopListEntry[] list = tradeKing.getMarketOperations().getTopList(TopListType.ACTIVE);
        mockServer.verify();

        assertEquals("First top list entry does not match.", list[0], mockData.topListEntry1);
        assertEquals("Second top list entry does not match.", list[1], mockData.topListEntry2);
        assertArrayEquals("Toplist result does not match", list, mockData.topList);
    }

    @Test(expected = ApiException.class)
    public void getTopList_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/toplists/topactive.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        tradeKing.getMarketOperations().getTopList(TopListType.ACTIVE);
        mockServer.verify();
    }

    @Test
    public void getNewsById() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/news/4cacd265647adb9a46977d5d2f8ff559.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/news_article"), MediaType.APPLICATION_JSON));

        NewsStory newsStory = tradeKing.getMarketOperations().getNewsById("4cacd265647adb9a46977d5d2f8ff559");
        mockServer.verify();

        assertEquals("Dates do not match", newsStory.getDate(), mockData.newsStory.getDate());
        assertEquals("News articles do not match", newsStory, mockData.newsStory);
    }

    @Test(expected = ApiException.class)
    public void getNewsById_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/news/4cacd265647adb9a46977d5d2f8ff559.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        tradeKing.getMarketOperations().getNewsById("4cacd265647adb9a46977d5d2f8ff559");
        mockServer.verify();
    }

    @Test
    public void getNewsList_keywords() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/news/search.json?maxhits=2&keywords=oil%2Cmetals"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/news_list"), MediaType.APPLICATION_JSON));

        NewsHeadline[] newsLists = tradeKing.getMarketOperations().getNewsList(new String[]{"oil", "metals"}, 2);
        mockServer.verify();

        assertEquals("First News Headline does not match", newsLists[0], mockData.newsHeadline1);
        assertEquals("Second News Headline does not match", newsLists[1], mockData.newsHeadline2);
        assertArrayEquals("Headlines do not match", newsLists, mockData.newsList);
    }


    @Test
    public void getNewsList_ticker() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/news/search.json?symbols=CORP1&maxhits=1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/news_list_single"), MediaType.APPLICATION_JSON));

        NewsHeadline[] newsLists = tradeKing.getMarketOperations().getNewsList("CORP1", 1);
        mockServer.verify();

        assertEquals("First News Headline does not match", newsLists[0], mockData.newsHeadline1);
    }

    @Test(expected = ApiException.class)
    public void getNewsList_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/news/search.json?symbols=CORP1&maxhits=1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        tradeKing.getMarketOperations().getNewsList("CORP1", 1);
        mockServer.verify();
    }

    @Test
    public void getOptionExpirationDates() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/options/expirations.json?symbol=CORP1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/option_expiration_dates"), MediaType.APPLICATION_JSON));

        LocalDate[] expirationDates = tradeKing.getMarketOperations().getOptionExpirationDates("CORP1");
        mockServer.verify();

        assertArrayEquals("Dates do not match", expirationDates, mockData.optionExpirationDates);

    }

    @Test
    public void getOptionExpirationDates_single() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/options/expirations.json?symbol=CORP1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/option_expiration_date_single"), MediaType.APPLICATION_JSON));

        LocalDate[] expirationDates = tradeKing.getMarketOperations().getOptionExpirationDates("CORP1");
        mockServer.verify();

        assertArrayEquals("Dates do not match", expirationDates, new LocalDate[]{mockData.optionExpiration1});
    }

    @Test(expected = ApiException.class)
    public void getOptionExpirationDates_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/options/expirations.json?symbol=CORP1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        tradeKing.getMarketOperations().getOptionExpirationDates("CORP1");
        mockServer.verify();
    }


    @Test
    public void getOptionStrikePrices() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/options/strikes.json?symbol=CORP1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/option_strike_prices"), MediaType.APPLICATION_JSON));

        Double[] strikePrices = tradeKing.getMarketOperations().getStrikePrices("CORP1");
        mockServer.verify();

        assertArrayEquals("Dates do not match", strikePrices, mockData.optionStrikePrices);

    }

    @Test
    public void getOptionStrikePrices_single() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/options/strikes.json?symbol=CORP1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/option_strike_price_single"), MediaType.APPLICATION_JSON));

        Double[] strikePrices = tradeKing.getMarketOperations().getStrikePrices("CORP1");
        mockServer.verify();

        assertArrayEquals("Dates do not match", strikePrices, new Double[]{mockData.optionStrikePrice1});
    }

    @Test(expected = ApiException.class)
    public void getOptionStrikePrices_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/options/strikes.json?symbol=CORP1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        tradeKing.getMarketOperations().getStrikePrices("CORP1");
        mockServer.verify();
    }

    @Test
    public void getQuoteForStocks() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/ext/quotes.json?symbols=CORP1%2CCORP2"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/stock_quotes"), MediaType.APPLICATION_JSON));

        StockQuote[] quotes = tradeKing.getMarketOperations().getQuoteForStocks(new String[]{"CORP1", "CORP2"});
        mockServer.verify();

        assertArrayEquals("The quotes do not match", quotes, mockData.stockQuotes);

    }

    @Test(expected = ApiException.class)
    public void getQuoteForStocks_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/ext/quotes.json?symbols=CORP1%2CCORP2"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        tradeKing.getMarketOperations().getQuoteForStocks(new String[]{"CORP1", "CORP2"});
        mockServer.verify();
    }

    @Test
    public void getQuoteForStocks_single() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/ext/quotes.json?symbols=CORP1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/stock_quote_single"), MediaType.APPLICATION_JSON));

        StockQuote quote = tradeKing.getMarketOperations().getQuoteForStock("CORP1");
        mockServer.verify();

        assertEquals("Quotes do not match", quote, this.mockData.stockQuote1);

    }

    @Test
    public void getQuoteForOptions_single() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/ext/quotes.json?symbols=CMPN1150320C00100000"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/option_quote_single"), MediaType.APPLICATION_JSON));
        try {
            OptionQuote optionQuote = tradeKing.getMarketOperations()
                    .getQuoteForOption("CMPN1", new LocalDate(2015, 3, 20), OptionQuote.OptionType.CALL, 100);
            mockServer.verify();
            assertEquals("Option quotes do not match", optionQuote, this.mockData.optionQuote1);

        } catch (OptionQuoteNotFoundException e) {
            assertFalse("Option not found exception thrown of existing option", false);
        }
    }

    @Test(expected = OptionQuoteNotFoundException.class)
    public void getQuoteForOptions_errorResponse() throws OptionQuoteNotFoundException {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/ext/quotes.json?symbols=CMPN1150320C00100000"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));
        tradeKing.getMarketOperations()
                .getQuoteForOption("CMPN1", new LocalDate(2015, 3, 20), OptionQuote.OptionType.CALL, 100);
        mockServer.verify();
    }

    @Test
    public void searchOptions_no_dates() {

        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/options/search.json?symbol=CMPN1&query=put_call-eq%3Acall+AND+strikeprice-gte%3A90.0+AND+strikeprice-lte%3A100.0"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("market/option_quotes_multiple"), MediaType.APPLICATION_JSON));

        OptionQuote[] quotes = this.tradeKing.getMarketOperations().searchOptions("CMPN1", 90.00, 100.00, OptionQuote.OptionType.CALL, null, null);
        mockServer.verify();

        assertArrayEquals("Option Qutotes are not equal", quotes, this.mockData.searchOptionQuotes);

    }

    @Test(expected = ApiException.class)
    public void searchOptions_no_dates_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "market/options/search.json?symbol=CMPN1&query=put_call-eq%3Acall+AND+strikeprice-gte%3A90.0+AND+strikeprice-lte%3A100.0+AND+xdate-gte%3A20150320+AND+xdate-lte%3A20150325"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        this.tradeKing.getMarketOperations().searchOptions("CMPN1", 90.00, 100.00, OptionQuote.OptionType.CALL, new LocalDate(2015, 3, 20), new LocalDate(2015, 3, 25));
        mockServer.verify();
    }
}
