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
public class Tree
{
    private TreeNode root; //This is the binary tree root    
    public Tree(){//Constructor sets root to null value
        root = null;
    }
    public boolean isEmpty(){//Check to see if there is a root node, returns null if true
        return root == null;    
    }
    public void insert(String value){//Assigns a string value  
        TreeNode match;
        if(isEmpty())//A new value becomes the root if it is empty
            root = new TreeNode(value);//Assigns a new TreeNode value to root
            else if((match = checkChildren(root, value)) == null) // This statement traverses the tree to evaluate if a new node should be added
                                                                  // or the total node count should be incremented
            root.add(value);//Add value to root
            else
            match.incFrequency();//Otherwise match the value returned from the incFrequency method
    }
    public void search(String value){
        TreeNode returnNode;
        if((returnNode = checkChildren(root, value)) == null) //This is a check that will determine what message is printed to the user when searching
           System.out.println("-- (" + value + ") was not found! --");//Displays a not found message
        else
           System.out.println("-- The following string (" + returnNode.getString() + ") was found: " + (returnNode.getFrequency()) +" --");//If there is a value it is displayed to user   
    }
    public TreeNode checkChildren(TreeNode node, String value) {//Method to search the tree using recursive calls, takes in node and value
        TreeNode returnNode = null;
        if(node != null){//If the node has value
            if((node.getString()).equals(value))//If equal the node is returned
            return node;
        if((returnNode = checkChildren(node.getLeft(), value)) != null){//If there is a node on the left, returnNode is returned
            return returnNode;
          }    
          return checkChildren(node.getRight(), value);    
        }
        return returnNode;
    }
    public void preOrder(TreeNode node){//Brings in a TreeNode 
        if(node != null){
            System.out.print(node.getString() + " ");//Prints the String in the node
            preOrder(node.getLeft());//Recursive call
            preOrder(node.getRight());//Recursive call
        }
    }    
    public void printPreOrder(){//Prints Pre-Order to the user
        System.out.println();
        preOrder(root);
    }
    public void inOrder(TreeNode node){
        if(node != null){
        inOrder(node.getLeft());
        System.out.print(node.getString() + " ");
        inOrder(node.getRight());
        }
    }    
    public void printInOrder(){//Prints In-Order to the user
        System.out.println();
        inOrder(root);
    }
    public void postOrder(TreeNode node){
        if(node != null){
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.getString() + " ");
        }
    }
    public void printPostOrder(){//Prints Post Order to the user
        System.out.println();
        postOrder(root);
    }
}
