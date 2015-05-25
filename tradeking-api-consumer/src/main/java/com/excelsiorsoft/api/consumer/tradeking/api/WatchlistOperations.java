/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.watchlist.WatchlistItem;

/**
 * Sub api to get, create, edit and delete watch lists
 */
public interface WatchlistOperations {

    /**
     * This will retrieve a list of Watch List for the authenticated user.
     *
     * @return a list of Strings that represent watchlist names
     */
    String[] getAllLists();

    /**
     * This will create a watchlist with the specified id (name) for the authenticated user.
     *
     * @param watchlistName name (also in api docs referenced as id) of a watchlist to create
     * @param tickers       symbols of the stock to add to watchlist. At least one ticker must be provided.
     * @return new list of watchlist names, including the new one if it was added successfully
     */
    String[] addList(String watchlistName, String[] tickers);

    /**
     * This will delete the watchlist with the name provided
     *
     * @param watchlistName name of the watchlist to delete
     */
    void deleteListById(String watchlistName);

    /**
     * This will add the symbols in the form parameters to the watchlist with the name provided.
     *
     * @param watchlistName the name of the watchlist to add tickers to
     * @param tickers       a list of tickers to add
     * @return list of names of the Watch Lists
     */
    String[] addSymbolsToList(String watchlistName, String[] tickers);

    /**
     * This will delete the symbols provided for the watchlist
     *
     * @param watchlistName the name of the watchlist to remove tickers from
     * @param tickers       ticker to remove from the watchlist
     */
    void deleteSymbolFromList(String watchlistName, String[] tickers);

    /**
     * This will get Watch List items
     *
     * @param watchlistName the name of watchlist to get items for
     * @return a list of watchlist items
     */
    WatchlistItem[] getItems(String watchlistName);
}
