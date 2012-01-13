
public class Run {

	public static void main(String[] args)
	{
		Puzzle p = new Puzzle(3,3);
		p._grid[0][0] = false;
		p._grid[0][1] = false;
		p._grid[0][2] = false;
		
		p._grid[1][0] = false;
		p._grid[1][1] = false;
		p._grid[1][2] = false;
		
		p._grid[2][0] = false;
		p._grid[2][1] = false;
		p._grid[2][2] = false;
		
		
		Solver test = new Solver(p);
		test.SolveAndGetSolutionTree();
	}
}
