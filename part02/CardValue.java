package cs3500.hw02;

/**
 * Enumeration for the different values of cards available per Suit
 * There are thirteen values, from Ace (with a value of 1), 2 through 10, Jack, Queen and King
 * (with values of 11, 12 and 13 respectively.
 */
enum CardValue {

  ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
  JACK(11), QUEEN(12), KING(13);

  private final int value;

  CardValue(int value) {
    this.value = value;
  }

  /**
   * Gets the value attached to the Enumeration.
   * @return int
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Overriding the toString method.
   * @return String
   */
  @Override
  public String toString() {
    String result = "";
    switch (this) {
      case ACE:
        result = "A";
        break;
      case TWO:
        result = "2";
        break;
      case THREE:
        result = "3";
        break;
      case FOUR:
        result = "4";
        break;
      case FIVE:
        result = "5";
        break;
      case SIX:
        result = "6";
        break;
      case SEVEN:
        result = "7";
        break;
      case EIGHT:
        result = "8";
        break;
      case NINE:
        result = "9";
        break;
      case TEN:
        result = "10";
        break;
      case JACK:
        result = "J";
        break;
      case QUEEN:
        result = "Q";
        break;
      case KING:
        result = "K";
        break;
      default:
        throw new IllegalArgumentException("Invalid card value");
    }
    return result;
  }
}
