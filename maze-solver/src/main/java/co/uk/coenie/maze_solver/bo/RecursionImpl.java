package co.uk.coenie.maze_solver.bo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.uk.coenie.maze_solver.model.Maze;
import co.uk.coenie.maze_solver.model.MazePoint;

@Component("recursionSolver")
public class RecursionImpl implements MazeSolver {

	@Autowired
	private Maze maze;

	private boolean[][] wasHere;
	private boolean[][] correctPath;

	@Override
	public void solve(String fileLocation) throws IOException {
		maze.loadFile(fileLocation);

		wasHere = new boolean[maze.getWidth()][maze.getHeight()];
		correctPath = new boolean[maze.getWidth()][maze.getHeight()];		

		for (int row = 0; row < maze.getHeight(); row++)  
			// Sets boolean Arrays to default values
			for (int col = 0; col < maze.getWidth(); col++){
				wasHere[col][row] = false;
				correctPath[col][row] = false;
			}

		String solution = createSolutionString(recursiveSolve(maze.getStart().getX(), maze.getStart().getY()));
		System.out.println(solution);

	}


	/**
	 * Creates a String representation of the solution which can be printed to required output.
	 * 
	 * @param solvable
	 * @return
	 */
	private String createSolutionString(boolean solvable) {
		if (solvable) {
			MazePoint startPos = maze.getStart();
			MazePoint endPos = maze.getEndLocation();
			char[][] solvedMaze = maze.getMaze().clone();
			
			// Adds taken path from correctPath to solvedMaze
			// X Y coordinates swapped between correctPath and maze
			// reading/writing runs from top to bottom (Y) then left to right (X)
			for (int y = 0; y < maze.getHeight(); y++) {
				for (int x = 0; x < maze.getWidth(); x++) {
					solvedMaze[y][x] = correctPath[x][y]?'X':solvedMaze[y][x];	
				}
			}
			
			solvedMaze[startPos.getY()][startPos.getX()] = 'S';
			solvedMaze[endPos.getY()][endPos.getX()] = 'E';
			
			StringBuilder stringB = new StringBuilder();
			
			for (char[] row:solvedMaze) {
				for (char c:row) {
					stringB.append(c=='0'?' ':c=='1'?'#':c);
				}
				stringB.append("\n");
			}
			
			return stringB.toString();
		} else {
			return "File ["+maze.getName() + "] is not solvable.";
		}
	}


	public boolean recursiveSolve(int x, int y) {
		if (x == maze.getEndLocation().getX() && y == maze.getEndLocation().getY()) return true; // If you reached the end
		if (!maze.isTraversable(x, y) || wasHere[x][y]) return false;  
		// If you are on a wall or already were here
		wasHere[x][y] = true;
		if (x != 0) // Checks if not on left edge
			if (recursiveSolve(x-1, y)) { // Recalls method one to the left
				correctPath[x][y] = true; // Sets that path value to true;
				return true;
			}
		if (x != maze.getWidth() - 1) // Checks if not on right edge
			if (recursiveSolve(x+1, y)) { // Recalls method one to the right
				correctPath[x][y] = true;
				return true;
			}
		if (y != 0)  // Checks if not on top edge
			if (recursiveSolve(x, y-1)) { // Recalls method one up
				correctPath[x][y] = true;
				return true;
			}
		if (y != maze.getHeight()- 1) // Checks if not on bottom edge
			if (recursiveSolve(x, y+1)) { // Recalls method one down
				correctPath[x][y] = true;
				return true;
			}
		return false;
	}

}
