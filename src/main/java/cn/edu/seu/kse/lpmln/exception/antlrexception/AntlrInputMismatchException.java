package cn.edu.seu.kse.lpmln.exception.antlrexception;


import org.antlr.v4.runtime.InputMismatchException;

/**
 * Created by admin on 2017/8/30.
 */
public class AntlrInputMismatchException extends AntlrRecognitionException {
    public AntlrInputMismatchException(InputMismatchException e){
        super(e);
    }
}
