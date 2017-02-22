package cs3500.hw02;

import java.util.Objects;

/**
 * A class representing what a card is in the game.
 */
public class Card {
  CardValue cardValue;
  Suit suit;

  Card(CardValue cardValue, Suit suit) {
    this.cardValue = cardValue;
    this.suit = suit;
  }

  /**
   * Overriding the equals method.
   * @param obj Object to be compared
   * @return true if two cards are equal
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Card)) {
      return false;
    }
    Card that = (Card) obj;
    return this.suit == that.suit && this.cardValue == that.cardValue;
  }


  /**
   * Overriding the hashCode method.
   * @return integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(cardValue, suit);
  }

  /**
   * Overriding the toString method.
   * @return String
   */
  @Override
  public String toString() {
    return this.cardValue.toString() + this.suit.toString();
  }

}


