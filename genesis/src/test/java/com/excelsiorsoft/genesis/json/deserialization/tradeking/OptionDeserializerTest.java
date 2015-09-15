package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import static com.excelsiorsoft.daedalus.dominion.WithSymbol.SYMBOL;
import static com.excelsiorsoft.daedalus.dominion.WithTimestamp.TIMESTAMP;
import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.impl.Option;
import com.excelsiorsoft.daedalus.util.Significant;

@Significant
public class OptionDeserializerTest {

	@SuppressWarnings("serial")
	@Test
	public void withSimpleOptionDeserializer() throws Throwable { 
		
		
		String optionJsonStr = "{\"response\":{\"@id\":\"4a897894:14d9c0d48cf:556a\",\"quotes\":{\"quote\":[{\"ask\":\"3.4000\",\"ask_time\":\"14:31\",\"asksz\":\"31\",\"basis\":\"4\",\"bid\":\"3.3500\",\"bid_time\":\"15:54\",\"bidsz\":\"70\",\"chg\":\"0.0000\",\"chg_sign\":\"e\",\"chg_t\":\"0.0000\",\"cl\":\"3.4500\",\"contract_size\":\"100\",\"date\":\"2015-05-28\",\"datetime\":\"2015-05-28T00:00:00-04:00\",\"days_to_expiration\":\"232\",\"dollar_value\":\"0\",\"exch\":\"OPRA\",\"exch_desc\":\"OPRA - Composite\",\"hi\":\"0.0000\",\"idelta\":\"-0.608064\",\"igamma\":\"0.076328\",\"imp_volatility\":\"0.334585\",\"incr_vl\":\"0\",\"irho\":\"-0.094850\",\"issue_desc\":\"PUT  JAN-16 $ 21.00\",\"itheta\":\"-0.004149\",\"ivega\":\"0.058007\",\"last\":\"3.4500\",\"lo\":\"0.0000\",\"name\":\"SILVER WHEATON CP\",\"op_delivery\":\"S\",\"op_flag\":\"0\",\"op_style\":\"A\",\"op_subclass\":\"0\",\"openinterest\":\"122\",\"opn\":\"0.0000\",\"opt_val\":\"3.672494\",\"pchg\":\"0.000 %\",\"pchg_sign\":\"u\",\"pcls\":\"3.4500\",\"phi\":\"3.4500\",\"plo\":\"3.4500\",\"popn\":\"3.4500\",\"pr_date\":\"2015-05-26\",\"pr_openinterest\":\"122\",\"prchg\":\"0.3900\",\"prem_mult\":\"100\",\"put_call\":\"put\",\"pvol\":\"3\",\"qcond\":\"0\",\"rootsymbol\":\"SLW\",\"secclass\":\"1\",\"sesn\":\"regular\",\"strikeprice\":\"21.00\",\"symbol\":\"SLW160115P00021000\",\"tcond\":\"R\",\"timestamp\":\"1432785600\",\"tr_num\":\"0\",\"tradetick\":\"e\",\"under_cusip\":\"828336107\",\"undersymbol\":\"SLW\",\"vl\":\"0\",\"vwap\":\"0.0000\",\"wk52hi\":\"3.4500\",\"wk52hidate\":\"20150422\",\"wk52lo\":\"2.5500\",\"wk52lodate\":\"20150429\",\"xdate\":\"20160115\",\"xday\":\"15\",\"xmonth\":\"01\",\"xyear\":\"2016\"},{\"ask\":\"2.7600\",\"ask_time\":\"15:56\",\"asksz\":\"80\",\"basis\":\"4\",\"bid\":\"2.7000\",\"bid_time\":\"15:54\",\"bidsz\":\"75\",\"chg\":\"0.1000\",\"chg_sign\":\"d\",\"chg_t\":\"0.1000\",\"cl\":\"2.8800\",\"contract_size\":\"100\",\"date\":\"2015-05-28\",\"datetime\":\"2015-05-28T14:18:00-04:00\",\"days_to_expiration\":\"232\",\"dollar_value\":\"26\",\"exch\":\"OPRA\",\"exch_desc\":\"OPRA - Composite\",\"hi\":\"2.9400\",\"idelta\":\"-0.534522\",\"igamma\":\"0.077837\",\"imp_volatility\":\"0.339401\",\"incr_vl\":\"3\",\"irho\":\"-0.081866\",\"issue_desc\":\"PUT  JAN-16 $ 20.00\",\"itheta\":\"-0.004354\",\"ivega\":\"0.059837\",\"last\":\"2.7800\",\"lo\":\"2.7800\",\"name\":\"SILVER WHEATON CP\",\"op_delivery\":\"S\",\"op_flag\":\"0\",\"op_style\":\"A\",\"op_subclass\":\"0\",\"openinterest\":\"6,567\",\"opn\":\"2.9400\",\"opt_val\":\"3.004255\",\"pchg\":\"3.472 %\",\"pchg_sign\":\"e\",\"pcls\":\"2.8800\",\"phi\":\"2.8800\",\"plo\":\"2.8800\",\"popn\":\"2.8800\",\"pr_date\":\"2015-05-27\",\"pr_openinterest\":\"6,567\",\"prchg\":\"0.0000\",\"prem_mult\":\"100\",\"put_call\":\"put\",\"pvol\":\"5\",\"qcond\":\"0\",\"rootsymbol\":\"SLW\",\"secclass\":\"1\",\"sesn\":\"regular\",\"strikeprice\":\"20.00\",\"symbol\":\"SLW160115P00020000\",\"tcond\":\"R\",\"timestamp\":\"1432837080\",\"tr_num\":\"2\",\"tradetick\":\"d\",\"trend\":\"ud\",\"under_cusip\":\"828336107\",\"undersymbol\":\"SLW\",\"vl\":\"13\",\"vwap\":\"0.0000\",\"wk52hi\":\"5.5000\",\"wk52hidate\":\"20131203\",\"wk52lo\":\"1.3100\",\"wk52lodate\":\"20140710\",\"xdate\":\"20160115\",\"xday\":\"15\",\"xmonth\":\"01\",\"xyear\":\"2016\"}]}}}";		
		
	  //String optionJsonStr = "{\"response\":{\"@id\":\"-7a930f26:14fcd5280f4:30b\",\"quotes\":{\"quote\":{\"adp_100\":\"16.1200\",\"adp_200\":\"18.4112\",\"adp_50\":\"13.1582\",\"adv_21\":\"5672981\",\"adv_30\":\"5853725\",\"adv_90\":\"5363276\",\"ask\":\"11.1700\",\"ask_time\":\"15:29\",\"asksz\":\"63\",\"basis\":\"6\",\"beta\":\"0.312\",\"bid\":\"11.1600\",\"bid_time\":\"15:29\",\"bidsz\":\"16\",\"bidtick\":\"1\",\"chg\":\"0.450000\",\"chg_sign\":\"d\",\"chg_t\":\"0.450000\",\"cl\":\"11.620000\",\"cusip\":\"82833610\",\"date\":\"2015-09-14\",\"datetime\":\"2015-09-14T15:29:00-04:00\",\"div\":\"0.0500\",\"divexdate\":\"20150824\",\"divfreq\":\"Q\",\"divpaydt\":\"20150904\",\"dollar_value\":\"57983475\",\"eps\":\"0.555375\",\"exch\":\"NYSE\",\"exch_desc\":\"New York Stock Exchange\",\"hi\":\"11.410000\",\"iad\":\"0.20\",\"incr_vl\":\"200\",\"last\":\"11.170000\",\"lo\":\"11.028900\",\"name\":\"SILVER WHEATON CORP\",\"op_flag\":\"1\",\"opn\":\"11.380000\",\"pchg\":\"3.873 %\",\"pchg_sign\":\"u\",\"pcls\":\"11.620000\",\"pe\":\"20.12\",\"phi\":\"11.680000\",\"plo\":\"11.030000\",\"popn\":\"11.290000\",\"pr_adp_100\":\"16.1989\",\"pr_adp_200\":\"18.4606\",\"pr_adp_50\":\"13.2749\",\"pr_date\":\"2015-09-11\",\"prbook\":\"1.050\",\"prchg\":\"0.210000\",\"pvol\":\"6406809\",\"qcond\":\"82\",\"secclass\":\"0\",\"sesn\":\"regular\",\"sesn_vl\":\"5652286\",\"sho\":\"404,279,000\",\"symbol\":\"SLW\",\"tcond\":\"R\",\"timestamp\":\"1442258940\",\"tr_num\":\"26051\",\"tradetick\":\"u\",\"trend\":\"eeeeeeeeuu\",\"vl\":\"5656151\",\"volatility12\":\"0.43526\",\"vwap\":\"11.184744\",\"wk52hi\":\"24.2200\",\"wk52hidate\":\"20150121\",\"wk52lo\":\"11.028900\",\"wk52lodate\":\"20150914\",\"yield\":\"1.80\"}}}}";
		
		//create context
		Map<String, Object> context = new HashMap<String,Object>(){{put(SYMBOL,"SLW");put(TIMESTAMP,nowFromEpoch());}};
		
		Collection<Option> result = new OptionDeserializer().deserialize(optionJsonStr, context);
		System.out.println("Deserialized :"+result);
		
	}
	
	 @Test
	 public void convertJsonToXmlOrBack() throws Throwable {
		 //http://www.json.org/javadoc/  (XML)
	 }
	 

}
