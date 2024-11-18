import java.util.*;
import java.io.*;

public class CountKeywords {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Java source file: ");
        String filename = input.nextLine();

        File file = new File(filename);

        if (file.exists()) {
            System.out.println("The number of keywords in " + filename
                    + " is " + countKeywords(file));
        } else {
            System.out.println("File " + filename + " does not exist");
        }
    }

    public static int countKeywords(File file) throws Exception {
        // Array of all Java keywords + true, false, and null
        String[] keywordString = {
            "abstract", "assert", "boolean", "break", "byte", "case", "catch",
            "char", "class", "const", "continue", "default", "do", "double",
            "else", "enum", "extends", "for", "final", "finally", "float",
            "goto", "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while", "true", "false", "null"
        };

        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
        int count = 0; // counter for keywords

        // use Scanner to read the file
        Scanner input = new Scanner(file);
        // checks if there is a /* */ block comment 
        boolean insideBlockComment = false;

        while (input.hasNextLine()) {
            String line = input.nextLine();

            // skip or clean comments
            if (insideBlockComment) {
                if (line.contains("*/")) {
                    insideBlockComment = false;
                    line = line.substring(line.indexOf("*/") + 2); 
                } else {
                    continue; // skip the entire line if still in a block comment
                }
            }
            
            // check for start of block comment 
            if (line.contains("/*")) {
                insideBlockComment = true;
                line = line.substring(0, line.indexOf("/*")); // remove everything after "/*"
            }
            
            // remove single-line comments
            if (line.contains("//")) {
                line = line.substring(0, line.indexOf("//"));
            }

            // remove string literals
            StringBuilder cleanedLine = new StringBuilder();
            boolean insideString = false; // track if we're inside a string literal
            
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);

                // Toggle insideString flag when encountering an unescaped double quote
                if (ch == '"' && (i == 0 || line.charAt(i - 1) != '\\')) {
                    insideString = !insideString; // Toggle inside or outside a string literal
                }

                // Add character to cleanedLine only if not inside a string
                if (!insideString) {
                    cleanedLine.append(ch);
                }
            }

            // Split the cleaned line into words to check for keywords
            String[] words = cleanedLine.toString().split("\\s+|[^a-zA-Z0-9_]+");
            for (String word : words) {
                if (keywordSet.contains(word)) { // Check if the word is a keyword
                    count++;
                }
            }
        }

        return count; // Return the total count of keywords
    }
}
