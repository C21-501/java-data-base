package database.service.tools;

import org.antlr.v4.runtime.*;

public class SQLErrorStrategy extends DefaultErrorStrategy {

    public SQLErrorStrategy(){
        super();
    }

    @Override
    public void reportError(Parser recognizer, RecognitionException e) {
        super.reportError(recognizer, e);
        throw new RecognitionException("Syntax Error",null,null,null);
    }

    @Override
    protected void reportNoViableAlternative(Parser recognizer, NoViableAltException e) {
        super.reportNoViableAlternative(recognizer, e);
        throw new RecognitionException("Syntax Error",null,null,null);
    }

    @Override
    protected void reportInputMismatch(Parser recognizer, InputMismatchException e) {
        super.reportInputMismatch(recognizer, e);
        throw new RecognitionException("Syntax Error",null,null,null);
    }

    @Override
    protected void reportFailedPredicate(Parser recognizer, FailedPredicateException e) {
        super.reportFailedPredicate(recognizer, e);
        throw new RecognitionException("Syntax Error",null,null,null);
    }

    @Override
    protected void reportUnwantedToken(Parser recognizer) {
        super.reportUnwantedToken(recognizer);
        throw new RecognitionException("Syntax Error",null,null,null);
    }

    @Override
    protected void reportMissingToken(Parser recognizer) {
        super.reportMissingToken(recognizer);
        throw new RecognitionException("Syntax Error",null,null,null);
    }
}
