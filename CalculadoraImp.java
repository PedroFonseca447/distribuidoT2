/**
 * Implementação das funções assinadas na interface
 */

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class CalculadoraImp extends UnicastRemoteObject implements Calculadora{
    //criar um arrayString para comecar a lista de contas  e um arrayDouble
    private ArrayList<Conta> contas = new ArrayList<>();
    
    //para operações(acho mais facil e viavel)
    //ou criar um nodo e assim facilitar nossa vida talvez?
    private double[] memory = new double[10];
    
    //
    public CalculadoraImp() throws RemoteException{
        super();
        
    }
    //metodo add somente a agencia pode acessar
    
    
    @Override
    public void cadastraConta(int id,double saldo,String nome) throws RemoteException {
       Conta c1 = new Conta(id, saldo, nome);
       contas.add(c1);
     
        
   // contas.criaConta(new Conta (id, saldo, nome));
}
    //metodo get para agencia e caixa
    @Override
    public Double getSaldo(int id) throws RemoteException {
            for(Conta conta:contas){
                if(conta.getId()==id){
                    return conta.getSaldo();
                }
             
            }
            return 0.0;
    
    }
    //metodo remove //metodo remove somente a agencia pode acessar
    @Override
    public void apagaConta(int id,double saldo,String nome) throws RemoteException {
        Conta r1=new Conta(id,saldo,nome);
        for(Conta conta:contas){
            if(r1.getId()==conta.getId()){
                contas.remove(conta);
            }
        }
    }
    //get no valor presente dentro da lista
    @Override
    public Conta getConta(int id) throws RemoteException {
       for(Conta conta:contas){
        if(conta.getId()==id){
            return conta;
        }
       }
       return null;
    }
    @Override
    public Double deposito(int id,double deposito)throws RemoteException {
        for(Conta conta:contas){
            if(id==conta.getId()){
              if(deposito<0){
                return 0.0;//gerar erro
              }
               conta.setSaldoDeposito(deposito);
               return conta.getSaldo();
            }
        }
        return 0.0;
    }
    @Override
    public Double saque(int id,double saque)throws RemoteException{
        for(Conta conta:contas){
            if(id==conta.getId()){
                if(conta.getSaldo()<saque||saque<0){
                    return 0.0;//planejamento gerar um erro
                }
                conta.setSaldoSaque(saque);
                return conta.getSaldo();
            }
        }
        return 0.0;
    }
    //atua sobre o 
    @Override
    public void store(int x, double y) throws RemoteException {
        memory[x] = y;
    }
    
    @Override
    public Double load(int x) throws RemoteException {
        return memory[x];
    }
}
