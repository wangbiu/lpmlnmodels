package cn.edu.seu.kse.lpmln.model;

/**
 * @author 许鸿翔
 * @date 2019/3/27
 */
public class SignedLiteral{
    private String literal;
    private boolean sign;
    public SignedLiteral(String literal,boolean sign){
        this.literal = literal;
        this.sign = sign;
    }

    public String getLiteral() {
        return literal;
    }

    public boolean isSign() {
        return sign;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof SignedLiteral){
            return ((SignedLiteral) o).getLiteral().equals(literal)&&((SignedLiteral) o).isSign()==sign;
        }
        return false;
    }
}
