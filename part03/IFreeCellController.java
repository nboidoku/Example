package cs3500.hw03;

import cs3500.hw02.IFreeCellModel;

import java.util.List;

/**
 * Interface for the FreeCell Controller. It is parameterized over the card type.
 */
public interface IFreeCellController<K> {

  /**
   * PlayGame takes in input and plays the FreeCell game.
   *
   * @param deck        the Deck to be used
   * @param model       The FreeCell Model to be used
   * @param numCascades Number of cascade Piles
   * @param numOpens    Number of Open Piles
   * @param shuffle     Should the deck be shuffled
   */
  void playGame(List<K> deck, IFreeCellModel<K> model, int numCascades,
                int numOpens, boolean shuffle);

}