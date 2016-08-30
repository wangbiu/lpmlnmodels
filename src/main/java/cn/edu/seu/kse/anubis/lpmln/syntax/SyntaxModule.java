package cn.edu.seu.kse.anubis.lpmln.syntax;

import cn.edu.seu.kse.anubis.lpmln.model.Function;
import cn.edu.seu.kse.anubis.lpmln.model.Rule;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/30.
 */
public class SyntaxModule {
    private HashSet<String> herbrandUniverse;
    private HashSet<Function> functions;
    private HashSet<String> relationnames;
    private int factor=0;

    public List<Rule> parse(File file) throws IOException {
        LPMLNLexer lexer=new LPMLNLexer(new ANTLRFileStream(file.getAbsolutePath()));
        CommonTokenStream tokens=new CommonTokenStream(lexer);
        LPMLNParser parser=new LPMLNParser(tokens);
        ParseTree tree=parser.lpmln_rule();
        LPMLNTranslationVisitor tvisitor=new LPMLNTranslationVisitor();
        tvisitor.visit(tree);

        List<Rule> rules=tvisitor.getRules();
        factor=tvisitor.getFactor();
        herbrandUniverse=tvisitor.getHerbrandUniverse();

        System.out.println("factor "+factor);
        System.out.println("herbrand universe " + herbrandUniverse);
        return rules;
    }

    public HashSet<String> getHerbrandUniverse() {
        return herbrandUniverse;
    }

    public void setHerbrandUniverse(HashSet<String> herbrandUniverse) {
        this.herbrandUniverse = herbrandUniverse;
    }

    public HashSet<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(HashSet<Function> functions) {
        this.functions = functions;
    }

    public HashSet<String> getRelationnames() {
        return relationnames;
    }

    public void setRelationnames(HashSet<String> relationnames) {
        this.relationnames = relationnames;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }
}
