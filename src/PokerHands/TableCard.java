package PokerHands;

import java.awt.Color;
import java.awt.Graphics;

public class TableCard {
	private int _y;
	private int _x;
	private Card firstCard;

	public TableCard(int x, int y, Card card) {
		_x = x;
		_y = y;
		firstCard = card;
	}
	
	public void addCard(Card card){
		firstCard = card;
	}
	
	public void faceUp(){
		if(!firstCard.isFaceUp()){
			firstCard.flip();
		}
	}
	
	public void display(final Graphics g) {
		g.setColor(Color.black);
		if (firstCard == null) {
			g.drawRect(_x, _y, Card.width, Card.height);
		} else {
			firstCard.draw(g, _x, _y);
		}
	}
}
