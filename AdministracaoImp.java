/**
 * Implementação das funções assinadas na interface
 */

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class AdministracaoImp extends UnicastRemoteObject implements Banco{
    //criar um arrayString para comecar a lista de contas  e um arrayDouble
    private ArrayList<Conta> contas = new ArrayList<>();
    //CONTADOR DE OPERAÇÕES PARA EVITAR IDEMPOTENCIA
    public int contadorOperacoesCadastro=0;    
    public int contadorOperacoesConfirma=0;
    public int contadorApagaConta=0;
    public int contadorOperacaoSaque=0;
    public int contadorOperacaoDeposito=0;
    public int contadorOperacoesConsultaSaldo=0;

 
    private double[] memory = new double[10];
    
    //
    public AdministracaoImp() throws RemoteException{
        super();
        
    }
    //metodo add somente a agencia pode acessar
    
    
    @Override
    public void cadastraConta(int id,double saldo,String nome)  throws RemoteException {
       Conta c1 = new Conta(id, saldo, nome);
       contas.add(c1);
       contadorOperacoesCadastro++;
     
        
   // contas.criaConta(new Conta (id, saldo, nome));
}
    //metodo get para agencia e caixa
    @Override
    public Double getSaldo(int id) throws RemoteException {
            for(Conta conta:contas){
                if(conta.getId()==id){
                    contadorOperacoesConsultaSaldo++;
                    return conta.getSaldo();

                }
             
            }
            return 0.0;
    
    }
    @Override
    public Double getSaldoS(int id) throws RemoteException {
            for(Conta conta:contas){
                if(conta.getId()==id){
                    
                    return conta.getSaldo();

                }
             
            }
            return 0.0;
    
    }
    //metodo remove //metodo remove somente a agencia pode acessar
    @Override
    public void apagaConta(int id) throws RemoteException {
        
        for(Conta conta:contas){
            if(id==conta.getId()){
                contas.remove(conta);
                contadorApagaConta++;
            }
        }
    }
    //get no valor presente dentro da lista
    @Override
    public Conta getConta(int id) throws RemoteException {
       for(Conta conta:contas){
        if(id==conta.getId()){
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
               contadorOperacaoDeposito++;
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
                contadorOperacaoSaque++;
                return conta.getSaldo();
            }
        }
        return 0.0;
    }
    //atua sobre o 
    @Override
    public String nome(int id) throws RemoteException {
       for(Conta conta:contas){
        if(id==conta.getId()){
            return conta.getNome();
        }
         }
         return "Não encontramos a conta";
    }
    
    @Override
    public int id(int id) throws RemoteException {
        for(Conta conta:contas){
            if(id==conta.getId()){
                contadorOperacoesConfirma++;
                return conta.getId();
                
            }
        }
        return 0;
    }
    @Override
    public boolean conta(int id) throws RemoteException{
        for(Conta conta:contas){
            if(id==conta.getId()){
                return true;
            }
        }
        return false;
    }
    @Override
    public int getContadorOperacaoDeposito() throws RemoteException{
        return contadorOperacaoDeposito;
    }
    @Override
    public int getOperacoesCadastro() throws RemoteException{
        return contadorOperacoesCadastro;
    }
    @Override
    public int getOperacaoSaque() throws RemoteException{
        return contadorOperacaoSaque;
    }
}
