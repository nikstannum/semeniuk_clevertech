package by.clevertech.data.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import lombok.extern.log4j.Log4j2;

/**
 * Implements {@link AutoCloseable}
 * <p>
 * This class provides a source of database connections.
 * 
 * @author Nikita Semeniuk
 *
 */
@Log4j2
public class DataSource implements AutoCloseable {

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;
    public final int poolSize = Integer.parseInt(ConfigManager.INSTANCE.getProperty("db.pool_size"));
    public static final DataSource INSTANCE = new DataSource();

    private DataSource() {
        freeConnections = new LinkedBlockingDeque<>(poolSize);
        givenAwayConnections = new ArrayDeque<>();
        init();
    }

    /**
     * grants an idle connection from the connection pool
     * 
     * @return {@link ProxyConnection}
     */
    public ProxyConnection getFreeConnections() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
        return connection;
    }

    /**
     * Fills the connection pool.
     * <p>
     * The pool size is defined in the properties file
     */
    private void init() {
        ConfigManager props = ConfigManager.INSTANCE;
        try {
            Class.forName(props.getProperty("db.driver"));
            Connection realConnection = DriverManager.getConnection(props.getProperty("db.url"),
                    props.getProperty("db.user"), props.getProperty("db.password"));
            for (int i = 0; i < poolSize; i++) {
                freeConnections.add(new ProxyConnection(realConnection));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e.getCause());
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    /**
     * returns the connection to the pool
     * 
     * @param connection the used connection
     */
    public void releaseConnection(ProxyConnection connection) {
        givenAwayConnections.remove(connection);
        freeConnections.offer(connection);
    }

    /**
     * destroys the pool
     */
    private void destroyPoll() {
        try {
            for (int i = 0; i < poolSize; i++) {
                freeConnections.take().reallyClose();
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    /**
     * closes the data source
     */
    @Override
    public void close() throws Exception {
        destroyPoll();
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e.getMessage(), e.getCause());
            }
        });
    }
}
