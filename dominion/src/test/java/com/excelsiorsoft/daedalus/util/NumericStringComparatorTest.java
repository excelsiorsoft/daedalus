package com.excelsiorsoft.daedalus.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class NumericStringComparatorTest {

	@Test
	public void test() {
		
		String[]  strikes = new String[] {"10.0", "13.0" , "15.0", "18.0", "20.0", "22.0", "25.0", "27.0", "3.0", "30.0", "35.0", "5.0" , "8.0" };
		
		List list = Arrays.asList(strikes);
		Map sorted = new TreeMap<>(new NumericStringComparator());
		for(int i = 0; i < strikes.length; i++) {
			sorted.put(strikes[i], strikes[i]);
		}
		System.out.println(sorted);
	}

}
