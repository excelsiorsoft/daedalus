package com.excelsiorsoft.daedalus.util;

import java.util.Comparator;

/**
 * Custom comparator that compares {@link String}s based on their numeric order
 * 
 * @author sleyzerzon
 *
 */
public  class NumericStringComparator implements Comparator<String> {

	private static Double value(String s) {
        try {
            return Double.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
	
	@Override
	public int compare(String s1, String s2) {
		Double i1 = value(s1);
        Double i2 = value(s2);
        if (i1 == null && i2 == null) {
            return s1.compareTo(s2);
        } else if (i1 == null) {
            return -1;
        } else if (i2 == null) {
            return 1;
        } else {
            return i1.compareTo(i2);
        }
	}
	
} 