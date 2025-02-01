package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeReader {
    private int rows;
    private int cols;
    private char[][] maze;

    public MazeReader(String filePath) {
        parseMaze(filePath);
    }

    private void parseMaze(String filePath) {
        try {
            System.out.println("**** Reading the maze from file " + filePath);
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.println();
            }
            reader.close();

            // maze dimensions
            rows = lines.size();
            cols = lines.get(0).length();
            maze = new char[rows][cols];

            //convert to 2D char array
            for (int i = 0; i < rows; i++) {
                maze[i] = lines.get(i).toCharArray();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the maze file: " + e.getMessage());
        }
    }

    //getters for the maze data
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public char[][] getMaze() {
        return maze;
    }
}
