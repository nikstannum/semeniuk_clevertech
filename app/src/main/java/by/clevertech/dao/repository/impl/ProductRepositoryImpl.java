package by.clevertech.dao.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import by.clevertech.dao.entity.Product;
import by.clevertech.dao.repository.ProductRepository;
import by.clevertech.exception.EntityCreateException;
import by.clevertech.exception.EntityDeleteException;
import by.clevertech.exception.EntityUpdateException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
	private static final String INSERT = "INSERT into ptoducts (name, price, discount) VALUES (:name, :price, :discount)";
	private static final String FIND_BY_ID = "SELECT p.product_id, p.name, p.price, p.discount FROM products p WHERE product_id=:id";
	private static final String FIND_ALL = "SELECT p.product_id, p.name, p.price, p.discount FROM products p";
	private static final String UPDATE = "UPDATE ptoducts SET name = :name, price = :price, discount = :discount WHERE product_id = :id";
	private static final String DELETE = "DELETE FROM products WHERE product_id = :id";

	private final NamedParameterJdbcTemplate template;

	@Override
	public Product create(Product entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", entity.getName());
		params.addValue("price", entity.getPrice());
		params.addValue("discount", entity.isDiscount());
		template.update(INSERT, params, keyHolder);
		Number key = keyHolder.getKey();
		if (key == null) {
			throw new EntityCreateException("couldn't create new product");
		}
		Long id = key.longValue();
		return findById(id);
	}

	@Override
	public Product findById(Long id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return template.queryForObject(FIND_BY_ID, params, this::mapRow);
	}

	@Override
	public List<Product> findAll() {
		return template.query(FIND_ALL, this::mapRow);
	}

	@Override
	public Product update(Product entity) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", entity.getName());
		params.addValue("price", entity.getPrice());
		params.addValue("discount", entity.isDiscount());
		params.addValue("id", entity.getId());
		int rowUpdated = template.update(UPDATE, params);
		if (rowUpdated == 0) {
			throw new EntityUpdateException("couldn't update product with id = " + entity.getId());
		}
		return findById(entity.getId());
	}

	@Override
	public boolean delete(Long id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		int rowUpdated = template.update(DELETE, params);
		if (rowUpdated == 0) {
			throw new EntityDeleteException("couldn't delete product with id = " + id);
		}
		return true;
	}

	private Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Product product = new Product();
		product.setId(resultSet.getLong("product_id"));
		product.setName(resultSet.getString("name"));
		product.setPrice(resultSet.getBigDecimal("price"));
		product.setDiscount(resultSet.getBoolean("discount"));
		return product;
	}

}
