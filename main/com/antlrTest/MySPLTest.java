package antlrTest;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class MySPLTest {

    public static void main(String[] args) throws Exception {
        String splQuery = "search index = main | stats count by host";

        SPLLexer lexer = new SPLLexer(CharStreams.fromString(splQuery));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SPLParser parser = new SPLParser(tokens);

        ParseTree tree = parser.query();
        System.out.println(tree.toStringTree(parser));
    }
}
