package cs3500.hw04;


import org.junit.Test;

import cs3500.hw02.Card;
import cs3500.hw02.IFreeCellModel;
import cs3500.hw02.PileType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test for FreeCellModelCreator.
 */
public class FreeCellModelCreatorTest {

  @Test
  public void testSimilarities() {
    IFreeCellModel<Card> aGame = FreeCellModelCreator.create(FreeCellModelCreator.GameType
            .MULTIMOVE);

    IFreeCellModel<Card> anotherGame = FreeCellModelCreator.create(FreeCellModelCreator
            .GameType
            .SINGLEMOVE);
    assertEquals(aGame.getGameState(), anotherGame.getGameState());
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    anotherGame.startGame(anotherGame.getDeck(), 52, 5, false);
    assertEquals(aGame.getGameState(), anotherGame.getGameState());
  }

  @Test
  public void testDifference() {
    IFreeCellModel<Card> aGame = FreeCellModelCreator.create(FreeCellModelCreator.GameType
            .MULTIMOVE);
    IFreeCellModel<Card> anotherGame = FreeCellModelCreator.create(FreeCellModelCreator
            .GameType
            .MULTIMOVE);
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    anotherGame.startGame(anotherGame.getDeck(), 52, 5, false);
    assertEquals(aGame.getGameState(), anotherGame.getGameState());
    aGame.move(PileType.CASCADE, 24, 0, PileType.OPEN, 1);
    aGame.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 51);
    aGame.move(PileType.CASCADE, 23, 0, PileType.CASCADE, 51);
    aGame.move(PileType.CASCADE, 51, 1, PileType.CASCADE, 25);
    assertNotEquals(aGame.getGameState(), anotherGame.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSingleMoveDifference() {
    IFreeCellModel<Card> aGame = FreeCellModelCreator.create(FreeCellModelCreator
            .GameType
            .SINGLEMOVE);
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    aGame.move(PileType.CASCADE, 24, 0, PileType.OPEN, 1);
    aGame.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 51);
    aGame.move(PileType.CASCADE, 23, 0, PileType.CASCADE, 51);
    aGame.move(PileType.CASCADE, 51, 1, PileType.CASCADE, 25);
  }
}