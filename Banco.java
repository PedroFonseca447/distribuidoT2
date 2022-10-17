/**
    Interface Calculadora com assinatura dos métodos 
 */

import java.rmi.*;

public interface Banco extends Remote {
    public void cadastraConta(int id,double saldo,String nome) throws RemoteException;
    public Double getSaldo(int id) throws RemoteException ;
    public void apagaConta(int id) throws RemoteException;
    public Conta getConta(int id) throws RemoteException;
    public Double deposito(int id,double deposito)throws RemoteException;
    public Double saque(int id,double saque)throws RemoteException;
   
    public int id(int id) throws RemoteException;
    public String nome(int id) throws RemoteException;
}