package cs3500.hw02;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Pile class.
 */
public class PileTest {


  @Test
  public void testPlaceOnTop() {
    ArrayList<Card> pileOneCard = new ArrayList<>();
    Pile pileOne = new CascadePile(pileOneCard);
    pileOne.placeOnTop(new Card(CardValue.ACE, Suit.SPADE));
    assertEquals(1, pileOneCard.size());
  }

  @Test
  public void testRemoveCard() {
    ArrayList<Card> pileOneCard = new ArrayList<>();
    Pile pileOne = new CascadePile(pileOneCard);
    pileOne.placeOnTop(new Card(CardValue.ACE, Suit.SPADE));
    pileOne.placeOnTop(new Card(CardValue.TEN,Suit.SPADE));
    pileOne.removeCard(1);
    assertEquals(1, pileOneCard.size());
  }

}