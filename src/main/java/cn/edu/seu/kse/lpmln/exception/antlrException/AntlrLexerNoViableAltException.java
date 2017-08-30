package cn.edu.seu.kse.lpmln.exception.antlrException;

import org.antlr.v4.runtime.LexerNoViableAltException;

/**
 * Created by admin on 2017/8/30.
 */
public class AntlrLexerNoViableAltException extends AntlrRecognitionException {
    public AntlrLexerNoViableAltException(LexerNoViableAltException e){
        super(e);
    }
}
