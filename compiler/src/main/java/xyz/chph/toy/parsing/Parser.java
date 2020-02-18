package xyz.chph.toy.parsing;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import xyz.chph.toy.antlr.ToyLexer;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.CompilationUnit;
import xyz.chph.toy.parsing.visitor.CompilationUnitVisitor;

import java.io.IOException;

public class Parser {
    public CompilationUnit getCompilationUnit(String module, String fileAbsolutePath) throws IOException {
        CharStream charStream = new ANTLRFileStream(fileAbsolutePath);
        ToyLexer lexer = new ToyLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        ToyParser parser = new ToyParser(tokenStream);

        ANTLRErrorListener errorListener = new ToyTreeWalkErrorListener();
        parser.addErrorListener(errorListener);

        CompilationUnitVisitor compilationUnitVisitor = new CompilationUnitVisitor(module);
        return parser.compilationUnit().accept(compilationUnitVisitor);
    }
}