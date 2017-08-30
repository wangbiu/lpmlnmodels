package cn.edu.seu.kse.lpmln.exception.antlrException;

import org.antlr.v4.runtime.FailedPredicateException;

/**
 * Created by admin on 2017/8/30.
 */
public class AntlrFailedPredicateException extends AntlrRecognitionException{
    public AntlrFailedPredicateException(FailedPredicateException e){
        super(e);
    }
}
