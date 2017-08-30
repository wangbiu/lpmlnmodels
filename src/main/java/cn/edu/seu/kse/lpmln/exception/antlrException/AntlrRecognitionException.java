package cn.edu.seu.kse.lpmln.exception.antlrException;

import org.antlr.v4.runtime.RecognitionException;

/**
 * Created by admin on 2017/8/30.
 */
public class AntlrRecognitionException extends Exception {
    RecognitionException exception;
    public AntlrRecognitionException(RecognitionException e){
        exception = e;
    }
}
