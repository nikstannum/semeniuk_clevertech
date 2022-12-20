package by.clevertech.data.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.clevertech.data.connection.DataSource;
import by.clevertech.data.entity.DiscountCard;
import by.clevertech.data.repository.CardRepository;
import lombok.RequiredArgsConstructor;

/**
 * Implements {@link CardRepository}
 * <p>
 * Class for data access {@link DiscountCard}.
 * <p>
 * Database access is provided based on the implementation Java JDBC API
 * 
 * @author Nikita Semeniuk
 *
 */
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {
    private static final String COL_DISCOUNT_SIZE = "discount_size";
    private static final String COL_CARD_ID = "card_id";
    private static final String FIND_BY_ID = "SELECT c.card_id, c.discount_size FROM discount_card c WHERE c.card_id = ?";
    private static final String FIND_ALL = "SELECT c.card_id, c.discount_size FROM discount_card c";
    private static final String INSERT = "INSERT into discount_card (discount_size) VALUES (?)";
    private static final String UPDATE = "UPDATE discount_card SET discount_size = ? WHERE card_id = ?";
    private static final String DELETE = "DELETE FROM discount_card WHERE card_id = ?";

    private final DataSource dataSource;

    @Override
    public Optional<DiscountCard> create(DiscountCard entity) {
        try (Connection connection = dataSource.getFreeConnections();
                PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setBigDecimal(1, entity.getDiscountSize());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                Long id = keys.getLong(COL_CARD_ID);
                return findById(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DiscountCard> findById(Long id) {
        try (Connection connection = dataSource.getFreeConnections();
                PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(processCard(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Optional<DiscountCard> update(DiscountCard entity) {
        try (Connection connection = dataSource.getFreeConnections();
                PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setBigDecimal(1, entity.getDiscountSize());
            statement.setLong(2, entity.getCardId());
            statement.executeUpdate();
            return findById(entity.getCardId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    private DiscountCard processCard(ResultSet resultSet) throws SQLException {
        DiscountCard card = new DiscountCard();
        card.setCardId(resultSet.getLong(COL_CARD_ID));
        card.setDiscountSize(resultSet.getBigDecimal(COL_DISCOUNT_SIZE));
        return card;
    }
}
