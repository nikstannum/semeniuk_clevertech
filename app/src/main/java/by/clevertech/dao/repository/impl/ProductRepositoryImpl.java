package by.clevertech.dao.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.clevertech.dao.connection.DataSource;
import by.clevertech.dao.entity.Product;
import by.clevertech.dao.repository.ProductRepository;
import by.clevertech.exception.AccessException;
import by.clevertech.exception.EntityCreateException;
import by.clevertech.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
	private static final String INSERT = "INSERT into ptoducts (name, price, discount) VALUES (?, ?, ?)";
	private static final String FIND_BY_ID = "SELECT p.product_id, p.name, p.price, p.discount FROM products p WHERE product_id = ?";
	private static final String FIND_ALL = "SELECT p.product_id, p.name, p.price, p.discount FROM products p";
	private static final String UPDATE = "UPDATE ptoducts SET name = ?, price = ?, discount = ? WHERE product_id = ?";
	private static final String DELETE = "DELETE FROM products WHERE product_id = ?";

	private final DataSource dataSource;

	@Override
	public Product create(Product entity) {
		try (Connection connection = dataSource.getFreeConnections();
						PreparedStatement statement = connection.prepareStatement(INSERT,
										Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, entity.getName());
			statement.setBigDecimal(2, entity.getPrice());
			statement.setBoolean(3, entity.isDiscount());
			statement.executeUpdate();
			ResultSet keys = statement.getGeneratedKeys();
			if (keys.next()) {
				long id = keys.getLong("product_id");
				return findById(id);
			}
		} catch (SQLException e) {
			throw new AccessException(e.getMessage(), e.getCause());
		}
		throw new EntityCreateException("couldn't create new product");
	}

	@Override
	public Product findById(Long id) {
		try (Connection connection = dataSource.getFreeConnections();
						PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return processProduct(resultSet);
			}
		} catch (SQLException e) {
			throw new AccessException(e.getMessage(), e.getCause());
		}
		throw new EntityNotFoundException("product with id = " + id + " wasn't found");
	}

	@Override
	public List<Product> findAll() {
		List<Product> list = new ArrayList<>();
		try (Connection connection = dataSource.getFreeConnections();
						Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(FIND_ALL);
			while (resultSet.next()) {
				list.add(processProduct(resultSet));
			}
			return list;
		} catch (SQLException e) {
			throw new AccessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Product update(Product entity) {
		try (Connection connection = dataSource.getFreeConnections();
						PreparedStatement statement = connection.prepareStatement(UPDATE)) {
			statement.setString(1, entity.getName());
			statement.setBigDecimal(2, entity.getPrice());
			statement.setBoolean(3, entity.isDiscount());
			statement.setLong(4, entity.getId());
			statement.executeUpdate();
			return findById(entity.getId());
		} catch (SQLException e) {
			throw new AccessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean delete(Long id) {
		try (Connection connection = dataSource.getFreeConnections();
						PreparedStatement statement = connection.prepareStatement(DELETE)) {
			statement.setLong(1, id);
			int rowsDelete = statement.executeUpdate();
			return rowsDelete == 1;
		} catch (SQLException e) {
			throw new AccessException(e.getMessage(), e.getCause());
		}
	}

	private Product processProduct(ResultSet resultSet) throws SQLException {
		Product product = new Product();
		product.setId(resultSet.getLong("product_id"));
		product.setName(resultSet.getString("name"));
		product.setPrice(resultSet.getBigDecimal("price"));
		product.setDiscount(resultSet.getBoolean("discount"));
		return product;
	}

}
