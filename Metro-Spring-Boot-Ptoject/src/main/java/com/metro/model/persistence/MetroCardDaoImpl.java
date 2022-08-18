package com.metro.model.persistence;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.metro.bean.MetroCard;
import com.metro.model.persistence.helper.MetroCardRowMapper;

@Repository
public class MetroCardDaoImpl implements MetroCardDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public MetroCard searchCardById(Integer cardId) {

		MetroCard metroCard = null;
		try {
			String query = "SELECT * FROM card where cardID=?";
			metroCard = jdbcTemplate.queryForObject(query, new MetroCardRowMapper(), cardId);
		}catch(EmptyResultDataAccessException ex) {
			return metroCard;
		}

		return metroCard;
	}

	@Override
	public int issueNewCard(MetroCard card) {
		int rows = 0;
		try {
			String query = "INSERT INTO  card(cardId,balance,passengerId) values(?,?,?)";
			rows = jdbcTemplate.update(query,card.getCardId(),card.getBalance(),card.getPassengerId());

		} catch (DataAccessException e) {
			return rows;
		}

		return rows;

	}


	@Override
	public int RefundCard(Integer cardId) {
		int rows = 0;
		try {
			String query = "delete from  card where cardId=?";
			rows = jdbcTemplate.update(query,cardId);

		} catch (DataAccessException e) {
			return rows;
		}

		return rows;
	}

	@Override
	public MetroCard getLastCard() {
		MetroCard card = null;

		try{
			String query = "SELECT * FROM card ORDER BY cardId DESC LIMIT 1";
			card = jdbcTemplate.queryForObject(query, new MetroCardRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return card;
		}

		return card;
	}

	@Override
	public ArrayList<MetroCard> getAllMetroCards(Integer passengerId) {
		List<MetroCard> cardArr = new ArrayList<MetroCard>();
		try{
			String query = "SELECT * FROM card WHERE passengerid = ?";
			cardArr = jdbcTemplate.query(query, new MetroCardRowMapper(),passengerId);
		} catch (EmptyResultDataAccessException e) {
			return (ArrayList<MetroCard>) cardArr;
		}
		return (ArrayList<MetroCard>) cardArr;
	}

	@Override
	public int updateBalance(Integer cardId, double balance) {
		int rows = 0;
		try {
			String query = "UPDATE card SET balance=? WHERE cardId=?";
			rows = jdbcTemplate.update(query,balance,cardId);

		} catch (DataAccessException e) {
			return rows;
		}

		return rows;
	}

}
