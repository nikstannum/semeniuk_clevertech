package by.clevertech.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import by.clevertech.exception.AccessException;

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

	public ProxyConnection getFreeConnections() {
		ProxyConnection connection = null;
		try {
			connection = freeConnections.take();
			givenAwayConnections.offer(connection);
		} catch (InterruptedException e) {
			throw new AccessException(e.getMessage(), e.getCause());
		}
		return connection;
	}

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
			throw new AccessException(e.getMessage(), e.getCause());
		} catch (ClassNotFoundException e) {
			throw new AccessException("Failed to load database driver", e.getCause());
		}
	}

	public void releaseConnection(ProxyConnection connection) {
		givenAwayConnections.remove(connection);
		freeConnections.offer(connection);
	}

	private void destroyPoll() {
		try {
			for (int i = 0; i < poolSize; i++) {
				freeConnections.take().reallyClose();
			}
		} catch (InterruptedException e) {
			throw new AccessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void close() throws Exception {
		destroyPoll();
		DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				throw new AccessException(e.getMessage(), e.getCause());
			}
		});
	}
}