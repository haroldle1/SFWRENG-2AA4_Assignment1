package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager; //For Log4j
import org.apache.logging.log4j.Logger;     //For Log4j

import org.apache.commons.cli.*; //Import Apache CLI

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner"); 

        //Define CLI options
        Options options = new Options();
        options.addOption("i", "input", true, "Input maze file (required)");

        CommandLineParser parser  = new DefaultParser();
        try {
            //Parse the command line argument
            CommandLine cmd = parser.parse(options, args);

            //Check if the -i flag is provided 
            if (cmd.hasOption("i")){
                String filePath = cmd.getOptionValue("i");
                logger.info("Reading the maze from file: "+ filePath);

                //Read the maze file
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    while ((line = reader.readLine()) != null){
                        for (int idx = 0; idx < line.length(); idx++){
                            if (line.charAt(idx) == '#'){
                                System.out.print("WALL ");
                            } else if (line.charAt(idx) == ' '){
                                System.out.print("PASS ");
                            }
                        }
                        System.out.print(System.lineSeparator());
                    }
                } catch (Exception e){
                    logger.error("Error reading the maze file.", e);
                }
            } else {
                logger.error("No input file provided. Use the -i falg to specify the maze file.");
                System.out.println("Usage: java -cp <jar> ca.mcmaster.se2aa4.mazerunner.Main -i <maze file>");
            }
        } catch (ParseException e){
            logger.error("Failed to parse command line arguments.", e);
        }

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of Maze Runner");
    }
}