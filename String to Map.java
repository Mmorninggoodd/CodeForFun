
/*
	Convert string to map.
	
	Some examples:
	{a:b,c:d}
	{}
	{a:{c:d},b:e}

*/

static Map<String, Object> stringToMap2(String str) {
	Deque<Map<String, Object>> levels = new ArrayDeque<>();
	levels.push(new HashMap<>());
	Deque<String> keys = new ArrayDeque<>();
	for (int i = 1; i < str.length();) {
		if(str.charAt(i) == ':') {  // value
			if(str.charAt(i + 1) == '{') {  // value is a new map
				levels.push(new HashMap<>());
				i += 2;
			}
			else {  // regular value
				int startIndex = i + 1;
				for(; i < str.length(); i++) if(str.charAt(i) == ',' || str.charAt(i) == '}') break;
				String value = str.substring(startIndex, i);
				levels.peek().put(keys.pop(), value);
			}
		}
		else if(str.charAt(i) == '}') {  // end of current level
			if(!keys.isEmpty()) {    // non-empty map
				Map<String, Object> peek = levels.pop();
				levels.peek().put(keys.pop(), peek);
			}
			i++;
		}
		else {  // key
			if(str.charAt(i) == ',') i++;   // skip potential semi-colon
			int delimiter = str.indexOf(':', i);
			keys.push(str.substring(i, delimiter));
			i = delimiter;
		}
	}
	return levels.pop();
}
