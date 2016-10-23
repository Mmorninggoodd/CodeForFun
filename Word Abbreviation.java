/*

题意：  我们通常压缩的时候会把  interval 压缩成 i8l, 即首尾字母加这个word的长度。 
1. 但是研究人员发现， internal 和 interval  如果按上面那种方法就会模糊不清，因为都会压缩成 i8l. 于是研究人员就想到一个办法，就是加长它们的prefix, 直到遇到它们第一个不同的字母，
比如：internal 和 interval 会压缩成： intern8l, interv8l.
          intension 和 intrusion  会变成： inte9n, intr9n

2. 当 压缩完后， 发现压缩后的长度 大于等于 原始长度时， 保留原始长度。比如 intern8l  长度也是 8， 那么就 保留原始： internal.

(例子我自己举的， 大概这意思)
input: 是一个 string
like god  internal me internet interval intension face intrusion. 

output: l2e god internal me i8t interval inte9n f2e intr9n
(注意： 输出的时候要按照 输入string  的顺序， 顺序不能变)。

*/

/*
	Can build a Trie tree for all words.
	Then we try to get abbreviation for each word by search shortest unique prefix for each word.
	
	Can record number of word on each Trie Node if input string doesn't have duplicate word.
*/