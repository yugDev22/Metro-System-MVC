package com.metro.model.persistence;

import java.util.ArrayList;

import com.metro.bean.MetroCard;

public interface MetroCardDao {
	public MetroCard searchCardById(Integer cardId);
	public int issueNewCard(MetroCard card);
	public MetroCard getLastCard();
	public int RefundCard(Integer cardId);
	public ArrayList<MetroCard> getAllMetroCards(Integer passengerId);
	public int updateBalance(Integer cardId, double balance);
}
