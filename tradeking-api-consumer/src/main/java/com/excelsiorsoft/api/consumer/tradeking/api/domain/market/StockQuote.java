/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.market;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Detailed Stock Quote
 */
public class StockQuote extends Quote {

    private double averageDailyPrice100;
    private double averageDailyPrice200;
    private double averageDailyPrice50;
    private long averageDailyVolume21;
    private long averageDailyVolume30;
    private long averageDailyVolume90;
    private double beta;
    private String companyName;
    private Integer tickDirectionSinceLastBid;
    private String CUSIP;
    private double lastDividend;
    private LocalDate exDividendDate;
    private DividendFrequency dividendFrequency;
    private LocalDate lastDividendPayDate;
    private double earningsPerShare;
    private double indicatedAnnualDividend;
    private int hasOptions;
    private double priorAverageDailyPrice100;
    private double priorAverageDailyPrice200;
    private double priorAverageDailyPrice50;
    private double bookValuePrice;
    private String sharesOutstanding;
    private double oneYearVolatility;
    private Double dividendYield;
    private double priceEarning;
    private long sessionVolume;
    private double dollarValue;


    public StockQuote() {
        super();
    }

    public StockQuote(double ask, LocalTime askTime, int askLatestSize, int basis, double bid, LocalTime bidTime, int bidLatestSize, double change, ChangeSign changeSign, String changeText, double previousClose, LocalDate dateLastTrade, double dollarValue, String exchangeCode, String exchangeDescription, double dayHigh, int volumeLastTrade, double lastPrice, double dayLow, String companyName, double openTradePrice, String changePercentage, ChangeSign changePercentageSign, double priorDayClose, double priorDayHigh, double priorDayLow, double priorDayOpen, LocalDate dateOfPriorTradeDay, double priorDayChange, int priorDayVolume, SecurityClass securityClass, TradingSession tradingSession, String symbol, TradeCondition tradeCondition, String timeStamp, int tradeCountSinceOpen, ChangeSign tradeDirection, String trendOf10LastTicks, int cumulativeVolume, double volumeWeightedAveragePrice, double week52high, LocalDate week52highDate, double week52low, LocalDate week52lowDate, DateTime dateTime, String quoteConditionCode, double averageDailyPrice100, double averageDailyPrice200, double averageDailyPrice50, long averageDailyVolume21, long averageDailyVolume30, long averageDailyVolume90, double beta, int tickDirectionSinceLastBid, String CUSIP, double lastDividend, LocalDate exDividendDate, DividendFrequency dividendFrequency, LocalDate lastDividendPayDate, double earningsPerShare, double indicatedAnnualDividend, int hasOptions, double priorAverageDailyPrice100, double priorAverageDailyPrice200, double priorAverageDailyPrice50, double bookValuePrice, String sharesOutstanding, double oneYearVolatility, double dividendYield, double priceEarning, long sessionVolume) {
        super(ask, askTime, askLatestSize, basis, bid, bidTime, bidLatestSize, change, changeSign, changeText, previousClose, dateLastTrade, exchangeCode, exchangeDescription, dayHigh, volumeLastTrade, lastPrice, dayLow, openTradePrice, changePercentage, changePercentageSign, priorDayClose, priorDayHigh, priorDayLow, priorDayOpen, dateOfPriorTradeDay, priorDayChange, priorDayVolume, securityClass, tradingSession, symbol, tradeCondition, timeStamp, tradeCountSinceOpen, tradeDirection, trendOf10LastTicks, cumulativeVolume, volumeWeightedAveragePrice, week52high, week52highDate, week52low, week52lowDate, dateTime, quoteConditionCode);
        this.averageDailyPrice100 = averageDailyPrice100;
        this.averageDailyPrice200 = averageDailyPrice200;
        this.averageDailyPrice50 = averageDailyPrice50;
        this.averageDailyVolume21 = averageDailyVolume21;
        this.averageDailyVolume30 = averageDailyVolume30;
        this.averageDailyVolume90 = averageDailyVolume90;
        this.beta = beta;
        this.tickDirectionSinceLastBid = tickDirectionSinceLastBid;
        this.CUSIP = CUSIP;
        this.lastDividend = lastDividend;
        this.exDividendDate = exDividendDate;
        this.dividendFrequency = dividendFrequency;
        this.lastDividendPayDate = lastDividendPayDate;
        this.earningsPerShare = earningsPerShare;
        this.indicatedAnnualDividend = indicatedAnnualDividend;
        this.hasOptions = hasOptions;
        this.priorAverageDailyPrice100 = priorAverageDailyPrice100;
        this.priorAverageDailyPrice200 = priorAverageDailyPrice200;
        this.priorAverageDailyPrice50 = priorAverageDailyPrice50;
        this.bookValuePrice = bookValuePrice;
        this.sharesOutstanding = sharesOutstanding;
        this.oneYearVolatility = oneYearVolatility;
        this.dividendYield = dividendYield;
        this.priceEarning = priceEarning;
        this.sessionVolume = sessionVolume;
        this.companyName = companyName;
        this.dollarValue = dollarValue;

    }

    /**
     * Average Daily Price - 100 day
     *
     * @return dollar figure
     */
    public double getAverageDailyPrice100() {
        return averageDailyPrice100;
    }

    /**
     * Average Daily Price - 200 day
     *
     * @return dollar figure
     */
    public double getAverageDailyPrice200() {
        return averageDailyPrice200;
    }

    /**
     * Average Daily Price - 50 day
     *
     * @return dollar figure
     */
    public double getAverageDailyPrice50() {
        return averageDailyPrice50;
    }

    /**
     * Average Daily Volume - 21 day
     *
     * @return volume number
     */
    public long getAverageDailyVolume21() {
        return averageDailyVolume21;
    }

    /**
     * Average Daily Volume - 30 day
     *
     * @return volume number
     */
    public long getAverageDailyVolume30() {
        return averageDailyVolume30;
    }

    /**
     * Average Daily Volume - 90 day
     *
     * @return volume number
     */
    public long getAverageDailyVolume90() {
        return averageDailyVolume90;
    }

    /**
     * Beta volatility measure
     *
     * @return beta value
     */
    public double getBeta() {
        return beta;
    }

    /**
     * Tick direction. Not documented. Values null, 1 and 3
     *
     * @return Tick direction Id
     */
    public Integer getTickDirectionSinceLastBid() {
        return tickDirectionSinceLastBid;
    }

    /**
     * A CUSIP is a nine-character alphanumeric code that identifies a North American financial security for the purposes of facilitating clearing and settlement of trades.
     *
     * @return String CUSIP value
     */
    public String getCUSIP() {
        return CUSIP;
    }

    /**
     * Latest announced cash dividend
     *
     * @return dollar value
     */
    public double getLastDividend() {
        return lastDividend;
    }

    /**
     * Ex-dividend date of div
     *
     * @return LocalDate Object
     */
    public LocalDate getExDividendDate() {
        return exDividendDate;
    }

    /**
     * Dividend frequency.
     *
     * @return DividendFrequency enum value if set, otherwise null
     */
    public DividendFrequency getDividendFrequency() {
        return dividendFrequency;
    }

    /**
     * Ex-dividend date of div
     *
     * @return LocalDate Object
     */
    public LocalDate getLastDividendPayDate() {
        return lastDividendPayDate;
    }

    /**
     * Earnings per share
     *
     * @return EPS ratio
     */
    public double getEarningsPerShare() {
        return earningsPerShare;
    }

    /**
     * Indicated annual dividend
     *
     * @return dollar figure
     */
    public double getIndicatedAnnualDividend() {
        return indicatedAnnualDividend;
    }

    /**
     * Security  options flag
     *
     * @return {@code true} if security has options, otherwise {@code false}
     */
    public boolean isHasOptions() {
        return (hasOptions == 1);
    }

    /**
     * Prior Average Daily Price "100" trade days
     *
     * @return dollar figure
     */
    public double getPriorAverageDailyPrice100() {
        return priorAverageDailyPrice100;
    }

    /**
     * Prior Average Daily Price "200" trade days
     *
     * @return dollar figure
     */
    public double getPriorAverageDailyPrice200() {
        return priorAverageDailyPrice200;
    }

    /**
     * Prior Average Daily Price "50" trade days
     *
     * @return dollar figure
     */
    public double getPriorAverageDailyPrice50() {
        return priorAverageDailyPrice50;
    }

    /**
     * Book Value Price (A ratio used to compare a stock's market value to its book value.)
     *
     * @return Price/Book Value Ratio
     */
    public double getBookValuePrice() {
        return bookValuePrice;
    }

    /**
     * Shares Outstanding
     *
     * @return number of outstanding shares, coma delimited
     */
    public String getSharesOutstanding() {
        return sharesOutstanding;
    }

    /**
     * one year volatility measure
     *
     * @return volatility ratio
     */
    public double getOneYearVolatility() {
        return oneYearVolatility;
    }

    /**
     * Dividend yield as %
     *
     * @return percentage if applicable, otherwise null
     */
    public Double getDividendYield() {
        return dividendYield;
    }

    /**
     * Price earnings ratio
     *
     * @return p/e ratio
     */
    public double getPriceEarning() {
        return priceEarning;
    }

    /**
     * Volume this session
     *
     * @return volume number
     */
    public long getSessionVolume() {
        return sessionVolume;
    }

    /**
     * Company name
     *
     * @return Company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Total dollar value of shares traded today
     *
     * @return dollar figure in standard form, i.e. 3.58616648E8
     */
    public double getDollarValue() {
        return dollarValue;
    }


    /**
     * Indicates the session state of the time when quote was reveived
     */
    public enum DividendFrequency {
        ANNUAL("A"), SEMI_ANNUAL("S"), QUARTERLY("Q"), MONTHLY("M"), NOT_SET("N");
        private final String value;

        DividendFrequency(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockQuote)) return false;
        if (!super.equals(o)) return false;

        StockQuote that = (StockQuote) o;

        if (Double.compare(that.averageDailyPrice100, averageDailyPrice100) != 0) return false;
        if (Double.compare(that.averageDailyPrice200, averageDailyPrice200) != 0) return false;
        if (Double.compare(that.averageDailyPrice50, averageDailyPrice50) != 0) return false;
        if (averageDailyVolume21 != that.averageDailyVolume21) return false;
        if (averageDailyVolume30 != that.averageDailyVolume30) return false;
        if (averageDailyVolume90 != that.averageDailyVolume90) return false;
        if (Double.compare(that.beta, beta) != 0) return false;
        if (Double.compare(that.bookValuePrice, bookValuePrice) != 0) return false;
        if (Double.compare(that.dollarValue, dollarValue) != 0) return false;
        if (Double.compare(that.earningsPerShare, earningsPerShare) != 0) return false;
        if (hasOptions != that.hasOptions) return false;
        if (Double.compare(that.indicatedAnnualDividend, indicatedAnnualDividend) != 0) return false;
        if (Double.compare(that.lastDividend, lastDividend) != 0) return false;
        if (Double.compare(that.oneYearVolatility, oneYearVolatility) != 0) return false;
        if (Double.compare(that.priceEarning, priceEarning) != 0) return false;
        if (Double.compare(that.priorAverageDailyPrice100, priorAverageDailyPrice100) != 0) return false;
        if (Double.compare(that.priorAverageDailyPrice200, priorAverageDailyPrice200) != 0) return false;
        if (Double.compare(that.priorAverageDailyPrice50, priorAverageDailyPrice50) != 0) return false;
        if (sessionVolume != that.sessionVolume) return false;
        if (!CUSIP.equals(that.CUSIP)) return false;
        if (!companyName.equals(that.companyName)) return false;
        if (dividendFrequency != that.dividendFrequency) return false;
        if (!dividendYield.equals(that.dividendYield)) return false;
        if (!exDividendDate.equals(that.exDividendDate)) return false;
        if (!lastDividendPayDate.equals(that.lastDividendPayDate)) return false;
        if (!sharesOutstanding.equals(that.sharesOutstanding)) return false;
        if (!tickDirectionSinceLastBid.equals(that.tickDirectionSinceLastBid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(averageDailyPrice100);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(averageDailyPrice200);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(averageDailyPrice50);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (averageDailyVolume21 ^ (averageDailyVolume21 >>> 32));
        result = 31 * result + (int) (averageDailyVolume30 ^ (averageDailyVolume30 >>> 32));
        result = 31 * result + (int) (averageDailyVolume90 ^ (averageDailyVolume90 >>> 32));
        temp = Double.doubleToLongBits(beta);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + tickDirectionSinceLastBid.hashCode();
        result = 31 * result + CUSIP.hashCode();
        temp = Double.doubleToLongBits(lastDividend);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + exDividendDate.hashCode();
        result = 31 * result + dividendFrequency.hashCode();
        result = 31 * result + lastDividendPayDate.hashCode();
        temp = Double.doubleToLongBits(earningsPerShare);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(indicatedAnnualDividend);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + hasOptions;
        temp = Double.doubleToLongBits(priorAverageDailyPrice100);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(priorAverageDailyPrice200);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(priorAverageDailyPrice50);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(bookValuePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + sharesOutstanding.hashCode();
        result = 31 * result + companyName.hashCode();
        temp = Double.doubleToLongBits(oneYearVolatility);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + dividendYield.hashCode();
        temp = Double.doubleToLongBits(priceEarning);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (sessionVolume ^ (sessionVolume >>> 32));
        temp = Double.doubleToLongBits(dollarValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
