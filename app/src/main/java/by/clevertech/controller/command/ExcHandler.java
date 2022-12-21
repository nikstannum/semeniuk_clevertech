package by.clevertech.controller.command;

import by.clevertech.service.exception.ClevertechException;
import by.clevertech.service.exception.ClientException;
import by.clevertech.service.exception.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;

/**
 * Application error handler
 * 
 * @author Nikita Semeniuk
 *
 */
@Log4j2
public enum ExcHandler {

    INSTANCE;

    private static final String MSG_APP_ERROR = "Application error";
    private static final String MSG_CLIENT_ERROR = "Client error";
    private static final String DEFAULT_MESSAGE = "Unknown error";

    public void handle(Exception e) {
        if (e instanceof EntityNotFoundException) {
            log.error(e);
            System.err.println(MSG_CLIENT_ERROR + " " + e.getMessage());
        } else if (e instanceof ClientException) {
            log.error(e);
            System.err.println(MSG_CLIENT_ERROR + " " + e.getMessage());
        } else if (e instanceof ClevertechException) {
            log.error(e);
            System.err.println(MSG_APP_ERROR + " " + e.getMessage());
        } else {
            log.error(e);
            System.err.println(MSG_APP_ERROR + " " + DEFAULT_MESSAGE);
        }
    }

}
