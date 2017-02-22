package cs3500.hw04;

import java.util.ArrayList;
import cs3500.hw02.Card;
import cs3500.hw02.FreeCellModel;
import cs3500.hw02.PileType;

/**
 * Multimove FreeCellModel Class.
 */
class MultiMoveModel extends FreeCellModel {

  MultiMoveModel() {
    super();
  }

  @Override
  public void move(PileType sourceType, int sourcePileNumber, int cardIndex, PileType destType,
                   int destPileNumber) throws IllegalArgumentException {

    int freeCascade = this.countFreeCascade();

    int freeOpen = this.countFreeOpen();

    int howManyCardsToMove = this.countCardsToMove(sourceType, sourcePileNumber, cardIndex);

    int maxMovement = (int) ((freeOpen + 1) * Math.pow(2, freeCascade));


    if (howManyCardsToMove > maxMovement) {
      throw new IllegalArgumentException("Not enough free cells to move that many cards");
    }


    Card temp = peekTheCard(sourceType, sourcePileNumber, cardIndex);

    if (!(isMultiMoveValid(sourceType, sourcePileNumber, cardIndex, destType, destPileNumber,
            temp))) {
      throw new IllegalArgumentException("Invalid Multi-Move");
    }

    ArrayList<Card> cardsToMove = getCards(sourceType, sourcePileNumber, cardIndex);

    appendCardsToDestination(cardsToMove, destType, destPileNumber);


  }


}