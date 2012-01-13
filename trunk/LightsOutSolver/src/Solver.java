import java.util.ArrayList;
import java.util.Stack;


public class Solver {
	
	Tree<Puzzle> _head;
	
	public Solver()
	{
		Puzzle seed = new Puzzle(3,3);
		seed._grid[0][0] = true;
		seed._grid[0][1] = false;
		seed._grid[0][2] = false;
		
		seed._grid[1][0] = false;
		seed._grid[1][1] = true;
		seed._grid[1][2] = false;
		
		seed._grid[2][0] = false;
		seed._grid[2][1] = false;
		seed._grid[2][2] = true;
		
		_head = new Tree<Puzzle>(seed);
	}
	
	public Tree<Puzzle> GetSolutionNode()
	{
		for(int y= 0; y < 3; y++)
		{
			for(int x = 0; x < 3; x++)
			{
				Puzzle newLeaf = new Puzzle(_head.getHead().PlayCoord(x, y));
				_head.addLeaf(newLeaf);
				if(newLeaf.IsPuzzleDone())
				{
					System.out.println("Finished Puzzle");
					return _head.getLatestChild();
				}	
			}
		}
		ArrayList<Tree<Puzzle>> curSubs = (ArrayList<Tree<Puzzle>>) _head.getSubTrees();
		
		while(true)
		{
			ArrayList<Tree<Puzzle>> agregateNewSubs = new ArrayList<Tree<Puzzle>>();
			for(int i =0; i < curSubs.size(); i++)
			{
				for(int y= 0; y < 3; y++)
				{
					for(int x = 0; x < 3; x++)
					{
						Puzzle newLeaf = new Puzzle(curSubs.get(i).getHead().PlayCoord(x, y));
						curSubs.get(i).addLeaf(newLeaf);
						if(newLeaf.IsPuzzleDone())
						{
							System.out.println("Finished Puzzle");
							return curSubs.get(i).getLatestChild();
						}	
					}
				}
				agregateNewSubs.addAll(curSubs.get(i).getSubTrees());
			}
		}
	}
	
	public void PrintSolution()
	{
		Tree<Puzzle> end = GetSolutionNode();
		
		Stack<Puzzle> finalSolution = new Stack<Puzzle>();
		finalSolution.add(end.getHead());
		while(end.getParent() != null)
		{
			end = end.getParent();
			finalSolution.add(end.getHead());
		}
		while(!finalSolution.isEmpty())
		{
			finalSolution.peek().PrintPuzzle();
			finalSolution.pop();
		}
	}
}
