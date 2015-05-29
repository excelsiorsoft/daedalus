package com.excelsiorsoft.genesis.tradeking.json.deserialization;

import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.Option;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OptionDeserializerTest { 

	/**
	 * https://oopsnullpointer.wordpress.com/2009/10/26/hello-world/
	 * http://www.cowtowncoder.com/blog/archives/2009/08/entry_305.html
	 * http://wiki.fasterxml.com/JacksonMixInAnnotations
	 * http://wiki.fasterxml.com/JacksonHowToCustomSerializers
	 * https://www.sghill.net/how-do-i-write-a-jackson-json-serializer-deserializer.html
	 * http://www.robinhowlett.com/blog/2015/01/01/building-a-custom-jackson-deserializer/
	 * http://www.leveluplunch.com/java/examples/convert-json-to-from-map-jackson/
	 * http://blog.palominolabs.com/2012/06/05/writing-a-custom-jackson-serializer-and-deserializer/
	 * http://www.baeldung.com/jackson 
	 * http://programmerbruce.blogspot.com.au/2011/05/deserialize-json-with-jackson-into.html
	 * 
	 * @throws Throwable 
	 */
	@Test
	public void receivingSetOfQuotes() throws Throwable {
		
		//String optionJsonStr = "{\"response\": {\"@id\": \"3fb221ee:14d9aea59d9:-972\",\"quotes\": {\"quote\": {\"ask\": \"2.9500\",\"ask_time\": \"10:31\",\"asksz\": \"61\",\"basis\": \"4\",\"bid\": \"2.9100\",         \"bid_time\": \"10:31\",         \"bidsz\": \"44\",         \"chg\": \"0.0600\",         \"chg_sign\": \"u\",         \"chg_t\": \"0.0600\",         \"cl\": \"2.8800\",         \"contract_size\": \"100\",         \"date\": \"2015-05-28\",         \"datetime\": \"2015-05-28T09:58:00-04:00\",         \"days_to_expiration\": \"232\",         \"dollar_value\": \"20\",         \"exch\": \"OPRA\",         \"exch_desc\": \"OPRA - Composite\",         \"hi\": \"2.9400\",         \"idelta\": \"-0.558702\",         \"igamma\": \"0.077895\",         \"imp_volatility\": \"0.339838\",         \"incr_vl\": \"10\",         \"irho\": \"-0.077263\",         \"issue_desc\": \"PUT  JAN-16 $ 20.00\",         \"itheta\": \"-0.004250\",         \"ivega\": \"0.058142\",         \"last\": \"2.9400\",         \"lo\": \"2.9400\",         \"name\": \"SILVER WHEATON CP\",         \"op_delivery\": \"S\",         \"op_flag\": \"0\",         \"op_style\": \"A\",         \"op_subclass\": \"0\",         \"openinterest\": \"6,567\",         \"opn\": \"2.9400\",         \"opt_val\": \"3.198191\",         \"pchg\": \"2.083 %\",         \"pchg_sign\": \"e\",         \"pcls\": \"2.8800\",         \"phi\": \"2.8800\",         \"plo\": \"2.8800\",         \"popn\": \"2.8800\",         \"pr_date\": \"2015-05-27\",         \"pr_openinterest\": \"6,567\",         \"prchg\": \"0.0000\",         \"prem_mult\": \"100\",         \"put_call\": \"put\",         \"pvol\": \"5\",         \"qcond\": \"0\",         \"rootsymbol\": \"SLW\",         \"secclass\": \"1\",         \"sesn\": \"regular\",         \"strikeprice\": \"20.00\",         \"symbol\": \"SLW160115P00020000\",         \"tcond\": \"R\",         \"timestamp\": \"1432821480\",         \"tr_num\": \"1\",         \"tradetick\": \"u\",         \"trend\": \"u\",         \"under_cusip\": \"828336107\",         \"undersymbol\": \"SLW\",         \"vl\": \"10\",         \"vwap\": \"0.0000\",         \"wk52hi\": \"5.5000\",         \"wk52hidate\": \"20131203\",         \"wk52lo\": \"1.3100\",         \"wk52lodate\": \"20140710\",         \"xdate\": \"20160115\",         \"xday\": \"15\",         \"xmonth\": \"01\",         \"xyear\": \"2016\"       }     }   } }";
		
		String optionJsonStr = "{\"response\":{\"@id\":\"4a897894:14d9c0d48cf:556a\",\"quotes\":{\"quote\":[{\"ask\":\"3.4000\",\"ask_time\":\"14:31\",\"asksz\":\"31\",\"basis\":\"4\",\"bid\":\"3.3500\",\"bid_time\":\"15:54\",\"bidsz\":\"70\",\"chg\":\"0.0000\",\"chg_sign\":\"e\",\"chg_t\":\"0.0000\",\"cl\":\"3.4500\",\"contract_size\":\"100\",\"date\":\"2015-05-28\",\"datetime\":\"2015-05-28T00:00:00-04:00\",\"days_to_expiration\":\"232\",\"dollar_value\":\"0\",\"exch\":\"OPRA\",\"exch_desc\":\"OPRA - Composite\",\"hi\":\"0.0000\",\"idelta\":\"-0.608064\",\"igamma\":\"0.076328\",\"imp_volatility\":\"0.334585\",\"incr_vl\":\"0\",\"irho\":\"-0.094850\",\"issue_desc\":\"PUT  JAN-16 $ 21.00\",\"itheta\":\"-0.004149\",\"ivega\":\"0.058007\",\"last\":\"3.4500\",\"lo\":\"0.0000\",\"name\":\"SILVER WHEATON CP\",\"op_delivery\":\"S\",\"op_flag\":\"0\",\"op_style\":\"A\",\"op_subclass\":\"0\",\"openinterest\":\"122\",\"opn\":\"0.0000\",\"opt_val\":\"3.672494\",\"pchg\":\"0.000 %\",\"pchg_sign\":\"u\",\"pcls\":\"3.4500\",\"phi\":\"3.4500\",\"plo\":\"3.4500\",\"popn\":\"3.4500\",\"pr_date\":\"2015-05-26\",\"pr_openinterest\":\"122\",\"prchg\":\"0.3900\",\"prem_mult\":\"100\",\"put_call\":\"put\",\"pvol\":\"3\",\"qcond\":\"0\",\"rootsymbol\":\"SLW\",\"secclass\":\"1\",\"sesn\":\"regular\",\"strikeprice\":\"21.00\",\"symbol\":\"SLW160115P00021000\",\"tcond\":\"R\",\"timestamp\":\"1432785600\",\"tr_num\":\"0\",\"tradetick\":\"e\",\"under_cusip\":\"828336107\",\"undersymbol\":\"SLW\",\"vl\":\"0\",\"vwap\":\"0.0000\",\"wk52hi\":\"3.4500\",\"wk52hidate\":\"20150422\",\"wk52lo\":\"2.5500\",\"wk52lodate\":\"20150429\",\"xdate\":\"20160115\",\"xday\":\"15\",\"xmonth\":\"01\",\"xyear\":\"2016\"},{\"ask\":\"2.7600\",\"ask_time\":\"15:56\",\"asksz\":\"80\",\"basis\":\"4\",\"bid\":\"2.7000\",\"bid_time\":\"15:54\",\"bidsz\":\"75\",\"chg\":\"0.1000\",\"chg_sign\":\"d\",\"chg_t\":\"0.1000\",\"cl\":\"2.8800\",\"contract_size\":\"100\",\"date\":\"2015-05-28\",\"datetime\":\"2015-05-28T14:18:00-04:00\",\"days_to_expiration\":\"232\",\"dollar_value\":\"26\",\"exch\":\"OPRA\",\"exch_desc\":\"OPRA - Composite\",\"hi\":\"2.9400\",\"idelta\":\"-0.534522\",\"igamma\":\"0.077837\",\"imp_volatility\":\"0.339401\",\"incr_vl\":\"3\",\"irho\":\"-0.081866\",\"issue_desc\":\"PUT  JAN-16 $ 20.00\",\"itheta\":\"-0.004354\",\"ivega\":\"0.059837\",\"last\":\"2.7800\",\"lo\":\"2.7800\",\"name\":\"SILVER WHEATON CP\",\"op_delivery\":\"S\",\"op_flag\":\"0\",\"op_style\":\"A\",\"op_subclass\":\"0\",\"openinterest\":\"6,567\",\"opn\":\"2.9400\",\"opt_val\":\"3.004255\",\"pchg\":\"3.472 %\",\"pchg_sign\":\"e\",\"pcls\":\"2.8800\",\"phi\":\"2.8800\",\"plo\":\"2.8800\",\"popn\":\"2.8800\",\"pr_date\":\"2015-05-27\",\"pr_openinterest\":\"6,567\",\"prchg\":\"0.0000\",\"prem_mult\":\"100\",\"put_call\":\"put\",\"pvol\":\"5\",\"qcond\":\"0\",\"rootsymbol\":\"SLW\",\"secclass\":\"1\",\"sesn\":\"regular\",\"strikeprice\":\"20.00\",\"symbol\":\"SLW160115P00020000\",\"tcond\":\"R\",\"timestamp\":\"1432837080\",\"tr_num\":\"2\",\"tradetick\":\"d\",\"trend\":\"ud\",\"under_cusip\":\"828336107\",\"undersymbol\":\"SLW\",\"vl\":\"13\",\"vwap\":\"0.0000\",\"wk52hi\":\"5.5000\",\"wk52hidate\":\"20131203\",\"wk52lo\":\"1.3100\",\"wk52lodate\":\"20140710\",\"xdate\":\"20160115\",\"xday\":\"15\",\"xmonth\":\"01\",\"xyear\":\"2016\"}]}}}";		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JacksonMixInRegistry());
		
		

		Option quote = mapper.readValue(optionJsonStr, new TypeReference<Option>() {});
				
		//assertEquals(BigDecimal.valueOf(quote.getAsk()).setScale(4).toPlainString(),ask);

		System.out.println(String.format("Deserialized json=%s into the following object=%s", optionJsonStr, quote));
		System.out.println("==============================");
		
		
		/*
		 * 
		 * JSON:

{
    "objects" : ["One", "Two", "Three"]
}

Code:

final String json = "{\"objects\" : [\"One\", \"Two\", \"Three\"]}";

final JsonNode arrNode = new ObjectMapper().readTree(json).get("objects");
if (arrNode.isArray()) {
    for (final JsonNode objNode : arrNode) {
        System.out.println(objNode);
    }
}

Output:

    "One"
    "Two"
    "Three"
		 * 
		*/
	
	}
	
	/**
	 * http://wiki.fasterxml.com/JacksonTreeModel
	 * 
	 * @throws Throwable
	 */
	@Test
	public void treeModelWithJacksonParser() throws Throwable {
		
		String optionJsonStr = "{\"response\":{\"@id\":\"4a897894:14d9c0d48cf:556a\",\"quotes\":{\"quote\":[{\"ask\":\"3.4000\",\"ask_time\":\"14:31\",\"asksz\":\"31\",\"basis\":\"4\",\"bid\":\"3.3500\",\"bid_time\":\"15:54\",\"bidsz\":\"70\",\"chg\":\"0.0000\",\"chg_sign\":\"e\",\"chg_t\":\"0.0000\",\"cl\":\"3.4500\",\"contract_size\":\"100\",\"date\":\"2015-05-28\",\"datetime\":\"2015-05-28T00:00:00-04:00\",\"days_to_expiration\":\"232\",\"dollar_value\":\"0\",\"exch\":\"OPRA\",\"exch_desc\":\"OPRA - Composite\",\"hi\":\"0.0000\",\"idelta\":\"-0.608064\",\"igamma\":\"0.076328\",\"imp_volatility\":\"0.334585\",\"incr_vl\":\"0\",\"irho\":\"-0.094850\",\"issue_desc\":\"PUT  JAN-16 $ 21.00\",\"itheta\":\"-0.004149\",\"ivega\":\"0.058007\",\"last\":\"3.4500\",\"lo\":\"0.0000\",\"name\":\"SILVER WHEATON CP\",\"op_delivery\":\"S\",\"op_flag\":\"0\",\"op_style\":\"A\",\"op_subclass\":\"0\",\"openinterest\":\"122\",\"opn\":\"0.0000\",\"opt_val\":\"3.672494\",\"pchg\":\"0.000 %\",\"pchg_sign\":\"u\",\"pcls\":\"3.4500\",\"phi\":\"3.4500\",\"plo\":\"3.4500\",\"popn\":\"3.4500\",\"pr_date\":\"2015-05-26\",\"pr_openinterest\":\"122\",\"prchg\":\"0.3900\",\"prem_mult\":\"100\",\"put_call\":\"put\",\"pvol\":\"3\",\"qcond\":\"0\",\"rootsymbol\":\"SLW\",\"secclass\":\"1\",\"sesn\":\"regular\",\"strikeprice\":\"21.00\",\"symbol\":\"SLW160115P00021000\",\"tcond\":\"R\",\"timestamp\":\"1432785600\",\"tr_num\":\"0\",\"tradetick\":\"e\",\"under_cusip\":\"828336107\",\"undersymbol\":\"SLW\",\"vl\":\"0\",\"vwap\":\"0.0000\",\"wk52hi\":\"3.4500\",\"wk52hidate\":\"20150422\",\"wk52lo\":\"2.5500\",\"wk52lodate\":\"20150429\",\"xdate\":\"20160115\",\"xday\":\"15\",\"xmonth\":\"01\",\"xyear\":\"2016\"},{\"ask\":\"2.7600\",\"ask_time\":\"15:56\",\"asksz\":\"80\",\"basis\":\"4\",\"bid\":\"2.7000\",\"bid_time\":\"15:54\",\"bidsz\":\"75\",\"chg\":\"0.1000\",\"chg_sign\":\"d\",\"chg_t\":\"0.1000\",\"cl\":\"2.8800\",\"contract_size\":\"100\",\"date\":\"2015-05-28\",\"datetime\":\"2015-05-28T14:18:00-04:00\",\"days_to_expiration\":\"232\",\"dollar_value\":\"26\",\"exch\":\"OPRA\",\"exch_desc\":\"OPRA - Composite\",\"hi\":\"2.9400\",\"idelta\":\"-0.534522\",\"igamma\":\"0.077837\",\"imp_volatility\":\"0.339401\",\"incr_vl\":\"3\",\"irho\":\"-0.081866\",\"issue_desc\":\"PUT  JAN-16 $ 20.00\",\"itheta\":\"-0.004354\",\"ivega\":\"0.059837\",\"last\":\"2.7800\",\"lo\":\"2.7800\",\"name\":\"SILVER WHEATON CP\",\"op_delivery\":\"S\",\"op_flag\":\"0\",\"op_style\":\"A\",\"op_subclass\":\"0\",\"openinterest\":\"6,567\",\"opn\":\"2.9400\",\"opt_val\":\"3.004255\",\"pchg\":\"3.472 %\",\"pchg_sign\":\"e\",\"pcls\":\"2.8800\",\"phi\":\"2.8800\",\"plo\":\"2.8800\",\"popn\":\"2.8800\",\"pr_date\":\"2015-05-27\",\"pr_openinterest\":\"6,567\",\"prchg\":\"0.0000\",\"prem_mult\":\"100\",\"put_call\":\"put\",\"pvol\":\"5\",\"qcond\":\"0\",\"rootsymbol\":\"SLW\",\"secclass\":\"1\",\"sesn\":\"regular\",\"strikeprice\":\"20.00\",\"symbol\":\"SLW160115P00020000\",\"tcond\":\"R\",\"timestamp\":\"1432837080\",\"tr_num\":\"2\",\"tradetick\":\"d\",\"trend\":\"ud\",\"under_cusip\":\"828336107\",\"undersymbol\":\"SLW\",\"vl\":\"13\",\"vwap\":\"0.0000\",\"wk52hi\":\"5.5000\",\"wk52hidate\":\"20131203\",\"wk52lo\":\"1.3100\",\"wk52lodate\":\"20140710\",\"xdate\":\"20160115\",\"xday\":\"15\",\"xmonth\":\"01\",\"xyear\":\"2016\"}]}}}";		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JacksonMixInRegistry());
		
		JsonNode response = mapper.readTree(optionJsonStr).get("response");
		System.out.println("response/quotes: " +response.path("quotes"));
		System.out.println("response/quotes/quote: " + response.path("quotes").path("quote"));
		
		for(JsonNode quote : response.path("quotes").path("quote")){
			System.out.println(""+ quote.path("undersymbol"));
		}
	
		
	}
	
	

}
