package cn.edu.seu.kse.anubis.lpmln.solver.syntax;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class SyntaxMoudle {

    public List<WeightedAnswerSet> parseDLVResult(String result){
        List<WeightedAnswerSet> as=null;
        DLVResultLexer lexer=new DLVResultLexer(new ANTLRInputStream(result));
        CommonTokenStream token=new CommonTokenStream(lexer);
        DLVResultParser parser=new DLVResultParser(token);
        DLVAnswerSetVisitor asvisitor=new DLVAnswerSetVisitor();
        as=asvisitor.visitPossible_worlds(parser.possible_worlds());

        return as;
    }

    public List<WeightedAnswerSet> parseClingoResult(String result){
        List<WeightedAnswerSet> as=null;
        ClingoResultLexer lexer=new ClingoResultLexer(new ANTLRInputStream(result));
        CommonTokenStream token=new CommonTokenStream(lexer);
        ClingoResultParser parser=new ClingoResultParser(token);
        ClingoAnswerSetVisitor asvisitor=new ClingoAnswerSetVisitor();
        as=asvisitor.visitPossible_worlds(parser.possible_worlds());

        return as;
    }
}
