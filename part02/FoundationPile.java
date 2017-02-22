package cs3500.hw02;

import java.util.ArrayList;

/**
 * Class for foundation Pile.
 * NEW CLASS, FOUNDATION EXTENDING PILE.
 */
class FoundationPile extends Pile {


  FoundationPile(ArrayList<Card> cards) {
    super(cards);
  }

  /**
   * Checks to see if the given card could be moved onto this foundation pile.
   *
   * @param moveCard the card to be placed
   * @return true if valid
   */
  boolean isMoveValid(Card moveCard) {
    return ((this.cards.size() == 0 && moveCard.cardValue.getValue() == 1) || (this.cards.size() > 0
            && (prevCardValue() - moveCard.cardValue
            .getValue() == -1)));

  }




}

