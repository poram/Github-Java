import java.util.*;
/**
 * This program allows the user to enter and search for strings. When strings
 * are added to the tree, they are wrapped inside a node object that holds the
 * string. The strings are are stored in a balanced Binary Tree.
 * 
 * @author (Pete Oram) 
 * @date (11/11/13)
 * // Data Structures 2214-003
 * // Program #2
 */
public class TreeNode
{
    private TreeNode right; // Reference to the right side of the subtree
    private TreeNode left; // Reference to the left side of the subtree
    private String stringData; // The information contained in node
    private int countString; // Counts how many times the string occurs
    //Constructor sets the initial values, brings in string str and sets it to 'string'
    public TreeNode(String str){
        stringData = str;//Assigns str to stringData
        countString = 1; //Sets the counter to 1
        left = null; // Sets the left side of the subtree to null value
        right = null; // Sets the right side of the subtree to null value
    }
    public void incFrequency(){// Ups the string counter
        countString++;    
    }
    public int getFrequency(){// Retrieves the number of times the string occurs
        return countString;
    }
    public String getString(){// Returns the value of the string
        return stringData;
    }
    public TreeNode getLeft(){// Gets the child on the left side of the tree
        return left;
    }
    public TreeNode getRight(){// Gets the child on the right side of the tree
        return right;
    }
    //This method will evaluate where to add a new tree node
    public int numChildren(TreeNode node){
      int childCount = 0; //Initializes the child counter to 0
        if(node.getLeft() != null){ //check left side to see if it is null
            childCount += 1 + numChildren(node.getLeft()); //Recursive call on numChildren method    
        }
        if(node.getRight() != null){ //check right side to see if is null
            childCount += 1 + numChildren(node.getRight()); // Recursive call on numChildren method
        }
      return childCount;    
    }        
    //This method will add new nodes to the tree
    public void add(String value){
        //Check the left and right sides of the tree
        if(left == null){
            left = new TreeNode(value); //If the left is null a new tree node is added
        }else if(right == null){
            right = new TreeNode(value); //If the right is null a new tree node is added
        }else{
            if(numChildren(left) <= numChildren(right)){ //Compares number of children on left to number on the right
                left.add(value);
            }else{
                right.add(value);
            }        
        }    
    }
    
}
