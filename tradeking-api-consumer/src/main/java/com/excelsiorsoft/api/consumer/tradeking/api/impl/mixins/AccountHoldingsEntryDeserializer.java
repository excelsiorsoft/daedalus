package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings.AccountHoldingEntry;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.TradeKingModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

class AccountHoldingsEntryDeserializer extends JsonDeserializer<AccountHoldingEntry[]> {


    @Override
    public AccountHoldingEntry[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        AccountHoldingEntry[] entriesList;

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new TradeKingModule());
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        try {
            entriesList = mapper.readValue(jsonParser, AccountHoldingEntry[].class);
        } catch (Exception e) {
            AccountHoldingEntry oneNode = mapper.readValue(jsonParser, AccountHoldingEntry.class);
            entriesList = new AccountHoldingEntry[]{oneNode};
        }

        return entriesList;
    }
}