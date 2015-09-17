package com.excelsiorsoft.gatherer.tradeking.connector.api;

import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.Accounts.ACCOUNTS;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.Accounts.ACCOUNTS_BALANCES;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.Accounts.ID;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.Accounts.ID_BALANCES;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.CLOCK;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.EXT_QUOTES;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.NEWS_ID;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.NEWS_SEARCH;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.OPTIONS_EXPIRATIONS;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.OPTIONS_SEARCH;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.OPTIONS_STRIKES;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.STREAM_EXT_QUOTES;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.TIMESALES;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.TOPLISTS_ACTIVE;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.TOPLISTS_GAINERS_ACTIVE_DOLLAR_AMT;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.TOPLISTS_GAINERS_DOLLAR_AMT;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.TOPLISTS_GAINERS_PERCENTAGE;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.TOPLISTS_LOSERS_DOLLAR;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.TOPLISTS_LOSERS_PERCENTAGE;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.MARKET.TOPLISTS_VOLUME;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.ORDER_TRADES.GET_ID_ORDERS;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.ORDER_TRADES.POST_ID_ORDERS;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.ORDER_TRADES.POST_ID_ORDERS_PREVIEW;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.UTILITY.STATUS;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.UTILITY.VERSION;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.WATCHLIST.DELETE_SYMBOL_WATCHLIST;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.WATCHLIST.DELETE_WATCHLISTS_ID;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.WATCHLIST.GET_WATCHLISTS;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.WATCHLIST.GET_WATCHLIST_ID;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.WATCHLIST.POST_SYMBOL_WATCHLIST_ID;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ApiCall.WATCHLIST.POST_WATCHLISTS;
import static org.scribe.model.Verb.DELETE;
import static org.scribe.model.Verb.GET;
import static org.scribe.model.Verb.POST;

import java.io.Serializable;

import org.scribe.model.Verb;



/**
 * A builder for different sorts of TradeKing API calls
 * 
 * @author sleyzerzon
 *
 */
public class ApiCall implements Serializable
{
	private static final long serialVersionUID = -3600236076708483532L;

	/**
	 * This call will return detailed balance and holding information for each account associated with a user.
	 * 
	 * @param format
	 * @return
	 */
	public static String getAccounts(ResponseFormat format)
	{
		return ACCOUNTS.resolveString("", format.toString());
	}

	/**
	 * This call will return summary balance information for each account associated with a user as well as the total value for all accounts associated with a user.
	 * 
	 * @param format
	 * @return
	 */
	public static String getAccountBalances(ResponseFormat format)
	{
		return ACCOUNTS_BALANCES.resolveString("", format.toString());
	}

	/**
	 * This call will return detailed balance and holding information for the account number specified in the URI.
	 * 
	 * @param format
	 * @param id
	 * @return
	 */
	public static String getAccountByID(ResponseFormat format, String id)
	{
		return ID.resolveString(id, format.toString());
	}

	/**
	 * This call will return detailed balance information for the account number specified in the URI.
	 * 
	 * @param format
	 * @param id
	 * @return
	 */
	public static String getAccountBalanceByID(ResponseFormat format, String id)
	{
		return ID_BALANCES.resolveString(id, ".", format.toString());
	}

	public enum Accounts
	{
		ACCOUNTS("https://api.tradeking.com/v1/accounts", "."), 
		ACCOUNTS_BALANCES("https://api.tradeking.com/v1/accounts/balances", "."), 
		ID("https://api.tradeking.com/v1/accounts/", "."), 
		ID_BALANCES("https://api.tradeking.com/v1/accounts/", "/balances", "", "",""),
		ID_HISTORY("https://api.tradeking.com/v1/accounts/","/history", "."), 
		ID_HOLDINGS("https://api.tradeking.com/v1/accounts/", "/holdings", ".");

		private String[] urlStrings;

		Accounts(String... urlStrings)
		{
			this.urlStrings = urlStrings;
		}

		public String resolveString(String... params)
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < urlStrings.length; i++)
			{
				sb.append(urlStrings[i]);
				if (params.length > i)
				{
					sb.append(params[i]);
				}
			}
			return sb.toString();
		}

		public Verb getVerb()
		{
			return Verb.GET;
		}
	}

	/**
	 * This call will return the most recent orders for the account specified in the URI.
	 * 
	 * @param format
	 * @param id
	 * @return
	 */
	public static String getOrderByAccountID(ResponseFormat format, String id)
	{
		return GET_ID_ORDERS.resolveString(id, "", format.toString());
	}

	/**
	 * This call will allow you to place an order. This requires the order data is submitted in FIXML format submitted as XML within the body.
	 * 
	 * @param format
	 * @param id
	 * @return
	 */
	public static String postOrderByAccountID(ResponseFormat format, String id)
	{
		return POST_ID_ORDERS.resolveString(id, "", format.toString());
	}

	/**
	 * This call will allow you to preview an order prior to actually placing it. This does not place the order.
	 * 
	 * @param format
	 * @param id
	 * @return
	 */
	public static String postOrderByAccountIDPreview(ResponseFormat format, String id)
	{
		return POST_ID_ORDERS_PREVIEW.resolveString(id, "", format.toString());
	}

	public enum ORDER_TRADES
	{
		GET_ID_ORDERS(GET, "https://api.tradeking.com/v1/accounts/", "/orders", "."), 
		POST_ID_ORDERS(POST, "https://api.tradeking.com/v1/accounts/", "/orders", "."), 
		POST_ID_ORDERS_PREVIEW(POST, "https://api.tradeking.com/v1/accounts/", "/orders/preview", ".");

		private Verb v;
		private String[] urlStrings;

		ORDER_TRADES(Verb v, String... urlStrings)
		{
			this.v = v;
			this.urlStrings = urlStrings;
		}

		public String resolveString(String... params)
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < urlStrings.length; i++)
			{
				sb.append(urlStrings[i]);
				if (params.length > i)
				{
					sb.append(params[i]);
				}
			}
			return sb.toString();
		}

		public Verb getVerb()
		{
			return v;
		}

	}

	/**
	 * This call will return the current state of the market, the time of the next state change (if the market is open), and the current server timestamp.
	 */
	public static String getMarketClock(ResponseFormat format)
	{
		return CLOCK.resolveString("", format.toString());
	}

	/**
	 * This call will return quotes for a symbol or list of symbols passed as a query parameter (see query parameters below). While this request type is GET, POST can also be used and is recommended
	 * for larger lists of symbols.
	 */
	public static String getQuote(ResponseFormat format)
	{
		return EXT_QUOTES.resolveString("", format.toString());
	}

	public static String getStreamingQuote(ResponseFormat format)
	{
		return STREAM_EXT_QUOTES.resolveString("", format.toString());
	}

	/**
	 * This call will return a listing of news headlines based on the current keyword and/or symbol search.
	 * 
	 * @param format
	 * @return
	 */
	public static String searchNews(ResponseFormat format)
	{
		return NEWS_SEARCH.resolveString("", format.toString());
	}

	/**
	 * This call will return an article identified by the URI id.
	 */
	public static String getNews(ResponseFormat format, String newsId)
	{
		return NEWS_ID.resolveString(newsId, "", format.toString());
	}

	/**
	 * This call will return the full list of available option strikes for a given symbol. While this request type is GET, POST can also be used and is recommended for longer queries.
	 * 
	 * @param format
	 * @return
	 */
	public static String searchOptions(ResponseFormat format)
	{
		return OPTIONS_SEARCH.resolveString("", format.toString());
	}

	/**
	 * This call will return the full list of available option strikes for a given symbol.
	 */
	public static String getOptionStrikes(ResponseFormat format)
	{
		return OPTIONS_STRIKES.resolveString("", format.toString());
	}

	/**
	 * This call will return the full list of available option expiration dates for a given symbol.
	 * 
	 * @param format
	 * @return
	 */
	public static String getOptionExpirations(ResponseFormat format)
	{
		return OPTIONS_EXPIRATIONS.resolveString("", format.toString());
	}

	/**
	 * This call will return time and sales quote data based on a symbol passed as a query parameter (see query parameters below).
	 */
	public static String getTimeSales(ResponseFormat format)
	{
		return TIMESALES.resolveString("", format.toString());
	}

	public static String getTopList(TopList list, ResponseFormat format)
	{
		return list.getLink().resolveString("", format.toString());
	}

	public enum TopList
	{
		LOSERS_DOLLAR(TOPLISTS_LOSERS_DOLLAR), 
		LOSERS_PERCENTAGE(TOPLISTS_LOSERS_PERCENTAGE), 
		VOLUME(TOPLISTS_VOLUME), 
		ACTIVE(TOPLISTS_ACTIVE), 
		GAINERS_DOLLAR(TOPLISTS_GAINERS_DOLLAR_AMT), 
		GAINERS_PERCENTAGE(TOPLISTS_GAINERS_PERCENTAGE), 
		GAINERS_ACTIVE(TOPLISTS_GAINERS_ACTIVE_DOLLAR_AMT);
		
		private MARKET link;

		TopList(MARKET link)
		{
			this.link = link;
		}

		public MARKET getLink()
		{
			return link;
		}
	}

	public enum MARKET
	{
		CLOCK("https://api.tradeking.com/v1/market/clock", "."), 
		EXT_QUOTES("https://api.tradeking.com/v1/market/ext/quotes", "."), 
		STREAM_EXT_QUOTES("https://stream.tradeking.com/v1/market/quotes", "."), 
		NEWS_SEARCH("https://api.tradeking.com/v1/market/news/search", "."), 
		NEWS_ID("https://api.tradeking.com/v1/market/news/", "", "."), 
		OPTIONS_SEARCH("https://api.tradeking.com/v1/market/options/search", "."), 
		OPTIONS_STRIKES("https://api.tradeking.com/v1/market/options/strikes", "."), 
		OPTIONS_EXPIRATIONS("https://api.tradeking.com/v1/market/options/expirations", "."), 
		TIMESALES("https://api.tradeking.com/v1/market/timesales", "."), 
		TOPLISTS_VOLUME("https://api.tradeking.com/v1/market/toplists/topvolume", "."), 
		TOPLISTS_LOSERS_DOLLAR("https://api.tradeking.com/v1/market/toplists/toplosers", "."), 
		TOPLISTS_LOSERS_PERCENTAGE("https://api.tradeking.com/v1/market/toplists/toppctlosers", "."), 
		TOPLISTS_ACTIVE("https://api.tradeking.com/v1/market/toplists/topactive", "."), 
		TOPLISTS_GAINERS_DOLLAR_AMT("https://api.tradeking.com/v1/market/toplists/topgainers", "."), 
		TOPLISTS_GAINERS_PERCENTAGE("https://api.tradeking.com/v1/market/toplists/toppctgainers", "."), 
		TOPLISTS_GAINERS_ACTIVE_DOLLAR_AMT("https://api.tradeking.com/v1/market/toplists/topactivegainersbydollarvalue", ".");

		private String[] urlStrings;

		MARKET(String... urlStrings)
		{
			this.urlStrings = urlStrings;
		}

		public String resolveString(String... params)
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < urlStrings.length; i++)
			{
				sb.append(urlStrings[i]);
				if (params.length > i)
				{
					sb.append(params[i]);
				}
			}
			return sb.toString();
		}

		public Verb getVerb()
		{
			return Verb.GET;
		}
	}

	/**
	 * This call will return general information associated with the user. More importantly it will also return all of the account numbers and account information for the user.
	 * 
	 * @param format
	 * @return
	 */
	public static String getMemberProfile(ResponseFormat format)
	{
		return MEMBER.PROFILE.resolveString("", format.toString());
	}

	public enum MEMBER
	{
		PROFILE("https://api.tradeking.com/v1/member/profile", ".");

		private String[] urlStrings;

		MEMBER(String... urlStrings)
		{
			this.urlStrings = urlStrings;
		}

		public String resolveString(String... params)
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < urlStrings.length; i++)
			{
				sb.append(urlStrings[i]);
				if (params.length > i)
				{
					sb.append(params[i]);
				}
			}
			return sb.toString();
		}

		public Verb getVerb()
		{
			return GET;
		}
	}

	/**
	 * This call will return the current server timestamp if the API and its backend systems are accessible. Otherwise it will return an error.
	 * 
	 * @param format
	 * @return
	 */
	public static String getTKStatus(ResponseFormat format)
	{
		return STATUS.resolveString("", format.toString());
	}

	/**
	 * 
	 This call will return the version of the API of the endpoint called.
	 * 
	 * @param format
	 * @return
	 */
	public static String getTKVersion(ResponseFormat format)
	{
		return VERSION.resolveString("", format.toString());
	}

	public enum UTILITY
	{
		STATUS("https://api.tradeking.com/v1/utility/status", "."), 
		VERSION("https://api.tradeking.com/v1/utility/version", ".");

		private String[] urlStrings;

		UTILITY(String... urlStrings)
		{
			this.urlStrings = urlStrings;
		}

		public String resolveString(String... params)
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < urlStrings.length; i++)
			{
				sb.append(urlStrings[i]);
				if (params.length > i)
				{
					sb.append(params[i]);
				}
			}
			return sb.toString();
		}

		public Verb getVerb()
		{
			return GET;
		}
	}

	public static String getWatchlists(ResponseFormat format)
	{
		return GET_WATCHLISTS.resolveString("", format.toString());
	}

	public static String postWatchlists(ResponseFormat format)
	{
		return POST_WATCHLISTS.resolveString("", format.toString());
	}

	public static String getWatchlistsById(String id, ResponseFormat format)
	{
		return GET_WATCHLIST_ID.resolveString(id, "", format.toString());
	}

	public static String deleteWatchlistsById(String id, ResponseFormat format)
	{
		return DELETE_WATCHLISTS_ID.resolveString(id, "", format.toString());
	}

	public static String postWatchlistsBySymbol(String watchList, ResponseFormat format)
	{
		return POST_SYMBOL_WATCHLIST_ID.resolveString(watchList, "", format.toString());
	}

	public static String deleteSymbolFromWatchList(String watchList, String symbol, ResponseFormat format)
	{
		return DELETE_SYMBOL_WATCHLIST.resolveString(watchList, symbol, "", format.toString());
	}

	public enum WATCHLIST
	{
		GET_WATCHLISTS(GET, "https://api.tradeking.com/v1/watchlists", "."), 
		POST_WATCHLISTS(POST, "https://api.tradeking.com/v1/watchlists", "."), 
		GET_WATCHLIST_ID(GET, "https://api.tradeking.com/v1/watchlists/", "", "."), 
		DELETE_WATCHLISTS_ID(DELETE, "https://api.tradeking.com/v1/watchlists/", "", "."), 
		POST_SYMBOL_WATCHLIST_ID(POST, "https://api.tradeking.com/v1/watchlist/", "/symbols", "."), 
		DELETE_SYMBOL_WATCHLIST(DELETE, "https://api.tradeking.com/v1/watchlists/", "/", ".", "");

		private String[] urlStrings;
		private Verb verb;

		WATCHLIST(Verb verb, String... urlStrings)
		{
			this.urlStrings = urlStrings;
			this.verb = verb;
		}

		public String resolveString(String... params)
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < urlStrings.length; i++)
			{
				sb.append(urlStrings[i]);
				if (params.length > i)
				{
					sb.append(params[i]);
				}
			}
			return sb.toString();
		}

		public Verb getVerb()
		{
			return verb;
		}

	}

}
