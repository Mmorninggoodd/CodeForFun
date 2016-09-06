/*

Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

One rectangle:
Bottom Left: (A,B)
Top Right: (C,D)

Anther:
Bottom Left: (E,F)
Top Right: (G,H)

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.

*/

public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area = (C-A)*(D-B) + (G-E)*(H-F);  // initial area of two rectangles
        if(B < H && F < D && E < C && A < G) // If there is any overlap, then remove overlap once
            area -= (Math.min(D, H) - Math.max(B, F)) * (Math.min(G, C) - Math.max(A,E)); 
        return area;
    }
}