public class Puzzle {
	public boolean[][] _grid;
	private int _height, _width;
	private int _comX, _comY;
	
	public Puzzle(int width, int height)
	{
		_height = height;
		_width = width;
		_grid = new boolean[_height][_width];
		for(int y = 0; y < _height; y++)
		{
			for(int x = 0; x < _width; x++)
			{
				_grid[y][x] = false;
			}
		}
	}
	
	public Puzzle(Puzzle toCopy)
	{
		_height = toCopy.GetHeight();
		_width = toCopy.GetWidth();
		_grid = new boolean[_height][_width];
		
		for(int y = 0; y < _height; y++)
		{
			for(int x = 0; x < _width; x++)
			{
				_grid[y][x] = toCopy._grid[y][x];
			}
		}
	}
	
	public int GetHeight()
	{
		return _height;
	}
	
	public int GetWidth()
	{
		return _width;
	}

	public Puzzle PlayCoord(int x, int y)
	{
		if( x >=_width || x < 0 ){
			System.out.println("X coord was not in bounds of puzzle");
			return null;
		}
		
		if( y >=_height || y < 0 ){
			System.out.println("Y coord was not in bounds of puzzle");
			return null;
		}
		
		Puzzle newPuz = new Puzzle(this);
		newPuz.ToggleCoordAndNeighbors(x, y);
		return newPuz;
	}
	
	public void ToggleCoordAndNeighbors(int x, int y)
	{
		_grid[y][x] = !_grid[y][x];
		if(y < _height-1)
			_grid[y+1][x] = !_grid[y+1][x];
		if(x < _width-1)
			_grid[y][x+1] = !_grid[y][x+1];
		if(y > 0)
			_grid[y-1][x] = !_grid[y-1][x];
		if(x > 0)
			_grid[y][x-1] = !_grid[y][x-1];
	}
	
	public boolean IsPuzzleDone()
	{
		int trueCount = 0;
		for(int y = 0; y < _height; y++)
		{
			for(int x = 0; x < _width; x++)
			{
				if(_grid[y][x])
					trueCount++;
			}
		}
		return (trueCount == _height*_width);
	}
	
	public long GetPuzzleSum()
	{
		long retSum = 0;
		for(int y = 0; y < _height; y++)
		{
			for(int x = 0; x < _width; x++)
			{
				if(_grid[y][x])
					retSum += Math.pow(2, x + y*_height);
			}
		}
		return retSum;
	}
	
	public void SetCommand(int x, int y)
	{
		_comX = x;
		_comY = y;
	}
	
	public String GetCommand()
	{
		return String.format("x = %d, y = %d", _comX, _comY);
	}
	
	public void PrintPuzzle()
	{
		System.out.println("-----------------------------");
		for(int y = 0; y < _height; y++)
		{
			for(int x = 0; x < _width; x++)
			{
				if(_grid[y][x])
					System.out.print('1');
				else
					System.out.print('0');
			}
			System.out.println();
		}
		System.out.println("-----------------------------");
		System.out.println("                             ");
	}
}
