package main;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {

		Map<String, Integer> map = new LinkedHashMap<>();
		
		map.put("1", 1);
		map.put("1", 2);
		System.out.println(map.values());
	}

}
