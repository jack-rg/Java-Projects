import java.util.*;
public class BlackJack{
  public static void main(String args[]){
  Scanner in = new Scanner(System.in);
    int player1, player2, newCard, dealer1, dealer2, playerTotal, dealerTotal;
    String hitStay;
      
    //player cards and total 
    player1 = (int)((Math.random()*10+2));
    player2 = (int)((Math.random()*10+2));
    System.out.println("Card 1 is " +  player1 + "." + " Card 2 is " +  player2 + ".");
    playerTotal = player1 + player2;
    System.out.println("Your card total is " + playerTotal + ".");
      
    //dealer cards
    dealer1 = (int)((Math.random()*10+2));
    dealer2 = (int)((Math.random()*10+2));
    System.out.println("The card that the dealer has is " + dealer1  + ".");
    dealerTotal = dealer1 + dealer2;
  
     
    //*****Fill in here ******
      
    //Player hit
    System.out.println("Would you like to hit? (yes/no)");
    hitStay = in.nextLine();
    while( playerTotal < 21 && hitStay.equals("yes")){
      newCard = (int)((Math.random()*10+2));
      playerTotal = playerTotal + newCard;
      System.out.println("The player has a " + newCard   + " for a total of " + playerTotal +".");
      if( playerTotal<21){
        System.out.println("Would you like to hit? (yes/no)");
        hitStay = in.nextLine();        
      }
    }
      
    //Dealer hit
    while( dealerTotal < 17 && playerTotal <=21){
      newCard = (int)((Math.random()*10+2));
      dealerTotal = dealerTotal + newCard;
      System.out.println("The dealer has a " + newCard   + " for a total of " + dealerTotal +".");
    } 
   
    
    System.out.println("The Player total is " + playerTotal);
    System.out.println("The Dealter total is " + dealerTotal);
    //Bust or Win or Push
    if(playerTotal > 21)
      System.out.println("The dealer wins.");
    else if(playerTotal == dealerTotal)
      System.out.println("Push.");
    else if(playerTotal <= 21 && dealerTotal > 21)
      System.out.println("The player wins.");
    else if(playerTotal > dealerTotal)
      System.out.println("The player wins.");
    else if(playerTotal < dealerTotal)
      System.out.println("The dealer wins.");
  }
  }