package PokerHands;

import java.util.ArrayList;

public class PokerHands {

	private Card[] combs = new Card[5];

	public PokerHands(Card[] pile) {
		if (pile.length != 5) {
			throw new IllegalArgumentException("Pile must contain five cards!");
		}
		combs = pile;
		int[] ranks = new int[combs.length];
		Card tmpC;
		int tmp;
		int j;
		ranks[0] = combs[0].getRank();
		for (int i = 1; i < combs.length; i++) {
			tmp = combs[i].getRank();
			tmpC = combs[i];
			j = i - 1;
			while (j >= 0 && tmp < ranks[j]) {
				ranks[j + 1] = ranks[j];
				combs[j + 1] = combs[j];
				j--;
			}
			ranks[j + 1] = tmp;
			combs[j + 1] = tmpC;
		}
	}

	private boolean isSameSuit() {
		int currentSuit = combs[0].getSuit();
		for (int i = 1; i < combs.length; i++) {
			if (currentSuit != combs[i].getSuit()) {
				return false;
			}
		}
		return true;
	}

	private int diffRank() {
		int dRank = 1;
		for (int i = 0; i < combs.length - 1; i++) {
			if (combs[i].getRank() != combs[i + 1].getRank()) {
				dRank++;
			}
		}
		return dRank;
	}

	private int pairCounter() {
		int counter = 0;
		int pair = 0;
		for (int i = 0; i < combs.length - 1; i++) {
			if (combs[i].getRank() == combs[i + 1].getRank()) {
				counter++;
				pair++;
			}
			if (combs[i].getRank() != combs[i + 1].getRank()) {
				if (counter > 1) {
					pair -= counter;
				}
				counter = 0;
			}
		}
		return pair;
	}

	private boolean likeStraight() {
		if (diffRank() == 5) {
			int i = 1;
			while (i < 5 && combs[i].getRank() - combs[i - 1].getRank() == 1) {
				i++;
			}
			if (i == 4 && combs[0].getRank() == 0
					&& combs[combs.length - 1].getRank() == 12) {
				i++;
			}
			return i == 5;

		}
		return false;
	}

	private int sum() {
		int sum = 0;
		for (Card card : combs) {
			sum += card.getRank();
		}
		return sum;
	}

	private int sameRankCount() {
		int maxCount = 1;
		int currCount = 1;
		int currentRank = combs[0].getRank();
		for (int i = 1; i < combs.length; i++) {
			if (currentRank == combs[i].getRank()) {
				currCount++;
			} else {
				if (currCount > maxCount) {
					maxCount = currCount;
				}
				currCount = 1;
				currentRank = combs[i].getRank();
			}
		}
		if (currCount > maxCount) {
			maxCount = currCount;
		}
		return maxCount;
	}

	public String definePokerHand() {

		if (isRoyalFlush()) {
			return "Royal Flush";
		}
		if (isFlush()) {
			return "Flush";
		}
		if (isFourOfAKind()) {
			return "Four Of A Kind";
		}
		if (isFullHouse()) {
			return "Full House";
		}
		if (isHighCard()) {
			return "High Card";
		}
		if (isOnePair()) {
			return "One Pair";
		}
		if (isTwoPair()) {
			return "Two Pair";
		}
		if (isStraight()) {
			return "Straight";
		}
		if (isStraightFlush()) {
			return "Straight Flush";
		}
		if (isThreeOfAKind()) {
			return "Three Of A Kind";
		}
		return null;
	}

	public boolean isRoyalFlush() {
		return (isSameSuit() && diffRank() == 5 && sum() == 50);
	}

	public boolean isStraightFlush() {
		return (isSameSuit() && likeStraight() && sum() != 50);
	}

	public boolean isFourOfAKind() {
		return sameRankCount() == 4;
	}

	public boolean isFullHouse() {
		return diffRank() == 2 && sameRankCount() == 3;
	}

	public boolean isFlush() {
		return isSameSuit() && !likeStraight();
	}

	public boolean isStraight() {
		return (likeStraight() && !isSameSuit());
	}

	public boolean isThreeOfAKind() {
		return (diffRank() == 3 && sameRankCount() == 3);
	}

	public boolean isTwoPair() {
		return pairCounter() == 2;
	}

	public boolean isOnePair() {
		return pairCounter() == 1;
	}

	public boolean isHighCard() {
		return (diffRank() == 5 && !isSameSuit() && !likeStraight());
	}

}
