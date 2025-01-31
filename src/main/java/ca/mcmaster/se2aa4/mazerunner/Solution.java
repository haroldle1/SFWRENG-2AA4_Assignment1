package ca.mcmaster.se2aa4.mazerunner;

public class Solution {
    private Maze maze;
    private char[][] mazeGrid;
    private int[] exitPoint;
    private int[] position;
    private int direction;
    private StringBuilder path;

    public Solution(Maze maze) {
        this.maze = maze;
        this.mazeGrid = maze.getMaze();
        this.position = maze.getEntryPoint(); 
        this.exitPoint = maze.getExitPoint();
        this.direction = 1;  // Assume starting by facing right
        this.path = new StringBuilder();
    }

    private boolean isAtExit() {
        return position[0] == exitPoint[0] && position[1] == exitPoint[1];
    }

    private boolean isPath(int[] pos) {
        int row = pos[0];
        int col = pos[1];
        // Ensure the position is within bounds and is a valid path
        return row >= 0 && row < mazeGrid.length && col >= 0 && col < mazeGrid[0].length && mazeGrid[row][col] == ' ';
    }

    private void moveRightHandRule() {
        // direction: 1 = right, 2 = down, 3 = left, 4 = up (clockwise)
        int x_pos = position[1]; 
        int y_pos = position[0]; 

        while (!isAtExit()) {
            if (direction == 1) { // Facing right
                if (y_pos + 1 < mazeGrid.length && mazeGrid[y_pos + 1][x_pos] == ' ') { 
                    // Turn right and move down
                    path.append('R');
                    direction = 2;
                    path.append('F');
                    y_pos += 1;
                } else if (x_pos + 1 < mazeGrid[0].length && mazeGrid[y_pos][x_pos + 1] == ' ') { 
                    // Move forward (right)
                    path.append('F');
                    x_pos += 1;
                } else { 
                    // Turn left and face up
                    path.append('L');
                    direction = 4;
                }
            } else if (direction == 2) { // Facing down
                if (x_pos - 1 >= 0 && mazeGrid[y_pos][x_pos - 1] == ' ') { 
                    // Turn right and move left
                    path.append('R');
                    direction = 3;
                    path.append('F');
                    x_pos -= 1;
                } else if (y_pos + 1 < mazeGrid.length && mazeGrid[y_pos + 1][x_pos] == ' ') { 
                    // Move forward (down)
                    path.append('F');
                    y_pos += 1;
                } else { 
                    // Turn left and face right
                    path.append('L');
                    direction = 1;
                }
            } else if (direction == 3) { // Facing left
                if (y_pos - 1 >= 0 && mazeGrid[y_pos - 1][x_pos] == ' ') { 
                    // Turn right and move up
                    path.append('R');
                    direction = 4;
                    path.append('F');
                    y_pos -= 1;
                } else if (x_pos - 1 >= 0 && mazeGrid[y_pos][x_pos - 1] == ' ') { 
                    // Move forward (left)
                    path.append('F');
                    x_pos -= 1;
                } else { 
                    // Turn left and face down
                    path.append('L');
                    direction = 2;
                }
            } else if (direction == 4) { // Facing up
                if (x_pos + 1 < mazeGrid[0].length && mazeGrid[y_pos][x_pos + 1] == ' ') { 
                    //Turn right if possible then move forward
                    path.append('R');
                    direction = 1;
                    path.append('F');
                    x_pos += 1;
                } else if (y_pos - 1 >= 0 && mazeGrid[y_pos - 1][x_pos] == ' ') { 
                    // Move forward if path is clear
                    path.append('F');
                    y_pos -= 1;
                } else { 
                    // Turn left when surrounded
                    path.append('L');
                    direction = 3;
                }
            }

            // Update position 
            position[0] = y_pos;
            position[1] = x_pos;
        }
    }

    public String solveMaze() {
        while (!isAtExit()) { // Keep moving until the exit is reached
            moveRightHandRule();
        }
        return path.toString();
    }
}
