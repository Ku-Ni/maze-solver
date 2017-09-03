package co.uk.coenie.maze_solver.bo;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import co.uk.coenie.maze_solver.config.SpringConfig;

public class Test_Full {
	MazeSolver mazeSolver;

	@Before
	public void setUp() throws Exception {
		try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class)){
			mazeSolver = context.getBean(RecursionImpl.class);	
		}
	}


	@Test
	public void test1_SmallMaze() throws IOException {
		String args = "E:\\Development\\Junifer maze\\small.txt";

		try {
			mazeSolver.solve(args);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	@Test
	public void test2_SmallMaze() throws Exception {
		String args = "E:\\Development\\Junifer maze\\input.txt";

		try {
			mazeSolver.solve(args);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	@Test
	public void test3_MediumMaze() throws Exception {
		String args = "E:\\Development\\Junifer maze\\medium_input.txt";

		try {
			mazeSolver.solve(args);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	@Test
	public void test4_LargeMaze() throws Exception {
		String args = "E:\\Development\\Junifer maze\\large_input.txt";

		try {
			mazeSolver.solve(args);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	@Test
	public void test5_NotSolvable() throws Exception {
		String args = "E:\\Development\\Junifer maze\\small_not_solvable.txt";

		try {
			mazeSolver.solve(args);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
