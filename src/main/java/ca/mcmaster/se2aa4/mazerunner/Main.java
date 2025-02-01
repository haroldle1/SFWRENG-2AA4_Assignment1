package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        // Define CLI options
        Options options = new Options();
        options.addOption("i", "input", true, "Input maze file (required)");
        options.addOption("p", "path", true, "Validate the provided path against the maze.");

        CommandLineParser parser = new DefaultParser();

        try {
            // Parse command-line arguments
            CommandLine cmd = parser.parse(options, args);

            // Check if -i is provided
            if (cmd.hasOption("i")) {
                String filePath = cmd.getOptionValue("i");
                logger.info("Reading the maze from file: " + filePath);

                // Load the maze
                Maze maze = new Maze(filePath);
                SolvingInterface solution = new Solution(maze);

                // If -p flag is provided, validate the given path
                if (cmd.hasOption("p")) {
                    String providedPath = cmd.getOptionValue("p");
                    logger.info("Validating provided path: " + providedPath);

                    // Create a PathValidator instance and validate the path
                    PathValidator validator = new PathValidator(solution);
                    boolean isValid = validator.validatePath(providedPath);

                    if (isValid) {
                        logger.info("The provided path is correct.");
                    } else {
                        logger.info("The provided path is incorrect.");
                    }
                } else {
                    // Solve the maze and print the canonical path
                    String path = solution.solveMaze();
                    System.out.println("Canonical Path: " + path);
                    logger.info("Maze solved successfully!");
                }
            } else {
                // Show correct usage if -i flag is missing
                logger.error("No input file provided. Use the -i flag to specify the maze file.");
                System.out.println("Usage: java -jar mazerunner.jar -i <maze file> [-p <path>]");
            }
        } catch (ParseException e) {
            logger.error("Failed to parse command line arguments.", e);
        }

        logger.info("** End of Maze Runner");
    }
}
