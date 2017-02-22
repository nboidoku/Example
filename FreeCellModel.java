package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * A class containing the FreeCellModel with a card argument.
 */
public class FreeCellModel implements IFreeCellModel<Card> {
  private ArrayList<Pile> foundationPile;
  private ArrayList<Pile> cascadePile;
  private ArrayList<Pile> openPile;
  private boolean gameStarted;


  /**
   * Constructor for the FreeCellModel class which instantiates the fields.
   */
  public FreeCellModel() {
    this.foundationPile = new ArrayList<>(4);
    this.cascadePile = new ArrayList<>();
    this.openPile = new ArrayList<>();
    this.gameStarted = false;
  }

  /**
   * Return a valid and complete deck of cards for a game of Freecell. There is
   * no restriction imposed on the ordering of these cards in the deck
   *
   * @return the deck of cards as a list
   */
  @Override
  public List<Card> getDeck() {
    ArrayList<Card> myDeck = new ArrayList<>();
    for (Suit suits : Suit.values()) {
      for (CardValue cardValues : CardValue.values()) {
        myDeck.add(new Card(cardValues, suits));
      }
    }
    return myDeck;
  }

  /**
   * * Deal a new game of Freecell with the given deck, with or without shuffling
   * it first. This method first verifies that the deck is valid.
   * It deals the deck among the cascade piles in round-robin fashion. Thus if
   * there are 4 cascade piles, the 1st pile will get cards 0, 3, 6, ..., the
   * 2nd pile will get cards 1, 4, 7, ..., the 3rd pile will get cards 2, 5, 8,
   * ... and the 4th pile will get cards 3, 6, 8, ....
   * Depending on the number of cascade piles, they may have a different number
   * of cards
   *
   * @param numCascadePiles number of cascade piles
   * @param numOpenPiles    number of open piles
   * @param deck            the deck to be dealt
   * @param shuffle         if true, shuffle the deck else deal the deck as-is
   * @throws IllegalArgumentException if the deck is invalid
   */
  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {
    gameStarted = true;
    foundationPile.clear();
    cascadePile.clear();
    openPile.clear();
    if (!(isValidDeck(deck))
            || (!isValidCascadePiles(numCascadePiles))
            ||
            (!isValidOpenPiles(numOpenPiles))) {
      throw new IllegalArgumentException("Not valid");
    }
    if (shuffle) {
      Collections.shuffle(deck);
    }
    for (int c = 0; c < numCascadePiles; c = c + 1) {
      this.cascadePile.add(new CascadePile(new ArrayList<>()));
    }
    for (int c = 0; c < deck.size(); c = c + 1) {
      this.cascadePile.get(c % cascadePile.size()).placeOnTop(deck.get(c));
    }
    for (int c = 0; c < numOpenPiles; c = c + 1) {
      this.openPile.add(new OpenPile(new ArrayList<>()));
    }
    for (int c = 0; c < 4; c = c + 1) {
      this.foundationPile.add(new FoundationPile(new ArrayList<>()));
    }
  }

  /**
   * Signal if the game is over or not.
   *
   * @return true if game is over, false otherwise.
   */
  @Override
  public boolean isGameOver() {
    boolean cascadeEmpty = true;
    boolean openEmpty = true;
    for (Pile pile : cascadePile) {
      cascadeEmpty = cascadeEmpty && pile.isAllEmpty();
    }
    for (Pile pile : openPile) {
      openEmpty = openEmpty && pile.isAllEmpty();
    }
    return cascadeEmpty && openEmpty;
  }

  /**
   * Move a card from the given source pile to the given destination pile, if
   * the move is valid.
   *
   * @param sourceType       the type of the source pile (see {@link PileType})
   * @param sourcePileNumber the pile number of the given type, starting at 0
   * @param cardIndex        the index of the card to be moved from the source pile, starting at 0
   * @param destType         the type of the destination pile (see {@link PileType})
   * @param destPileNumber   the pile number of the given type, starting at 0
   */
  @Override
  public void move(PileType sourceType, int sourcePileNumber, int cardIndex, PileType destType,
                   int destPileNumber) throws IllegalArgumentException {
    Card moveCard;
    Card temp;
    temp = peekTheCard(sourceType, sourcePileNumber, cardIndex);

    if (!(isMoveValid(sourceType, sourcePileNumber, cardIndex, destType, destPileNumber,
            temp))) {
      throw new IllegalArgumentException("Invalid move");
    } else {
      moveCard = getTheCard(sourceType, sourcePileNumber, cardIndex);
      placeTheCardOnTop(moveCard, destType, destPileNumber);
    }

  }


  /**
   * Return the present state of the game as a string. The string is formatted
   * as follows:
   *
   * <pre>
   * F1:[b]f11,[b]f12,[b],...,[b]f1n1[n] (Cards in foundation pile 1 in order)
   * F2:[b]f21,[b]f22,[b],...,[b]f2n2[n] (Cards in foundation pile 2 in order)
   * ...
   * Fm:[b]fm1,[b]fm2,[b],...,[b]fmnm[n] (Cards in foundation pile m in order)
   * O1:[b]o11[n]                        (Cards in open pile 1)
   * O2:[b]o21[n]                        (Cards in open pile 2)
   * ...
   * Ok:[b]ok1[n]                        (Cards in open pile k)
   * C1:[b]c11,[b]c12,[b]...,[b]c1p1[n]  (Cards in cascade pile 1 in order)
   * C2:[b]c21,[b]c22,[b]...,[b]c2p2[n]  (Cards in cascade pile 2 in order)
   * ...
   * Cs:[b]cs1,[b]cs2,[b]...,[b]csps     (Cards in cascade pile s in order)
   * </pre>
   * where [b] is a single space, [n] is newline. Note that there is no
   * newline on the last line.  If a pile has no cards in it, there should be no
   * space character after the colon and before the newline.
   */

  @Override
  public String getGameState() {
    String result = "";
    if (!(gameStarted)) {
      return result;
    }
    for (int c = 0; c < foundationPile.size(); c = c + 1) {
      result = result + "F" + Integer.toString(c + 1) + ":" + foundationPile.get(c).toString() +
              "\n";
    }
    for (int c = 0; c < openPile.size(); c = c + 1) {
      result = result + "O" + Integer.toString(c + 1) + ":" + openPile.get(c).toString() + "\n";
    }
    for (int c = 0; c < cascadePile.size(); c = c + 1) {
      result = result + "C" + Integer.toString(c + 1) + ":" + cascadePile.get(c).toString();
      if (c != cascadePile.size() - 1) {
        result = result + "\n";
      }
    }
    return result;
  }


  /**
   * Checks if the given deck is valid using by checking the size of the deck and using a HashSet
   * The deck is valid if it has exactly 52  cards and no duplicates.
   * A HashSet does not add duplicate values so if both the size of the deck and the HashSet are
   * equal(52), the deck is valid.
   *
   * @param deck The deck to be validated
   * @return true is the deck is valid or false if it is not
   */

  private boolean isValidDeck(List<Card> deck) {
    HashSet<Card> validDeck = new HashSet<>();
    for (Card card : deck) {
      validDeck.add(card);
    }
    return deck.size() == 52 && validDeck.size() == 52;
  }

  /**
   * Checks if the given number of cascade piles is valid.
   *
   * @return True if the number is valid or false if not.
   */
  private boolean isValidCascadePiles(int numPiles) {
    return numPiles >= 4;

  }

  /**
   * Checks if the given number of open piles is valid.
   *
   * @return True if the number is valid or false if not
   */
  private boolean isValidOpenPiles(int numPiles) {
    return numPiles > 0;
  }

  /**
   * Checks to see if the move to be made is valid given the inputs.
   *
   * @param sourceType       the type of the source pile (see {@link PileType})
   * @param sourcePileNumber the pile number of the given type, starting at 0
   * @param cardIndex        the index of the card to be moved from the source pile, starting at 0
   * @param destType         the type of the destination pile (see {@link PileType})
   * @param destPileNumber   the pile number of the given type, starting at 0
   * @return true if the move can be completed or false otherwise
   */
  private boolean isMoveValid(PileType sourceType, int sourcePileNumber, int cardIndex, PileType
          destType, int destPileNumber, Card moveCard) {
    boolean isValid = true;
    switch (sourceType) {
      case FOUNDATION:
        if (sourcePileNumber >= foundationPile.size()) {
          isValid = false;
        }
        if (cardIndex != foundationPile.get(sourcePileNumber).cards.size() - 1) {
          isValid = false;
        }
        break;
      case OPEN:
        if (sourcePileNumber >= openPile.size()) {
          isValid = false;
        }
        if (cardIndex != 0) {
          isValid = false;
        }
        break;
      case CASCADE:
        if (sourcePileNumber >= cascadePile.size()) {
          isValid = false;
        }
        if (cardIndex != (cascadePile.get(sourcePileNumber).cards.size()) - 1) {
          isValid = false;
        }
        break;
      default:
        throw new IllegalArgumentException("Invalid PileType");
    }

    switch (destType) {
      case FOUNDATION:
        if (destPileNumber >= foundationPile.size()) {
          isValid = false;
        }
        if (!(this.foundationPile.get(destPileNumber).isMoveValid(moveCard))) {
          isValid = false;
        }
        break;
      case OPEN:
        if (destPileNumber >= openPile.size()) {
          isValid = false;
        }
        if (!(this.openPile.get(destPileNumber).isMoveValid(moveCard))) {
          isValid = false;
        }
        break;
      case CASCADE:
        if (this.cascadePile.get(destPileNumber).cards.size() == 0) {
          isValid = true;
          break;
        }
        if (destPileNumber >= cascadePile.size()) {
          isValid = false;
        }
        if (!(this.cascadePile.get(destPileNumber).isMoveValid(moveCard))) {
          isValid = false;
        }
        break;
      default:
        throw new IllegalArgumentException("Invalid PileType");
    }
    return isValid;
  }

  /**
   * Gets the card from the top of the Pile.
   *
   * @param sourceType       The source Type
   * @param sourcePileNumber The pile number
   * @param cardIndex        The card Index
   * @return the intended card
   */
  private Card getTheCard(PileType sourceType, int sourcePileNumber, int cardIndex) {
    Card moveCard;
    switch (sourceType) {
      case FOUNDATION:
        moveCard = this.foundationPile.get(sourcePileNumber).removeCard(cardIndex);
        break;
      case OPEN:
        moveCard = this.openPile.get(sourcePileNumber).removeCard(cardIndex);
        break;
      case CASCADE:
        moveCard = this.cascadePile.get(sourcePileNumber).removeCard(cardIndex);
        break;
      default:
        throw new IllegalArgumentException("Invalid PileType");
    }
    return moveCard;
  }

  protected Card peekTheCard(PileType sourceType, int sourcePileNumber, int cardIndex) {
    Card peekCard;
    switch (sourceType) {
      case FOUNDATION:
        peekCard = this.foundationPile.get(sourcePileNumber).peek(cardIndex);
        break;
      case OPEN:
        peekCard = this.openPile.get(sourcePileNumber).peek(cardIndex);
        break;
      case CASCADE:
        peekCard = this.cascadePile.get(sourcePileNumber).peek(cardIndex);
        break;
      default:
        throw new IllegalArgumentException("Invalid PileType");
    }
    return peekCard;
  }


  /**
   * Places the card at the top of the Pile.
   *
   * @param moveCard       the card to be moved.
   * @param destType       the destination pile to be moved to.
   * @param destPileNumber the destination pile number to be moved to.
   */
  private void placeTheCardOnTop(Card moveCard, PileType destType, int destPileNumber) {
    switch (destType) {
      case FOUNDATION:
        this.foundationPile.get(destPileNumber).placeOnTop(moveCard);
        break;
      case OPEN:
        this.openPile.get(destPileNumber).placeOnTop(moveCard);
        break;
      case CASCADE:
        this.cascadePile.get(destPileNumber).placeOnTop(moveCard);
        break;
      default:
        throw new IllegalArgumentException("Invalid PileType");
    }
  }

  /**
   * Counts the number of Free Open Cells in the Piles.
   *
   * @return The number of FreeCells
   */
  protected int countFreeOpen() {
    int freeCells = 0;
    for (Pile pile : openPile) {
      freeCells = freeCells + pile.howManyFreeCells();
    }
    return freeCells;
  }

  /**
   * Counts the number of Free Cascade Cells in the Piles.
   *
   * @return The number of Free Cascade Cells
   */
  protected int countFreeCascade() {
    int freeCells = 0;
    for (Pile pile : cascadePile) {
      freeCells = freeCells + pile.howManyFreeCells();
    }
    return freeCells;
  }


  /**
   * Counts the number of cards to be moved.
   *
   * @param sourceType       the source pile type
   * @param sourcePileNumber the source pile number
   * @param cardIndex        the index of the card
   * @return the number
   */
  protected int countCardsToMove(PileType sourceType, int sourcePileNumber, int cardIndex) {
    switch (sourceType) {
      case CASCADE:
        return this.cascadePile.get(sourcePileNumber).howManyFromIndex(cardIndex);
      case FOUNDATION:
        return this.foundationPile.get(sourcePileNumber).howManyFromIndex(cardIndex);
      case OPEN:
        return this.openPile.get(sourcePileNumber).howManyFromIndex(cardIndex);
      default:
        throw new IllegalArgumentException("Invalid PileType");
    }
  }


  /**
   * Checks to see if a multimove is valid, which is different from the SINGLEMOVE because this
   * one allows movement of any cards to empty Cascade Cells.
   *
   * @param sourceType       the source pile type
   * @param sourcePileNumber the source pile number
   * @param cardIndex        the index of the card
   * @param destType         the destination pile type
   * @param destPileNumber   the destination pile number
   * @param moveCard         the card to be moved
   * @return true if it can be moved
   */
  protected boolean isMultiMoveValid(PileType sourceType, int sourcePileNumber, int cardIndex,
                                     PileType destType, int destPileNumber, Card moveCard) {
    boolean isValid = true;
    switch (sourceType) {
      case FOUNDATION:
        if (sourcePileNumber >= foundationPile.size()) {
          isValid = false;
        }
        if (cardIndex != foundationPile.get(sourcePileNumber).cards.size() - 1) {
          isValid = false;
        }
        break;
      case OPEN:
        if (sourcePileNumber >= openPile.size()) {
          isValid = false;
        }
        if (cardIndex != 0) {
          isValid = false;
        }
        break;
      case CASCADE:
        if (sourcePileNumber >= cascadePile.size()) {
          isValid = false;
        }
        break;
      default:
        throw new IllegalArgumentException("Invalid PileType");
    }

    switch (destType) {
      case FOUNDATION:
        if (destPileNumber >= foundationPile.size()) {
          isValid = false;
        }
        if (!(this.foundationPile.get(destPileNumber).isMoveValid(moveCard))) {
          isValid = false;
        }
        break;
      case OPEN:
        if (this.countCardsToMove(sourceType, sourcePileNumber, cardIndex) > 1) {
          isValid = false;
        }
        if (destPileNumber >= openPile.size()) {
          isValid = false;
        }
        if (!(this.openPile.get(destPileNumber).isMoveValid(moveCard))) {
          isValid = false;
        }
        break;
      case CASCADE:
        if (this.cascadePile.get(destPileNumber).cards.size() == 0) {
          isValid = true;
          break;
        }
        if (destPileNumber >= cascadePile.size()) {
          isValid = false;
        }
        if (!(this.cascadePile.get(destPileNumber).isMoveValid(moveCard))) {
          isValid = false;
        }
        break;
      default:
        throw new IllegalArgumentException("Invalid PileType");
    }
    return isValid;
  }

  /**
   * Gets all the cards to be moved.
   *
   * @param sourceType       The source pile type
   * @param sourcePileNumber The source pile number
   * @param cardIndex        The card indec
   * @return An ArrayList of cards to be moved
   */
  protected ArrayList<Card> getCards(PileType sourceType, int sourcePileNumber, int cardIndex) {
    ArrayList<Card> myCards = new ArrayList<>();
    switch (sourceType) {
      case FOUNDATION:
        myCards.add(this.foundationPile.get(sourcePileNumber).removeCard(cardIndex));
        break;
      case OPEN:
        myCards.add(this.openPile.get(sourcePileNumber).removeCard(cardIndex));
        break;
      case CASCADE:
        myCards = this.cascadePile.get(sourcePileNumber).removeCards(cardIndex);
        break;
      default:
        throw new IllegalArgumentException("Invalid Type");
    }
    return myCards;
  }

  /**
   * Moves the cards to the destination.
   *
   * @param newCards       the cards to the destination
   * @param destType       the destination pile type
   * @param destPileNumber the destination pile number
   */
  protected void appendCardsToDestination(ArrayList<Card> newCards, PileType destType, int
          destPileNumber) {
    switch (destType) {
      case FOUNDATION:
        this.foundationPile.get(destPileNumber).placeCardsOnTop(newCards);
        break;
      case OPEN:
        this.openPile.get(destPileNumber).placeCardsOnTop(newCards);
        break;
      case CASCADE:
        this.cascadePile.get(destPileNumber).placeCardsOnTop(newCards);
        break;
      default:
        throw new IllegalArgumentException("Invalid PileType");
    }
  }
}


