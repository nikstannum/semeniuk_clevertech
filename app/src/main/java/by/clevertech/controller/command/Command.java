package by.clevertech.controller.command;

/**
 * The entry point for processing data in an application is provided by a class
 * that implements the interface {@link Command}
 * 
 * @author Nikita Semeniuk
 *
 */
public interface Command {
    /**
     * command execution method
     * 
     * @param args the command line arguments
     */
    public void execute(String[] args);

}
