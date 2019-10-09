import java.util.*;
public class Animal{
  public static void main(String args[]){
  Scanner in = new Scanner(System.in);
System.out.println("What's your favorite animal?");
    String animal=in.nextLine();
   if(animal.equals("Dragon"))
        System.out.println("Me too!");
      else
    System.out.println(animal + "s are cool, but I prefer dragons!");
  }
}