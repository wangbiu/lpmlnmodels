package cn.edu.seu.kse.lpmln.model;

/**
 * @author 许鸿翔
 * @date 2019/3/27
 */
public class SignedLiteral{
    private String literal;
    private boolean sign;
    SignedLiteral(String literal,boolean sign){
        this.literal = literal;
        this.sign = sign;
    }

    public String getLiteral() {
        return literal;
    }

    public boolean isSign() {
        return sign;
    }
}
