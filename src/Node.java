
import java.util.*;

public class Node {

   private String name;
   private Node firstChild;
   private Node nextSibling;

   Node (String n, Node d, Node r) {
      // TODO!!! Your constructor here
   }
   
   public static Node parsePostfix (String s) {
      return null;  // TODO!!! return the root
   }

   public String leftParentheticRepresentation() {
      return ""; // TODO!!! return the string without spaces
   }

   public static void main (String[] param) {
      String s = "(B1,C)A";
      Node t = Node.parsePostfix (s);
      String v = t.leftParentheticRepresentation();
      System.out.println (s + " ==> " + v); // (B1,C)A ==> A(B1,C)
   }
}

