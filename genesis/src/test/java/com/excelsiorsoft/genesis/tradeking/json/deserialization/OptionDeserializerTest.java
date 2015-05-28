package com.excelsiorsoft.genesis.tradeking.json.deserialization;

import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.Option;
import com.fasterxml.jackson.core.type.TypeReference;
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
	 * 
	 * @throws Throwable
	 */
	@Test
	public void test() throws Throwable {
		
		String optionJsonStr = "{{\"response\":{\"@id\":\"6ddfbf6:14d96edde48:-7c9e\",\"quotes\":{\"quote\":[{\"ask\":\"3.5500\",\"ask_time\":\"15:45\",\"asksz\":\"846\",\"basis\":\"4\",\"bid\":\"3.4500\",\"bid_time\":\"15:18\",\"bidsz\":\"179\",\"chg\":\"0.0000\",\"chg_sign\":\"e\",\"chg_t\":\"0.0000\",\"cl\":\"3.4500\",\"contract_size\":\"100\",\"date\":\"2015-05-27\",\"datetime\":\"2015-05-27T00:00:00-04:00\",\"days_to_expiration\":\"233\",\"dollar_value\":\"0\",\"exch\":\"OPRA\",\"exch_desc\":\"OPRA - Composite\",\"hi\":\"0.0000\",\"idelta\":\"-0.614936\",\"igamma\":\"0.075079\",\"imp_volatility\":\"0.337544\",\"incr_vl\":\"0\",\"irho\":\"-0.085950\",\"issue_desc\":\"PUT  JAN-16 $ 21.00\",\"itheta\":\"-0.004120\",\"ivega\":\"0.056771\",\"last\":\"3.4500\",\"lo\":\"0.0000\",\"name\":\"SILVER WHEATON CP\",\"op_delivery\":\"S\",\"op_flag\":\"0\",\"op_style\":\"A\",\"op_subclass\":\"0\",\"openinterest\":\"122\",\"opn\":\"0.0000\",\"opt_val\":\"3.773644\",\"pchg\":\"0.000 %\",\"pchg_sign\":\"u\",\"pcls\":\"3.4500\",\"phi\":\"3.4500\",\"plo\":\"3.4500\",\"popn\":\"3.4500\",\"pr_date\":\"2015-05-26\",\"pr_openinterest\":\"119\",\"prchg\":\"0.3900\",\"prem_mult\":\"100\",\"put_call\":\"put\",\"pvol\":\"3\",\"qcond\":\"0\",\"rootsymbol\":\"SLW\",\"secclass\":\"1\",\"sesn\":\"regular\",\"strikeprice\":\"21.00\",\"symbol\":\"SLW160115P00021000\",\"tcond\":\"R\",\"timestamp\":\"1432699200\",\"tr_num\":\"0\",\"tradetick\":\"e\",\"under_cusip\":\"828336107\",\"undersymbol\":\"SLW\",\"vl\":\"0\",\"vwap\":\"0.0000\",\"wk52hi\":\"3.4500\",\"wk52hidate\":\"20150422\",\"wk52lo\":\"2.5500\",\"wk52lodate\":\"20150429\",\"xdate\":\"20160115\",\"xday\":\"15\",\"xmonth\":\"01\",\"xyear\":\"2016\"}]}}}";
		
		ObjectMapper mapper = new ObjectMapper();

		Option quote = mapper.readValue(optionJsonStr, new TypeReference<Option>() {});
		
		//assertEquals(BigDecimal.valueOf(quote.getAsk()).setScale(4).toPlainString(),ask);

		System.out.println(String.format("Deserialized json=%s into the following object=%s", optionJsonStr, quote));
		System.out.println("==============================");
	}

}
