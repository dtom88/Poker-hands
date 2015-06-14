package PokerHands;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PokerHandsTest {

	private Card[] _input;
	private PokerHands comb;
	private String _output;

	@Parameters
	public static List<Object[]> data() {

		int count = 5;

		Card[] royalFlush = new Card[count];
		for (int i = 0; i < count; i++) {
			royalFlush[i] = new Card(0, 8 + i);
		}

		Card[] straigt = new Card[count];
		straigt[0] = new Card(0, 12);
		for (int i = 1; i < count; i++) {
			straigt[i] = new Card(-1 + i, -1 + i);
		}

		Card[] straightFlush = new Card[count];
		for (int i = 0; i < count; i++) {
			straightFlush[i] = new Card(1, 6 + i);
		}

		Card[] fourOfAKind = new Card[count];
		for (int i = 0; i < 4; i++) {
			fourOfAKind[i] = new Card(0 + i, 4);
		}
		fourOfAKind[4] = new Card(1, 5);

		Card[] fullHouse = new Card[count];
		for (int i = 0; i < 3; i++) {
			fullHouse[i] = new Card(0 + i, 4);
		}
		for (int i = 3; i < 5; i++) {
			fullHouse[i] = new Card(-3 + i, 5);
		}

		Card[] flush = { new Card(0, 4), new Card(0, 8), new Card(0, 11),
				new Card(0, 6), new Card(0, 7) };

		Card[] threeOfAKind = new Card[count];
		for (int i = 0; i < 3; i++) {
			threeOfAKind[i] = new Card(0 + i, 4);
		}
		for (int i = 3; i < 5; i++) {
			threeOfAKind[i] = new Card(-2 + i, 3 + i);
		}

		Card[] twoPair = new Card[count];
		for (int i = 0; i < 2; i++) {
			twoPair[i] = new Card(0 + i, 4);
		}
		for (int i = 2; i < 4; i++) {
			twoPair[i] = new Card(-1 + i, 3);
		}
		twoPair[4] = new Card(0, 10);

		Card[] onePair = new Card[count];
		for (int i = 0; i < 3; i++) {
			onePair[i] = new Card(0 + i, 4 + i);
		}
		for (int i = 3; i < 5; i++) {
			onePair[i] = new Card(-2 + i, 3 + i);
		}

		Card[] highCard = { new Card(0, 4), new Card(1, 8), new Card(3, 10),
				new Card(0, 7), new Card(0, 13) };

		Card[] onePair2 = { new Card(0, 1), new Card(1, 6), new Card(2, 7),
				new Card(0, 10), new Card(3, 10) };

		Card[] fullHouse2 = { new Card(0, 12), new Card(1, 6), new Card(2, 12),
				new Card(0, 6), new Card(3, 12) };

		return Arrays
				.asList(new Object[][] { { fullHouse2, "Full House" },
						{ royalFlush, "Royal Flush" }, { straigt, "Straight" },
						{ flush, "Flush" }, { onePair, "One Pair" },
						{ twoPair, "Two Pair" }, { highCard, "High Card" },
						{ threeOfAKind, "Three Of A Kind" },
						{ fourOfAKind, "Four Of A Kind" },
						{ fullHouse, "Full House" },
						{ straightFlush, "Straight Flush" },
						{ onePair2, "One Pair" } });
	}

	public PokerHandsTest(final Card[] input, final String output) {
		_input = input;
		_output = output;
		comb = new PokerHands(input);
	}

	@Test
	public void testDefinePokerHand() {
		assertEquals(_output, comb.definePokerHand());
	}
}
