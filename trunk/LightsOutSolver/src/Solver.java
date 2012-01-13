import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class Solver {
	
	Tree<Puzzle> _head, _end;
	
	
	public Solver(Puzzle p)
	{
		_head = new Tree<Puzzle>(p);
	}
	
	private Tree<Puzzle> GetSolutionNode()
	{
		ArrayList<Long> puzzlesThatHaveBeenLookedAt = new ArrayList<Long>();
		ArrayList<Tree<Puzzle>> curSubs = new ArrayList<Tree<Puzzle>>();
		curSubs.add(_head);

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
						if(Collections.binarySearch(puzzlesThatHaveBeenLookedAt, newLeaf.GetPuzzleSum()) < 0)
						{
							newLeaf.SetCommand(x, y);
							curSubs.get(i).addLeaf(newLeaf);
							puzzlesThatHaveBeenLookedAt.add(newLeaf.GetPuzzleSum());
							Collections.sort(puzzlesThatHaveBeenLookedAt);
							
							if(newLeaf.IsPuzzleDone())
							{
								System.out.println("Finished Puzzle");
								return curSubs.get(i).getLatestChild();
							}	
						}
					}
				}
				agregateNewSubs.addAll(curSubs.get(i).getSubTrees());
			}
			curSubs.clear();
			curSubs.addAll(agregateNewSubs);
		}
	}
	
	public Stack<Puzzle> SolveAndGetSolutionTree()
	{
		_end = GetSolutionNode();
		
		Stack<Puzzle> finalSolution = new Stack<Puzzle>();
		finalSolution.add(_end.getHead());
		while(_end.getParent() != null)
		{
			_end = _end.getParent();
			finalSolution.add(_end.getHead());
		}
		return finalSolution;
	}
	
	public void PrintSolution()
	{	
		Stack<Puzzle> finalSolution = new Stack<Puzzle>();
		finalSolution.add(_end.getHead());
		while(_end.getParent() != null)
		{
			_end = _end.getParent();
			finalSolution.add(_end.getHead());
		}
		while(!finalSolution.isEmpty())
		{
			finalSolution.peek().PrintPuzzle();
			finalSolution.pop();
		}
	}
}
