package by.clevertech.controller;

import by.clevertech.controller.command.Command;
import by.clevertech.controller.factory.CommandFactory;

public class Controller {

    public void process(String[] args) {
        String command;
        if (args.length == 1) {
            command = "file";
        } else {
            command = "cli";
        }
        CommandFactory factory = CommandFactory.getINSTANCE();
        Command commandInstance = factory.getCommand(command);
        commandInstance.execute(args);
    }

}
