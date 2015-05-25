/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.market;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;


/**
 * Abstract quote class that holds all the data that stocks and options have in common.
 * It is extended by {@link OptionQuote} and {@link StockQuote} for type specific data
 */
abstract public class Quote extends TradeKingObject {

    private double ask;
    private LocalTime askTime;
    private int askLatestSize;
    private int basis;
    private double bid;
    private LocalTime bidTime;
    private int bidLatestSize;
    private double change;
    private ChangeSign changeSign;
    private String changeText;
    private double previousClose;
    private LocalDate dateLastTrade;
    private String exchangeCode;
    private String exchangeDescription;
    private double dayHigh;
    private long volumeLastTrade;
    private double lastPrice;
    private double dayLow;
    private double openTradePrice;
    private String changePercentage;
    private ChangeSign changePercentageSign;
    private double priorDayClose;
    private double priorDayHigh;
    private double priorDayLow;
    private double priorDayOpen;
    private LocalDate dateOfPriorTradeDay;
    private double priorDayChange;
    private long priorDayVolume;
    private SecurityClass securityClass;
    private TradingSession tradingSession;
    private String symbol;
    private TradeCondition tradeCondition;
    private String timeStamp;
    private int tradeCountSinceOpen;
    private ChangeSign tradeDirection;
    private String trendOf10LastTicks;
    private long cumulativeVolume;
    private double volumeWeightedAveragePrice;
    private double week52high;
    private LocalDate week52highDate;
    private double week52low;
    private LocalDate week52lowDate;
    private DateTime dateTime;
    private String quoteConditionCode;

    Quote() {
    }

    Quote(double ask, LocalTime askTime, int askLatestSize, int basis, double bid, LocalTime bidTime, int bidLatestSize, double change, ChangeSign changeSign, String changeText, double previousClose, LocalDate dateLastTrade, String exchangeCode, String exchangeDescription, double dayHigh, long volumeLastTrade, double lastPrice, double dayLow, double openTradePrice, String changePercentage, ChangeSign changePercentageSign, double priorDayClose, double priorDayHigh, double priorDayLow, double priorDayOpen, LocalDate dateOfPriorTradeDay, double priorDayChange, long priorDayVolume, SecurityClass securityClass, TradingSession tradingSession, String symbol, TradeCondition tradeCondition, String timeStamp, int tradeCountSinceOpen, ChangeSign tradeDirection, String trendOf10LastTicks, long cumulativeVolume, double volumeWeightedAveragePrice, double week52high, LocalDate week52highDate, double week52low, LocalDate week52lowDate, DateTime dateTime, String quoteConditionCode) {
        this.ask = ask;
        this.askTime = askTime;
        this.askLatestSize = askLatestSize;
        this.basis = basis;
        this.bid = bid;
        this.bidTime = bidTime;
        this.bidLatestSize = bidLatestSize;
        this.change = change;
        this.changeSign = changeSign;
        this.changeText = changeText;
        this.previousClose = previousClose;
        this.dateLastTrade = dateLastTrade;
        this.exchangeCode = exchangeCode;
        this.exchangeDescription = exchangeDescription;
        this.dayHigh = dayHigh;
        this.volumeLastTrade = volumeLastTrade;
        this.lastPrice = lastPrice;
        this.dayLow = dayLow;
        this.openTradePrice = openTradePrice;
        this.changePercentage = changePercentage;
        this.changePercentageSign = changePercentageSign;
        this.priorDayClose = priorDayClose;
        this.priorDayHigh = priorDayHigh;
        this.priorDayLow = priorDayLow;
        this.priorDayOpen = priorDayOpen;
        this.dateOfPriorTradeDay = dateOfPriorTradeDay;
        this.priorDayChange = priorDayChange;
        this.priorDayVolume = priorDayVolume;
        this.securityClass = securityClass;
        this.tradingSession = tradingSession;
        this.symbol = symbol;
        this.tradeCondition = tradeCondition;
        this.timeStamp = timeStamp;
        this.tradeCountSinceOpen = tradeCountSinceOpen;
        this.tradeDirection = tradeDirection;
        this.trendOf10LastTicks = trendOf10LastTicks;
        this.cumulativeVolume = cumulativeVolume;
        this.volumeWeightedAveragePrice = volumeWeightedAveragePrice;
        this.week52high = week52high;
        this.week52highDate = week52highDate;
        this.week52low = week52low;
        this.week52lowDate = week52lowDate;
        this.dateTime = dateTime;
        this.quoteConditionCode = quoteConditionCode;
    }

    /**
     * Ask price
     *
     * @return dollar figure
     */
    public double getAsk() {
        return ask;
    }

    /**
     * String time hh:mm of Time of latest ask
     *
     * @return String hh:mm time
     */
    public LocalTime getAskTime() {
        return askTime;
    }

    /**
     * Size of latest ask (in 100's)
     *
     * @return integer size of latest ask in hundreds
     */
    public int getAskLatestSizeHundreds() {
        return askLatestSize;
    }

    /**
     * Reported precision (quotation decimal places)
     *
     * @return int count of decimal digits
     */
    public int getBasis() {
        return basis;
    }

    /**
     * Bid price
     *
     * @return dollar figure
     */
    public double getBid() {
        return bid;
    }

    /**
     * String time hh:mm of Time of latest bid
     *
     * @return String hh:mm time
     */
    public LocalTime getBidTime() {
        return bidTime;
    }

    /**
     * Size of latest bid (in 100's)
     *
     * @return integer size of latest ask in hundreds
     */
    public int getBidLatestSizeHundreds() {
        return bidLatestSize;
    }

    /**
     * Change since prior day close (cl)
     *
     * @return dollar figure
     */
    public double getChange() {
        return change;
    }

    /**
     * Change sign  as even, up, down
     *
     * @return ChangeSign enum value
     */
    public ChangeSign getChangeSign() {
        return changeSign;
    }

    /**
     * Change in Text Format, does not have currency symbol, or anything special, just all the trailing decimals
     *
     * @return text change value with all the trailing decimals
     */
    public String getChangeText() {
        return changeText;
    }

    /**
     * Prior day close
     *
     * @return dollar figure
     */
    public double getPreviousClose() {
        return previousClose;
    }

    /**
     * Trade date of last trade
     *
     * @return date
     */
    public LocalDate getDateLastTrade() {
        return dateLastTrade;
    }

    /**
     * Exchange Code of quote provider
     *
     * @return exchange code
     */
    public String getExchangeCode() {
        return exchangeCode;
    }

    /**
     * Exchange Description of quote provider
     *
     * @return string exchange description
     */
    public String getExchangeDescription() {
        return exchangeDescription;
    }

    /**
     * High Trade Price for the trading day
     *
     * @return dollar figure
     */
    public double getDayHigh() {
        return dayHigh;
    }

    /**
     * Volume of last trade
     *
     * @return int volume of last trade
     */
    public long getVolumeLastTrade() {
        return volumeLastTrade;
    }

    /**
     * Last trade price
     *
     * @return dollar figure
     */
    public double getLastPrice() {
        return lastPrice;
    }

    /**
     * Low Trade Price for the trading day
     *
     * @return dollar figure
     */
    public double getDayLow() {
        return dayLow;
    }


    /**
     * Open trade price
     *
     * @return dollar figure
     */
    public double getOpenTradePrice() {
        return openTradePrice;
    }

    /**
     * Percentage change from prior day close
     *
     * @return String percentage with percentage symbol in it
     */
    public String getChangePercentage() {
        return changePercentage;
    }

    /**
     * Percentage change sign from a prior day close
     *
     * @return enum change sign value
     */
    public ChangeSign getChangePercentageSign() {
        return changePercentageSign;
    }

    /**
     * Prior day close
     *
     * @return dollar figure
     */
    public double getPriorDayClose() {
        return priorDayClose;
    }

    /**
     * Prior day high value
     *
     * @return dollar figure
     */
    public double getPriorDayHigh() {
        return priorDayHigh;
    }

    /**
     * Prior day low value
     *
     * @return dollar figure
     */
    public double getPriorDayLow() {
        return priorDayLow;
    }

    /**
     * Prior day open
     *
     * @return dollar figure
     */
    public double getPriorDayOpen() {
        return priorDayOpen;
    }

    /**
     * Trade Date of Prior Last
     *
     * @return LocalDate Object
     */
    public LocalDate getDateOfPriorTradeDay() {
        return dateOfPriorTradeDay;
    }

    /**
     * Prior day change
     *
     * @return dollar figure, for a negative has a preceding minus
     */
    public double getPriorDayChange() {
        return priorDayChange;
    }

    /**
     * Prior day total volume
     *
     * @return long volume
     */
    public long getPriorDayVolume() {
        return priorDayVolume;
    }

    /**
     * Security class (stock or option)
     *
     * @return SecurityClass enum value
     */
    public SecurityClass getSecurityClass() {
        return securityClass;
    }

    /**
     * Trading session as pre, regular, post
     *
     * @return TradingSession enum value
     */
    public TradingSession getTradingSession() {
        return tradingSession;
    }

    /**
     * Symbol from data provider
     *
     * @return symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Trade condition code – halted or resumed
     *
     * @return TradeCondition enum value
     */
    public TradeCondition getTradeCondition() {
        return tradeCondition;
    }

    /**
     * Unix Timestamp
     *
     * @return String timestamp value
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Number of trades since market open
     *
     * @return int count
     */
    public int getTradeCountSinceOpen() {
        return tradeCountSinceOpen;
    }

    /**
     * Tick direction from prior trade – (e,u,d) even, up, down)
     *
     * @return ChangeSign enum value
     */
    public ChangeSign getTradeDirection() {
        return tradeDirection;
    }

    /**
     * Trend based on 10 prior ticks (e,u,d) even, up, down
     *
     * @return string of ten consecutive letters
     */
    public String getTrendOf10LastTicks() {
        return trendOf10LastTicks;
    }

    /**
     * Cumulative volume
     *
     * @return long volume
     */
    public long getCumulativeVolume() {
        return cumulativeVolume;
    }

    /**
     * Volume weighted average price
     *
     * @return dollar figure
     */
    public double getVolumeWeightedAveragePrice() {
        return volumeWeightedAveragePrice;
    }

    /**
     * 52 week high
     *
     * @return dollar figure
     */
    public double getWeek52high() {
        return week52high;
    }

    /**
     * 52 week high date
     *
     * @return LocalDate object
     */
    public LocalDate getWeek52highDate() {
        return week52highDate;
    }

    /**
     * 52 week low
     *
     * @return Dollar value
     */
    public double getWeek52low() {
        return week52low;
    }

    /**
     * 52 week low date
     *
     * @return LocalDate object
     */
    public LocalDate getWeek52lowDate() {
        return week52lowDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quote)) return false;

        Quote quote = (Quote) o;

        if (Double.compare(quote.ask, ask) != 0) return false;
        if (askLatestSize != quote.askLatestSize) return false;
        if (basis != quote.basis) return false;
        if (Double.compare(quote.bid, bid) != 0) return false;
        if (bidLatestSize != quote.bidLatestSize) return false;
        if (Double.compare(quote.change, change) != 0) return false;
        if (cumulativeVolume != quote.cumulativeVolume) return false;
        if (Double.compare(quote.dayHigh, dayHigh) != 0) return false;
        if (Double.compare(quote.dayLow, dayLow) != 0) return false;
        if (Double.compare(quote.lastPrice, lastPrice) != 0) return false;
        if (Double.compare(quote.openTradePrice, openTradePrice) != 0) return false;
        if (Double.compare(quote.previousClose, previousClose) != 0) return false;
        if (Double.compare(quote.priorDayChange, priorDayChange) != 0) return false;
        if (Double.compare(quote.priorDayClose, priorDayClose) != 0) return false;
        if (Double.compare(quote.priorDayHigh, priorDayHigh) != 0) return false;
        if (Double.compare(quote.priorDayLow, priorDayLow) != 0) return false;
        if (Double.compare(quote.priorDayOpen, priorDayOpen) != 0) return false;
        if (priorDayVolume != quote.priorDayVolume) return false;
        if (tradeCountSinceOpen != quote.tradeCountSinceOpen) return false;
        if (volumeLastTrade != quote.volumeLastTrade) return false;
        if (Double.compare(quote.volumeWeightedAveragePrice, volumeWeightedAveragePrice) != 0) return false;
        if (Double.compare(quote.week52high, week52high) != 0) return false;
        if (Double.compare(quote.week52low, week52low) != 0) return false;
        if (!askTime.equals(quote.askTime)) return false;
        if (!bidTime.equals(quote.bidTime)) return false;
        if (!changePercentage.equals(quote.changePercentage)) return false;
        if (changePercentageSign != quote.changePercentageSign) return false;
        if (changeSign != quote.changeSign) return false;
        if (!changeText.equals(quote.changeText)) return false;
        if (!dateLastTrade.equals(quote.dateLastTrade)) return false;
        if (!dateOfPriorTradeDay.equals(quote.dateOfPriorTradeDay)) return false;
        if (!dateTime.equals(quote.dateTime)) return false;
        if (!exchangeCode.equals(quote.exchangeCode)) return false;
        if (!exchangeDescription.equals(quote.exchangeDescription)) return false;
        if (!quoteConditionCode.equals(quote.quoteConditionCode)) return false;
        if (securityClass != quote.securityClass) return false;
        if (!symbol.equals(quote.symbol)) return false;
        if (!timeStamp.equals(quote.timeStamp)) return false;
        if (tradeCondition != quote.tradeCondition) return false;
        if (tradeDirection != quote.tradeDirection) return false;
        if (tradingSession != quote.tradingSession) return false;
        if (!trendOf10LastTicks.equals(quote.trendOf10LastTicks)) return false;
        if (!week52highDate.equals(quote.week52highDate)) return false;
        if (!week52lowDate.equals(quote.week52lowDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(ask);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + askTime.hashCode();
        result = 31 * result + askLatestSize;
        result = 31 * result + basis;
        temp = Double.doubleToLongBits(bid);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + bidTime.hashCode();
        result = 31 * result + bidLatestSize;
        temp = Double.doubleToLongBits(change);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + changeSign.hashCode();
        result = 31 * result + changeText.hashCode();
        temp = Double.doubleToLongBits(previousClose);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + dateLastTrade.hashCode();
        result = 31 * result + exchangeCode.hashCode();
        result = 31 * result + exchangeDescription.hashCode();
        temp = Double.doubleToLongBits(dayHigh);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (volumeLastTrade ^ (volumeLastTrade >>> 32));
        temp = Double.doubleToLongBits(lastPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dayLow);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(openTradePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + changePercentage.hashCode();
        result = 31 * result + changePercentageSign.hashCode();
        temp = Double.doubleToLongBits(priorDayClose);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(priorDayHigh);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(priorDayLow);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(priorDayOpen);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + dateOfPriorTradeDay.hashCode();
        temp = Double.doubleToLongBits(priorDayChange);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (priorDayVolume ^ (priorDayVolume >>> 32));
        result = 31 * result + securityClass.hashCode();
        result = 31 * result + tradingSession.hashCode();
        result = 31 * result + symbol.hashCode();
        result = 31 * result + tradeCondition.hashCode();
        result = 31 * result + timeStamp.hashCode();
        result = 31 * result + tradeCountSinceOpen;
        result = 31 * result + tradeDirection.hashCode();
        result = 31 * result + trendOf10LastTicks.hashCode();
        result = 31 * result + (int) (cumulativeVolume ^ (cumulativeVolume >>> 32));
        temp = Double.doubleToLongBits(volumeWeightedAveragePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(week52high);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + week52highDate.hashCode();
        temp = Double.doubleToLongBits(week52low);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + week52lowDate.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + quoteConditionCode.hashCode();
        return result;
    }

    /**
     * Security class used in quotes, to indicate if the quote is for option or for stock
     */
    public enum SecurityClass {
        STOCK("0"), OPTION("1");
        private final String value;

        SecurityClass(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }


    public enum TradeCondition {
        HALTED("H"), RESUMED("R");
        private final String value;

        TradeCondition(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    /**
     * Indicates the session state of the time when quote was reveived
     */
    public enum TradingSession {
        MARKET("regular"), PRE_MARKET("pre"), POST_MARKET("post");
        private final String value;

        TradingSession(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

}
