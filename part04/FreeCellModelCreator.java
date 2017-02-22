package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw02.FreeCellModel;
import cs3500.hw02.IFreeCellModel;

/**
 * Enumeration for the FreeCellModelCreator.
 */
public class FreeCellModelCreator {

  public enum GameType {
    SINGLEMOVE, MULTIMOVE
  }

  /**
   * Creates a game of FreeCell using the enums.
   * @param type the enum to be used
   * @return an IFreeCellModel of the GameType.
   */
  public static IFreeCellModel<Card> create(GameType type) {
    switch (type) {
      case SINGLEMOVE:
        return new FreeCellModel();
      case MULTIMOVE:
        return new MultiMoveModel();
      default:
        throw new IllegalArgumentException("Invalid FreeCellModelCreator");
    }
  }

}
