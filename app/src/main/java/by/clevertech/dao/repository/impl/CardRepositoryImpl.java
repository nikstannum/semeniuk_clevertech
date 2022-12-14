package by.clevertech.dao.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.clevertech.dao.connection.DataSource;
import by.clevertech.dao.entity.DiscountCard;
import by.clevertech.dao.repository.CardRepository;
import by.clevertech.exception.AccessException;
import by.clevertech.exception.EntityCreateException;
import by.clevertech.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {
	private static final String FIND_BY_ID = "SELECT c.card_id, c.discount_size FROM discount_card c WHERE c.card_id = ?";
	private static final String FIND_ALL = "SELECT c.card_id, c.discount_size FROM discount_card c";
	private static final String INSERT = "INSERT into discount_card (discount_size) VALUES (?)";
	private static final String UPDATE = "UPDATE discount_card SET discount_size = ? WHERE card_id = ?";
	private static final String DELETE = "DELETE FROM discount_card WHERE card_id = ?";

	private final DataSource dataSource;

	@Override
	public DiscountCard create(DiscountCard entity) {
		try (Connection connection = dataSource.getFreeConnections();
						PreparedStatement statement = connection.prepareStatement(INSERT,
										Statement.RETURN_GENERATED_KEYS)) {
			statement.setBigDecimal(1, entity.getDiscountSize());
			statement.executeUpdate();
			ResultSet keys = statement.getGeneratedKeys();
			if (keys.next()) {
				Long id = keys.getLong("card_id");
				return findById(id);
			}
		} catch (SQLException e) {
			throw new AccessException(e.getMessage(), e.getCause());
		}
		throw new EntityCreateException("couldn't create new card");
	}

	@Override
	public DiscountCard findById(Long id) {
		try (Connection connection = dataSource.getFreeConnections();
						PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return processCard(resultSet);
			}
		} catch (SQLException e) {
			throw new AccessException(e.getMessage(), e.getCause());
		}
		throw new EntityNotFoundException("card with id = " + id + " wasn't found");
	}

	@Override
	public List<DiscountCard> findAll() {
		List<DiscountCard> list = new ArrayList<>();
		try (Connection connection = dataSource.getFreeConnections();
						Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(FIND_ALL);
			while (resultSet.next()) {
				list.add(processCard(resultSet));
			}
			return list;
		} catch (SQLException e) {
			throw new AccessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public DiscountCard update(DiscountCard entity) {
		try (Connection connection = dataSource.getFreeConnections();
						PreparedStatement statement = connection.prepareStatement(UPDATE)) {
			statement.setBigDecimal(1, entity.getDiscountSize());
			statement.setLong(2, entity.getCardId());
			statement.executeUpdate();
			return findById(entity.getCardId());
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

	private DiscountCard processCard(ResultSet resultSet) throws SQLException {
		DiscountCard card = new DiscountCard();
		card.setCardId(resultSet.getLong("card_id"));
		card.setDiscountSize(resultSet.getBigDecimal("discount_size"));
		return card;
	}
}
