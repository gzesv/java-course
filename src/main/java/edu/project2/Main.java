package edu.project2;

import edu.project2.generators.BinaryTreeGenerator;
import edu.project2.generators.Generator;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.render.Renderer;
import edu.project2.render.SimpleRenderer;
import edu.project2.solvers.DepthFirstSearchSolver;
import edu.project2.solvers.Solver;
import java.util.List;

@SuppressWarnings("HideUtilityClassConstructor")
public class Main {
    @SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
    public static void main(String[] args) {
        Generator generator = new BinaryTreeGenerator();
        Maze maze = generator.generate(51, 51);

        Renderer simpleRenderer = new SimpleRenderer();
        String renderMaze = simpleRenderer.render(maze);

        System.out.println(renderMaze);

        System.out.println();

        Solver solver = new DepthFirstSearchSolver();

        List<Coordinate> path = solver.solve(maze, new Coordinate(1, 1), new Coordinate(49, 49));

        String renderMazeWithPath = simpleRenderer.render(maze, path);

        System.out.println(renderMazeWithPath);
    }

}
