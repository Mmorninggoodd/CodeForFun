/*
	Given the Movable interface, and a borderless maze.
	Check whether you can reach destination/

*/

/*
	Solve using dfs.
*/
interface Movable {
    void move();
    boolean checkMoveSuccess();
}