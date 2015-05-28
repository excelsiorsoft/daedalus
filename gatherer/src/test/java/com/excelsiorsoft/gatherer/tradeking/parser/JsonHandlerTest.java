package com.excelsiorsoft.gatherer.tradeking.parser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class JsonHandlerTest {
	
	
	
	/**
	 * 
	 * 
	 * 
	 * @author sleyzerzon
	 *
	 */
	public static class QuoteDeserializer extends JsonDeserializer<Quote> {
	    
		@Override
	    public Quote deserialize(JsonParser jsonParser,
	            DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
	        ObjectCodec oc = jsonParser.getCodec();
	        JsonNode node = oc.readTree(jsonParser);
	        
	        String toDeserialize = node.get("response").toString();
	        
	        Object document = Configuration.defaultConfiguration().jsonProvider().parse(toDeserialize);
			String symbol = JsonPath.read(document, "$.quotes.quote[0].symbol");
			String bid = JsonPath.read(document, "$.quotes.quote[0].bid");
			String ask = JsonPath.read(document, "$.quotes.quote[0].ask");

	        
	        return Quote.builder().bid(bid).ask(ask).symbol(symbol).build();
	        
	    }
	}
	
	//endpoint specific value object, not our app domain object
	@JsonDeserialize(using = QuoteDeserializer.class)
	public final static class Quote {
		
		double bid;
		double ask;
		String symbol;
		
		public double getBid() {
			return bid;
		}
		public void setBid(double bid) {
			this.bid = bid;
		}
		public double getAsk() {
			return ask;
		}
		public void setAsk(double ask) {
			this.ask = ask;
		}
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		
		public  static QuoteBuilder builder(){
			return new QuoteBuilder();
		}
		
		public static class QuoteBuilder {

			Quote quote = new Quote();

			public QuoteBuilder symbol(String symbol) {
				quote.setSymbol(symbol);
				return this;
			}

			public QuoteBuilder bid(String bid) {
				quote.setBid(Double.parseDouble(bid));
				return this;
			}

			public QuoteBuilder ask(String ask) {
				quote.setAsk(Double.parseDouble(ask));
				return this;
			}
			
			public Quote build(){
				return quote;
			}
		}

		@Override
		public String toString() {
			return String.format("Quote [bid=%s, ask=%s, symbol=%s]", bid, ask, symbol);
		}
		
	}
	
	private static String jsonStr = null;
	
	@BeforeClass
	public static void setUp(){
		
		jsonStr = "{\"response\":{\"@id\":\"2ef96f11:14d8cb4d340:-6433\",\"quotes\":{\"quote\":[{\"ask\":\"3.1500\",\"ask_time\":\"15:02\",\"asksz\":\"46\",\"basis\":\"4\",\"bid\":\"3.0500\",\"bid_time\":\"15:02\",\"bidsz\":\"220\",\"chg\":\"0.3100\",\"chg_sign\":\"u\",\"chg_t\":\"0.3100\",\"cl\":\"3.0600\",\"contract_size\":\"100\",\"date\":\"2015-05-22\",\"datetime\":\"2015-05-22T10:54:00-04:00\",\"days_to_expiration\":\"238\",\"dollar_value\":\"18\",\"exch\":\"OPRA\",\"exch_desc\":\"OPRA - Composite\",\"hi\":\"3.0600\",\"idelta\":\"-0.573511\",\"igamma\":\"0.076237\",\"imp_volatility\":\"0.328936\",\"incr_vl\":\"6\",\"irho\":\"-0.083323\",\"issue_desc\":\"PUT  JAN-16 $ 21.00\",\"itheta\":\"-0.004233\",\"ivega\":\"0.061261\",\"last\":\"3.0600\",\"lo\":\"3.0600\",\"name\":\"SILVER WHEATON CP\",\"op_delivery\":\"S\",\"op_flag\":\"0\",\"op_style\":\"A\",\"op_subclass\":\"0\",\"openinterest\":\"113\",\"opn\":\"3.0600\",\"opt_val\":\"3.447055\",\"pchg\":\"11.273 %\",\"pchg_sign\":\"u\",\"pcls\":\"2.7500\",\"phi\":\"2.7500\",\"plo\":\"2.7500\",\"popn\":\"2.7500\",\"pr_date\":\"2015-05-19\",\"pr_openinterest\":\"113\",\"prchg\":\"0.1600\",\"prem_mult\":\"100\",\"put_call\":\"put\",\"pvol\":\"3\",\"qcond\":\"0\",\"rootsymbol\":\"SLW\",\"secclass\":\"1\",\"sesn\":\"regular\",\"strikeprice\":\"21.00\",\"symbol\":\"SLW160115P00021000\",\"tcond\":\"R\",\"timestamp\":\"1432306440\",\"tr_num\":\"1\",\"tradetick\":\"u\",\"trend\":\"u\",\"under_cusip\":\"828336107\",\"undersymbol\":\"SLW\",\"vl\":\"6\",\"vwap\":\"0.0000\",\"wk52hi\":\"3.4500\",\"wk52hidate\":\"20150422\",\"wk52lo\":\"2.5500\",\"wk52lodate\":\"20150429\",\"xdate\":\"20160115\",\"xday\":\"15\",\"xmonth\":\"01\",\"xyear\":\"2016\"},{\"adp_100\":\"20.6884\",\"adp_200\":\"20.8542\",\"adp_50\":\"19.6564\",\"adv_21\":\"3764579\",\"adv_30\":\"3610419\",\"adv_90\":\"4155478\",\"ask\":\"19.3600\",\"ask_time\":\"19:59\",\"asksz\":\"1\",\"basis\":\"6\",\"beta\":\"0.380\",\"bid\":\"18.9800\",\"bid_time\":\"19:59\",\"bidsz\":\"5\",\"bidtick\":\"5\",\"chg\":\"0.220000\",\"chg_sign\":\"d\",\"chg_t\":\"0.220000\",\"cl\":\"19.360000\",\"cusip\":\"82833610\",\"date\":\"2015-05-22\",\"datetime\":\"2015-05-22T16:02:00-04:00\",\"div\":\"0.0500\",\"divexdate\":\"20150518\",\"divfreq\":\"Q\",\"divpaydt\":\"20150602\",\"dollar_value\":\"45358814\",\"eps\":\"0.555375\",\"exch\":\"NYSE\",\"exch_desc\":\"New York Stock Exchange\",\"hi\":\"19.580000\",\"iad\":\"0.20\",\"incr_vl\":\"79682\",\"last\":\"19.360000\",\"lo\":\"19.300000\",\"name\":\"SILVER WHEATON CORP\",\"op_flag\":\"1\",\"opn\":\"19.565000\",\"pchg\":\"1.124 %\",\"pchg_sign\":\"d\",\"pcls\":\"19.580000\",\"pe\":\"34.86\",\"phi\":\"19.650000\",\"plo\":\"19.410000\",\"popn\":\"19.540000\",\"pr_adp_100\":\"20.7098\",\"pr_adp_200\":\"20.9267\",\"pr_adp_50\":\"19.6200\",\"pr_date\":\"2015-05-21\",\"prbook\":\"1.770\",\"prchg\":\"-0.030000\",\"pvol\":\"2178238\",\"qcond\":\"82\",\"secclass\":\"0\",\"sesn\":\"regular\",\"sesn_vl\":\"2478004\",\"sho\":\"403,969,000\",\"symbol\":\"SLW\",\"tcond\":\"R\",\"timestamp\":\"1432324920\",\"tr_num\":\"11759\",\"tradetick\":\"e\",\"trend\":\"ueedeueeee\",\"vl\":\"2529866\",\"volatility12\":\"0.385212\",\"vwap\":\"19.395370\",\"wk52hi\":\"27.6600\",\"wk52hidate\":\"20140710\",\"wk52lo\":\"16.5700\",\"wk52lodate\":\"20141105\",\"yield\":\"1.04\"},{\"ask\":\"2.5200\",\"ask_time\":\"15:52\",\"asksz\":\"28\",\"basis\":\"4\",\"bid\":\"2.4700\",\"bid_time\":\"15:58\",\"bidsz\":\"60\",\"chg\":\"0.0800\",\"chg_sign\":\"u\",\"chg_t\":\"0.0800\",\"cl\":\"2.5100\",\"contract_size\":\"100\",\"date\":\"2015-05-22\",\"datetime\":\"2015-05-22T15:05:00-04:00\",\"days_to_expiration\":\"238\",\"dollar_value\":\"44\",\"exch\":\"OPRA\",\"exch_desc\":\"OPRA - Composite\",\"hi\":\"2.5100\",\"idelta\":\"-0.498808\",\"igamma\":\"0.076295\",\"imp_volatility\":\"0.335126\",\"incr_vl\":\"1\",\"irho\":\"-0.072142\",\"issue_desc\":\"PUT  JAN-16 $ 20.00\",\"itheta\":\"-0.004398\",\"ivega\":\"0.062201\",\"last\":\"2.5100\",\"lo\":\"2.4700\",\"name\":\"SILVER WHEATON CP\",\"op_delivery\":\"S\",\"op_flag\":\"0\",\"op_style\":\"A\",\"op_subclass\":\"0\",\"openinterest\":\"6,563\",\"opn\":\"2.5000\",\"opt_val\":\"2.808393\",\"pchg\":\"3.292 %\",\"pchg_sign\":\"u\",\"pcls\":\"2.4300\",\"phi\":\"2.4300\",\"plo\":\"2.4200\",\"popn\":\"2.4200\",\"pr_date\":\"2015-05-21\",\"pr_openinterest\":\"6,540\",\"prchg\":\"0.1500\",\"prem_mult\":\"100\",\"put_call\":\"put\",\"pvol\":\"23\",\"qcond\":\"0\",\"rootsymbol\":\"SLW\",\"secclass\":\"1\",\"sesn\":\"regular\",\"strikeprice\":\"20.00\",\"symbol\":\"SLW160115P00020000\",\"tcond\":\"R\",\"timestamp\":\"1432321500\",\"tr_num\":\"4\",\"tradetick\":\"e\",\"trend\":\"udue\",\"under_cusip\":\"828336107\",\"undersymbol\":\"SLW\",\"vl\":\"22\",\"vwap\":\"0.0000\",\"wk52hi\":\"5.5000\",\"wk52hidate\":\"20131203\",\"wk52lo\":\"1.3100\",\"wk52lodate\":\"20140710\",\"xdate\":\"20160115\",\"xday\":\"15\",\"xmonth\":\"01\",\"xyear\":\"2016\"}]}}}";
	}
	
	
	@Test
	public void deserializingJsonWithJacksonMappingAndJsonPath() throws Throwable {
		
		String ask = "3.1500";
		
		//String json = "{\"response\":{\"@id\":\"2ef96f11:14d8cb4d340:-6433\",\"quotes\":{\"quote\":[{\"ask\":\"3.1500\",\"ask_time\":\"15:02\",\"asksz\":\"46\",\"basis\":\"4\",\"bid\":\"3.0500\",\"bid_time\":\"15:02\",\"bidsz\":\"220\",\"chg\":\"0.3100\",\"chg_sign\":\"u\",\"chg_t\":\"0.3100\",\"cl\":\"3.0600\",\"contract_size\":\"100\",\"date\":\"2015-05-22\",\"datetime\":\"2015-05-22T10:54:00-04:00\",\"days_to_expiration\":\"238\",\"dollar_value\":\"18\",\"exch\":\"OPRA\",\"exch_desc\":\"OPRA - Composite\",\"hi\":\"3.0600\",\"idelta\":\"-0.573511\",\"igamma\":\"0.076237\",\"imp_volatility\":\"0.328936\",\"incr_vl\":\"6\",\"irho\":\"-0.083323\",\"issue_desc\":\"PUT  JAN-16 $ 21.00\",\"itheta\":\"-0.004233\",\"ivega\":\"0.061261\",\"last\":\"3.0600\",\"lo\":\"3.0600\",\"name\":\"SILVER WHEATON CP\",\"op_delivery\":\"S\",\"op_flag\":\"0\",\"op_style\":\"A\",\"op_subclass\":\"0\",\"openinterest\":\"113\",\"opn\":\"3.0600\",\"opt_val\":\"3.447055\",\"pchg\":\"11.273 %\",\"pchg_sign\":\"u\",\"pcls\":\"2.7500\",\"phi\":\"2.7500\",\"plo\":\"2.7500\",\"popn\":\"2.7500\",\"pr_date\":\"2015-05-19\",\"pr_openinterest\":\"113\",\"prchg\":\"0.1600\",\"prem_mult\":\"100\",\"put_call\":\"put\",\"pvol\":\"3\",\"qcond\":\"0\",\"rootsymbol\":\"SLW\",\"secclass\":\"1\",\"sesn\":\"regular\",\"strikeprice\":\"21.00\",\"symbol\":\"SLW160115P00021000\",\"tcond\":\"R\",\"timestamp\":\"1432306440\",\"tr_num\":\"1\",\"tradetick\":\"u\",\"trend\":\"u\",\"under_cusip\":\"828336107\",\"undersymbol\":\"SLW\",\"vl\":\"6\",\"vwap\":\"0.0000\",\"wk52hi\":\"3.4500\",\"wk52hidate\":\"20150422\",\"wk52lo\":\"2.5500\",\"wk52lodate\":\"20150429\",\"xdate\":\"20160115\",\"xday\":\"15\",\"xmonth\":\"01\",\"xyear\":\"2016\"},{\"adp_100\":\"20.6884\",\"adp_200\":\"20.8542\",\"adp_50\":\"19.6564\",\"adv_21\":\"3764579\",\"adv_30\":\"3610419\",\"adv_90\":\"4155478\",\"ask\":\"19.3600\",\"ask_time\":\"19:59\",\"asksz\":\"1\",\"basis\":\"6\",\"beta\":\"0.380\",\"bid\":\"18.9800\",\"bid_time\":\"19:59\",\"bidsz\":\"5\",\"bidtick\":\"5\",\"chg\":\"0.220000\",\"chg_sign\":\"d\",\"chg_t\":\"0.220000\",\"cl\":\"19.360000\",\"cusip\":\"82833610\",\"date\":\"2015-05-22\",\"datetime\":\"2015-05-22T16:02:00-04:00\",\"div\":\"0.0500\",\"divexdate\":\"20150518\",\"divfreq\":\"Q\",\"divpaydt\":\"20150602\",\"dollar_value\":\"45358814\",\"eps\":\"0.555375\",\"exch\":\"NYSE\",\"exch_desc\":\"New York Stock Exchange\",\"hi\":\"19.580000\",\"iad\":\"0.20\",\"incr_vl\":\"79682\",\"last\":\"19.360000\",\"lo\":\"19.300000\",\"name\":\"SILVER WHEATON CORP\",\"op_flag\":\"1\",\"opn\":\"19.565000\",\"pchg\":\"1.124 %\",\"pchg_sign\":\"d\",\"pcls\":\"19.580000\",\"pe\":\"34.86\",\"phi\":\"19.650000\",\"plo\":\"19.410000\",\"popn\":\"19.540000\",\"pr_adp_100\":\"20.7098\",\"pr_adp_200\":\"20.9267\",\"pr_adp_50\":\"19.6200\",\"pr_date\":\"2015-05-21\",\"prbook\":\"1.770\",\"prchg\":\"-0.030000\",\"pvol\":\"2178238\",\"qcond\":\"82\",\"secclass\":\"0\",\"sesn\":\"regular\",\"sesn_vl\":\"2478004\",\"sho\":\"403,969,000\",\"symbol\":\"SLW\",\"tcond\":\"R\",\"timestamp\":\"1432324920\",\"tr_num\":\"11759\",\"tradetick\":\"e\",\"trend\":\"ueedeueeee\",\"vl\":\"2529866\",\"volatility12\":\"0.385212\",\"vwap\":\"19.395370\",\"wk52hi\":\"27.6600\",\"wk52hidate\":\"20140710\",\"wk52lo\":\"16.5700\",\"wk52lodate\":\"20141105\",\"yield\":\"1.04\"},{\"ask\":\"2.5200\",\"ask_time\":\"15:52\",\"asksz\":\"28\",\"basis\":\"4\",\"bid\":\"2.4700\",\"bid_time\":\"15:58\",\"bidsz\":\"60\",\"chg\":\"0.0800\",\"chg_sign\":\"u\",\"chg_t\":\"0.0800\",\"cl\":\"2.5100\",\"contract_size\":\"100\",\"date\":\"2015-05-22\",\"datetime\":\"2015-05-22T15:05:00-04:00\",\"days_to_expiration\":\"238\",\"dollar_value\":\"44\",\"exch\":\"OPRA\",\"exch_desc\":\"OPRA - Composite\",\"hi\":\"2.5100\",\"idelta\":\"-0.498808\",\"igamma\":\"0.076295\",\"imp_volatility\":\"0.335126\",\"incr_vl\":\"1\",\"irho\":\"-0.072142\",\"issue_desc\":\"PUT  JAN-16 $ 20.00\",\"itheta\":\"-0.004398\",\"ivega\":\"0.062201\",\"last\":\"2.5100\",\"lo\":\"2.4700\",\"name\":\"SILVER WHEATON CP\",\"op_delivery\":\"S\",\"op_flag\":\"0\",\"op_style\":\"A\",\"op_subclass\":\"0\",\"openinterest\":\"6,563\",\"opn\":\"2.5000\",\"opt_val\":\"2.808393\",\"pchg\":\"3.292 %\",\"pchg_sign\":\"u\",\"pcls\":\"2.4300\",\"phi\":\"2.4300\",\"plo\":\"2.4200\",\"popn\":\"2.4200\",\"pr_date\":\"2015-05-21\",\"pr_openinterest\":\"6,540\",\"prchg\":\"0.1500\",\"prem_mult\":\"100\",\"put_call\":\"put\",\"pvol\":\"23\",\"qcond\":\"0\",\"rootsymbol\":\"SLW\",\"secclass\":\"1\",\"sesn\":\"regular\",\"strikeprice\":\"20.00\",\"symbol\":\"SLW160115P00020000\",\"tcond\":\"R\",\"timestamp\":\"1432321500\",\"tr_num\":\"4\",\"tradetick\":\"e\",\"trend\":\"udue\",\"under_cusip\":\"828336107\",\"undersymbol\":\"SLW\",\"vl\":\"22\",\"vwap\":\"0.0000\",\"wk52hi\":\"5.5000\",\"wk52hidate\":\"20131203\",\"wk52lo\":\"1.3100\",\"wk52lodate\":\"20140710\",\"xdate\":\"20160115\",\"xday\":\"15\",\"xmonth\":\"01\",\"xyear\":\"2016\"}]}}}";
		jsonStr = "{\"response\":{\"@id\":\"2ef96f11:14d8cb4d340:-6433\",\"quotes\":{\"quote\":[{\"ask\":\""+ ask +"\",\"bid\":\"3.0500\",\"symbol\":\"SLW160115P00021000\"}]}}}";

		ObjectMapper mapper = new ObjectMapper();

		Quote quote = mapper.readValue(jsonStr, new TypeReference<Quote>() {});
		
		assertEquals(BigDecimal.valueOf(quote.getAsk()).setScale(4).toPlainString(),ask);

		System.out.println(String.format("Deserialized json=%s into the following object=%s", jsonStr, quote));
		System.out.println("==============================");
		
	}
	
	@Test
	public void grabbingValuesViaJsonPath() throws Throwable {
		
		/*System.out.println("market/ext/quotes call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		String jsonStr = foreman.makeApiCall(getExtQuotes(json, "slw, slw160115P00020000, slw160115P00021000 ", "")).getResponse();*/
		System.out.println("json="+jsonStr);
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonStr);
		String firstAsk = JsonPath.read(document, "$.response.quotes.quote[0].ask");
		System.out.println("ask:" + firstAsk);
		
		System.out.println("=============================="); 
		
	}
	
	

}
