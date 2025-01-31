package ca.mcmaster.se2aa4.mazerunner;

public class PathValidator {
    private Maze maze;
    private Solution solution;

    public PathValidator(Maze maze) {
        this.maze = maze;
        this.solution = new Solution(maze);
    }

    public boolean validatePath(String providedPath) {
        // Solve the maze and get the correct factorized path
        String correctPath = solution.solveMaze(); 

        // Compare user-provided factorized path with the correct one
        if (providedPath.equals(correctPath)) {
            return true; // The paths match
        } else {
            return false; // The paths do not match
        }
    }
}
