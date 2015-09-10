/**
 * 
 */
package com.excelsiorsoft.gatherer.tradeking.market.flow.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author sleyzerzon
 *
 */
public class RandomPick<T> {
	
	public final static <T> T randomFrom(List<T> elements){
		
		T result = null;
		Random random = new Random();
		int index = random.nextInt(elements.size());
		result = elements.get(index);
		return result;
		
	}
	
	public static void main(String [] args) {
		
		
		for(int i = 1; i<=20; i++) {
			new RandomPick();
			System.out.println(i+": "+randomFrom(Arrays.asList(new String[] {"Abraham Linkoln", "Jane Eire", "Richard Nixon", "Miss America"})));
		}
	}

}
