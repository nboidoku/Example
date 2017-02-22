package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by niiakoboi-doku on 9/25/16.
 */
public class CardValueTest {

  private Card aceOfSpades = new Card(CardValue.ACE, Suit.SPADE);
  private Card twoOfDiamonds = new Card(CardValue.TWO, Suit.DIAMOND);
  private Card jackOfClubs = new Card(CardValue.JACK, Suit.CLUB);
  private Card queenOfHearts = new Card(CardValue.QUEEN, Suit.HEART);

  @Test
  public void testValue() {
    assertEquals(1, aceOfSpades.cardValue.getValue());
    assertEquals(2, twoOfDiamonds.cardValue.getValue());
    assertEquals(11, jackOfClubs.cardValue.getValue());
    assertEquals(12, queenOfHearts.cardValue.getValue());
  }
}