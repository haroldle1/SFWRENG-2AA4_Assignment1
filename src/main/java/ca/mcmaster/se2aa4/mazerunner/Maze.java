package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Arrays;

public class Maze {
    private char[][] maze;
    private int rows;
    private int cols;
    private int[] entryPoint;
    private int[] exitPoint;

    public Maze(String filePath) {
        parseMaze(filePath);
    }

    public void parseMaze(String filePath) {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath)); // Read the file line by line
            String line;

            while ((line = reader.readLine()) != null) {
                // If the line is empty, treat it as a row of 'PASS'
                if (line.trim().isEmpty()) {
                    lines.add(" ".repeat(cols)); // Add a row of spaces equivalent to the number of columns
                } else {
                    lines.add(line);
                }
            }
            reader.close();

            // Give the maze 2D list data structure
            rows = lines.size(); // line is a list of rows of line --> see how many rows it has in this list
            cols = lines.get(0).length(); // line[0] = line.get(0)
            maze = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                maze[i] = lines.get(i).toCharArray();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the maze file: " + e.getMessage());
        }
    }

    public void getEntry() {
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
        System.out.println("Entry Point: " + Arrays.toString(entryPoint));
        System.out.println("Exit Point: " + Arrays.toString(exitPoint));
    }

    // Print the maze for debugging
    public void printMaze() {
        for (char[] row : maze) {
            for (char cell : row) {
                if (cell == '#') {
                    System.out.print("WALL ");
                } else {
                    System.out.print("PASS ");
                }
            }
            System.out.println();
        }
    }

    // Getters
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
