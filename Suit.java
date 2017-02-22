package cs3500.hw02;

/**
 * Enumeration for the 4 types of suits in a deck of cards for a game of Solitaire.
 */
enum Suit {

  HEART, SPADE, DIAMOND, CLUB;

  @Override
  public String toString() {
    String result;

    switch (this) {
      case HEART:
        result = "♥";
        break;
      case DIAMOND:
        result = "♦";
        break;
      case SPADE:
        result = "♠";
        break;
      case CLUB:
        result = "♣";
        break;
      default:
        throw new IllegalArgumentException("Invalid suit");
    }
    return result;

  }
}


