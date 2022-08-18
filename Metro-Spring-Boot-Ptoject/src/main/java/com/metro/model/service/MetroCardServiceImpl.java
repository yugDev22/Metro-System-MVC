package com.metro.model.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.metro.bean.MetroCard;
import com.metro.model.persistence.MetroCardDao;
import com.metro.model.persistence.MetroCardDaoImpl;

@Service
public class MetroCardServiceImpl implements MetroCardService {

	@Autowired
	private MetroCardDao metroCardDao;

	@Override
	public MetroCard searchMetroCardById(Integer cardId) {
		return metroCardDao.searchCardById(cardId);

	}

	@Override
	public MetroCard issueNewMetroCard(MetroCard card) {
		if(searchMetroCardById(card.getCardId())==null) {
			if(card.getBalance()>=20) {
				card.setCardId(getCardId());
				if(metroCardDao.issueNewCard(card)>0) {
					return card;
				}
			}
		}
		return null;
	}

	@Override
	public Double checkCardBalance(Integer id) {
		MetroCard card = searchMetroCardById(id);
		if(card!=null) {
			return card.getBalance();
		}
		return null;
	}

	@Override
	public Integer AddCardBalance(Integer id, Double balance) {
		MetroCard card = searchMetroCardById(id);
		if(card!=null && balance>0) {
			if(metroCardDao.updateBalance(id,card.getBalance()+balance)>0)
				return 1;
		}
		return null;

	}

	@Override
	public MetroCard RefundMetroCard(Integer cardId) {
		if(searchMetroCardById(cardId)!=null) {
			if(metroCardDao.RefundCard(cardId)>0) {
				return searchMetroCardById(cardId);
			}
		}
		return null;

	}

	@Override
	public Integer getCardId() {
		MetroCard card = metroCardDao.getLastCard();
		if(card!=null) {
			return card.getCardId()+1;
		}
		return 1001;
	}

	@Override
	public ArrayList<MetroCard> getAllCards(Integer passengerId) {
		return metroCardDao.getAllMetroCards(passengerId);
	}

	@Override
	public int deductBalance(Integer cardId, double balance) {
		MetroCard card = searchMetroCardById(cardId);
		balance = card.getBalance()-balance;
		return metroCardDao.updateBalance(cardId, balance);
	}
	

}
