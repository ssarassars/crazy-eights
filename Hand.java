
//This problem requires us to complete a Hand class that IMPLEMENTS the HandActions class and models a players hand in the game also representing the player//
// A hand will consist of the cards that the player has and actions that the player can take during their turn in the game//

//Hand class implements HandActions class
public class Hand implements HandActions {
 
  public static final boolean simpleLogic = true;
  
  private Card[] cards;
  public Hand(Card[] cards) {
    this.cards = cards;
    
    
  }
// initialize hand with the given input cards
    
  public Card[] sortHand(){
    return null;
  }
  
  public Card[] displayHand(){
    return null;
  }
    
  public Card playCard(Card[] pile){
// purpose: to play a card in the game
// input: pile is the top card of the discard pile (a single card)
// output: returns null if the player either needs to take a card from the deck
// or chooses to take a card from the deck.
// otherwise, the player has a (valid) card in their hand
// to play on the top card and returns this card.
// side effects: when the method returns a card that card is removed from their hand.
    
    Card topCard = pile[pile.length - 1];
    for (int i=0; i < cards.length; i++) {
      Card playerCard = cards[i];
      if ((playerCard.getRank() == topCard.getRank()) || (playerCard.getSuit() == topCard.getSuit()) || (playerCard.getRank() == 8) || playerCard.getRank() == 1){
        this.pop(playerCard, i);
        return playerCard; 
      }
    }
    
    return null;
  }
  
  public Card peek () {
    return cards[cards.length - 1];
  }
  
    /* remove the top card from the deck and return it */
  //Inputs and returns: Input card c and index of card position as integers, returns card c
  //Preconditions: Cards are added to hand
  //Postconditions: cards go to discard pile
  public Card pop(Card c, int index){
    Card[] newCards = new Card[cards.length -1];
    for (int i=index+1; i < this.cards.length; i++) {
      Card temp = this.cards[index];
      this.cards[index] = this.cards[i];
      this.cards[i] = temp;
      index = i;
    }
    //Copy previous array elements 
    System.arraycopy( this.cards, 0, newCards, 0, newCards.length );
    this.cards = newCards;
    return c;
  }
  
  public Card[] getCards () {
    return this.cards;
  }
  
  //New added array of cards in hand
  //Input and returns: inputs card in array of cards, returns null
  //preconditions: Cards in array exists in indexed form
  //postconditions: Cards are added to the currently existing array of cards
 public Card[] getCard(Card[] cards){
// add the cards in the input to the hand
// always returns null for our game
    Card[] newCards = new Card [cards.length + this.cards.length];
     
    System.arraycopy( this.cards, 0, newCards, 0, this.cards.length );
    for (int i=0; i <cards.length; i++) {
      newCards[this.cards.length + i] = cards[i];
    }
    
    this.cards = newCards;
    
    return null;
    
  }
 public String message(String question){
// There is only question that will be asked in our game of crazy eights
// (Q: what suit do you want the 8 you just played to be?)
// Any non-null input string will be considered to ask this question.
//
// Purpose: After playing an 8 (of any suit) this method, when called,
// will return one of the four valid suits in Card.SUITS, to define
// what suit the played 8 should be.
//
   // Logic: The suit is changed to the most frequently occuring suit on the player's hand of cards 
   //preconditions: The rank played on the discard pile is 8
   //postconditions: the suit changed to is the most common suit on player's hand
   //side effect: easier to get rid of cards by playing suit-wise
    String suit = null;
    int maxNUmberOfSuit = 0;
    
    if (question != null) {
      for (int i=0; i<cards.length; i++) {
        int count = 0;
        for (int j=0; j<cards.length; j++) {
          if (cards[i].getSuit().equals(cards[j].getSuit())) {
            count++;
          }
        }
        
        if (count >= maxNUmberOfSuit) {
          suit = cards[i].getSuit();
        }
      }
    }
    
    return suit;
  }
 //Print the array of cards each player has at the beginning of the game and after each play in the Game class
 public void print(){
   System.out.print("[");
   for (int i=0; i<cards.length; i++){
     System.out.print(cards[i] + ",");
   }
   System.out.println("]");
 }

 //testing
     public static void main (String[] args) {
       Hand hand = new Hand( new Card[]{new MyCard(3, "Spades"), new MyCard(8, "Hearts")}); 
       System.out.println(hand.playCard(new Card[]{new MyCard(7, "Diamonds")}));
       

       System.out.println(hand.message("any string"));
       System.out.println(hand.playCard(new Card[]{new MyCard(2, "Spades")}));
       
              for (int i=0; i < hand.cards.length; i++)
         System.out.println(hand.cards[i]);
       System.out.println(hand.playCard(new Card[]{new MyCard(6, "Spades")}));
       System.out.println(hand.getCard(new Card[]{ new MyCard(3, "Clubs")}));
       System.out.println(hand.playCard(new Card[]{new MyCard(3, "Spades")}));
      
         
     }
}
