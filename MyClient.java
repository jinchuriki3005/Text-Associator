//Max Tran, CSE 373 Winter 2017, HW#3
/*A client class for TextAssociator. Generate a TextAssociator between
a state and cities within it. Allowing the user to look up the name of 
the cities that are associated with a given state. Moreover, the user 
can also add a word and its association*/
import java.io.*;
import java.util.*;

public class MyClient {
  	public static void main(String[] args) throws IOException {
  		TextAssociator t = new TextAssociator();
      //adding association to TextAssociator t
      t.addNewWord("California");
      t.addAssociation("California","Los Angeles");
      t.addAssociation("California","Berkeley");
      t.addNewWord("Washington");
      t.addAssociation("Washington","Seattle");
      t.addAssociation("Washington","Tacoma");
      t.addNewWord("Oregon");
      t.addAssociation("Oregon","Portland");
      t.addAssociation("Oregon","Salem");
      t.addNewWord("Idaho");
      t.addAssociation("Idaho","Boise");
      t.addAssociation("Idaho","Nampa");
      t.addNewWord("Utah");
      t.addAssociation("Utah","Moab");
      t.addAssociation("Utah","Orem");
      t.addNewWord("New York");
      t.addAssociation("New York","Rochester");
      t.addAssociation("New York","Ithaca");
      t.addAssociation("New York","Buffalo");
      t.addNewWord("Florida");
      t.addAssociation("Florida","Miami");
      t.addAssociation("Florida","Jacksonville");
      t.addAssociation("Florida","Orlando");
      t.addNewWord("New Jersey");
      t.addAssociation("New Jersey","Trenton");
      t.addNewWord("Alabama");
      t.addAssociation("Alabama","Montgomery");
      t.addAssociation("Alabama","Auburn");
      t.addNewWord("Arizona");
      t.addAssociation("Arizona","Phoenix");
      t.addAssociation("Arizona","Mesa");
      t.addNewWord("Texas");
      t.addAssociation("Texas","Houston");
      t.addAssociation("Texas","Dallas");
      t.addAssociation("Texas","Austin");
      t.addNewWord("Arkansas");
      t.addAssociation("Arkansas","Fort Smith");
      t.addAssociation("Arkansas","Bentonville");
      t.addNewWord("Virginia");
      t.addAssociation("Virginia","Norfolk");
      t.addAssociation("Virginia","Richmond");
      t.addNewWord("Colorado");
      t.addAssociation("Colorado","Denver");
      t.addAssociation("Colorado","Aspen");
      t.addNewWord("Wyoming");
      t.addAssociation("Wyoming","Jackson");
      t.addAssociation("Wyoming","Casper");
      t.addNewWord("Connecticut");
      t.addAssociation("Connecticut","Norwalk");
      t.addAssociation("Connecticut","Stamford");
      t.addNewWord("Ohio");
      t.addAssociation("Ohio","Cleveland");
      t.addAssociation("Ohio","Akron");
      t.addNewWord("Oklahoma");
      t.addAssociation("Oklahoma","Tulsa");
      t.addAssociation("Oklahoma","Norman");
      t.addNewWord("Montana");
      t.addAssociation("Montana","Bozeman");
      t.addAssociation("Montana","Helena");
      t.addNewWord("Minnesota");
      t.addAssociation("Minnesota","Duluth");
      t.addAssociation("Minnesota","Mankato");
      t.addNewWord("Hawaii");
      t.addAssociation("Hawaii","Honolulu");
      t.addAssociation("Hawaii","Waipahu");
      t.prettyPrint();
      String response = "";
      Scanner scan = new Scanner(System.in);
      //prompting a user for the response if he/she wants to continue the application
      while(!response.equals("no")){
         System.out.println("Please input a word so we can return its association");
         String input = scan.nextLine();
         System.out.println(t.getAssociations(input));
         System.out.println("Want to continue?");
         response = scan.nextLine();
      }
      //prompting the user if she/he wants to add more word and its association
      System.out.println("Want to add more associations?");
      String answer = scan.nextLine();
      while(answer.equals("yes")){
         System.out.println("what's the word that you want to do a getAssociation()?");
         String input = scan.nextLine();
         t.addNewWord(input);
         System.out.println("what's another word that you want to associate with the previous word?");
         String association = scan.nextLine();
         t.addAssociation(input, association);
         System.out.println("Want to add more associations?");
         answer = scan.nextLine();
      }
      t.prettyPrint();
   }	
}