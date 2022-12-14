package by.clevertech.dao.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import by.clevertech.dao.entity.DiscountCard;
import by.clevertech.dao.repository.CardRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {
	public static final String FIND_BY_ID = "SELECT c.card_id, c.discount_size FROM discount_card c WHERE c.card_id = ?";

	private final JdbcTemplate jdbcTemplate;

	@Override
	public DiscountCard create(DiscountCard entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiscountCard findById(Long id) {
		return jdbcTemplate.queryForObject(FIND_BY_ID, this::mapRow, id);
		// TODO Auto-generated method stub
	}

	private DiscountCard mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		DiscountCard card = new DiscountCard();
		card.setCardId(resultSet.getLong("card_id"));
		card.setDiscountSize(resultSet.getBigDecimal("discount_size"));
		return card;
	}

	@Override
	public List<DiscountCard> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiscountCard update(DiscountCard entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
