import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.Trees;

import javax.swing.*;

import java.nio.file.*;
import java.util.List;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            CharStream input = CharStreams.fromPath(Paths.get("test.txt"));

            GymlangLexer lexer = new GymlangLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GymlangParser parser = new GymlangParser(tokens);

            ParseTree tree = parser.stmt();
            customVisitor visitor = new customVisitor();
            String result = visitor.visit(tree);

            /*
            if (parser.getNumberOfSyntaxErrors() > 0) {
                System.out.println("FAIL: Syntax errors found in test.txt");
            } else {
                System.out.println("PASS: No syntax errors found in test.txt");
            }
            */

            System.out.println(result);
            // System.out.println("Parse Tree: " + tree.toStringTree(parser));

//            SwingUtilities.invokeLater(() -> Trees.inspect(tree, parser));

        } catch (Exception e) {
            System.out.println("FAIL: Error while parsing test.txt");
            e.printStackTrace();
        }
    }
}