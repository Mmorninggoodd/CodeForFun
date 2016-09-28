/*
	Number of distinct palindromes.
*/

/*
	Manacher
	O(n^2) Time O(n^2) Space
*/
public static int countPalindromes(String s) {
	int n = s.length();
	HashSet<String> res = new HashSet<>();
	if(n == 0) return 0;
	int[][] R = new int[2][n + 1];
	s = "@" + s + "#";

	for(int j = 0; j < 2; j++) {
		int rp = 0;
		R[j][0] = 0;
		for(int i = 1; i <= n;) {
			while(s.charAt(i - rp - 1) == s.charAt(i + j + rp)) rp++;
			R[j][i] = rp;
			int k = 1;
			while((R[j][i - k] != rp - k) && (k < rp)) {
				R[j][i + k] = Math.min(R[j][i - k], rp - k);
				k++;
			}
			rp = Math.max(rp - k, 0);
			i += k;
		}

	}
	for(int j = 0; j < 2; j++) {
		for(int i = 1; i < n; i++) {
			if(R[j][i] == 0) res.add(String.valueOf(s.charAt(i)));
			else res.add(s.substring(i - R[j][i], i + R[j][i] + j));
		}
	}
	return res.size();
}