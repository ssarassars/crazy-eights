
//This problem requires us to implement comparable<card> and constructors for rank and suit.//


//purpose: This class extend the Card class
//preconditions: 
public class MyCard extends Card {

  private int rank;
  protected String suit;
  
  public MyCard(String rank, String suit){
// purpose: creates a card with given rank and suit
// preconditions: suit must be a string found in Cards.SUITS
// rank must be a string found in Cards.RANKS
    if (this.contains(SUITS, suit) && this.contains(RANKS, rank)) {
          this.rank = this.getIndexFromList(RANKS, rank);
          this.suit = suit;
    }
  }
  public MyCard(int rank, String suit){
// purpose: creates a card with the given rank and suit
// preconditions: suit must be a string found in Cards.SUITS
// rank is an integer satisfying 1 <= rank <= 14, where
// 1 for joker, 2 for 2, 3 for 3, .., 10 for 10
// 11 for jack, 12 for queen, 13 for king, 14 for ace 
    if (this.contains(SUITS, suit) && rank > 0 && rank <=14) {
          this.rank = rank;
          this.suit = suit;
    }
    
  }
  //Purpose: Getting the index position of string from array list
  //Inputs and returns: Input String array list and String key, return index i
  //Preconditions: list in string array so that position can be index
  private int getIndexFromList (String[] list, String key) {
    for (int i=0; i < list.length; i++) {
      if (list[i] == key)
        return i;
    }
    
    return -1;
  }
  
  //Purpose: Check if list contains a string
  //Inputs and returns: Input String array list and string key, returns boolean true or false 
  //Preconditions: list in string array so that it can be checked if exists
  //Postconditions: Concludes if string is in list
  private boolean contains (String[] list, String key) {
    for (int i=0; i < list.length; i++) {
      if (list[i] == key)
        return true;
    }
    
    return false;
  }
  
  
  //Purpose: Implement the comparable<card> method in the Card class
  //Inputs & returns: Input card card and returns value
  //Side effects: Compare  rank, suit, index
  @Override
  public int compareTo (Card card) {
    int currentCardIndex = this.getIndexFromList(this.SUITS, this.getSuit());
    int cardIndex = this.getIndexFromList(this.SUITS, card.getSuit());
    
    int value = ((Integer)(currentCardIndex)).compareTo(cardIndex);
    if (value == 0) {
           value = ((Integer)this.getRank()).compareTo(card.getRank());
    }
    return value;
  }
  public int getRank(){
// Purpose: Get the current card’s rank as an integer
// Output: the rank of the card
// joker -> 1, 2 -> 2, 3 -> 3, ..., 10 -> 10
// jack -> 11, queen -> 12, king -> 13, ace -> 14 
    return this.rank;
  }
  public String getRankString(){
// Purpose: Get the current card’s rank as a string
// Returns the cards’s rank as one of the strings in Card.RANKS
// (whichever corresponds to the card)
    
    return RANKS[this.rank];
  }
  
  public String getSuit(){
// Purpose: Get the current card’s suit
// Returns the card’s suit as one of the strings in Card.SUITS
// (whichever corresponds to the card)
    
      return this.suit;
  }
  //Testing
  public static void main (String[] args) {
    MyCard c = new MyCard("Queen", "Diamonds");
    System.out.println(c.getRank());
    System.out.println(c.getRankString());
    System.out.println(c.getSuit());
    System.out.println(c);
    Card d = new MyCard("4", "Spades");
     System.out.println(c.compareTo(d));
     System.out.println(d.compareTo(c));
     
     MyCard e = new MyCard("Jack", "Spades"); 
          System.out.println(d.compareTo(e));
     System.out.println(e.compareTo(e));
         System.out.println(e.getRank());
    System.out.println(e.getSuit());
    
    Card j = new MyCard(1, "None");
    System.out.println(j); 
        System.out.println(j.getRank());
    System.out.println(j.getRankString());
    System.out.println(j.getSuit());
  }

}
