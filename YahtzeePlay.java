/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 */
import java.awt.Color;
import info.gridworld.grid.*;
import info.gridworld.grid.Location;
import info.gridworld.world.World;
import java.util.*;


public class YahtzeePlay extends World<Cell>
{
   int[] dice;
   int[] selected;
   int[] score;
   int[] scoreSelected;
   int rollEvent=0;
   
   public YahtzeePlay() {
     super(new BoundedGrid<Cell>(20,30));
     buildBoard();
     dice = new int[5];
     selected = new int[5];
     score = new int[14];
     scoreSelected = new int[14];
     fillScoreBoard();
     findFillTotal();
     setSelected();
   }
   
   
   //param "loc" is where the mouse is clicked
   public boolean locationClicked(Location loc)
   {
      int sel = loc.getCol();
      if (sel<=5) selected[sel] = selected[sel] * -1;
      showDice();

      return true;      
   }
   

   //param 'description is the STRING version of the key pressed
   //param 'loc' is the selected location when the key is pressed 
   public boolean keyPressed(String description, Location loc)
   { 
       setMessage(description);
       if (description.equals("SPACE")&& rollEvent<2){
         rollDice();
         showDice();
         rollEvent++;
       }
       else if(description.equals("1") && rollEvent==2 && scoreSelected[1]==0){
         checkNum(1);
         dashDice();
       }
       else if(description.equals("2")&& rollEvent==2 && scoreSelected[2]==0){
         checkNum(2);
         dashDice();
       }
       else if(description.equals("3")&& rollEvent==2 && scoreSelected[3]==0){
         checkNum(3);
         dashDice();
       }
       else if(description.equals("4")&& rollEvent==2&& scoreSelected[4]==0){
         checkNum(4);
         dashDice();
       }
       else if(description.equals("5")&& rollEvent==2&& scoreSelected[5]==0){
         checkNum(5);
         dashDice();
       }
       else if(description.equals("6")&& rollEvent==2&& scoreSelected[6]==0){
         checkNum(6);
         dashDice();
       }
       else if(description.equals("T")&& rollEvent==2&& scoreSelected[7]==0){
         checkThreeKind();
         dashDice();
       }
       else if(description.equals("F")&& rollEvent==2&& scoreSelected[8]==0){
         checkFourKind();
         dashDice();
       }
       else if(description.equals("H")&& rollEvent==2&& scoreSelected[9]==0){
         checkFullHouse();
         dashDice();
       }
       else if(description.equals("S")&& rollEvent==2&& scoreSelected[10]==0){
         checkSmallStraight();
         dashDice();
       }
       else if(description.equals("L")&& rollEvent==2&& scoreSelected[11]==0){
         checkLargeStraight();
         dashDice();
       }
       else if(description.equals("C")&& rollEvent==2&& scoreSelected[13]==0){
         checkChance();
         dashDice();
       }
       else if(description.equals("Y")&& rollEvent==2&& scoreSelected[12]==0){
         checkYahtzee();
         dashDice();
       }
       else
           return false;
       
       fillScoreBoard();
       findFillTotal();
       return true;
   }
 
   
   //*****************************
   //***** CODE DOWN HERE ********
   //***************************** 
   
   
    //FILL the selected array with all -1s
    public void setSelected(){
      for(int x=0; x<selected.length; x++)
       selected[x]=-1;

    }  
   
   //Fill up the dice arrray spots
   //whose selected array's spots are 
   //equal to -1 with random numbers from 1 to 6
   public void rollDice(){
      for(int y=0; y<selected.length; y++){
      if( selected[y]==-1 )
        dice[y]=(int)((Math.random()*6)+1);
   }
   }
   //scoring
   //put the value in the parameter POINTS into the INDEX location of the score array
   //set the INDEX location of the scoreSelected array to a positive integer
   public void fillScore(int points, int index){
    score[ index] = points;
    scoreSelected[ index ] = 4;


   }
   
   //count how many times 'num' appears
   //in the dice array.  Fill the 'num' index
   //of the score array with ONLY the total
   //of the dice equal to 'num'
   //Example.  if 'num' where 5, only sum all the FIVES
   //send the sum and the index to be filled to the
   //fillScore method.  Ex.  fillScore( total, num)
   public void checkNum(int num){
     int[] tally = new int[7];
     
     for(int x=0; x<5; x++){
        tally[dice[x]]++;
     }
     int sum=num*tally[num];
 fillScore(sum, num);

   }
   
  
   //IF you have at least 3 of a kind
   //add the total of the dice in the dice array
   //and place that total in the 7th index of the score array using the fillScore method
   public void checkThreeKind(){
  int[] tally2 = new int[7]; 
     int total=0;
     int full=0;
     for(int z=0; z<5; z++){
       total=total+dice[z];
     }
     
     for(int x=0; x<5; x++){
        tally2[dice[x]]++;
     }
     
     for(int y=1; y<7; y++){
       if(tally2[y]>=3){
         full++;
       }
     }
       if(full>0)
          fillScore(total, 7);
        else
       fillScore(0, 7);

   }
   
   //IF you have at least 4 of a kind
   //add the total of the dice in the dice array
   //and place that total in the 8th index of the score array using the fillScore method   
   public void checkFourKind(){
   int[] tally3 = new int[7]; 
     int total=0;
     int full=0;
     for(int z=0; z<5; z++){
       total=total+dice[z];
     }
     
     for(int x=0; x<5; x++){
        tally3[dice[x]]++;
     }
     
     for(int y=1; y<7; y++){
       if(tally3[y]>=4){
         full++;
       }
     }
     if(full>0)
       fillScore(total, 8);
     else
       fillScore(0, 8);
     

   }
   
   //IF you have a FULL house in the dice array, EXACTLY 2 and 3 of a kind
   // place 25 in the 9th index of the score array using the fillScore method
   public void checkFullHouse(){
   int[] tally2 = new int[7]; 
     int total=0;
     int full=0;
     for(int z=0; z<5; z++){
       total=total+dice[z];
     }
     
     for(int x=0; x<5; x++){
        tally2[dice[x]]++;
     }
     
     for(int y=1; y<7; y++){
       if(tally2[y]==3){
       for(int c=1; c<7; c++){
         if(tally2[c]==2){
        full++;
       }
      }
     }
     }
     if(full>0)
      fillScore(25, 9);
            else
       fillScore(0, 9);

   }
   
   
   //IF you have a SMALL straight in the dice array, place 30 in the
   //10th index of the score array  using the fillScore method
   public void checkSmallStraight(){
   int[] tally = new int[7]; 
     
     int full=0;

     //tally
     for(int x=0; x<5; x++){
        tally[dice[x]]++;
     }
     for(int y=1; y<4; y++){   
       if(tally[y]>0){
         if(tally[y+1]>0){
           if(tally[y+2]>0){
             if(tally[y+3]>0){
                full++;
             }
           }         
         }          
       }            
     }
    
     if(full>=1)
       fillScore(30, 10);
        else
       fillScore(0, 10);

   }
   
   
   //IF you have a LARGE straight in the dice array, place 40 in the 
   //11th index of the score array using the fillScore method
   public void checkLargeStraight(){
    int[] tally = new int[7]; 
     
     int full=0;

     //tally
     for(int x=0; x<5; x++){
        tally[dice[x]]++;
     }
     for(int y=1; y<3; y++){   
       if(tally[y]>0){
         if(tally[y+1]>0){
           if(tally[y+2]>0){
             if(tally[y+3]>0){
               if(tally[y+4]>0){
                full++;
             }
           }         
         }          
       }            
     }
   }
     if(full>=1)
       fillScore(40, 11);
        else
       fillScore(0, 11);

   }
   
   
   
   //IF you have 5 of a KIND in the dice array, YAHTZEE, place 50
   //in the 12th index of the score array using the fillScore method
   public void checkYahtzee(){
       int[] tally4 = new int[7]; 
     int total=0;
     int full=0;
     for(int z=0; z<5; z++){
       total=total+dice[z];
     }
     
     for(int x=0; x<5; x++){
        tally4[dice[x]]++;
     }
     
     for(int y=0; y<6; y++){
       if(tally4[y]==5){
       full++;
       }
     }
         
         if(full>0)
         fillScore(50, 12);
     else
       fillScore(0, 12);

   }
   
   
   
   //TOTAL all 5 dice in the dice array and place that amount
   //into the 13th index of the score array using the fillScore method
   public void checkChance(){
        int total=0;
  for(int z=0; z<5; z++){
    total=total+dice[z];
  }
       
     fillScore(total, 13);


   }

   //find the total of the score array and 
   //put that amount next to the TOTAL label on
   //the screen.
   //HINT: Use the code from fillScoreBoard() to help you out
    public void findFillTotal(){
     Grid<Cell> gr = getGrid();
     int sum = 0;
    

    

     


//find the total of the score array, spots 1 to 13
     
     for(int y=1; y<score.length; y++)
       sum=sum+score[y];
     
      String s = sum+"";
       //make a substring of each digit in s and print them.
     String ones="";
     String tens="";
     String hundreds="";
     
     
     if(s.length()==3){
      ones = s.substring(2);
      tens = s.substring(1,2);
      hundreds = s.substring(0,1);
      
     Location loc1 = new Location( 17, 15);
     Cell cel1 = new Cell( hundreds, Color.GREEN);
     gr.put(  loc1,  cel1);
     
     Location loc2 = new Location( 17, 16);
     Cell cel2 = new Cell( tens, Color.GREEN);
     gr.put(  loc2,  cel2);
     
     Location loc3 = new Location( 17, 17);
     Cell cel3 = new Cell( ones, Color.GREEN);
     gr.put(  loc3,  cel3);
     }
     
     
     
     
     if(s.length()==2){
     ones = s.substring(1);
     tens = s.substring(0,1);
     
     Location loc2 = new Location( 17, 16);
     Cell cel2 = new Cell( tens, Color.GREEN);
     gr.put(  loc2,  cel2);
     
     Location loc3 = new Location( 17, 17);
     Cell cel3 = new Cell( ones, Color.GREEN);
     gr.put(  loc3,  cel3);
     }
     
     
      if(s.length()==1){
        ones = s.substring(0);
     
     Location loc3 = new Location( 17, 17);
     Cell cel3 = new Cell( ones, Color.GREEN);
     gr.put(  loc3,  cel3);
      }
   }  
   

   
   
   //*******************************
   //******************************
   // REALLY NO NEED TO GO BELOW HERE
   // EXCEPT IF YOU WANT TO SEE HOW STUFF WORKS
   //********************************
   //******************************* 
 

    
   public void fillScoreBoard(){
     Grid<Cell> gr = getGrid();    
     int row = 2;
     int col = 16;
     String s_score="";
     for(int spot=1; spot<score.length; spot++){
       s_score = score[spot] + "";
       if(scoreSelected[spot]>0){
         if(s_score.length()==1){
           gr.put(new Location(row+spot, col ), new Cell("", Color.PINK));
           gr.put(new Location(row+spot, col+1 ), new Cell(s_score, Color.PINK));
         }
         else{
           gr.put(new Location( row+spot, col  ), new Cell(s_score.substring(0,1), Color.PINK));
           gr.put(new Location( row+spot, col+1 ), new Cell(s_score.substring(1), Color.PINK));         
         }
       }
       else{
          gr.put(new Location(row+spot, col ), new Cell("", Color.PINK));
          gr.put(new Location(row+spot, col+1 ), new Cell("-", Color.PINK));
       }
     }
     
   }
   
   
   public void dashDice(){
     Grid<Cell> gr = getGrid();
     int row=6;
     int col=0;
     Color c = Color.WHITE;
     for(int v =0; v<dice.length; v++){
        gr.put(new Location(row, col+v), new Cell("-", c));
     }
     setSelected();
     rollEvent=0;
   }
   
   
   public void showDice(){
     Grid<Cell> gr = getGrid();
     int row=6;
     int col=0;
     Color c = Color.BLUE;
     for(int v =0; v<dice.length; v++){
       if(selected[v] == -1)
          c = Color.ORANGE;
        else
          c=Color.YELLOW; 
        
        gr.put(new Location(row, col+v), new Cell(dice[v]+"", c));
     }
     
   }    
   
   public void buildBoard(){
     Grid<Cell> gr = getGrid();
     
     //YAHTZEE
     int col = 7;
     String title = "YAHTZEE";
     for(int x=0; x<title.length(); x++){
      Cell next =  new Cell(title.substring(x,x+1) , Color.LIGHT_GRAY);
      gr.put(new Location(0, col + x) , next);
     }
 
     //ROLL BOARD
     
     String roll = "ROLL";
     col=0;
     for(int x=0; x<roll.length(); x++){
      Cell next =  new Cell(roll.substring(x,x+1) , Color.LIGHT_GRAY);
      gr.put(new Location(5, col + x) , next);
     }
     
     //SCORE BOARD
     String score = "SCORE";
     col=13;
     for(int x=0; x<score.length(); x++){
      Cell next =  new Cell(score.substring(x,x+1) , Color.LIGHT_GRAY);
      gr.put(new Location(2, col + x) , next);
     }    
 
     ArrayList<String> cat = new ArrayList<String>();
     cat.add("1's");
     cat.add("2's");
     cat.add("3's");
     cat.add("4's");
     cat.add("5's");
     cat.add("6's"); 
     cat.add("Three of A Kind");
     cat.add("Four of A Kind");
     cat.add("House Full");
     cat.add("Small Straight");
     cat.add("Large Straight");
     cat.add("Yahtzee");
     cat.add("Chance");
     
     int row = 2;
     for(String word: cat){
       col=15-word.length();
       row++;
       for(int x=word.length()-1; x>=0; x--){
          Cell t = null;
          String let = word.substring(x, x+1);
          if(let.equals(" "))
             t = new Cell( let, Color.WHITE);
          else{
             if(x==0)
               t = new Cell( let, Color.YELLOW);
             else
               t = new Cell( let, Color.LIGHT_GRAY);
             }
          gr.put(new Location( row, col+x), t);
        }
     }

     col = 13;
     String dash = "-----";
     for(int x=0; x<dash.length(); x++){
      Cell next =  new Cell(dash.substring(x,x+1) , Color.WHITE);
      gr.put(new Location(16, col + x) , next);
     }

     col = 10;
     String tot = "TOTAL";
     for(int x=0; x<tot.length(); x++){
      Cell next =  new Cell(tot.substring(x,x+1) , Color.LIGHT_GRAY);
      gr.put(new Location(17, col + x) , next);
     }

   }
   

  

   public static void main(String[] args)
   {
      new YahtzeePlay().show();
     
   }
   

}
