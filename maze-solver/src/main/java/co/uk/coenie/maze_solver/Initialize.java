package co.uk.coenie.maze_solver;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import co.uk.coenie.maze_solver.bo.MazeSolver;
import co.uk.coenie.maze_solver.config.SpringConfig;

public class Initialize {

	public static void main(String[] args) {
		int status = 0;
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class)){
			context.getBean(MazeSolver.class).solve(args[0]);
		} catch (IOException e) {
			e.printStackTrace();
			status = 1;
		}
		
		System.exit(status);
	}
}
