package by.clevertech.controller;

import by.clevertech.controller.command.Command;
import by.clevertech.controller.command.ExcHandler;
import by.clevertech.controller.factory.CommandFactory;

/**
 * A class that determines where to send data for processing
 * 
 * @author Nikita Semeniuk
 *
 */
public class Controller {
    /**
     * parses command-line arguments and decides whether there is a path to a file
     * or a set of ready-made input parameters
     * 
     * @param args of command line
     */
    public void process(String[] args) {
        String command;
        command = args[0].contains("/") ? "file" : "cli";
        CommandFactory factory = CommandFactory.getINSTANCE();
        Command commandInstance = factory.getCommand(command);
        try {
            commandInstance.execute(args);
        } catch (Exception e) {
            ExcHandler.INSTANCE.handle(e);

        }
    }

}
