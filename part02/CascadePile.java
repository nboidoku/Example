package cs3500.hw02;

import java.util.ArrayList;

/**
 * Class for CascadePiles.
 * NEW CLASS, CASCADE EXTENDING PILE.
 */
class CascadePile extends Pile {

  CascadePile(ArrayList<Card> card) {
    super(card);
  }

  /**
   * Checks to see if the given card could be moved onto this cascade pile.
   *
   * @param moveCard the card to be moved
   * @return true if valid
   */


  boolean isMoveValid(Card moveCard) {
    return isOneLess(moveCard, prevCardValue()) && isDifferentSuit(moveCard,
            prevCardSuit());
  }

  /**
   * Checks if one card is of a different suit as another.
   *
   * @param moveCard the Card to be moved
   * @param other the suit being checked against
   * @return true if suits are different
   */
  private boolean isDifferentSuit(Card moveCard, Suit other) {
    switch (moveCard.suit) {
      case DIAMOND:
        return (other == Suit.CLUB ||
                other == Suit.SPADE);
      case CLUB:
        return (other == Suit.DIAMOND
                ||
                other == Suit.HEART);
      case SPADE:
        return (other == Suit.DIAMOND
                ||
                other == Suit.HEART);
      case HEART:
        return (other == Suit.CLUB
                ||
                other == Suit.SPADE);
      default:
        throw new IllegalArgumentException("Invalid Suit");
    }
  }

  /**
   * Checks if the next card is one less than this card
   *
   * @param moveCard the card to be moved.
   * @param other the value to which it's being checked against
   * @return true if it is one less
   */
  private boolean isOneLess(Card moveCard, int other) {
    return (this.cards.size() == 0) || ((this.cards.size() > 0
            && (other - moveCard.cardValue
            .getValue() == 1)));
  }

}
