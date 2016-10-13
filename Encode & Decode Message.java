/*

Given a message, implement encode() and decode() such that:
* encode()
E.g. message is "hello world"
(1) return distinct chars -> 8
(2) sort chars by frequency -> "lohe wrd"
(3) translate the message with the following dictionary:
"l" -> 01
"o" -> 011
"h" -> 0111
"e" -> 01111
...
So in the example, after translation it would be "0111011110101...."

for this function return (1)+(2)+(3) as a string, i.e. "8lohe wrd0111011110101...." 


* decode()
take in the output from encode, return the message string.

FOLLOW UP QUESTION: for encode, how to save space? e.g. (3) translation, we can store them as bits then convert every 8 bits as a char to return.

*/

public static String encode(String message) {
	Map<Character, Integer> frequencies = new HashMap<>();
	for(char c : message.toCharArray()) frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
	PriorityQueue<Map.Entry<Character, Integer>> sortedChar = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
	sortedChar.addAll(frequencies.entrySet());
	StringBuilder code = new StringBuilder(), encodedMessage = new StringBuilder();
	code.append('0');
	encodedMessage.append(frequencies.size());
	Map<Character, String> dict = new HashMap<>();
	for(Map.Entry<Character, Integer> entry : sortedChar) {
		code.append('1');
		encodedMessage.append(entry.getKey());
		dict.put(entry.getKey(), code.toString());
	}
	for(char c : message.toCharArray()) {
		encodedMessage.append(dict.get(c));
	}
	return encodedMessage.toString();
}


public static String decode(String encodedMessage) {
	int count = encodedMessage.charAt(0) - '0';
	Map<Integer, Character> dict = new HashMap<>();
	for(int i = 1; i <= count; i++) {
		dict.put(i, encodedMessage.charAt(i));
	}
	StringBuilder decodedMessage = new StringBuilder();
	for(int i = count + 1; i < encodedMessage.length();) {
		int start = i;
		i = encodedMessage.indexOf('0', start + 1);
		if(i == -1) i = encodedMessage.length();
		decodedMessage.append(dict.get(i - start - 1));
	}
	return decodedMessage.toString();
}



/*
	Follow up. Use bits to store.

*/
public static String encode(String message) {
	Map<Character, Integer> frequencies = new HashMap<>();
	for(char c : message.toCharArray()) frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
	PriorityQueue<Map.Entry<Character, Integer>> sortedChar = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
	sortedChar.addAll(frequencies.entrySet());
	StringBuilder encodedMessage = new StringBuilder();
	encodedMessage.append(frequencies.size());
	int count = 1;
	Map<Character, Integer> dict = new HashMap<>();
	for(Map.Entry<Character, Integer> entry : sortedChar) {
		encodedMessage.append(entry.getKey());
		dict.put(entry.getKey(), ++count);
	}
	final char defaultChar = 0xFFFF;
	char curChar = defaultChar;
	int index = 0;
	for(char c : message.toCharArray()) {
		curChar &= ~(1 << (15 - index));
		index += dict.get(c);
		while(index >= 16) {
			encodedMessage.append(curChar);
			index -= 16;
			curChar = defaultChar;
		}
	}
	if(index > 0) {
		curChar = (char) ((curChar >>> (16 - index)) << (16 - index));
		encodedMessage.append(curChar);
	}
	return encodedMessage.toString();
}
public static String decode(String encodedMessage) {
	int index = 0, count = 0;
	while(Character.isDigit(encodedMessage.charAt(index))) {
		count = count * 10 + encodedMessage.charAt(index) - '0';
		index++;
	}
	Map<Integer, Character> dict = new HashMap<>();
	for(int i = index; i < index + count; i++) {
		dict.put(i - index + 1, encodedMessage.charAt(i));
	}
	index += count;
	StringBuilder decodedMessage = new StringBuilder();
	int start = 0,end = start + 1;
	for(int i = index; i < encodedMessage.length(); i++) {
		char c = encodedMessage.charAt(i);
		for(int j = end % 16; j < 16; j++) {
			int countBit = 0;
			while(j < 16 && (c & (1 << (15 - j))) != 0) {
				j++;
				countBit++;
			}
			end += countBit;
			if((j < 16 || (j == 16 && i == encodedMessage.length() - 1)) && (end % 16 == 0 || countBit != 0)) {
				decodedMessage.append(dict.get(end - start - 1));
				start = end;
				end++;
			}
		}
	}
	return decodedMessage.toString();
}