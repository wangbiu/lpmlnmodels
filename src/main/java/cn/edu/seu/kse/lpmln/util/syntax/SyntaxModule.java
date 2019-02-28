package cn.edu.seu.kse.lpmln.util.syntax;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.util.syntax.clingoResult.ClingoAnswerSetVisitor;
import cn.edu.seu.kse.lpmln.util.syntax.clingoResult.ClingoResultLexer;
import cn.edu.seu.kse.lpmln.util.syntax.clingoResult.ClingoResultParser;
import cn.edu.seu.kse.lpmln.util.syntax.dlvResult.DLVAnswerSetVisitor;
import cn.edu.seu.kse.lpmln.util.syntax.dlvResult.DLVResultLexer;
import cn.edu.seu.kse.lpmln.util.syntax.dlvResult.DLVResultParser;
import cn.edu.seu.kse.lpmln.util.syntax.lpmln.LPMLNLexer;
import cn.edu.seu.kse.lpmln.util.syntax.lpmln.LPMLNParser;
import cn.edu.seu.kse.lpmln.util.syntax.lpmln.LPMLNTranslationVisitor;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/30.
 */
public class SyntaxModule {
    private HashSet<String> relationnames;

    public static LpmlnProgram parseLPMLN(File file) throws IOException {
        LPMLNLexer lexer=new LPMLNLexer(new ANTLRFileStream(file.getAbsolutePath()));
        CommonTokenStream tokens=new CommonTokenStream(lexer);
        LPMLNParser parser=new LPMLNParser(tokens);
        ParseTree tree=parser.lpmln_rule();
        LPMLNTranslationVisitor tvisitor=new LPMLNTranslationVisitor();
        tvisitor.visit(tree);

        LpmlnProgram program = new LpmlnProgram(tvisitor.getRules(),tvisitor.getFactor(),tvisitor.getHerbrandUniverse(),tvisitor.getMetarule(),new HashSet<>());
        return program;
    }

    public static LpmlnProgram parseLPMLN(String programStr) {
        LPMLNLexer lexer=new LPMLNLexer(new ANTLRInputStream(programStr));
        CommonTokenStream tokens=new CommonTokenStream(lexer);
        LPMLNParser parser=new LPMLNParser(tokens);
        ParseTree tree=parser.lpmln_rule();
        LPMLNTranslationVisitor tvisitor=new LPMLNTranslationVisitor();
        tvisitor.visit(tree);

        LpmlnProgram program = new LpmlnProgram(tvisitor.getRules(),tvisitor.getFactor(),tvisitor.getHerbrandUniverse(),tvisitor.getMetarule(),new HashSet<>());
        return program;
    }

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

    public HashSet<String> getRelationnames() {
        return relationnames;
    }

    public void setRelationnames(HashSet<String> relationnames) {
        this.relationnames = relationnames;
    }
}
