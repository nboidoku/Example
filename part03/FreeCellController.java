package cs3500.hw03;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cs3500.hw02.IFreeCellModel;
import cs3500.hw02.Card;
import cs3500.hw02.PileType;


/**
 * FreeCell Controller Class to implement the IFreeCell Controller interface.
 */
public class FreeCellController implements IFreeCellController<Card> {

  private final Readable rd;
  private final Appendable ap;

  public FreeCellController(Readable rd, Appendable ap) {
    this.rd = rd;
    this.ap = ap;
  }


  @Override
  public void playGame(List<Card> deck, IFreeCellModel<Card> model, int numCascades, int
          numOpens, boolean shuffle) {

    if (model == null) {
      throw new IllegalArgumentException();
    }

    if (ap == null || rd == null) {
      throw new IllegalStateException("Null appendable or Readable");
    }

    if (deck == null) {
      throw new IllegalArgumentException("Null Deck");
    }

    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (Exception e) {
      appendWithoutNewLine("Could not start game.");
      return;
    }

    Scanner scan = new Scanner(rd);


    while (!(model.isGameOver())) {
      appendAppendable(model.getGameState());
      appendAppendable("Make a move:");

      PileType sourceType = null;
      int sourcePile = 0;
      int index = 0;
      PileType destinationType = null;
      int destinationPile = 0;

      while (scan.hasNext()) {
        String temp = scan.next();
        try {
          if (hasQuit(temp)) {
            appendAppendable("Game quit prematurely.");
            return;
          } else {
            sourceType = findPileType(temp);
            sourcePile = findPileNumber(temp);
            break;
          }
        } catch (Exception e) {
          appendAppendable("Invalid SourceType. Input source pile again:");
        }
      }


      while (scan.hasNext()) {
        String temp = scan.next();
        try {
          if (hasQuit(temp)) {
            appendAppendable("Game quit prematurely.");
            return;
          } else {
            index = getIndex(temp);
            break;
          }
        } catch (Exception e) {
          appendAppendable("Invalid index. Input index again:");
        }
      }

      while (scan.hasNext()) {
        String temp = scan.next();
        try {
          if (hasQuit(temp)) {
            appendAppendable("Game quit prematurely.");
            return;
          } else {
            destinationType = findPileType(temp);
            destinationPile = findPileNumber(temp);
            break;
          }
        } catch (Exception e) {
          appendAppendable("Invalid Destination: Input destination again:");
        }
      }

      sourcePile = sourcePile - 1;
      index = index - 1;
      destinationPile = destinationPile - 1;

      try {
        model.move(sourceType, sourcePile, index, destinationType, destinationPile);
      } catch (Exception e) {
        appendAppendable("Invalid move");
      }
    }

    appendAppendable(model.getGameState());
    appendWithoutNewLine("Game over.");

  }

  /**
   * Checks to see if the user input matches quitting.
   *
   * @param userInput the input to be checked.
   * @return true if quit.
   */
  private boolean hasQuit(String userInput) {
    return userInput.charAt(0) == 'Q'
            || userInput.charAt(0) == 'q';
  }

  /**
   * Finds the pileType per given input.
   *
   * @param userInput the user input
   * @return PileType of input
   */
  private PileType findPileType(String userInput) {
    char firstCharacter = userInput.charAt(0);
    switch (firstCharacter) {
      case 'C':
        return PileType.CASCADE;
      case 'F':
        return PileType.FOUNDATION;
      case 'O':
        return PileType.OPEN;
      default:
        throw new IllegalArgumentException("Bad PileType");
    }
  }

  /**
   * Gets the pileNumber of the given input.
   *
   * @param userInput given input.
   * @return int matching pileNumber.
   */
  private int findPileNumber(String userInput) {
    String restOfNumber = userInput.substring(1);
    try {
      return Integer.parseInt(restOfNumber);
    } catch (Exception e) {
      throw new IllegalArgumentException("Bad Pile Number");
    }
  }

  /**
   * Gets the index of the given input.
   *
   * @param userInput given user input
   * @return int of index
   */
  private int getIndex(String userInput) {
    int myIndex;
    try {
      myIndex = Integer.parseInt(userInput);
    } catch (Exception e) {
      throw new IllegalArgumentException("Bad index");
    }
    if (myIndex < 0) {
      throw new IllegalArgumentException("Bad index");
    }
    return myIndex;
  }

  /**
   * Appends the given string and moves to the next line.
   *
   * @param appendable given string to be appended
   */
  private void appendAppendable(String appendable) {
    try {
      this.ap.append(appendable);
      this.ap.append("\n");
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

  /**
   * Appends the given string.
   *
   * @param appendable given string to be appended
   */
  private void appendWithoutNewLine(String appendable) {
    try {
      this.ap.append(appendable);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

}


