package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class PathValidator {
    private SolvingInterface solution;
    private static final Logger logger = LogManager.getLogger();

    public PathValidator(SolvingInterface solution) {
        this.solution = solution;
    }

    private boolean isCano(String providedPath){
        for (int i = 0; i < providedPath.length(); i++){
             if (Character.isDigit(providedPath.charAt(i))){
                return false;
            }
        }
        return true;
    }   
    public boolean validatePath(String providedPath) {
        if (providedPath == null || providedPath.isEmpty()) {
            logger.error("Path cannot be empty.");
            return false;
        }
        String correctPath = solution.solveMaze(); 

        if (isCano(providedPath)){    //check if provided path is canonical or factorized
            providedPath = solution.factorizePath(providedPath);  
        }
        if (providedPath.equals(correctPath)) {
            return true; 
        } else {
            return false; 
        }
    }
}
