package PokerHands;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Poker extends Applet implements ActionListener {

	CardDeck pile;

	TableCard[] tableCard = new TableCard[5];
	Card[] combin = new Card[5];
	PokerHands comb;

	JButton buttonDeal = new JButton("Deal!");
	JButton buttonPlay = new JButton("Paly cards out!");
	JButton buttonComb = new JButton("Define The Poker-hand!");

	private String combination = "";

	public void initCard() {
		
		pile = new CardDeck(300, 100);
		for (int i = 0; i < tableCard.length; i++) {
			tableCard[i] = new TableCard(5 + 55 * i, 140, pile.top());
			combin[i] = pile.pop();
		}
		comb = new PokerHands(combin);

	}
	
	public void initButtons(){
		
		this.setLayout(null);
		
		buttonDeal.setSize(220, 20);
		buttonDeal.setLocation(5, 20);
		this.add(buttonDeal);
		buttonDeal.addActionListener(this);
		buttonDeal.setVisible(true);
		buttonDeal.setEnabled(false);
		
		buttonPlay.setSize(220, 20);
		buttonPlay.setLocation(5, 60);
		this.add(buttonPlay);
		buttonPlay.addActionListener(this);
		buttonPlay.setVisible(true);
		
		buttonComb.setSize(220, 20);
		buttonComb.setLocation(5, 100);
		this.add(buttonComb);
		buttonComb.addActionListener(this);
		buttonComb.setVisible(true);
		buttonComb.setEnabled(false);
		
	}
	
	public void init(){
		initCard();
		initButtons();
	}

	public void paint(final Graphics g) {
		super.paint(g);
		pile.display(g);
		for (int i = 0; i < tableCard.length; i++) {
			tableCard[i].display(g);
		}
		g.setColor(Color.BLACK);
		g.drawRect(250, 50, 115, 30);
		g.setColor(Color.BLUE);
		g.drawString(combination, 260, 70);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == buttonDeal) {	
			buttonPlay.setEnabled(true);
			buttonDeal.setEnabled(false);		
			combination = "";
				this.initCard();
		} 
			if (e.getSource() == buttonPlay) {
			for (TableCard card : tableCard) {
				card.faceUp();
			}
			buttonComb.setEnabled(true);
			buttonPlay.setEnabled(false);
		}
			if (e.getSource() == buttonComb) {
			combination = comb.definePokerHand();
			buttonComb.setEnabled(false);
			buttonDeal.setEnabled(true);
		}
		repaint();
	}

}
