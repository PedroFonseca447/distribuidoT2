/**
    Interface Calculadora com assinatura dos métodos 
 */

import java.rmi.*;

public interface Calculadora extends Remote {
    public void cadastraConta(int id,double saldo,String nome) throws RemoteException;
    public Double getSaldo(int id) throws RemoteException ;
    public void apagaConta(int id,double saldo,String nome) throws RemoteException;
    public Conta getConta(int id) throws RemoteException;
    public Double deposito(int id,double deposito)throws RemoteException;
    public Double saque(int id,double saque)throws RemoteException;
    public void store (int x, double y) throws RemoteException;
    public Double load (int x) throws RemoteException;
}