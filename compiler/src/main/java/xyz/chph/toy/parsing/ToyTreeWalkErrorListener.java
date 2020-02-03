package xyz.chph.toy.parsing;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ToyTreeWalkErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        String errorFormat = "You fucked up at line %d,char %d :(. Details:%n%s";
        String errorMsg = String.format(errorFormat, line, charPositionInLine, msg);
        System.err.println(errorMsg);
    }
}
