package cn.edu.seu.kse.lpmln.exception.antlrexception;

import org.antlr.v4.runtime.NoViableAltException;

/**
 * Created by admin on 2017/8/30.
 */
public class AntlrNoViableAltException extends AntlrRecognitionException{
    public AntlrNoViableAltException(NoViableAltException e){
        super(e);
    }
}
