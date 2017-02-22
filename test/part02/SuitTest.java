package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Suit class.
 */
public class SuitTest {

  private Card aceOfSpades = new Card(CardValue.ACE, Suit.SPADE);
  private Card twoOfDiamonds = new Card(CardValue.TWO, Suit.DIAMOND);
  private Card jackOfClubs = new Card(CardValue.JACK, Suit.CLUB);
  private Card queenOfHearts = new Card(CardValue.QUEEN, Suit.HEART);

  @Test
  public void testSuitToString() {
    assertEquals("♠", aceOfSpades.suit.toString());
    assertEquals("♦", twoOfDiamonds.suit.toString());
    assertEquals("♣", jackOfClubs.suit.toString());
    assertEquals( "♥", queenOfHearts.suit.toString());
  }
}