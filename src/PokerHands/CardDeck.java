package PokerHands;

import java.awt.Color;
import java.awt.Graphics;

public class CardDeck {
	
	protected int x;
	protected int y;
	
	private Card firstCard;
	
	CardDeck(int x, int y){
				this.x = x;
		this.y = y;
		firstCard = null;
		
		CardPile pileOne = new CardPile(0, 0);
		CardPile pileTwo = new CardPile(0, 0);
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j <= 12; j++) {
				pileOne.addCard(new Card(i, j));
				count++;
			}
		}
		for (; count > 0; count--) {
			int limit = ((int) (Math.random() * 1000)) % count;
			for (int i = 0; i < limit; i++) {
				pileTwo.addCard(pileOne.pop());
			}
			addCard(pileOne.pop());
			while (!pileTwo.empty()) {
				pileOne.addCard(pileTwo.pop());
			}
		}
	}
	public void addCard(final Card aCard) {
		aCard.link = firstCard;
		firstCard = aCard;
	}

	public void display(final Graphics g) {
		g.setColor(Color.black);
		if (firstCard == null) {
			g.drawRect(x, y, Card.width, Card.height);
		} else {
			firstCard.draw(g, x, y);
		}
	}

	public Card pop() {
		Card result = null;
		if (firstCard != null) {
			result = firstCard;
			firstCard = firstCard.link;
		}
		return result;
	}
	
	public Card top() {
		return firstCard;
	}

}
