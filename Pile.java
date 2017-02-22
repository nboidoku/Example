package cs3500.hw02;

import java.util.ArrayList;

/**
 * A class representing a pile of cards in the FreeCell Game.
 * CHANGED. THIS WAS NOT ABSTRACT FOR HW02. MADE IT ABSTRACT
 */
abstract class Pile {

  ArrayList<Card> cards;

  Pile(ArrayList<Card> cards) {
    this.cards = cards;
  }


  /**
   * Places a card at the end of a pile.
   *
   * @param card the card to be placed
   */
  void placeOnTop(Card card) {
    this.cards.add(card);
  }

  /**
   * Removes a card from a particular index.
   *
   * @param index the index the card should be removed from
   * @return the removed card
   */
  Card removeCard(int index) {
    return this.cards.remove(index);
  }

  Card peek(int index) {
    return this.cards.get(index);
  }

  /**
   * Overriding the toString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    String result = "";
    for (int c = 0; c < cards.size(); c = c + 1) {
      result = result + " " + cards.get(c).toString();
      if (c != cards.size() - 1) {
        result = result + ",";
      }
    }
    return result;
  }

  /**
   * returns the cardValue of the last card in this pile.
   *
   * @return the value of the CardValue
   */
  int prevCardValue() {
    return this.cards.get(cards.size() - 1).cardValue.getValue();
  }

  /**
   * returns the suit of the last card in this pile.
   *
   * @return the Suit
   */
  Suit prevCardSuit() {
    return this.cards.get(cards.size() - 1).suit;
  }

  /**
   * Checks to see if a move to this pile is valid.
   *
   * @param moveCard the Card about to be moved
   */
  abstract boolean isMoveValid(Card moveCard);

  /**
   * Checks to see if the cards in this pile are empty.
   *
   * @return true if empty or false if not.
   */
  boolean isAllEmpty() {
    return this.cards.isEmpty();
  }

  int howManyFreeCells() {
    if (this.cards.isEmpty()) {
      return 1;
    } else {
      return 0;
    }
  }


  /**
   * Checks to see how many cards are between the index and the lasr card.
   *
   * @param index The index to be checked
   * @return the number of cards
   */
  int howManyFromIndex(int index) {
    return this.cards.size() - index;
  }


  /**
   * Removes the cards from the source pile and puts it in a buffer array.
   *
   * @param index the index of the card to be moved
   * @return A buffer array with the move cards
   */
  ArrayList<Card> removeCards(int index) {

    ArrayList<Card> myCards = new ArrayList<>();

    for (int c = 0; c <= cards.size() - index; c = c + 1) {
      myCards.add(cards.remove(index));
    }

    return myCards;
  }

  /**
   * Places an arrayList of cards onto it's destination pile.
   *
   * @param cards the cards to be added to the destination.
   */
  void placeCardsOnTop(ArrayList<Card> cards) {
    this.cards.addAll(cards);
  }

}

