package cs3500.hw04;

import org.junit.Test;

import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.IFreeCellModel;
import cs3500.hw02.PileType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Test for MultiMoveModel.
 */
public class MultiMoveModelTest {

  private IFreeCellModel<Card> aGame =  FreeCellModelCreator.create(FreeCellModelCreator.GameType
          .MULTIMOVE);

  private IFreeCellModel<Card> anotherGame =  FreeCellModelCreator.create(FreeCellModelCreator
          .GameType
          .MULTIMOVE);

  @Test(expected = IllegalArgumentException.class)
  public void testValidNumberMoves() {
    List<Card> deck = aGame.getDeck();
    aGame.startGame(deck, 7, 4, false);
    System.out.println(aGame.getGameState());
    aGame.move(PileType.CASCADE, 0, 2, PileType.CASCADE, 1);
  }

  @Test
  public void testAMove() {
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
  public void testBadMoveToCascade() {
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    anotherGame.startGame(anotherGame.getDeck(), 52, 5, false);
    assertEquals(aGame.getGameState(), anotherGame.getGameState());
    aGame.move(PileType.CASCADE, 24, 0, PileType.OPEN, 1);
    aGame.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 51);
    aGame.move(PileType.CASCADE, 23, 0, PileType.CASCADE, 51);
    aGame.move(PileType.CASCADE, 51, 1, PileType.CASCADE, 22);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadMoveToFoundation() {
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    anotherGame.startGame(anotherGame.getDeck(), 52, 5, false);
    assertEquals(aGame.getGameState(), anotherGame.getGameState());
    aGame.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 51);
    aGame.move(PileType.CASCADE, 23, 0, PileType.CASCADE, 51);
    aGame.move(PileType.CASCADE, 51, 1, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadMoveToOpen() {
    aGame.startGame(aGame.getDeck(), 52, 5, false);
    anotherGame.startGame(anotherGame.getDeck(), 52, 5, false);
    assertEquals(aGame.getGameState(), anotherGame.getGameState());
    aGame.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 51);
    aGame.move(PileType.CASCADE, 23, 0, PileType.CASCADE, 51);
    aGame.move(PileType.CASCADE, 51, 1, PileType.OPEN, 0);
  }

}