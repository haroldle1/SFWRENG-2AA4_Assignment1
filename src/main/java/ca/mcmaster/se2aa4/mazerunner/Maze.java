package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public class Maze {
    private char[][] maze;
    private int rows;
    private int cols;
    private int[] entryPoint;
    private int[] exitPoint;

    public Maze(String filePath) {
        // Use MazeReader to load maze
        MazeReader reader = new MazeReader(filePath);
        this.rows = reader.getRows();
        this.cols = reader.getCols();
        this.maze = reader.getMaze();

        findEntryAndExit();
    }

    private void findEntryAndExit() {
        for (int i = 0; i < rows; i++) {
            if (maze[i][0] == ' ') {
                entryPoint = new int[]{i, 0};
            }
            if (maze[i][cols - 1] == ' ') {
                exitPoint = new int[]{i, cols - 1};
            }
        }
        if (entryPoint == null || exitPoint == null) {
            throw new RuntimeException("You must have at least 1 entry and 1 exit point");
        }
        //System.out.println("Entry Point: " + Arrays.toString(entryPoint));
        //System.out.println("Exit Point: " + Arrays.toString(exitPoint));
    }

    public char[][] getMaze() {
        return maze;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[] getExitPoint() {
        return exitPoint;
    }

    public int[] getEntryPoint() {
        return entryPoint;
    }
}
