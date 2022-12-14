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
import org.springframework.stereotype.Repository;

import by.clevertech.dao.entity.DiscountCard;
import by.clevertech.dao.repository.CardRepository;
import by.clevertech.exception.EntityCreateException;
import by.clevertech.exception.EntityDeleteException;
import by.clevertech.exception.EntityUpdateException;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {
	private static final String FIND_BY_ID = "SELECT c.card_id, c.discount_size FROM discount_card c WHERE c.card_id = :cardId";
	private static final String FIND_ALL = "SELECT c.card_id, c.discount_size FROM discount_card c";
	private static final String INSERT = "INSERT into discount_card (discount_size) VALUES (:discountSize)";
	private static final String UPDATE = "UPDATE discount_card SET discount_size = :discountSize WHERE card_id = :cardId";
	private static final String DELETE = "DELETE FROM discount_card WHERE card_id = :cardId";

	private final NamedParameterJdbcTemplate template;

	@Override
	public DiscountCard create(DiscountCard entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("discount_size", entity.getDiscountSize());
		template.update(INSERT, params, keyHolder);
		Number key = keyHolder.getKey();
		if (key == null) {
			throw new EntityCreateException("couldn't create new card");
		}
		Long id = key.longValue();
		return findById(id);
	}

	@Override
	public DiscountCard findById(Long id) {
		Map<String, Object> params = new HashMap<>();
		params.put("cardId", id);
		return template.queryForObject(FIND_BY_ID, params, this::mapRow);
	}

	@Override
	public List<DiscountCard> findAll() {
		return template.query(FIND_ALL, this::mapRow);
	}

	@Override
	public DiscountCard update(DiscountCard entity) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("discountSize", entity.getDiscountSize());
		params.addValue("cardId", entity.getCardId());
		int rowUpdated = template.update(UPDATE, params);
		if (rowUpdated == 0) {
			throw new EntityUpdateException("couldn't update card with id = " + entity.getCardId());
		}
		return findById(entity.getCardId());
	}

	@Override
	public boolean delete(Long id) {
		Map<String, Object> params = new HashMap<>();
		params.put("cardId", id);
		int rowUpdated = template.update(DELETE, params);
		if (rowUpdated == 0) {
			throw new EntityDeleteException("couldn't delete card with id = " + id);
		}
		return true;
	}

	private DiscountCard mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		DiscountCard card = new DiscountCard();
		card.setCardId(resultSet.getLong("card_id"));
		card.setDiscountSize(resultSet.getBigDecimal("discount_size"));
		return card;
	}
}
