/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.member.TradeKingUser;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.member.UserAccount;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.member.UserProfile;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.TradeKingModule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TKUserResponse extends TKResponse {

    public static final String FIELD_VALUE = "value";

    enum UserProfileKeys {

        UUID("UUID"),
        GAINS_KEEPER("gainsKeeper"),
        LAST_NAME("primaryLastName"),
        DASHBOARD_COLLAPSED("accountSummaryDashboardCollapsed"),
        NEW_MESSAGE("NewMsg"),
        FIRST_NAME("primaryFirstName"),
        REAL_TIME_STOCK_QUOTE("rtq"),
        EMAIL_ADDRESS("emailAddress1"),
        FDIC_PAPER("fdicPaper"),
        AGREEMENT_FDIC("AGREEMENT_FDIC"),
        USE_TRADING_PASSWORD("useTradingPassword");

        private final String key;

        UserProfileKeys(String key) {
            this.key = key;
        }

        public static UserProfileKeys fromString(String key) {
            if (key != null) {
                for (UserProfileKeys b : UserProfileKeys.values()) {
                    if (key.equalsIgnoreCase(b.key)) {
                        return b;
                    }
                }
            }
            return null;
        }
    }

    private String error;

    @JsonIgnore
    private TradeKingUser user;

    /**
     * For User Profile TradeKing returns an array instead of some sensible normal object
     * The getter methods get it from the List that is turned into hash map for faster access.
     *
     * @param userData data fed in my deserializer
     * @throws Exception if cannot deserialize data
     */
    @SuppressWarnings("unchecked")
    @JsonSetter("userdata")
    public void deserializeUserData(Map<String, Object> userData) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new TradeKingModule());

        boolean isDisabled = "true".equals(userData.get("disabled"));
        boolean resetPassword = "true".equals(userData.get("resetpassword"));
        boolean resetTradingPassword = "true".equals(userData.get("resettradingpassword"));

        UserAccount[] accounts = (UserAccount[]) this.extractArray(UserAccount[].class, userData, "account");

        //User Profile Object
        UserProfile userProfile = new UserProfile();

        Map<String, List> data = (LinkedHashMap<String, List>) userData.get("userprofile");
        List<Map<String, String>> entry = (List<Map<String, String>>) data.get("entry");

        for (Map<String, String> entryLine : entry) {
            UserProfileKeys key = UserProfileKeys.fromString(entryLine.get("name"));

            //catch all
            if (null == key) {
                userProfile.add(entryLine.get("name"), entryLine.get(FIELD_VALUE));
                continue;
            }

            switch (key) {
                case AGREEMENT_FDIC:
                    userProfile.setFDICAgreement(entryLine.get(FIELD_VALUE));
                    break;
                case DASHBOARD_COLLAPSED:
                    userProfile.setAccountSummaryDashboardCollapsed(entryLine.get(FIELD_VALUE));
                    break;
                case EMAIL_ADDRESS:
                    userProfile.setEmailAddress(entryLine.get(FIELD_VALUE));
                    break;
                case UUID:
                    userProfile.setUUID(entryLine.get(FIELD_VALUE));
                    break;
                case FDIC_PAPER:
                    userProfile.setFDICPaper(entryLine.get(FIELD_VALUE));
                    break;
                case FIRST_NAME:
                    userProfile.setFirstName(entryLine.get(FIELD_VALUE));
                    break;
                case GAINS_KEEPER:
                    userProfile.setGainsKeeper("Y".equals(entryLine.get(FIELD_VALUE)));
                    break;
                case LAST_NAME:
                    userProfile.setLasName(entryLine.get(FIELD_VALUE));
                    break;
                case NEW_MESSAGE:
                    userProfile.setHasMewMessage("Y".equals(entryLine.get(FIELD_VALUE)));
                    break;
                case REAL_TIME_STOCK_QUOTE:
                    userProfile.setRealTimeStockQuote("Y".equals(entryLine.get(FIELD_VALUE)));
                    break;
                case USE_TRADING_PASSWORD:
                    userProfile.setTradingPasswordUsed("Y".equals(entryLine.get(FIELD_VALUE)));
                    break;
                default:
                    userProfile.add(entryLine.get("name"), entryLine.get(FIELD_VALUE));
            }
        }

        this.user = new TradeKingUser(isDisabled, resetPassword, resetTradingPassword, accounts, userProfile);

    }

    public TradeKingUser getUserData() {
        return user;
    }

    public String getError() {
        return error;
    }
}

