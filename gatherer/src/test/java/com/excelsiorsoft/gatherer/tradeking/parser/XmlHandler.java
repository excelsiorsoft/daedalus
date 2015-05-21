package com.excelsiorsoft.gatherer.tradeking.parser;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;


public class XmlHandler {
	
	private Document getDocument(String response) 
	{
		SAXReader reader = new SAXReader();
		Document document = null;
		
		try {
			
			document = reader.read(new ByteArrayInputStream(response.getBytes()));
		}
		catch (Throwable e) {
			
			throw new RuntimeException("Parse response failed", e);
		}
		
		return document;
	}
	
	public Map<String, String> parseMarketClock(String response) 
	{
		Document doc = getDocument(response);
		
		
		for(Iterator it = doc.nodeIterator(); it.hasNext();){
			it.next();
		}
		
		return /*getMarketClockPaths(doc);*/null;
	}
	
	
	
}
