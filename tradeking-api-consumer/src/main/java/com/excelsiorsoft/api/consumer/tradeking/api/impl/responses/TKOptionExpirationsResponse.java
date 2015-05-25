/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;


import com.fasterxml.jackson.annotation.JsonSetter;
import org.joda.time.LocalDate;

import java.util.Map;

public class TKOptionExpirationsResponse extends TKResponse {

    private String error;

    private LocalDate[] dates;

    @JsonSetter("expirationdates")
    public void setDates(Map datesResponse) throws Exception {
        this.dates = (LocalDate[]) this.extractArray(LocalDate[].class, datesResponse, "date");
    }

    public String getError() {
        return error;
    }

    public LocalDate[] getDates() {
        return dates;
    }
}
