package cn.edu.seu.kse.anubis.lpmln.syntax;

import cn.edu.seu.kse.anubis.lpmln.model.Function;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by 王彬 on 2016/8/30.
 */
public class SyntaxModule {
    private HashSet<String> herbrandUniverse;
    private HashSet<Function> functions;
    private HashSet<String> relationnames;

    public void parse(File file){

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
}
