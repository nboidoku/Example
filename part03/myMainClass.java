//package cs3500.hw03;
//
//
//
//import java.io.StringReader;
//import java.util.List;
//
//import cs3500.hw02.FreeCellModel;
//import cs3500.hw02.Card;
//
///**
// * Created by niiakoboi-doku on 10/7/16.
// */
//public class myMainClass {
//
//  static FreeCellModel model = new FreeCellModel();
//  static List<Card> cards = model.getDeck();
//  public static void main(String[] args) {
//    Readable rd = new StringReader("Q");
//    Appendable ap = new StringBuffer();
//    IFreeCellController controller = new FreeCellController(rd, ap);
//    controller.playGame(cards, model, 8, 4, false);
//
//  }
//}
