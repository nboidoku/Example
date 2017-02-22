package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Card Class.
 */
public class CardTest {

  private Card aceOfSpades = new Card(CardValue.ACE, Suit.SPADE);
  private Card twoOfDiamonds = new Card(CardValue.TWO, Suit.DIAMOND);
  private Card jackOfClubs = new Card(CardValue.JACK, Suit.CLUB);
  private Card queenOfHearts = new Card(CardValue.QUEEN, Suit.HEART);


  @Test
  public void testEquals() {
    assertTrue(aceOfSpades.equals(new Card(CardValue.ACE, Suit.SPADE)));
    assertTrue((!twoOfDiamonds.equals(new Card(CardValue.ACE, Suit.SPADE ))));
  }

  @Test
  public void testCardToString() {
    assertEquals("A♠", aceOfSpades.toString());
    assertEquals("2♦", twoOfDiamonds.toString());
    assertEquals("J♣", jackOfClubs.toString());
    assertEquals( "Q♥", queenOfHearts.toString());
  }

}