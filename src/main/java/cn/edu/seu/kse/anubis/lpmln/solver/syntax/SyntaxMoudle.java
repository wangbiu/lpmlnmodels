package cn.edu.seu.kse.anubis.lpmln.solver.syntax;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class SyntaxMoudle {

    public List<WeightedAnswerSet> parse(String result){
        List<WeightedAnswerSet> as=null;
        DLVResultLexer lexer=new DLVResultLexer(new ANTLRInputStream(result));
        CommonTokenStream token=new CommonTokenStream(lexer);
        DLVResultParser parser=new DLVResultParser(token);
        AnswerSetVisitor asvisitor=new AnswerSetVisitor();
        as=asvisitor.visitPossible_worlds(parser.possible_worlds());

        return as;
    }
}
