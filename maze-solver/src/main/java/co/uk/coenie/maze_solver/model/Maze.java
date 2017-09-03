package co.uk.coenie.maze_solver.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Maze {
	private String name;
	private int width;
	private int height;
	private MazePoint startPosition;
	private MazePoint endPosition;
	private char[][] maze;
	
	
	public void loadFile(String fileLocation) throws IOException{
		File file = new File(fileLocation);
		List<String> lines = new ArrayList<>();
		name = file.getName();
		
		try(BufferedReader bReader = new BufferedReader(new FileReader(file))){
			String readLine = "";
			
			while ((readLine = bReader.readLine()) != null) {
	            lines.add(readLine);
	        }
		}

		setSize(StringUtils.split(lines.remove(0), " "));
		setStart(StringUtils.split(lines.remove(0), " "));
		setEnd(StringUtils.split(lines.remove(0), " "));
		
		maze = new char[getWidth()][getHeight()];
		char[][] tmp = new char[getHeight()][getWidth()];  // need to flip input
		
		for (int row = 0; row<getHeight(); row++) {
			String rowStr = StringUtils.replace(lines.get(row), " ","");
			tmp[row] = rowStr.toCharArray();
		}
		
		maze=tmp;		
				
	}
	
	public void setSize(String[] sizeArr) {
		this.width = Integer.valueOf(sizeArr[0]);
		this.height = Integer.valueOf(sizeArr[1]);
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public MazePoint getStart() {
		return startPosition;
	}
	public void setStart(String[] startArr) {
		startPosition = new MazePoint(Integer.valueOf(startArr[0]), Integer.valueOf(startArr[1]));
	}
	
	
	public MazePoint getEndLocation() {
		return endPosition;
	}
	public void setEnd(String[] endArr) {
		endPosition = new MazePoint(Integer.valueOf(endArr[0]), Integer.valueOf(endArr[1]));
	}
	
	
	public char[][] getMaze() {
		return maze;
	}
//	public void setMaze(char[][] maze) {
//		this.maze = maze;
//	}

	public boolean isTraversable(int x, int y) {
		return maze[y][x] == '0';
	}
	
	public char getLocation(int x, int y) {
		// x and y locations backwards due to way 2 dimensional array is initiated
		return maze[y][x];
	}

	
	public String getName() {
		return name;
	}
	
	
	
}
