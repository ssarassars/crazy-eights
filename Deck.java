
//This problem requires us to complete a Deck class that models a deck of cards to be used in a card game.//


import java.util.Random;


//Method requires Deck to be 52 cards plus 2 jokers created
public class Deck{
  
    private final static String[] RANKS = {
    "2", "3", "4", "5", "6", "7", "8", "9", "10", 
    "Jack", "Queen", "King", "Ace"};
    
  private final static String[] SUITS = { "Diamonds", 
    "Clubs", "Hearts", "Spades"};

  private Card[] cards;
  /* creates a deck of 54 cards as described in the Cards problem 
   * (52 cards that are in the standard deck of cards plus two jokers)
   */
  public Deck(){
    this(1, 2);
  }

  /* create a deck with specified number of copies of the 
   * standard deck (52 cards) and with the specified 
   * number of jokers 
   * 
   * Preconditions: inputs must be non-negative
   */
  //Inputs: numStandards and Numjokers as integer values
  //Returns a deck of cards with the right number of each suit, rank and jokers
 //postconditions: indexed suit/rank of cards
  public Deck(int numStandard, int numJokers){
    if (numStandard >= 0 && numJokers >= 0) {
      int numCards = numStandard * 52 + numJokers;
      
       this.cards = new Card[numCards]; 
       int index = 0;
       
      for (int i=0; i < numStandard; i++) {
        for (int j=0; j < SUITS.length; j++) {
            for (int k=0; k < RANKS.length; k++) {
              this.cards[index] = new MyCard (RANKS[k], SUITS[j]);
              index++;
            }
          }
        }
      
      for (int i =0; i<numJokers; i++) {
          this.cards[index] = new MyCard ("Joker", "None");
          index++;
      }
      }
  }
  
  
  
  /* Purpose: returns the number of cards left in the deck when called */
  public int numCards(){return this.cards.length;}
  
  
  /* purpose: returns all the cards in the deck without modifying the deck 
   * the ordering in the array is the order of the cards at this
   * given time */
  public Card[] getCards(){return this.cards;}
  
  
  /* purpose: return the top card of the deck without modifying the deck */
  public Card peek(){return this.cards[cards.length - 1];}
  
  
  /* purpose: remove the top card from the deck and return it */
  //Side effect, efficient when moving a card to the discard pile during the beginning of crazy eights game
  public Card pop(){
    Card topElement = this.peek();
    Card[] newCards = new Card[this.numCards() - 1];
    
    for (int i=0; i < newCards.length; i++) {
      newCards [i] = this.cards[i];
    }

    this.cards = newCards;
    
    return topElement;
  }
  

  /* randomly shuffle the order of the cards in the deck */
  //Inputs and returns: nil, returns a shuffled deck
  //Preconditions: ordered cards in deck
  //Postconditons: Shuffled such that the higher rank cards switch with the lower rank cards
  //Side effects: 52nd card goes to 6th position
  public void shuffleDeck(){
      // Shuffle array
    Random rand = new Random ();
    for (int i=this.numCards(); i > 1; i--) {
      int randomIndex = rand.nextInt(i);
      this.swapCard(i-1, randomIndex);
    }
  }
  //Swap cards between two positions
  //Inputs and returns: Inputs two position indexes as integers and returns a swapped deck
  private void swapCard (int i, int j) {
      Card temp = this.cards[i];
      this.cards[i] = this.cards[j];
      this.cards[j] = temp;
  }
  /* sorts the deck so that cards are in the order 
   * specified in the Cards problem (low cards first)
   */
  //No input parameters, returns deck such that the high value cards are at the top of deck and low value cards are at bottom//
  //
  public void sortDeck(){
    for (int i=0; i<this.numCards(); i++) {
      for (int j=0; j<this.numCards(); j++) {
        if (this.cards[i].compareTo(this.cards[j]) < 0) 
          this.swapCard(i,j);
      }
    }
  }
  //Testing
    public static void main (String[] args) {
      Deck standard = new Deck(1,0);
      System.out.println(standard.numCards()); 

      standard.sortDeck(); 

                   for (int i=0; i < standard.numCards(); i++)
            System.out.println(standard.cards[i]);
      Card c = standard.pop();
      System.out.println(c); 

      System.out.println(standard.numCards());
    }
}
