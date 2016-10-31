/*
给你很多圆，用圆心和半径表示，求有没有圆是相交的。朴素解法是枚举任意两圆，复杂度O(N^2)。小哥问有没有优化。优化的思路是，每个圆的x值都是个区间，只有那些x区间相交的圆，它们才有可能相交。如果两个圆x区间相交，那么就比较圆心距离和半径和，看是不是相交。这样不用枚举任意两个圆。然而worst case还是O(N^2)，比如所有圆的x区间都有覆盖。

*/
