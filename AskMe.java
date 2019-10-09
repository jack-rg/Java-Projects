import java.util.*;
public class AskMe{
  public static void main(String args[]){
  Scanner in = new Scanner(System.in);
  //declare variables
  String question, choice1, choice2, askme;
  choice1="";
  choice2="";
  
  
  System.out.println("My name is Sir Lord Jackson III. Go ahead, ask me anything!");
  askme="Would you like to ask a question? (yes/no)";
  System.out.println(askme);
   question=in.nextLine(); 
   
   while(question.equals("yes")){
           System.out.println("Would you like to ask my: name, age, or favorites?");
           choice1=in.nextLine(); 
  
  
           if(choice1.equals("name"))
           System.out.println("I told you my name is Sir Lord Jackson III. Can't you read????");
       
       
            else if(choice1.equals("age"))
            System.out.println("I am a billion years old. So old that when I went to school, there was no 'History' class.");
         
  
             else if(choice1.equals("favorites")){
                 System.out.println("Would you like to ask my favorite: animal, color, person, game, movie, or subject?");
                 choice2=in.nextLine();
                 
                 if(choice2.equals("animal"))
                      System.out.println("Dragons. Because they are big. And they can fly. And they can burn down the homes of my enemies.");
                 
                 
                 else if(choice2.equals("color"))
                      System.out.println("The color of the middle of the sun. When you get the chance, try to stare at it all you can, it's beautiful.");
                         
                 
                  else if(choice2.equals("person")){
                  System.out.println("You ;)");
                  for(int x=0;x<15;x++)  
                  System.out.println("...");
                  System.out.println("jk it's me.");
                                                    }
                  else if(choice2.equals("game"))
                    System.out.println("I like the one where you lose. Also, Tetris is pretty cool if you think about it.");
                         
                   else if(choice2.equals("movie"))
                     System.out.println("I like the one with the people that do that thing in that place. I don't remember the name, but don't worry, it was voted 'America's #1 movie' by the NY Times, very unique.");
                  
                   else if(choice2.equals("subject"))
                     System.out.println("I don't like any subject. I didn't go to school. I am school. Your homework is due to me tomorrow. Automatic F. You fail. Great job.");
                   
                   else
                     System.out.println("I gave you options, you fool. Use them.");
             }
             else
             System.out.println("I gave you options, you fool. Use them.");
  System.out.println(askme);
  question=in.nextLine();   
  
   }
             
   System.out.println("I am the almighty Lord and my answers are law. Jolly Good Show!");
       }
  }
