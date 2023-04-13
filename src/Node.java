import java.util.*;

public class Node {

   private String name;
   private Node firstChild;
   private Node nextSibling;

   Node (String n, Node d, Node r) {
      setName(n);
      setFirstChild(d);
      setNextSibling(r);
   }

   Node (){

   }
   private void setName(String n){this.name = n;} // made this method private

   private void setNextSibling(Node p){this.nextSibling = p;} // made this method private

   private void setFirstChild(Node n){this.firstChild = n;} // made this method private

   @Override
   public String toString(){
      return leftParentheticRepresentation();
   }

   public static Node parsePostfix(String s) {
      /*added try-catch, if String s is not written properly
      parsePostfix throws an exception which were previously described in errorHandlingMethod*/

      try{
         if (s.length() == 0)
            throw new RuntimeException("The given string representation of a tree is empty-> " + s);
         if (s.length() > 4 && !s.contains(",") && !s.contains("(((("))
            throw new RuntimeException("Illegal sequence " + s);
         if(!s.matches("[\\w(),+--/ *]+"))
            throw new RuntimeException("String representation of a tree contains invalid symbols-> " + s );
         if(s.contains(" "))
            throw new RuntimeException("String representation of a tree contains whitespaces characters-> " + s);
         if(s.contains(",,"))
            throw new RuntimeException("String representation of a tree contains double commas-> " + s);
         if(s.contains("()"))
            throw new RuntimeException("String representation of a tree contains empty subtree-> " + s);
         if(s.contains(",") && !s.contains("(") && !s.contains(")"))
            throw new RuntimeException("String representation of a tree contains multiple root nodes, which is impossible-> " + s);
         for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(' && s.charAt(i+1) == ',')
               throw new RuntimeException("String contains comma error, parenthesis can't be followed by comma-> " +s);
            if(s.charAt(i) == ')' && (s.charAt(i+1) == ',' || s.charAt(i+1) == ')'))
               throw new RuntimeException("String representation is not build according to left parenthesis representation-> " +s);
         }
      } catch (Exception e){
         throw new RuntimeException("String representation caused the following output: " + e.getMessage() +
                 "\nPlease check the input string-> " + s);
      }

      Stack<Node> newStack = new Stack<>();
      Node newNode = new Node();
      StringTokenizer tok = new StringTokenizer(s, "(),", true);
      while (tok.hasMoreTokens()) {
         String token = tok.nextToken().trim();
         switch (token) {
            case "(":
               newStack.push(newNode);
               newNode.firstChild = new Node();
               newNode = newNode.firstChild;
               break;
            case ")":
               newNode = newStack.pop();
               break;
            case ",":
               if (newStack.empty())
                  throw new RuntimeException("String representation of a tree contains redundant comma" + s);
               newNode.nextSibling = new Node();
               newNode = newNode.nextSibling;
               break;
            default:
               newNode.name = token;
         }
      }
      return newNode;
   }

   public String leftParentheticRepresentation() {
      StringBuilder buffer = new StringBuilder();
      buffer.append(this.name);
      if(this.firstChild != null){
         buffer.append("(");
         buffer.append(this.firstChild.leftParentheticRepresentation());
         buffer.append(")");
      }
      if(this.nextSibling != null){
         buffer.append(",");
         buffer.append(this.nextSibling.leftParentheticRepresentation());
      }
      return buffer.toString();
   }
   public static void main (String[] param) {
      String s = "(B1,C)A";
      String s1 = "A";
      String s2 = "A(B),(C(E)D)";
      String s3 = " ";
      Node t = Node.parsePostfix (s);
      Node t1 = Node.parsePostfix(s1);
      Node t2 = Node.parsePostfix(s2);
      Node t3 = Node.parsePostfix(s3);
      String v3 = t3.leftParentheticRepresentation();
      String v2 = t2.leftParentheticRepresentation();
      String v1 = t1.leftParentheticRepresentation();
      String v = t.leftParentheticRepresentation();
      System.out.println (s + " ==> " + v); // (B1,C)A ==> A(B1,C)
      System.out.println(s1 + " ==> " + v1); // A ==> A
      System.out.println(s2 + " ==> " + v2); // throws an error
      System.out.println(s3 + " ==> " + v3); // throws an error
   }
}