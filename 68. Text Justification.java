/*

Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.

*/

public class Solution {
	/* 1ms
		O(n) time
		Just be careful for corner cases
	
	*/
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        int i = 0;
        while(i < words.length) { // while has words left
			/*
				Count how many words to add into this line
			*/
            int j = i + 1, curWidth = words[i].length(); // first add this word
            while(j < words.length && curWidth + words[j].length() + 1 <= maxWidth) { // add words until capacity is full
                curWidth += words[j++].length() + 1;
            }
			/*
				Start building this line below
			*/
            StringBuilder sb = new StringBuilder();
            sb.append(words[i]); // add first word
            if(j - i == 1) { // only one word in this line
                appendSpace(sb, maxWidth - words[i].length());
            }
            else if(j != words.length){ // not the last line
                int numGap = j - i - 1, spacePerGap = (maxWidth - curWidth) / numGap + 1;
                int extraSpace = maxWidth - curWidth - numGap * (spacePerGap - 1);
                for(int k = i + 1; k < j; k++) {   // add remaining words
                    appendSpace(sb, spacePerGap);  // even spaces
                    if(extraSpace-- > 0) sb.append(' ');
                    sb.append(words[k]);
                }
            }
            else {  // this is the last line
                for(int k = i + 1; k < j; k++) {
                    sb.append(' ');
                    sb.append(words[k]);
                }
                appendSpace(sb, maxWidth - curWidth);
            }
            res.add(sb.toString());
            i = j; // advance i
        }
        return res;
    }
    private void appendSpace(StringBuilder sb, int num) {
        for(int i = 0; i < num; i++) sb.append(' ');
    }
}