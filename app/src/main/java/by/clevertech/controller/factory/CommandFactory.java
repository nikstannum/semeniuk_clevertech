package by.clevertech.controller.factory;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.controller.command.Command;
import by.clevertech.controller.command.impl.CliCommand;
import by.clevertech.controller.command.impl.FileCommand;
import by.clevertech.service.CheckService;
import by.clevertech.service.factory.SerializerFactory;
import by.clevertech.service.factory.ServiceFactory;

/**
 * Factory to receive the {@link Command}
 * 
 * @author Nikita Semeniuk
 *
 */
public class CommandFactory {

    private static final CommandFactory INSTANCE = new CommandFactory();
    private final Map<String, Command> commands;

    private CommandFactory() {
        commands = new HashMap<>();
        commands.put("file", new FileCommand(ServiceFactory.INSTANCE.getService(CheckService.class),
                SerializerFactory.INSTANCE.getSerializer("string")));
        commands.put("cli", new CliCommand(ServiceFactory.INSTANCE.getService(CheckService.class),
                SerializerFactory.INSTANCE.getSerializer("string")));
    }

    /**
     * main factory method
     * 
     * @param <T>     return type
     * @param command the non-null command
     * @return class that implements the interface {@link Command}
     */
    public <T> T getCommand(String command) {
        return (T) commands.get(command);
    }

    /**
     * @return this {@link CommandFactory}
     */
    public static CommandFactory getINSTANCE() {
        return INSTANCE;
    }

}
