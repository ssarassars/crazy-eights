
//This problem requires us to combine all the logic compiled in the previous classes to create a Crazy Eights game played between two computer players//
//Previous classes include: Card, MyCard, Deck, HandActions, hand//


public class Game{  
   private Deck deck;
   private Hand[] players;
   private MyCard topCard;
   //Purpose: To get the number of standard deck of cards and jokers 
   //Inputs and returns: Inputs number of players given in the command line argument from the main method, returns the new deck to play with
   //Preconditions: number of players has to be between 2 and 7 and an integer
   //Postconditions: new deck of cards created and starts playing game
   public Game (int numOfPlayers) {
     if(numOfPlayers >= 2 && numOfPlayers <= 5) {
       deck = new Deck (1,2);
     }else {
       deck = new Deck(2,4);
     }
     
     players = new Hand[numOfPlayers];
     
     for (int i=0; i<players.length; i++) {
       players[i] = new Hand (new Card [0]);
     }
     System.out.println("This is the Crazy Eights game! There are " + numOfPlayers + " players!\n" + "      ");
     playGame();
     System.out.println("\nGame over!!!");
   }
   //Purpose: Shuffle deck by calling the shuffleDeck method from the Deck class
   //iNPUTS and returns: calls methods from other classes, returns fairly played game of Crazy Eights
   //Preconditions: Methods from deck, hand and MyCard classes have to abide by game logic
   //Postconditions: Game is played
   public void playGame () {
     //shuffle decks
     deck.shuffleDeck();

     //give each players 8 cards one each at one round by calling the pop method and getCard
     for (int i=0; i < 8; i++) {
       for (int j=0; j <players.length; j++) {
         Card[] array = new Card[1];
         Card c = deck.pop();
         array[0] = c;
         players[j].getCard(array);
         
       }
     }
     //prints initial player hands
     //Purpose: To show suit and rank of each individual 8 cards
     for(int k=0; k<players.length; k++) {
       System.out.println("Player " + k + " has these cards: ");
     players[k].print();
     }
     
     topCard = (MyCard) deck.pop();
     int currentPlayerIndex = 0;
     while (!isGameFinished()) {
       Card potentialNextCard = null;
       
       while (potentialNextCard == null && deck.getCards().length != 0) {
        potentialNextCard = players[currentPlayerIndex].playCard(new Card[]{topCard});
        if (potentialNextCard == null){
         Card c = deck.pop();
         players[currentPlayerIndex].getCard(new Card[]{c});
        }
        }
       
       System.out.println ("\ntopCard is " + topCard);
       System.out.println("Player " + currentPlayerIndex + " has played " + potentialNextCard);
       System.out.println("Player " + currentPlayerIndex + " has " + players[currentPlayerIndex].getCards().length + " cards left.");
       players[currentPlayerIndex].print();
      
       //Check for the four special situations
       //Check for joker card (rank = 1)
       if (potentialNextCard != null && potentialNextCard.getRank() != 1)
        topCard = (MyCard) potentialNextCard;
       //Purpose: Check for rank 2 card
       //Postconditions: NEXT player withdraws two cards from deck and skips turn
       if (topCard.getRank() == 2) {
         System.out.println("\n 2 is played!!!\n" + "Next player must pick up two cards! Next player's turn is skipped!\n");
         for (int i=0; i < 2; i++) {
            Card c = deck.pop();
            players[currentPlayerIndex].getCard(new Card[]{c});
         }
          
         //skip the next player 
          currentPlayerIndex++;
      //Purpose: Check for rank 4 card
       //Postconditions: next player misses turn
       }else if (topCard.getRank() == 4) {
          currentPlayerIndex++;
          System.out.println("\n 4 is played!!!\n" + "Next player's turn is skipped!\n");
          //Purpose: Check for rank 8 card
          //Postconditions: The suit can be determined by the player
          //Sideeffect: Calling suit method
       }else if (topCard.getRank() == 8) {
         topCard.suit = players[currentPlayerIndex].message("changeSuit");
         System.out.println("\n 8 is played!!!\n" + "Suit is changed to whichever suit the player wants!\n");
       }
    currentPlayerIndex = getIndexOfNextPlayer(currentPlayerIndex);

     }
     
   }
   //Getting the player number whose turn is next
   public int getIndexOfNextPlayer (int indexOfCurrentPlayer) {
     if (indexOfCurrentPlayer == players.length-1) {
       return 0;
     }
     
     return (indexOfCurrentPlayer % players.length) + 1;
   }
   //Purpose: checking is game has finished
   //Inputs and returns: Returns true or false boolean
   //Preconditions: game is finisehd when deck is empty or when a player hand is empty(player has won)
   //Postconditions: Game continues if game is not finished
   //Side effect: Prevents unfair play
   public boolean isGameFinished () {
     if (deck.getCards().length == 0) {
       System.out.println("Deck is empty!");
       return true;
     }
      
     
     for (int i=0; i < players.length; i++) {
       if (players[i].getCards().length == 0) {
         System.out.println("Player " + i + " has won!!!");
         return true;
       }
     }
     
     return false;
   }
   
   public void print () {
     for (int i=0; i<players.length; i++) {
       System.out.println("======================Player " + i + "===============\n");
       System.out.println(players[i].getCards().length);
       for (int j=0; j<players[i].getCards().length; j++) 
         System.out.println (players[i].getCards()[j]);
     }
   }
   //Main method inputs the number of players and exits if an incorrect value is entered
   public static void main (String[] args) {
     boolean validInput = false;
     if (args.length == 1) {
        int x = Integer.parseInt(args[0]);
        
       if (x>=2 && x<=7){
         Game g = new Game (x);
         //System.out.println (g.players);
         //g.print();
         validInput = true;
       }
     }
       if (!validInput)
         System.out.println("To run the program correctly, you have to enter valid number of players between 2 and 7 inclusive.");
 }
  
 }
   

