public class Conta {
    private int id;
    private double saldo;
    private String nome;

    public Conta(int id,double saldo,String nome){
        this.id=id;
        this.saldo=saldo;
        this.nome=nome;
    }
    

    public int getId(){
        return id;
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
        return "[Conta"+this.id+"saldo: "+this.saldo+"nome: "+this.nome+"]";
    }
}
