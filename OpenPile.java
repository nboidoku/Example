package cs3500.hw02;

import java.util.ArrayList;

/**
 * Class for Open Piles.
 * NEW CLASS, CASCADE EXTENDING PILE.
 */
class OpenPile extends Pile {


  OpenPile(ArrayList<Card> cards) {
    super(cards);
  }

  /**
   * Checks to see if a card could be moved onto this open pile.
   *
   * @return true if valid
   */
  boolean isMoveValid(Card card) {
    return this.cards.size() == 0;
  }


}
