package cs3500.hw03;

import cs3500.hw02.FreeCellModel;
import cs3500.hw02.Card;

import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for my Controller class.
 */
public class FreeCellControllerTest {


  private Appendable ap;
  private Readable rd;
  private FreeCellController myController;
  private FreeCellModel freeCellModel;
  private List<Card> deck;

  /**
   * Optional initializing method.
   */
  private void init() {
    rd = new StringReader("C1 5 Hello");
    ap = new StringBuffer();
    myController = new FreeCellController(rd, ap);
    freeCellModel = new FreeCellModel();
    deck = freeCellModel.getDeck();
  }


  @Test(expected = IllegalStateException.class)
  public void testNullValues() {
    myController = new FreeCellController(rd, ap);
    freeCellModel = new FreeCellModel();
    deck = freeCellModel.getDeck();
    myController = new FreeCellController(rd, ap);
    myController.playGame(deck, freeCellModel, 8, 5, false);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    rd = new StringReader("C1 5 Hello");
    ap = new StringBuffer();
    deck = new ArrayList<>();
    myController = new FreeCellController(rd, ap);
    myController.playGame(deck, freeCellModel, 8, 5, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullDeck() {
    rd = new StringReader("C1 5 Hello");
    ap = new StringBuffer();
    myController = new FreeCellController(rd, ap);
    freeCellModel = new FreeCellModel();
    myController.playGame(deck, freeCellModel, 8, 5, false);
  }


  @Test
  public void testCannotStart() {
    init();
    myController.playGame(deck, freeCellModel, 2, 5, false);
    assertEquals(ap.toString(), "Could not start game.");
  }

  @Test
  public void gameQuitFromSource() {
    init();
    rd = new StringReader("Q");
    myController = new FreeCellController(rd, ap);
    myController.playGame(deck, freeCellModel, 8, 5, false);
    assertEquals(ap.toString(), freeCellModel.getGameState() + "\nMake a move:" + "\nGame quit " +
            "prematurely.\n");
    assertTrue(ap.toString().endsWith("Game quit prematurely.\n"));
  }

  @Test
  public void gameQuitFromIndex() {
    init();
    rd = new StringReader("C1 Q");
    myController = new FreeCellController(rd, ap);
    myController.playGame(deck, freeCellModel, 8, 5, false);
    assertEquals(ap.toString(), freeCellModel.getGameState() + "\nMake a move:" + "\nGame quit " +
            "prematurely.\n");
    assertTrue(ap.toString().endsWith("Game quit prematurely.\n"));
  }

  @Test
  public void gameQuitFromDestination() {
    init();
    rd = new StringReader("C1 1 Q");
    myController = new FreeCellController(rd, ap);
    myController.playGame(deck, freeCellModel, 8, 5, false);
    assertEquals(ap.toString(), freeCellModel.getGameState() + "\nMake a move:" + "\nGame quit " +
            "prematurely.\n");
    assertTrue(ap.toString().endsWith("Game quit prematurely.\n"));
  }

  @Test
  public void makeValidMove() {
    init();
    rd = new StringReader("C1 7 O1 Q");
    myController = new FreeCellController(rd, ap);
    myController.playGame(deck, freeCellModel, 8, 5, false);
    assertTrue(ap.toString().contains("O1: 10♣"));
  }

  private String winningFormula = makeWinningString();


  private String makeWinningString() {
    String winner = "";
    for (int c = 1; c < 53; c = c + 1) {
      winner = winner + "C" + Integer.toString(c) + " 1 " + "F" + Integer.toString((c / 13) + 1)
              + " ";
    }
    return winner + "C13 1 F1 C26 1 F2 C39 1 F3 C52 1 F4";
  }


  @Test
  public void isGameOver() {
    init();
    rd = new StringReader(winningFormula);
    myController = new FreeCellController(rd, ap);
    myController.playGame(deck, freeCellModel, 52, 5, false);
    assertTrue(freeCellModel.isGameOver());
  }

}



