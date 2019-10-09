back
backbimport java.util.Scanner;
public class Game
{
    boolean intro = true;
    public static void main(String[] args){
       Game game = new Game();
       game.fullGame();
     }
    String ingredients = "";
    int ingredientCount = 0;
    //METHODS
    public int getItemOrgin(String item){
       if(item.equalsIgnoreCase("SPIDER EYE")){
          return 10;
       }else if(item.equalsIgnoreCase("ICE SAW")){
          return 13;
       }else if(item.equalsIgnoreCase("AXE")){
          return 13;
       }else if(item.equalsIgnoreCase("PINECONE")){
          return 14;
       }else if(item.equalsIgnoreCase("SALAMANDER TAIL")){
          return 19;
       }else{
          return 0; 
       }
    }
    public void delay(double sec){  
      double start = System.currentTimeMillis();
      double end = start + sec*1000; //60 seconds * 1000 ms/sec
      while(System.currentTimeMillis() < end){
          //don't do anything
      }
    }
    public void type(double sec, String word, int n){
      if(n != word.length()){
         double start = System.currentTimeMillis();
         double end = start + sec*1000.0; //60 seconds * 1000 ms/sec
         while(System.currentTimeMillis() < end){
            System.out.print("");
         }
         System.out.print(word.substring(n,n+1));
         n++;
         type(sec, word, n);
       }else{
          System.out.println("");
       }
    }
    int prevOption;
    public boolean hasItem(String item){
       for(int i = 0; i < inventory.length;i++){
          if(inventory[i][0].equalsIgnoreCase(item)){
             return true;
          }
       }
       return false;
    }
    public void removeFromInventory(String item){
       boolean putAway = true;
       if(item.equalsIgnoreCase("FROST POTION") || item.equalsIgnoreCase("FIRE POTION")||item.equalsIgnoreCase("DAMAGE POTION")||item.equalsIgnoreCase("INVISIBILITY POTION")){
          putAway = false;
       }
       if(getItemOrgin(item) == 0 && putAway){
          type(.05,"You have no items named \"" + item + "\".",0);
       }else
        if(getItemOrgin(item) == prevOption || !putAway){
          boolean foundItem = false;
           for(int i = inventory.length-1; i >= 0;i--){
             if(inventory[i][0].equalsIgnoreCase(item)){
                inventory[i][0] = "";
                inventory[i][1] = "";
                foundItem = true;
                type(.05,"You have removed the " + item + " from your inventory.",0);
                break;
             }
          }
          if(!foundItem){
              type(.05,"You have no items named \"" + item + "\".",0);
          }
       }else{
           type(.05,"To remove an item you must be in the same place you found it.",0);
       }
    }
    public void addToInventory(String name,String picture){
        boolean foundSpace = false;
        for(int i = 0; i < inventory.length;i++){
          if(inventory[i][0].length() == 0){
             inventory[i][0] = name;
             inventory[i][1] = picture;
             foundSpace = true;
             type(.05,"You have one new item in your inventory.",0);
             break;
          }
       }
       if(!foundSpace){
           type(.05,"You're inventory is full. If you need more space then use REMOVE [item].",0);  
       }
    }
    String ingredeintInput = "";
    public void addToCauldron(String item, double five, double ten, double fifteen){
       boolean foundItem = false;
       if(hasItem(item)){
          if(five < System.currentTimeMillis() && System.currentTimeMillis() < ten){
            ingredeintInput += "FIVE SECONDS";
            if(ingredients.length() > 0){
                ingredients += " + FIVE SECONDS";
            }else{
                ingredients += "FIVE SECONDS";
            }
          }else if(ten < System.currentTimeMillis() && System.currentTimeMillis() < fifteen){
            ingredeintInput += "TEN SECONDS";
            if(ingredients.length() > 0){
                ingredients += " + TEN SECONDS";
            }else{
                ingredients += "TEN SECONDS";
            }
          }else if(fifteen < System.currentTimeMillis()){
            ingredeintInput += "FIFTEEN SECONDS";  
            if(ingredients.length() > 0){
                ingredients += " + FIFTEEN SECONDS";
            }else{
                ingredients += "FIFTEEN SECONDS";
            }
          }
           for(int i = inventory.length-1; i >= 0;i--){
              if(inventory[i][0].equalsIgnoreCase(item)){
                inventory[i][0] = "";
                inventory[i][1] = "";
                foundItem = true;
                type(.05,"You have removed the " + item + " from your inventory and added to the cauldron.",0);
                break;
             }
          }
         }
       if(!foundItem){
          type(.05,"You have no items named \"" + item + "\".",0);
       }else{
          ingredientCount += 1;
          ingredeintInput += item;
          if(ingredients.equalsIgnoreCase("")){
             ingredients += item;       
          }else{
             ingredients += " + " + item;
          }
       }
    }
    public String potionResult(String combo){
       if(combo.equalsIgnoreCase("SPIDER EYETEN SECONDSSALAMANDER TAILPINECONE")){
          return "FIRE POTION";
       }else if(combo.equalsIgnoreCase("SPIDER EYETEN SECONDSICE CUBEPINECONE")){
          return "FROST POTION";
       }else if(combo.equalsIgnoreCase("PINECONETHORNSRUST")){
           return "DAMAGE POTION";
       }else if(combo.equalsIgnoreCase("GLASS SHARDSFIFTEEN SECONDSRUSTTEN SECONDS")){
           return "INVISIBILITY POTION";
       }else{
          return "NO";
       }
    }
    //OPTIONS
    public void inventoryOption() throws IndexOutOfBoundsException{
       delay(1);
       System.out.print("\f");
       makeInventory();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       try{
          if(Option.equalsIgnoreCase("BACK")){
           goToOption(prevOption);
          }else if(Option.substring(0,6).equalsIgnoreCase("REMOVE")){
             removeFromInventory(Option.substring(7,Option.length()));
             inventoryOption();
          }else if(Option.equalsIgnoreCase("MAIN MENU")){
              type(.05,"Are you sure you want to exit to the main menu? All of your progress will be lost. (YES/NO)",0);
              areYouSureOption();
          }else{
              type(.05,"You may only type one of the words in the scene above.",0);
              inventoryOption();
          }
       }catch(Exception e){
           type(.05,"You may only type one of the words in the scene above.",0);
           inventoryOption();
       }
    }
    public void areYouSureOption(){
        Scanner sc = new Scanner(System.in);
        String Option = sc.nextLine();
        if(Option.equalsIgnoreCase("YES")){
           mainMenuOption();
        }else if(Option.equalsIgnoreCase("NO")){
           inventoryOption();
        }else{
           type(.05,"You may only type one of the words in the scene above.",0);
           areYouSureOption();
        }
    }
    public void goToOption(int opNum){
       if(opNum == 0){
           option1();
       }else if(opNum == 1){
           mainHallOption();
       }else if(opNum == 2){
           castleOption();
       }else if(opNum == 3){
           potionLabOption();
       }else if(opNum == 4){
           shelfOption();
       }else if(opNum == 5){
           potionBookPage1Option();
       }else if(opNum == 6){
           potionBookPage2Option();
       }else if(opNum == 7){
           potionBookPage3Option();
       }else if(opNum == 8){
           potionBookPage4Option();
       }else if(opNum == 9){
           cauldronOption();
       }else if(opNum == 10){
           potionTableOption();
       }else if(opNum == 11){
           fullGame();
       }else if(opNum == 12){
           fullGame();
       }else if(opNum == 13){
           closetOption();
       }else if(opNum == 14){
           rightPathOption(); 
       }else if(opNum == 15){
           houseOption(); 
       }else if(opNum == 16){
           lakeOption();
       }else if(opNum == 17){
           lakeCloseUpOption();
       }else if(opNum == 18){
           takePotionOption();
       }else if(opNum == 19){
           crackOption();
       }
    }
    public void option1(){
        prevOption = 0;
        delay(1);
        System.out.print("\f");
        makeCastle();
        type(.05,"Where would you like to go?",0);
        Scanner sc = new Scanner(System.in);
        String Option = sc.nextLine();
        if(Option.equalsIgnoreCase("DOOR")){
           type(.05,"You open the door...",0);
           mainHallOption();
        }else if(Option.equalsIgnoreCase("CRACK")){
           type(.05,"You walk through the crack in the side of the castle...",0);
           crackOption();
        }else if(Option.equalsIgnoreCase("LEFT")){
           type(.05,"Your way is blocked by a giant wall. A sign is pinned up: 'AREA STILL IN DEVELOPMENT'. You shrug and walk back to the castle.",0);
           castleOption();
        }else if(Option.equalsIgnoreCase("RIGHT")){
           type(.05,"You turn right and walk down the path...",0);
           rightPathOption();
        }else if(Option.equalsIgnoreCase("INVENTORY")){
            inventoryOption();
        }else{
           type(.05,"You may only type one of the words in the scene above.",0);
           option1();
        }
    }
    public void mainHallOption(){
        prevOption = 1;
        delay(1);
        System.out.print("\f");
        makeMainHall();
        Scanner sc = new Scanner(System.in);
        String Option = sc.nextLine();
        if(Option.equalsIgnoreCase("RIGHT DOOR")){
           closetOption();
        }else if(Option.equalsIgnoreCase("LEFT DOOR")){
           potionLabOption();
        }else if(Option.equalsIgnoreCase("CENTER DOOR")){
           type(.05,"You push on the door, but it doesn't budge. It seems to be barricaded from the inside.",0);
           mainHallOption();
        }else if(Option.equalsIgnoreCase("BACK")){
           type(.05,"You turn back and exit the castle...",0);
           castleOption();
        }else if(Option.equalsIgnoreCase("INVENTORY")){
            inventoryOption();
        }else{
           type(.05,"You may only type one of the words in the scene above.",0);
           mainHallOption();
        }
    }
    public void castleOption(){
        prevOption = 2;
        delay(1);
        System.out.print("\f");
        makeCastle();
        Scanner sc = new Scanner(System.in);
        String Option = sc.nextLine();
        if(Option.equalsIgnoreCase("DOOR")){
           type(.05,"You open the door...",0);
           mainHallOption();
        }else if(Option.equalsIgnoreCase("CRACK")){
           type(.05,"You walk through the crack in the side of the castle...",0);
           crackOption();
        }else if(Option.equalsIgnoreCase("LEFT")){
           type(.05,"Your way is blocked by a giant wall. A sign is pinned up: 'AREA STILL IN DEVELOPMENT'. You shrug and walk back to the castle.",0);
           castleOption();
        }else if(Option.equalsIgnoreCase("RIGHT")){
           type(.05,"You turn right and walk down the path...",0);
           rightPathOption();
        }else if(Option.equalsIgnoreCase("INVENTORY")){
            inventoryOption();
        }else{
           type(.05,"You may only type one of the words in the scene above.",0);
           castleOption();
        }
    }
    public void potionLabOption(){
        prevOption = 3;
        delay(1);
        System.out.print("\f");
        makePotionLab();
        Scanner sc = new Scanner(System.in);
        String Option = sc.nextLine();
        if(Option.equalsIgnoreCase("CAULDRON")){
           type(.05,"You walk over to the cauldron.",0);
           cauldronOption();
        }else if(Option.equalsIgnoreCase("SHELF")){
           type(.05,"You walk over to the bookshelf.",0);
           shelfOption();
        }else if(Option.equalsIgnoreCase("TABLE")){
           type(.05,"You walk over to the table.",0);
           potionTableOption();
        }else if(Option.equalsIgnoreCase("BACK")){
           mainHallOption();
        }else if(Option.equalsIgnoreCase("INVENTORY")){
            inventoryOption();
        }else{
           type(.05,"You may only type one of the words in the scene above.",0);
           potionLabOption();
        }
    }
    public void shelfOption(){
        prevOption = 4;
        delay(1);
        System.out.print("\f");
        makeShelf();
        Scanner sc = new Scanner(System.in);
        String Option = sc.nextLine();
        if(Option.equalsIgnoreCase("READ BOOK")){
           type(.05,"You open one of the books...",0);
           potionBookPage1Option();
        }else if(Option.equalsIgnoreCase("BACK")){
           potionLabOption();
        }else if(Option.equalsIgnoreCase("INVENTORY")){
            inventoryOption();
        }else{
           type(.05,"You may only type one of the words in the scene above.",0);
           shelfOption();
        }
    }
    public void potionBookPage1Option(){
       prevOption = 5;
       delay(1);
       System.out.print("\f");
       makePotionBookPage1();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       if(Option.equalsIgnoreCase("BACK")){
           type(.05,"You close the book.",0);
           shelfOption();
       }else if(Option.equalsIgnoreCase("PAGE RIGHT")){
           potionBookPage2Option();
       }else if(Option.equalsIgnoreCase("INVENTORY")){
            inventoryOption();
        }else{
           type(.05,"You may only type one of the words in the scene above.",0);
           potionBookPage1Option();
       }
    }
    public void potionBookPage2Option(){
       prevOption = 6;
       delay(1);
       System.out.print("\f");
       makePotionBookPage2();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       if(Option.equalsIgnoreCase("BACK")){
           type(.05,"You close the book.",0);
           shelfOption();
       }else if(Option.equalsIgnoreCase("PAGE RIGHT")){
           potionBookPage3Option();
       }else if(Option.equalsIgnoreCase("PAGE LEFT")){
           potionBookPage1Option();
       }else if(Option.equalsIgnoreCase("INVENTORY")){
            inventoryOption();
        }else{
          type(.05,"You may only type one of the words in the scene above.",0);
          potionBookPage2Option();
       }
    }
    public void potionBookPage3Option(){
       prevOption = 7;
       delay(1);
       System.out.print("\f");
       makePotionBookPage3();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       if(Option.equalsIgnoreCase("BACK")){
           type(.05,"You close the book.",0);
           shelfOption();
       }else if(Option.equalsIgnoreCase("PAGE RIGHT")){
           potionBookPage4Option();
       }else if(Option.equalsIgnoreCase("PAGE LEFT")){
           potionBookPage2Option();
       }else if(Option.equalsIgnoreCase("INVENTORY")){
            inventoryOption();
        }else{
           type(.05,"You may only type one of the words in the scene above.",0);
           potionBookPage3Option();
       }
    }
    public void potionBookPage4Option(){
       prevOption = 8;
       delay(1);
       System.out.print("\f");
       makePotionBookPage4();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       if(Option.equalsIgnoreCase("BACK")){
           type(.05,"You close the book.",0);
           shelfOption();
       }else if(Option.equalsIgnoreCase("PAGE LEFT")){
           potionBookPage3Option();
       }else if(Option.equalsIgnoreCase("INVENTORY")){
            inventoryOption();
        }else{
          type(.05,"You may only type one of the words in the scene above.",0);
          potionBookPage4Option();
       }
    }
    public void cauldronOption() throws IndexOutOfBoundsException{
       if(ingredientCount >= 3){
          delay(1);
          brewAnimation();
          takePotionOption();
       }
       prevOption = 9;
       delay(1);
       System.out.print("\f");
       makeCauldron();
       double start = System.currentTimeMillis();
       double fiveSec = start + 5000.0;
       double tenSec = start + 10000.0;
       double fifteenSec = start + 15000.0;
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       try{
          if(Option.equalsIgnoreCase("BACK")){
             ingredients = "";
             ingredientCount = 0;
             potionLabOption();
          }else if(Option.substring(0,3).equalsIgnoreCase("ADD")){
              if(hasItem(Option.substring(4,Option.length()))){    
                 addToCauldron(Option.substring(4,Option.length()),fiveSec,tenSec,fifteenSec);
                 cauldronOption();
              }else{
                 type(.05,"You have no items named \"" + Option.substring(4,Option.length()) + "\".",0);
                 cauldronOption();
              }
          }else if(Option.equalsIgnoreCase("INVENTORY")){
             inventoryOption();
          }else{
             type(.05,"You may only type one of the words in the scene above.",0);
             cauldronOption();
          }
       }catch(Exception e){
           type(.05,"You have no items named \"" + Option.substring(4,Option.length()) + "\".",0);  
           cauldronOption();
       }finally{
          type(.05,"You may only type one of the words in the scene above.",0);
          cauldronOption();
       }
    }
    public void takePotionOption(){
       if(potionResult(ingredeintInput).equalsIgnoreCase("NO")){
           System.out.println(ingredients);
           type(.05,"This does not make a potion... just goop.",0);
           delay(1);
           ingredients = "";
           ingredientCount = 0;
           ingredeintInput = "";
           cauldronOption();
       }else{
          String item = potionResult(ingredeintInput);
          prevOption = 18;
          delay(1);
          System.out.print("\f");
          makeTakePotion(item);
          Scanner sc = new Scanner(System.in);
          String Option = sc.nextLine();
          if(Option.equalsIgnoreCase("BACK")){
              type(.05,"You leave the potion.",0);
              ingredients = "";
              ingredientCount = 0;
              ingredeintInput = "";
              cauldronOption();
          }else if(Option.equalsIgnoreCase("TAKE " + item)){
              addToInventory(item,"(\")");
              ingredients = "";
              ingredientCount = 0;
              ingredeintInput = "";
              cauldronOption();
          }else if(Option.equalsIgnoreCase("INVENTORY")){
               cauldronOption();
          }else{
             type(.05,"You may only type one of the words in the scene above.",0);
             takePotionOption();
          }
       }    
    }
    public void potionTableOption(){
       prevOption = 10; 
       delay(1);
       System.out.print("\f");
       makePotionTable();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       if(Option.equalsIgnoreCase("BACK")){
           potionLabOption();
       }else if(Option.equalsIgnoreCase("TAKE EYE")){
           addToInventory("SPIDER EYE","(O)");
           potionTableOption();
       }else if(Option.equalsIgnoreCase("INVENTORY")){
            inventoryOption();
        }else{
          type(.05,"You may only type one of the words in the scene above.",0);
          potionTableOption();
       }
    }
    public void rightPathOption(){
       prevOption = 14;
       delay(1);
       System.out.print("\f");
       makeTrail1();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       if(Option.equalsIgnoreCase("BACK")){
           castleOption();
       }else if(Option.equalsIgnoreCase("TAKE PINECONE")){
           addToInventory("PINECONE","#");
           rightPathOption();
       }else if(Option.equalsIgnoreCase("INVENTORY")){
            inventoryOption();
        }else if(Option.equalsIgnoreCase("ROSE BUSH")){
            type(.05,"You try to gather the thorns, but you end up dropping them as they prick you.",0);
            rightPathOption();
        }else if(Option.equalsIgnoreCase("CONTINUE")){
            type(.05,"You continue down the path.",0);
            houseOption();
        }else{
          type(.05,"You may only type one of the words in the scene above.",0);
          rightPathOption();
       }
    }
    boolean grateOpen = false;
    public void closetOption() throws IndexOutOfBoundsException{
       prevOption = 13;
       delay(1);
       System.out.print("\f");
       makeCloset();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       try{
          if(Option.equalsIgnoreCase("BACK")){
             mainHallOption();
          }else if(Option.substring(0,5).equalsIgnoreCase("THROW") && !grateOpen){
              if(hasItem(Option.substring(6,Option.length()))){
                removeFromInventory("FIRE POTION");
                delay(.5);
                type(.05,"You throw the " + Option.substring(6,Option.length()) + ".",0);
                delay(.5);
                if(Option.substring(6,Option.length()).equalsIgnoreCase("FIRE POTION")){
                   grateOpen = true;
                   System.out.print("\f");
                   makeCloset();
                   type(.05,"The grate's wooden bars turn to ash.",0);
                   closetOption();
                }else{
                   type(.05,"The "+ Option.substring(6,Option.length()) +" has no effect on the ",0);
                   delay(.5);
                   type(.05,"Use a potion more suitable for the grate.",0);
                   closetOption();
                }
              }else{
                type(.05,"You do not have a potion called "+Option.substring(6,Option.length())+" to throw.",0);
                closetOption();
              }
          }else if(Option.equalsIgnoreCase("PULL OFF GRATE")){
             type(.05,"You tug at the grate but it doesn't budge.",0);
             closetOption();
          }else if(Option.equalsIgnoreCase("SAW GRATE") && hasItem("ICE SAW")){
             type(.05,"You try to saw the grate, but the saw does not fit between the bars. Some sort of fire could burn through the wooden bars.",0);
             closetOption();
          }else if(Option.equalsIgnoreCase("INVENTORY")){
             inventoryOption();
          }else if(Option.equalsIgnoreCase("TAKE ICE SAW") && !hasItem("ICE SAW")){
             addToInventory("ICE SAW","<|");
             closetOption();
          }else if(Option.equalsIgnoreCase("REACH IN") && grateOpen){
            if(hasItem("KEY")){
                type(.05,"You reach into the grate. It is empty.",0);
                closetOption();
            }else{
               type(.05,"You reach into the grate and feel a key.",0);
               addToInventory("KEY","0=^=^");
               closetOption();
            }
          }else if(Option.equalsIgnoreCase("TAKE AXE") && !hasItem("AXE")){
             addToInventory("AXE","<|>");
             closetOption();
          }else{
             type(.05,"You may only type one of the words in the scene above.",0);
             closetOption();
          }
       }catch(Exception e){
           type(.05,"You may only type one of the words in the scene above.",0);
           closetOption();
       }
    }
    public void houseOption(){
       prevOption = 15;
       delay(1);
       System.out.print("\f");
       makeHouse();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       if(Option.equalsIgnoreCase("BACK")){
           rightPathOption();
       }else if(Option.equalsIgnoreCase("INVENTORY")){
           inventoryOption();
       }else if(Option.equalsIgnoreCase("CONTINUE")){
           type(.05,"You continue down the path.",0);
           lakeOption();
       }else if(Option.equalsIgnoreCase("HOUSE") && !hasItem("KEY")){
           type(.05,"The door is locked and the windows are boarded up.",0);
           houseOption();
       }else if(Option.equalsIgnoreCase("HOUSE") && hasItem("KEY")){
           type(.05,"You push the key into the lock, but the key won't turn. 'WHAT?!' You say in exasperation. Enraged and heartbroken, you flail around and accidentally shove the key into your own neck.",0);
           type(.05,"Red, warm, blood pours from the open wound. You don't care; the game has cheated you out of your time and hard work.",0);
           type(.05,"Your fists are still pounding on the door as your vision starts to fade. You are becoming weaker by the second.",0);
           type(.05,"You scream your last, and collapse into a pool of your own blood. You die, mouth open, eyes wide, and the key still lodged in your jugular.",0);
           delay(1);
           gameOverOption();
       }else{
           type(.05,"You may only type one of the words in the scene above.",0);
           houseOption();
       }
    }
    public void lakeOption(){
       prevOption = 16;
       delay(1);
       System.out.print("\f");
       makeLake();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       if(Option.equalsIgnoreCase("BACK")){
           houseOption();
       }else if(Option.equalsIgnoreCase("LAKE")){
           lakeCloseUpOption();
       }else if(Option.equalsIgnoreCase("INVENTORY")){
           inventoryOption();
       }else{
           type(.05,"You may only type one of the words in the scene above.",0);
           lakeOption();
       }
    }
    public void lakeCloseUpOption(){
       prevOption = 17;
       delay(1);
       System.out.print("\f");
       makeLakeCloseUp();
       type(.05,"The lake is covered in ice.",0);
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       if(Option.equalsIgnoreCase("BACK")){
           lakeOption();
       }else if(Option.equalsIgnoreCase("SAW ICE") && hasItem("ICE SAW")){
           type(.05,"You saw an ice cube out of the ice covered lake.",0);
           addToInventory("ICE CUBE","[]");
           lakeCloseUpOption();
       }else if(Option.equalsIgnoreCase("INVENTORY")){
           inventoryOption();
       }else{
           type(.05,"You may only type one of the words in the scene above.",0);
           lakeCloseUpOption();
       }
    }
    boolean salamanderDead = false;
    public void crackOption() throws IndexOutOfBoundsException{
       prevOption = 19;
       delay(1);
       System.out.print("\f");
       makeCrack();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       try{
          if(Option.equalsIgnoreCase("BACK")){
              castleOption();
          }else if(Option.equalsIgnoreCase("PET") && !salamanderDead){
              type(.05,"This is not a normal salamander... this is a fire salamander... and you just pet the fire salamander...",0);
              delay(.5);
              type(.05,"Good job.",0);
              gameOverOption();
          }else if(Option.equalsIgnoreCase("INVENTORY")){
              inventoryOption();
          }else if(Option.substring(0,5).equalsIgnoreCase("THROW") && !salamanderDead){
              if(hasItem(Option.substring(6,Option.length()))){
                removeFromInventory("FROST POTION");
                delay(.5);
                type(.05,"You throw the " + Option.substring(6,Option.length()) + ".",0);
                delay(.5);
                if(Option.substring(6,Option.length()).equalsIgnoreCase("FROST POTION")){
                   salamanderDead = true;
                   System.out.print("\f");
                   makeCrack();
                   type(.05,"The potion freezes the fire salamander and it dies.",0);
                   crackOption();
                }else{
                   type(.05,"The "+ Option.substring(6,Option.length()) +" has no effect on the fire Salamander...",0);
                   delay(.5);
                   type(.05,"Use a potion more suitable for this enemy.",0);
                   crackOption();
                }
              }else{
                type(.05,"You do not have a potion called "+Option.substring(6,Option.length())+" to throw.",0);
                crackOption();
              }
          }else if(Option.equalsIgnoreCase("CUT OFF TAIL") && salamanderDead && hasItem("AXE") && !hasItem("SALAMANDER TAIL")){
              type(.05,"You use your axe to cut off the dead salamander's tail.",0);
              addToInventory("SALAMANDER TAIL","~");
              crackOption();
          }else{
              type(.05,"You may only type one of the words in the scene above.",0);
              crackOption();
          }
        }catch(Exception e){
            type(.05,"You may only type one of the words in the scene above.",0);
            crackOption();
        }
    }
    public void mainMenuOption(){
       delay(1);
       System.out.print("\f");
       makeMainMenu();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       if(Option.equalsIgnoreCase("PLAY")){
           option1();
       }else if(Option.equalsIgnoreCase("INSTRUCTIONS")){
           instructionsOption();
       }else if(Option.equalsIgnoreCase("QUIT")){
           System.out.print("\f");
           System.out.print("You may now exit the game.");
           System.exit(0);
       }else{
           type(.05,"You may only type one of the words in the scene above.",0);
           mainMenuOption();
       }
    }
    public void instructionsOption(){
       delay(1);
       System.out.print("\f");
       makeInstructions();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       if(Option.equalsIgnoreCase("MAIN MENU")){
           mainMenuOption();
       }else{ 
           type(.05,"You may only type one of the words in the scene above.",0);
           instructionsOption();
       }
    }
    public void gameOverOption(){
       delay(1);
       System.out.print("\f");
       makeGameOver();
       Scanner sc = new Scanner(System.in);
       String Option = sc.nextLine();
       if(Option.equalsIgnoreCase("MAIN MENU")){
           mainMenuOption();
       }else if(Option.equalsIgnoreCase("QUIT")){
           System.out.print("\f");
           System.out.print("You may now exit the game.");
           System.exit(0);
       }else{ 
           type(.05,"You may only type one of the words in the scene above.",0);
           gameOverOption();
       }
    }
    public void fullGame(){
       if(intro == true){
          introAnimation();
          mainMenuOption();
       }
       prevOption = 11;
       delay(1);
       System.out.print("\f");
       makeCastle();
       option1();
    }
    //ART
    String[][] inventory = {{"",""},{"",""},{"",""},{"",""},{"",""}};
    public void makeCastle(){
       System.out.println("    /\\       _    _    _        /\\");           
       System.out.println("   /  \\     | |__| |__| |      /  \\     <LEFT/RIGHT>");
       System.out.println("  /    \\    |___________|     /    \\");            
       System.out.println(" /      \\    |         |     /      \\");
       System.out.println("/________\\   |    ^    |    /________\\");
       System.out.println(" |      |    |   / \\   |     |      |");
       System.out.println(" |   ^  |    |   | |   |     |  ^   | ");
       System.out.println(" |  / \\ |    |   |_|   |     | / \\  |");
       System.out.println(" |  | | |  _ |  _    _ |  _  | | |  |");
       System.out.println(" |  |_| |_| |__| |__| |__| |_| |_|  |");
       System.out.println(" |      |                    |      |");
       System.out.println(" |      |       (DOOR)       |      |");
       System.out.println(" |      |        ___         |      |");
       System.out.println(" |      |       |  .|       (CRACK)_|");
       System.out.println(" |      |       |   |        |  _./ |");
       System.out.println(" |      |       |   |        |  |/   \\.");
       System.out.println("                \\    \\");
       System.out.println(" _______________/    /__________________");
       System.out.println("");
       System.out.println("");
       System.out.println("________________________________________");
                      
    }
    public void makeMainHall(){
       System.out.println(" ____      ___     _(CENTER DOOR)_     ___      ____");
       System.out.println("(____)    (___)   (__)  _____  (__)   (___)    (____)");
       System.out.println(" ||||      |||     ||  |  |  |  ||     |||      ||||");
       System.out.println(" ||||      |||     ||  | .|. |  ||     |||      ||||");
       System.out.println(" |(LEFT DOOR)|     ||  |  |  |  ||     |(RIGHT DOOR)");
       System.out.println(" ||||  _   |||    (__)         (__)    |||   _  ||||");
       System.out.println(" |||| |.|  |||                         |||  |.| ||||");
       System.out.println(" |||| | | (___)                       (___) | | ||||");
       System.out.println(" ||||                                           ||||");
       System.out.println("(____)                  (BACK)                 (____)");
    }
    public void makePotionLab(){
       System.out.println(" ________"); 
       System.out.println("|        |       _|_      _|_"); 
       System.out.println("|]]___[]]|      /...\\    /...\\"); 
       System.out.println("| (SHELF)|"); 
       System.out.println("|___[]]__|   (CAULDRON)"); 
       System.out.println("|        |       .      (TABLE) ");  
       System.out.println("|[]]]]___|         .    _[\"]__"); 
       System.out.println("|        |        .    |      |"); 
       System.out.println("|__[]]]__|      (___)  |      |"); 
       System.out.println("     (BACK)     /---\\");  
    }
    public void makeShelf(){
       System.out.println("||  _ _ __            ||");
       System.out.println("|| |8|*|\\*\\           ||");
       System.out.println("|| |8|-| \\o\\ (READ BOOK)");
       System.out.println("|| |8|-|  \\o\\         ||");
       System.out.println("||_|8|*|___\\*\\________||");
       System.out.println("| ____________________ |");
       System.out.println("||   (BACK)             ||");
    }
    public void makeCauldron(){
       System.out.println("   _______");
       System.out.println("  /       \\ (ADD [Ingredient])");
       System.out.println(" /  *  *   \\");
       System.out.println("|   ~   *   |");
       System.out.println("| *   * ~   |");
       System.out.println(" \\ ~ *   * /   (BACK)");
       System.out.println("  \\_______/");
       System.out.println(ingredients);
    }
    public void makePotionTable(){
        System.out.println("      ========");
        System.out.println("      |(O)(O)|");
        System.out.println("      |(O)(O)|");
        System.out.println("      |(O)(O)|  (TAKE EYE)");
        System.out.println("______|(O)(O)|__________");
        System.out.println("______________________  |");
        System.out.println("                      | |");
        System.out.println("        (BACK)        | |");
        System.out.println("                      | |");
        System.out.println("                      | |");
     
    }
    public void makeMainMenu(){
        System.out.println("_____ _____ _____ _____ _____ _____ _____ _____ _____");
        System.out.println("|_____|_____|_____|_____|_____|_____|_____|_____|_____|");
        System.out.println("|  \\/  |  / \\  |_ _| \\ | | |  \\/  | ____| \\ | | | | |  ");
        System.out.println("| |\\/| | / _ \\  | ||  \\| | | |\\/| |  _| |  \\| | | | |  ");
        System.out.println("| |  | |/ ___ \\ | || |\\  | | |  | | |___| |\\  | |_| |  ");
        System.out.println("|_|__|_/_/__ \\_\\___|_|_\\_|_|_| _|_|_____|_|_\\_|\\___/__ ");
        System.out.println("|_____|_____|_____|_____|_____|_____|_____|_____|_____|");
        System.out.println("(PLAY)               (INSTRUCTIONS)              (QUIT)");
    }
    public void makeGameOver(){
       System.out.println("  ______ ______ ______ ______ ______ ______ ______ ______ ______ ");
       System.out.println(" |______|______|______|______|______|______|______|______|______|");
       System.out.println("  / ____|   /\\   |  \\/  |  ____|  / __ \\ \\    / /  ____|  __ \\   ");
       System.out.println(" | |  __   /  \\  | \\  / | |__    | |  | \\ \\  / /| |__  | |__) |  ");
       System.out.println(" | | |_ | / /\\ \\ | |\\/| |  __|   | |  | |\\ \\/ / |  __| |  _  /   ");
       System.out.println(" | |__| |/ ____ \\| |  | | |____  | |__| | \\  /  | |____| | \\ \\ ");
       System.out.println("  \\_____/_/____\\_\\_|__|_|______|__\\____/___\\/___|______|_|__\\_\\_ ");
       System.out.println(" |______|______|______|______|______|______|______|______|______|");
       System.out.println("(MAIN MENU)                  (QUIT)");
    }
    public void makeInstructions(){
       System.out.println(" ______ ______ ______ ______ ______ ______ ______ ______ ");
       System.out.println("|______|______|______|______|______|______|______|______|");  
       System.out.println("|_   _|         | |                 | | (_)                ");
       System.out.println("  | |  _ __  ___| |_ _ __ _   _  ___| |_ _  ___  _ __  ___ ");
       System.out.println("  | | | '_ \\/ __| __| '__| | | |/ __| __| |/ _ \\| '_ \\/ __|");
       System.out.println(" _| |_| | | \\__ \\ |_| |  | |_| | (__| |_| | (_) | | | \\__ \\");
       System.out.println("|_____|_|_|_|___/\\__|_|___\\__,_|\\___|\\__|_|\\___/|_|_|_|___/   ");
       System.out.println("|______|______|______|______|______|______|______|______|");
       System.out.println("");
       System.out.println("-To navigate, type the words in the scenes displayed above.");
       System.out.println("");
       System.out.println("-When adding ingredients to the cauldron, use ADD and then the ingredient you would like to add.");
       System.out.println("");
       System.out.println("-Adding time to the cauldron is done by simply waiting. There are intervals of 5, 10, and 15 seconds you can wait for. (Remember that time will start the second you enter the cauldron scene, so get brewing as fast as possible!)"); 
       System.out.println("");
       System.out.println("-In some scenes you can throw potions. Do this with THROW and then the potion that you want to throw.");
       System.out.println("");
       System.out.println("-To access your inventory, type INVENTORY.");
       System.out.println("");
       System.out.println("-To remove any item from your inventory use REMOVE and then the item you would like to remove. (Remember that you must return to the place that you found the item, to remove it.)");
       System.out.println("(MAIN MENU)");
    }
    public void makePotionBookPage1(){
       System.out.println(" ____________________________________");
       System.out.println("|\\ __  - __  --  _ -_   =  \\###/");
       System.out.println("||\\_________________________\\#/______");
       System.out.println("|||        (PAGE RIGHT>      |");
       System.out.println("|||  Potions for Beginners   |");
       System.out.println("|||  ~~~~~~~~~~~~~~~~~~~~~   |");
       System.out.println("||| Fire Potion:             |");
       System.out.println("|||                          |");
       System.out.println("||| 1.Add one spider eye.    |");
       System.out.println("||| 2.Wait ten seconds. (no  |");
       System.out.println("||| less and not much more)  |");
       System.out.println("||| 3.Add salamander tail.   |");
       System.out.println("||| 4.Add one pinecone.      |");
       System.out.println("|||                __        |");
       System.out.println("||| Ingredients:   ||        |");
       System.out.println("||| [(O),~,#]     /  \\       |");
       System.out.println("|||           ___|~~~~|_____ |");
       System.out.println("\\|| (BACK)       \\____/      |");
       System.out.println(" \\|__________________________|_______");
    }
    public void makePotionBookPage2(){
       System.out.println(" ____________________________________");
       System.out.println("|\\ __  - __  --  _ -_   =  \\###/");
       System.out.println("||\\_________________________\\#/______");
       System.out.println("|||   <PAGE LEFT/RIGHT>      |");
       System.out.println("|||                          |");
       System.out.println("|||                          |");
       System.out.println("||| Frost Potion:            |");
       System.out.println("|||                          |");
       System.out.println("||| 1.Add one spider eye.    |");
       System.out.println("||| 2.Wait ten seconds. (no  |");
       System.out.println("||| less and not much more)  |");
       System.out.println("||| 3.Add one icecube.       |");
       System.out.println("||| 4.Add one pinecone.      |");
       System.out.println("|||                __        |");
       System.out.println("||| Ingredients:   ||        |");
       System.out.println("||| [(O),[],#]    /  \\       |");
       System.out.println("|||           ___|~~~~|_____ |");
       System.out.println("\\|| (BACK)       \\____/      |");
       System.out.println(" \\|__________________________|_______");
    }
    public void makePotionBookPage3(){
       System.out.println(" ____________________________________");
       System.out.println("|\\ __  - __  --  _ -_   =  \\###/");
       System.out.println("||\\_________________________\\#/______");
       System.out.println("|||    <PAGE LEFT/RIGHT>     |");
       System.out.println("|||                          |");
       System.out.println("|||                          | ");
       System.out.println("||| Damage Potion:           |");
       System.out.println("|||                          |");
       System.out.println("||| 1.Add pinecone.          |");
       System.out.println("||| 2.Add rosebush thorns.   |");
       System.out.println("||| 3.Add rust.              |");
       System.out.println("|||                          |");
       System.out.println("|||                __        |");
       System.out.println("||| Ingredients:   ||        |");
       System.out.println("||| [#,^^^,*]     /  \\       |");
       System.out.println("|||           ___|~~~~|_____ |");
       System.out.println("|||              \\____/      |");
       System.out.println("\\|| (BACK)                   |");
       System.out.println(" \\|__________________________|______");
    }
    public void makePotionBookPage4(){
       System.out.println(" ____________________________________");
       System.out.println("|\\ __  - __  --  _ -_   =  \\###/");
       System.out.println("||\\_________________________\\#/______");
       System.out.println("|||       <PAGE LEFT)        |");
       System.out.println("|||                          |");
       System.out.println("|||                          |");
       System.out.println("||| Invisibility Potion:     |");
       System.out.println("|||                          |");
       System.out.println("||| 1.Add glass shards.      |");
       System.out.println("||| 2.Wait 15 seconds.       |");
       System.out.println("||| 3.Add rust.              |");
       System.out.println("||| 4.Wait 10 seconds.       |");
       System.out.println("||| 5.Add nothing.           |");
       System.out.println("|||                __        |");
       System.out.println("||| Ingredients:   ||        |");
       System.out.println("||| [>>,*, ]      /  \\       |");
       System.out.println("|||           ___|~~~~|_____ |");
       System.out.println("\\|| (BACK)       \\____/      |");
       System.out.println(" \\|__________________________|_____");
    }
    public void makeTrail1(){
       System.out.println(" ////\\\\\\\\");
       System.out.println("/////\\\\\\\\\\             /\\");
       System.out.println("///|. |/\\\\/\\ (CONTINUE)/\\\\");
       System.out.println("///| o/////\\//\\      //||\\\\");
       System.out.println("   |.//////\\\\\\||      /||\\");
       System.out.println("   |///|//||\\\\\\ /   \\  ||");
       System.out.println("   |//////||\\\\\\/    / (ROSE BUSH)");
       System.out.println("   |  ||| ||  /     \\    @@ ");
       System.out.println("(TAKE  || ||  \\      \\  @\\/@");
       System.out.println("PINECONE)   # /      /   /@@");
       System.out.println("      #   #  /(BACK) \\");
    }
    public void makeInventory(){
       System.out.println("Inventory");
       System.out.println("--------");
       System.out.println("|"+inventory[0][0]+"|");
       System.out.println("["+inventory[0][1]+"]");
       System.out.println("--------");
       System.out.println("|"+inventory[1][0]+"|");
       System.out.println("["+inventory[1][1]+"]");
       System.out.println("--------");
       System.out.println("|"+inventory[2][0]+"|");
       System.out.println("["+inventory[2][1]+"]");
       System.out.println("--------          (REMOVE [Item])");
       System.out.println("|"+inventory[3][0]+"|");
       System.out.println("["+inventory[3][1]+"]");
       System.out.println("--------          (BACK)");
       System.out.println("|"+inventory[4][0]+"|");
       System.out.println("["+inventory[4][1]+"]");
       System.out.println("--------          (MAIN MENU) ");       
    }
    public void makeCloset(){
       String grateChoice = "PULL OFF GRATE";
       String sawChoice = "(TAKE ICE SAW)";
       String axeChoice = "(TAKE AXE)";
       String axe1 = "<|>";
       String axe2 = "[|]";
       String axe3 = "|";
       String saw1 = "<|";
       String saw2 = "<|]";
       String saw3 = "<|";
       String grate = "\\\\\\\\";
       if(hasItem("ICE SAW")){
          grateChoice = "SAW GRATE";
          sawChoice = "";
          saw1 = "  ";
          saw2 = "[ ]";
          saw3 = "  ";
       }
       if(grateOpen){
          grate = "\\__\\";
          grateChoice = "REACH IN";
       }
       if(hasItem("AXE")){
          axeChoice = "          ";
          axe1 = "   ";
          axe2 = "[ ]";
          axe3 = " ";
       }
       System.out.println(axeChoice + sawChoice);  
       System.out.println("  "+axe1+"   \\|/   "+saw1+"");
       System.out.println("  "+axe2+"   [|]   "+saw2+"  ___");
       System.out.println("   "+axe3+"     |    "+saw3+"  |  .| ");
       System.out.println("                  |   | ");
       System.out.println("__________________|   |");
       System.out.println("          "+grate+"      (BACK)");
       System.out.println("   (" + grateChoice + ")  ");
    }
    public void makeHouse(){
       System.out.println("               (");
       System.out.println("                )");
       System.out.println("       /\\    __||_________");
       System.out.println("   /\\ //\\\\  /_____________\\    /\\");
       System.out.println("  //\\\\//\\\\\\ |   (HOUSE)   |   //\\\\ /\\");
       System.out.println(" //||\\\\||\\\\\\|       _     |  //||\\//\\\\");
       System.out.println("///||\\\\\\|\\\\ | [_]  |.|  (CONTINUE)//\\\\\\");
       System.out.println(" //||\\\\||   |______| |____|/  \\\\///||\\\\\\ ");
       System.out.println("   ||             |_______     \\\\//||\\\\");
       System.out.println("                         /      \\  ||     ");
       System.out.println("                        /        \\ ||");
       System.out.println("                       /  (BACK)  \\     ");   
        
    }
    public void makeLake(){
       System.out.println("                          /\\   /\\");
       System.out.println("               /\\   /\\   //\\\\ //\\\\");
       System.out.println("   ________   //\\\\ //\\\\ ///\\\\///\\\\\\");
       System.out.println("  /        \\ ///\\\\\\//\\\\\\//||////\\\\\\\\");
       System.out.println(" /          ////\\\\\\\\||\\\\ _||_//||\\\\");
       System.out.println(" |  (LAKE)   //||\\\\ ||  /    / ||");
       System.out.println(" \\_          / ||   || /    /  ||");
       System.out.println("   \\________/  ||     /    /");
       System.out.println("                     /    /");
       System.out.println("                    (BACK)");
       }
    public void makeLakeCloseUp(){
       String choice = "       ";
        if(hasItem("ICE SAW")){
           choice = " (SAW ICE) ";
        }else if(hasItem("POOSI") && !hasItem("ICE SAW")){
           choice = "(USE POOSI)";
       }
       System.out.println("  ________________ //\\\\/||\\\\\\");
       System.out.println(" /                ///\\\\\\||");
       System.out.println("/                ///||\\\\\\|");
       System.out.println("|   "+choice+"     ||  ||");
       System.out.println("\\___               /||");
       System.out.println("    \\_____________/     (BACK)");
    }
    public void makeTakePotion(String potionName){
       System.out.println("   ____");
       System.out.println("   |  |");
       System.out.println("   |  |   (TAKE "+ potionName +")");
       System.out.println("  /    \\");
       System.out.println(" /~~~~~~\\");
       System.out.println("|        |  (BACK)");
       System.out.println("|        |");
       System.out.println(" \\______/");
    }
    public void makeDeadCrack(){
       System.out.println("");
    }
    public void makeCrack(){
       String eye = "o";
       String textOne = "(PET)";
       String textTwo = "";
       String tailOne = "`.";
       String tailTwo = "`. \\";
       String tailThree = "'.\\";
       String tailFour = "`";
        if(salamanderDead){
          eye = "x";
          textOne = "     ";
          if(hasItem("AXE") && !hasItem("SALAMANDER TAIL")){
             textTwo = "(CUT OFF TAIL)";
          }
          if(hasItem("SALAMANDER TAIL")){
              tailOne = "  ";
              tailTwo = "    ";
              tailThree = "   ";
              tailFour = " ";
          }
       }
       System.out.println("__|__|__|__|__|__|__|__|__|");
       System.out.println("_|__|__|__|  |__|__|__|__|");
       System.out.println("|__|__|  "+textOne+"   |__|__|__|"+textTwo);
       System.out.println("_|_    _"+eye+"_/\\^/\\^/\\_   |__|");
       System.out.println("|__| >-\\_"+eye+"_ ____ _ "+tailOne+"  |__|");
       System.out.println("_|        //    \\\\"+tailTwo+" __|");
       System.out.println("       _/-\"------\"\\ "+tailThree+" __|");
       System.out.println("(BACK)/            \\   "+tailFour+"  ");
    }
    //Animations
    public void brewAnimationFramePreOne(){
       System.out.println("Brewing...");
       System.out.println("      ");
       System.out.println("       ");
       System.out.println("      ");
       System.out.println("  /~~~~\\");
       System.out.println(" |      |");
       System.out.println("  \\____/");
       System.out.println(" /------\\");
       System.out.println("/        \\");
    }
    public void brewAnimationFramePreTwo(){
       System.out.println("Brewing...");
       System.out.println("      ");
       System.out.println("       ");
       System.out.println("     o");
       System.out.println("  /~~~~\\");
       System.out.println(" |      |");
       System.out.println("  \\____/");
       System.out.println(" /------\\");
       System.out.println("/        \\");
    }
    public void brewAnimationFramePreThree(){
       System.out.println("Brewing...");
       System.out.println("      ");
       System.out.println("     o ");
       System.out.println("      o");
       System.out.println("  /~~~~\\");
       System.out.println(" |      |");
       System.out.println("  \\____/");
       System.out.println(" /------\\");
       System.out.println("/        \\");
    }
    public void brewAnimationFrameOne(){
       System.out.println("Brewing...");
       System.out.println("     *");
       System.out.println("      o");
       System.out.println("    o ");
       System.out.println("  /~~~~\\");
       System.out.println(" |      |");
       System.out.println("  \\____/");
       System.out.println(" /------\\");
       System.out.println("/        \\");
    }
    public void brewAnimationFrameTwo(){
       System.out.println("Brewing...");
       System.out.println("      *");
       System.out.println("    o  ");
       System.out.println("     o ");
       System.out.println("  /~~~~\\");
       System.out.println(" |      |");
       System.out.println("  \\____/");
       System.out.println(" /------\\");
       System.out.println("/        \\");
    }
    public void brewAnimationFrameThree(){
       System.out.println("Brewing...");
       System.out.println("    *");
       System.out.println("     o");
       System.out.println("      o");
       System.out.println("  /~~~~\\");
       System.out.println(" |      |");
       System.out.println("  \\____/");
       System.out.println(" /------\\");
       System.out.println("/        \\");
    }
    public void brewAnimation(){
        int repeatCount = 0;
        boolean first = true;
        while(repeatCount < 8){
           if(first == true){
              System.out.print("\f");
              brewAnimationFramePreOne();
              delay(.25);
              System.out.print("\f");
              brewAnimationFramePreTwo();
              delay(.25);
              System.out.print("\f");
              brewAnimationFramePreThree();
              delay(.25);
              first = false;
           }
           System.out.print("\f");
           brewAnimationFrameOne();
           delay(.25);
           System.out.print("\f");
           brewAnimationFrameTwo();
           delay(.25);
           System.out.print("\f");
           brewAnimationFrameThree();
           delay(.25);
           repeatCount += 1;
        }
    }
    public void introFrameOne(){
        type(.05,"Enter FullScreen Now.",0);
    }
    public void introFrameTwo(){
       System.out.println("                        __,");
       System.out.println("                       (_/(_");
       System.out.println("");
       System.out.println(" _________  ___  ___  ________  _________  ________"); 
       System.out.println("|\\___   ___\\\\  \\|\\  \\|\\   __  \\|\\___   ___\\\\   ____\\  ");
       System.out.println("\\|___ \\  \\_\\ \\  \\\\\\  \\ \\  \\|\\  \\|___ \\  \\_\\ \\  \\___|_    ");     
       System.out.println("     \\ \\  \\ \\ \\   __  \\ \\   __  \\   \\ \\  \\ \\ \\_____  \\   ");
       System.out.println("      \\ \\  \\ \\ \\  \\ \\  \\ \\  \\ \\  \\   \\ \\  \\ \\|____|\\  \\  ");
       System.out.println("       \\ \\__\\ \\ \\__\\ \\__\\ \\__\\ \\__\\   \\ \\__\\  ____\\_\\  \\ ");
       System.out.println("        \\|__|  \\|__|\\|__|\\|__|\\|__|    \\|__| |\\_________\\   ");        
       System.out.println("                                             \\|_________|      ");
       System.out.println("");
       System.out.println(" ________  ________  ________   ________  ________  _____ ______ ");  
       System.out.println("|\\   __  \\|\\   __  \\|\\   ___  \\|\\   ___ \\|\\   __  \\|\\   _ \\  _   \\  ");    
       System.out.println("\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\   ");
       System.out.println(" \\ \\   _  _\\ \\   __  \\ \\  \\\\ \\  \\ \\  \\ \\\\ \\ \\  \\\\\\  \\ \\  \\\\|__| \\  \\  ");
       System.out.println("  \\ \\  \\\\  \\\\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\_\\\\ \\ \\  \\\\\\  \\ \\  \\    \\ \\  \\  ");
       System.out.println("   \\ \\__\\\\ _\\\\ \\__\\ \\__\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\ \\__\\    \\ \\__\\   ");
       System.out.println("    \\|__|\\|__|\\|__|\\|__|\\|__| \\|__|\\|_______|\\|_______|\\|__|     \\|__| ");
       System.out.println("                 _ __                               ");
       System.out.println("                ( /  )         /       _/_o         ");
       System.out.println("                 /--'_   __ __/ , , _, / ,  __ _ _  ");
       System.out.println("                /   / (_(_)(_/_(_/_(__(__(_(_)/ / /_");
    }
    public void introFrameThree(){
       System.out.println("   ?????????        ????????    ????????????     ???????????     ????????       ???          ???        ");
       System.out.println("   ???      ???    ???    ???   ????????????     ???????????    ???    ???    ???  ???       ???        ");    
       System.out.println("   ???      ???    ???    ???        ???             ???        ???    ???    ???   ???      ???        ");
       System.out.println("   ???      ???    ???    ???        ???             ???        ???    ???    ???    ???     ???        ");  
       System.out.println("???????????        ???    ???        ???             ???        ???    ???    ???     ???    ???        ");
       System.out.println("   ???             ???    ???        ???             ???        ???    ???    ???      ???   ???        ");
       System.out.println("   ???             ???    ???        ???         ???????????    ???    ???    ???        ??? ???        ");
       System.out.println(" ??????             ????????         ???         ???????????     ????????     ???          ???          ");
       System.out.println("");
       System.out.println(" ?????????       ?????????        ????????????     ???????????        ???            ???????????        ");
       System.out.println("???      ???    ???     ???       ???      ???     ???????????        ???            ???                ");
       System.out.println("???      ??     ???     ???       ???       ??         ???            ???            ???                ");
       System.out.println("???             ???     ???       ???                  ???            ???            ???????            ");
       System.out.println("???             ???????????       ????????????         ???            ???            ???????            ");
       System.out.println("???     ??      ???     ???                ???         ???            ???            ???                ");  
       System.out.println("???     ???     ???     ???      ???      ???          ???            ?????????????  ???                ");
       System.out.println("?????????       ???     ???       ???????????        ???????          ?????????????  ???????????        ");
    }
    public void introAnimation(){
       System.out.print("\f");
       introFrameOne();
       delay(5);
       System.out.print("\f");
       introFrameTwo();
       delay(8);
       System.out.print("\f");
       introFrameThree();
       delay(5);
    }
    public void enter(int lines){
       int repeat = 0;
       while(repeat >= lines){
           System.out.println(""); 
           repeat++;
       }
    }
}

