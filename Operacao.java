import java.io.Serializable;

public class Operacao implements Serializable {
    private int operacaoNumero;
    private String nomeOperacao;

    public Operacao(int operacaoNumero,String nomeOperacao){
        this.operacaoNumero=operacaoNumero;
        this.nomeOperacao=nomeOperacao;
    }

    public String getnomeOperacao(){
        return nomeOperacao;
    }

    public int getoperacaoNumero(){
        return operacaoNumero;
    }

    @Override
    public String toString(){
        return "[Nota de operacao"+this.nomeOperacao+this.operacaoNumero+"]";
    }

}
