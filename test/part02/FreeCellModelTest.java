package cs3500.hw02;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test Class for FreeCellModel class.
 */
public class FreeCellModelTest {

  private FreeCellModel aGame;
  private FreeCellModel anotherGame;

  @Before
  public void init() {
    aGame = new FreeCellModel();
    anotherGame = new FreeCellModel();
  }


  @Test
  public void testGetDeck() {
    assertEquals(52, aGame.getDeck().size());
  }

  @Test
  public void testCardNoShuffle() {
    aGame.startGame(aGame.getDeck(), 5, 5, false);
    anotherGame.startGame(aGame.getDeck(), 5, 5, false);
    assertEquals(aGame.getGameState(), anotherGame.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDeck() {
    List<Card> fakeDeck = aGame.getDeck();
    fakeDeck.add(new Card(CardValue.SEVEN, Suit.SPADE));
    aGame.startGame(fakeDeck, 5, 5, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalOpenPile() {
    aGame.startGame(aGame.getDeck(), 8, 0, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCascadePile() {
    aGame.startGame(aGame.getDeck(), 3, 3, false);
  }

  @Test
  public void testShuffle() {
    List<Card> deckForBoth = aGame.getDeck();
    aGame.startGame(deckForBoth, 7, 7, false);
    anotherGame.startGame(deckForBoth, 7, 7, true);
    assertNotEquals(aGame.getGameState(), anotherGame.getGameState());
  }

  @Test
  public void testGameOverTrue() {
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    for (int c = 0; c < 13; c = c + 1) {
      aGame.move(PileType.CASCADE, c, 0, PileType.FOUNDATION, 0);
      aGame.move(PileType.CASCADE, c + 13, 0, PileType.FOUNDATION, 1);
      aGame.move(PileType.CASCADE, c + 26, 0, PileType.FOUNDATION, 2);
      aGame.move(PileType.CASCADE, c + 39, 0, PileType.FOUNDATION, 3);
    }
    assertTrue(aGame.isGameOver());
  }

  @Test
  public void testGameOverFalse() {
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    for (int c = 0; c < 13; c = c + 1) {
      aGame.move(PileType.CASCADE, c, 0, PileType.FOUNDATION, 0);
      aGame.move(PileType.CASCADE, c + 13, 0, PileType.FOUNDATION, 1);
      aGame.move(PileType.CASCADE, c + 26, 0, PileType.FOUNDATION, 2);
    }
    assertFalse(aGame.isGameOver());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalFoundationMove() {
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    aGame.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    aGame.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCascadeMove() {
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    aGame.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalOpenMove() {
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    aGame.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    aGame.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);
  }

  @Test
  public void testStartGameRestarts() {
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    String begin = aGame.getGameState();
    assertFalse(aGame.isGameOver());
    for (int c = 0; c < 13; c = c + 1) {
      aGame.move(PileType.CASCADE, c, 0, PileType.FOUNDATION, 0);
      aGame.move(PileType.CASCADE, c + 13, 0, PileType.FOUNDATION, 1);
      aGame.move(PileType.CASCADE, c + 26, 0, PileType.FOUNDATION, 2);
      aGame.move(PileType.CASCADE, c + 39, 0, PileType.FOUNDATION, 3);
    }
    assertTrue(aGame.isGameOver());
    assertFalse(begin.equals(aGame.getGameState()));
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    assertFalse(aGame.isGameOver());
    assertTrue(begin.equals(aGame.getGameState()));
  }


  @Test
  public void testEmptyGetGameState() {
    assertEquals(aGame.getGameState(), "");
  }

  @Test
  public void testEmptyGameStateAfterException() {
    try {
      aGame.startGame(aGame.getDeck(), 2, 5, false);
    } catch (IllegalArgumentException e) {
        //This is to make sure the game state is empty after exception
    } finally {
      assertEquals(aGame.getGameState(), "");
    }
  }

  @Test
  public void testHowManyFreeOpens() {
    aGame.startGame(aGame.getDeck(), 7, 5, false);
    assertEquals(5, aGame.countFreeOpen());

  }

  @Test
  public void testHowManyFreeCascade() {
    aGame.startGame(aGame.getDeck(), 60, 5, false);
    assertEquals(8, aGame.countFreeCascade());
  }

  @Test
  public void testHowManyCardsToMove() {
    aGame.startGame(aGame.getDeck(), 7, 5, false);
    assertEquals(0, aGame.countCardsToMove(PileType.OPEN, 0, 0));
    aGame.move(PileType.CASCADE, 0, 7, PileType.OPEN, 0);
    assertEquals(1, aGame.countCardsToMove(PileType.OPEN, 0, 0));
    assertEquals(1, aGame.countCardsToMove(PileType.CASCADE, 0, 6));
    assertEquals(2, aGame.countCardsToMove(PileType.CASCADE, 0, 5));
    assertEquals(7, aGame.countCardsToMove(PileType.CASCADE, 3, 0));
  }



}

