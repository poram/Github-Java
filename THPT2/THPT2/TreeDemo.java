import java.util.*;
// Pete Oram
// ITCS-2214-003
// Program #2
// 11/11/13
// Driver file for project 2.
// ITCS 2214 -- Professor Jugan

public class TreeDemo {
   public static void main(String[] args){
      //Library of words to be added to the tree.
      String[] words = {"Amok", "Nirvana", "Levin", "Minotaur", "Naif", 
                        "Brevet", "Dehort", "Costive", "Boffin", "Hoyle", 
                        "Scion", "Pissoir", "Looby", "Kvell", "Redact", "Pi" };
   
      //For easier readability, swap words for numbers.                  
      String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};  
                 
      Random rand = new Random();                        // Initialize Random
      Tree myTree = new Tree();                          // Initialize the Tree
      
      for (int addLoop = 0; addLoop < 30; addLoop++)     // Loop to add items to the Tree
         myTree.insert(words[rand.nextInt(16)]);         // Method call to the tree to insert nodes
      System.out.println();
      System.out.println("---Searches---");// Start multiple searches
      System.out.println();
      myTree.search(words[rand.nextInt(16)]);   
      myTree.search(words[rand.nextInt(16)]);
      myTree.search(words[rand.nextInt(16)]);
      System.out.println();	
      System.out.println("---Printing---");              // Print the tree using all 
      myTree.printPreOrder();                            // three type of order
      myTree.printInOrder();
      myTree.printPostOrder();
   }
}