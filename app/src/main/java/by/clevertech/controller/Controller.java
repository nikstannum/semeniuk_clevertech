package by.clevertech.controller;

import by.clevertech.controller.command.Command;
import by.clevertech.controller.command.ExcHandler;
import by.clevertech.controller.factory.CommandFactory;

public class Controller {

    public void process(String[] args) {
        String command;
        if (args[0].contains("/")) {
            command = "file";
        } else {
            command = "cli";
        }
        CommandFactory factory = CommandFactory.getINSTANCE();
        Command commandInstance = factory.getCommand(command);
        try {
            commandInstance.execute(args);
        } catch (Exception e) {
            ExcHandler.INSTANCE.handle(e);

        }
    }

}
