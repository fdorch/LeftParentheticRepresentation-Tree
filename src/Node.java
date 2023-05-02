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
   public String toXML(){
      String leftParenthetic = this.leftParentheticRepresentation();
      StringBuilder buf = new StringBuilder();
         int i = 1;
         buf.append("\n<L" + i + "> ");
        StringTokenizer tok = new StringTokenizer(leftParenthetic, "(),", true);
        while (tok.hasMoreTokens()){
           String token = tok.nextToken().trim();
           if (token.matches("[A-Z]")){
              buf.append(token);
           }
           else if(token.equals("(")){
               i++;
              for (int j = 0; j < i; j++){
                 buf.append(" ");
              }
               buf.append("\n"+"   ".repeat(i-1)+"<L"+i+"> ");
           }
           else if(token.equals(")")){
              buf.append("\n"+"   ".repeat(i-1)+"</L"+i+"> ");
              i--;
           }
           else if (token.equals(",")){
              buf.append("</L"+i+"> ");
              buf.append("\n<L"+i+"> ");
           }
        }
      buf.append("\n</L1>");
      return buf.toString();
   }

   public static void main (String[] param) {
      String s = "((C)B,(E,F)D,G)A"; // A(B(C(D(E))))     ((A)B,(C)D)E
      Node t1 = Node.parsePostfix(s);
      String v1 = t1.leftParentheticRepresentation();
      String v2 = t1.toXML();
      System.out.println(v1);
      System.out.println(v2);
   }
}
