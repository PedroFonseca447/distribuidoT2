import java.io.Serializable;

public class Conta implements Serializable{
    private int id;
    private double saldo;
    private String nome;
    private int operacao;

    public Conta(int id,double saldo,String nome,int operacao){
        this.id=id;
        this.saldo=saldo;
        this.nome=nome;
        this.operacao=operacao;
    }
    

    public int getId(){
        return id;
    }
    public int getOperacao(){
        return operacao;
    }

    public Double getSaldo(){
        return saldo;
    }

    public String getNome(){
        return nome;
    }
    
    public double setSaldoDeposito(Double operador){
        return this.saldo=saldo+operador;
    }
    public double setSaldoSaque(Double operador){
        return this.saldo=saldo-operador;
    }
    @Override
    public String toString(){
        return "[Conta"+this.id+"saldo: "+this.saldo+"nome: "+this.nome+""+this.operacao+"]";
    }
}
