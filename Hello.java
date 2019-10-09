import java.util.*;
public class Hello{
  public static void main(String args[]){
  Scanner in = new Scanner(System.in);
  String name;
  int age, future, past;
  System.out.println("Hello, what is your name?");
  name=in.nextLine(); 
  System.out.println("How old are you, " + name + "?");
  age=in.nextInt(); 
  future= age+5;
  past=age-5;
  System.out.println(name + ", you were " + past + " five years ago and will be " + future + " in five years.");
  }
}