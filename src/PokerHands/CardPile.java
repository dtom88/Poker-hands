package PokerHands;

import java.awt.Color;
import java.awt.Graphics;

class CardPile {

	private Card firstCard;

	protected int x;
	protected int y;

	CardPile(final int xl, final int yl) {
		x = xl;
		y = yl;
		firstCard = null;
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

	public boolean empty() {
		return firstCard == null;
	}

	public Card pop() {
		Card result = null;
		if (firstCard != null) {
			result = firstCard;
			firstCard = firstCard.link;
		}
		return result;
	}

}