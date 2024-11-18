
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MatchGroupingSymbols {
    public static void main(String[] args) {
    // get file name from command line argument
    String fileName = args[0];
    try {
    // open file
    File file = new File(fileName);
    Scanner input = new Scanner(file);
    
    // use a stack to track grouping symbols
    Stack<Character> stack = new Stack<>();
    boolean isBalanced = true;
    
    // go through file line by line
    while (input.hasNextLine() && isBalanced) {
        String line = input.nextLine();
        // checks each character
        for (int i =0; i < line.length(); i++) {
            char ch = line.charAt(i);
            
            // push opening symbols onto stack
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            
            // if closing symbol, check for match
            else if (ch == ')' || ch == ']' || ch == '}') {
                // if the stack is empty, there is no match
                if (stack.isEmpty()) {
                    isBalanced = false;
                    break;
                }
            
                // get last opening symbol from stack
                char top = stack.pop();
            
                // check opening and closing symbols match
                if ((ch == ')' && top != '(') ||
                    (ch == ']' && top != '[') ||
                    (ch == '}' && top != '{')) {
                    isBalanced = false;
                    break;
                }
            }
        }
    }        
        if (!stack.isEmpty()) {
            isBalanced = false;
        }
        // print result
        if (isBalanced) {
            System.out.println("All grouping symbols are paired correctly. ");
        } else {
            System.out.println("There is an error, grouping symbols are paired incorrectly. ");
        }
        input.close();
    } catch (FileNotFoundException e) {
            // print error message if the file is not found
            System.out.println("Error: File " + fileName +  " not found. ");
        }
}
    
}
