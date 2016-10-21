/*
	Two Big Float Numbers' addition.

	"342.112" + "12.09" = "354.202"
	
	Be careful about cases when being split by "\\." like 
	".32" -> {"","32"}, 
	"21" -> {"21"}, 
	"0.0" -> {"0", "0"}
	"12." -> {"12"}
	
	Tested cases:
	".51" + "1.49" -> "2"
	"32.23" + "2.82" -> "35.05"
	"32.23" + "2.97" -> "35.2"
	"32.23" + "2.771" -> "35.001"
*/

public static String floatAdd(String num1, String num2) {
	if(num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) return null;
	String[] strs1 = num1.split("\\."), strs2 = num2.split("\\.");
	StringBuilder res = new StringBuilder();
	int carry = 0;
	// Decimal Part
	if(strs1.length != 1 || strs2.length != 1) {
		if(strs1.length == 1) res.append(strs2[1]).reverse();
		else if(strs2.length == 1) res.append(strs1[1]).reverse();
		else {
			for(int i = Math.max(strs1[1].length(), strs2[1].length()) - 1; i >= 0; i--) {
				int cur = (i < strs1[1].length() ? strs1[1].charAt(i) - '0' : 0) +
						(i < strs2[1].length() ? strs2[1].charAt(i) - '0' : 0) + carry;
				if(cur % 10 != 0 || res.length() != 0) res.append(cur % 10);
				carry = cur / 10;
			}
		}
		if(res.length() != 0) res.append(".");
	}
	// Integer part
	for(int i = strs1[0].length() - 1, j = strs2[0].length() - 1; i >= 0 || j >= 0; i--, j--) {
		int cur = (i >= 0 ? strs1[0].charAt(i) - '0' : 0) + (j >= 0 ? strs2[0].charAt(j) - '0' : 0) + carry;
		res.append(cur % 10);
		carry = cur / 10;
	}
	return res.reverse().toString();
}