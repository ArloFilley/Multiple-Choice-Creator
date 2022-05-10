import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws Exception{
    game();
  }

  // gameplay loop happens inside this funciton so that it can be
  // called multiple times if the player wants to play again
  public static void game() throws Exception {
    Global.choices = "I";
    while (true) {
      reader();
    }
  }

  // this funciton finds the index consisting of all the choices the user has made so far and
  // adds to the choices by getting input
  // it also deals with the logic to check for keywords such as GOTO and GAMEOVER
  // it technically returns integers although these are just used to get out of the function
  // and the have no real purpose
  public static int reader() throws Exception{
    // Creates the BufferedReader to read Story.txt
    FileReader fr = new FileReader("Story.txt");
    BufferedReader story = new BufferedReader(fr);
    // Creates a Scanner to get inputs from the user
    Scanner sc = new Scanner(System.in);
    // Creates a String called content which is used for the loop 
    String content = story.readLine();

    // runs a loop to check through all lines searching for the index or
    // untill it reaches the end of the file with the keyword FILEOVER
    while (!content.equals("FILEOVER")) {
      if (content.equals(Global.choices)) {
        content = story.readLine();
        while (true) {
          // these if else statments check the line for keywords and call the appropriate function
          if (content.equals("GAMEOVER")){
            // calls the gameOver fuction giving it the reason that the player has dies
            gameOver((String)story.readLine());
          } else if (content.equals("FIGHT")){
            fight(story.readLine());
          } else if (content.length() > 4 && content.substring(0,4).equals("GOTO")){
            Global.choices = content.substring(5);
            return 1;
          } else if (content.equals("NEWCHOICE")){
            Global.choices += sc.nextLine().toUpperCase();
            return 1;
          } else
            System.out.println(content);
          content = story.readLine();
        }
      }
      content = story.readLine();
    }
    return 0;
  }

  public static void gameOver (String deathReason) throws Exception{
    Scanner sc = new Scanner(System.in);
    FileWriter fw = new FileWriter("Stats.txt");
    BufferedWriter player = new BufferedWriter(fw);

    player.write("150\n25\n20\n0");
    player.close();

    if (deathReason.equals(null)){
      System.out.println("You died");
    } else
      System.out.println("You died through " + deathReason);
    System.out.println("Would you like to play again\nY or N");
    String cont = sc.next().toLowerCase();
    if (cont.equals("y")){
      System.out.println("----------\nNEW GAME\n----------");
      game();
    } else
      System.out.println("EXITING GAME");
      System.exit(0);
  }

  public static int fight (String enemy) throws Exception{
    Scanner input = new Scanner(System.in);
    FileReader efr = new FileReader("Enemies.txt");
    BufferedReader enemies = new BufferedReader(efr);
    String content;

    FileReader pfr = new FileReader("Stats.txt");
    BufferedReader Player = new BufferedReader(pfr);
    Fighter p = new Fighter();
    p.health = Integer.parseInt((String)Player.readLine());
    p.attack = Integer.parseInt((String)Player.readLine());
    p.defence = Integer.parseInt((String)Player.readLine());
    p.experience = Integer.parseInt((String)Player.readLine());

    while (true) {
      content = enemies.readLine();
      if (content.equals(enemy)) {
        Fighter e = new Fighter();
        e.health = Integer.parseInt((String)enemies.readLine());
        e.attack = Integer.parseInt((String)enemies.readLine());
        e.defence = Integer.parseInt((String)enemies.readLine());
        e.experience = Integer.parseInt((String)enemies.readLine());

        Player.close();
        enemies.close();
        
        System.out.println("!!!!!!!!!!!\nFIGHT\n!!!!!!!!!!");
        System.out.println("you are fighting a " + enemy.toLowerCase());

        while (true) {
          System.out.println("Do you want to:\n1: ATTACK\n2: Heal");
          int choice = input.nextInt();

          if(choice==1){
            System.out.println("you attack");
            System.out.println("You deal " + p.attack + " damage");
            e.health -= p.attack;
          } else if (choice==2){
            System.out.println("you heal");
            System.out.println("you have gained " + p.attack + " health");
            p.health += p.attack;
          } else
            System.out.println("you sit there twiddling your thumbs\ninteresting strategy");

          System.out.println("\n" + enemy + " attacks");
          System.out.println(enemy + " deals " + e.attack + " damage");
          p.health -= e.attack;

          if (p.health<=0) {
            System.out.println("you feel very sleepy reaching 0 hp");
            System.out.println("you faint losing the battle");
            System.out.println("End of game - Would you like to play again Y or N");
            gameOver("battle");
          } else if(e.health<=0) {
            System.out.println("with a final stroke of your sword you defeat the " + enemy);
            p.experience += e.experience;
            if (p.experience > 99) {
              levelUp(p.experience);
            }
            return 1;
          }
          System.out.println("you have " + p.health + " health");
          System.out.println("enemy " + enemy.toLowerCase() + " has " + e.health + " health");
        }
      } else if (content.equals("FILEOVER")){
        System.out.println("error no enemy of type " + enemy);
        System.exit(0);
      }
    }
  }

  public static void levelUp(int experience) throws Exception{
    Random myRand = new Random();
    FileReader pfr = new FileReader("Stats.txt");
    BufferedReader playerRead = new BufferedReader(pfr);

    Fighter p = new Fighter();
    p.health = Integer.parseInt((String)playerRead.readLine());
    p.attack = Integer.parseInt((String)playerRead.readLine());
    p.defence = Integer.parseInt((String)playerRead.readLine());
    p.experience = experience;
    playerRead.close();

    System.out.println(p.experience);

    while (p.experience > 99) {
      p.experience -= 100;
      p.health += myRand.nextInt(15);
      p.attack += myRand.nextInt(5);
      p.defence += myRand.nextInt(5);
    }

    FileWriter pfw = new FileWriter("Stats.txt");
    BufferedWriter playerWrite = new BufferedWriter(pfw);

    String health = String.valueOf(p.health);
    String attack = String.valueOf(p.attack);
    String defence = String.valueOf(p.defence);

    System.out.println("//////////\nLEVEL UP\n//////////");
    System.out.println("NEW STATS");
    System.out.println(health + " health");
    System.out.println(attack + " attack");
    System.out.println(defence + " defence");

    playerWrite.write(String.valueOf(p.health) + "\n");
    playerWrite.write(String.valueOf(p.attack) + "\n");
    playerWrite.write(String.valueOf(p.defence) + "\n");
    playerWrite.write(String.valueOf(p.experience));

    playerWrite.close();
  }
}
