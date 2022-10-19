
/**
 *  Processo agência
 * solicita abertura de conta,autenticação de conta
 * fechamento de conta, deposito,saque e consulta de saldo
 * idepo= apaga conta e solicita saldo
 * n-idepo= saque,deposito,cria conta
 */

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ProcessoAgencia {
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        int contadorOperacoes=0;

        //Procura pelo servico da calculadora no IP e porta definidos
        Banco c = (Banco) Naming.lookup("rmi://localhost:1099/CalcService");

        Scanner in = new Scanner(System.in);
        System.out.println("digite 1 para iniciar");
        int escolha=Integer.parseInt(in.nextLine());
        
        while(escolha==1) {          
            System.out.println("1 - cria conta");
            System.out.println("2 - consulta saldo");
            System.out.println("3 - saque");
            System.out.println("4 - deposito");
            System.out.println("5 - fechamento de conta");
            System.out.println("6 - confirma conta");
            System.out.println("0 - sair");
            int key = Integer.parseInt(in.nextLine());
           switch(key){
            case 1:
            contadorOperacoes++;
                    // if(contadorOperacoes!=contadorOperacoes){
                    //     throw new ILLEGAL_ARGUMENT_EXCEPTION("operacao ja realizada");
                    // }
                       
                    System.out.println("Digite o numero da nova conta");
                    int idC=Integer.parseInt(in.nextLine());
                    System.out.println("Digite o saldo inicial da nova conta");
                    double saldoC= Double.parseDouble(in.nextLine());
                    System.out.println("Digite o nome vinculado a conta");
                    String nome = in.nextLine();
                        try{
                    if(c.conta(idC)==true){
                        System.out.println("CONTA COM ID JA EXISTENTE TENTE NOVAMENTE COM OUTRO ID");
                        continue;
                    }}catch(Exception RemoteException){
                        System.out.println("erro de conexao");
                    }
                  
                    try {
                        c.cadastraConta(idC, saldoC, nome);
                    } catch (Exception RemoteException) {
                        System.out.println("erro de conexao");
                        
                    }
                   
                    try {
                        System.out.println("Operacao é: \n"+c.getOperacoesCadastro() );
                    } catch (Exception RemoteException) {
                        System.out.println("erro de conexao");
                    }
                      

                        break;
                   case 2:
                    contadorOperacoes++;
                    // if(contadorOperacoes!=contadorOperacoes){
                    //     throw new ILLEGAL_ARGUMENT_EXCEPTION("operação ja realizada");
                    // }
                    System.out.println("Digite seu id para identificarmos sua conta");
                    int sc =Integer.parseInt(in.nextLine());
                    try{
                    if(c.conta(sc)==false){
                        System.out.println("CONTA COM ID Invalido");
                        continue;
                    }
                }catch(Exception remoException){
                    System.out.println("erro de conexao");
                }
                    System.out.println("Digite seu id para identificarmos sua /conta");
                    try {
                        System.out.printf("Result: %.2f\n",c.getSaldoS(sc) );
                    } catch (Exception e) {
                        System.out.println("erro de conexao");
                    }
                    
                    System.out.println(contadorOperacoes);
                        
                    break;
               case 3:

                    System.out.println("Digite sua id");
                    int idS=Integer.parseInt(in.nextLine());
                    try{
                    if(c.conta(idS)==false){
                        System.out.println("CONTA INEXISTENTE");
                        continue;
                    }
                }catch(Exception remoException){
                    System.out.println("erro de conexao");
                }
                    System.out.println("Digite o valor a ser sacado");
                    double valorSaque=Double.parseDouble(in.nextLine());
                    try{
                    if(valorSaque<0){
                       System.out.println("VALOR SACADO INVALIDO");
                       continue;
                    }
                    }catch(Exception remoException){
                        System.out.println("erro de conexao");
                    }
                    try{
                    if(valorSaque>c.getSaldo(idS)){
                       System.out.println("FUNDOS INSUFICIENTES ");
                    }
                }catch(Exception remoException){
                    System.out.println("erro de conexao");
                }
                    try{
                    c.saque(idS, valorSaque);
                    }catch(Exception remoException){
                        System.out.println("erro de conexao");
                    }
                    try {
                        System.out.println("Saldo em conta pos saque: "+c.getSaldoS(idS));
                    } catch (Exception remoException) {
                        System.out.println("erro de conexao");
                    }
                    try {
                        System.out.println("Numero da operacao: "+c.getOperacaoSaque());
                    } catch (Exception remoException) {
                        System.out.println("erro de conexao");
                    }
                   
                        break;
                     case 4:
                    System.out.println("Digite sua id");
                    int id=Integer.parseInt(in.nextLine());
                    try{
                    if(c.conta(id)==false){
                        System.out.println("CONTA INEXISTENTE");
                        continue;
                    }
                }catch(Exception remoException){
                    System.out.println("erro de conexao");
                    
                }
                    System.out.println("Digite o valor a ser depositado");
                    double valorDeposito=Double.parseDouble(in.nextLine());
                    if(valorDeposito<0){
                       System.out.println("VALOR INSERIDO INVALIDO");
                       continue;
                    }
                    try {
                        c.deposito(id, valorDeposito);
                    } catch (Exception RemoteException) {
                        System.out.println("erro de conexao");
                    }
           
                    try {
                        System.out.println("Saldo em conta pos deposito: "+c.getSaldoS(id));
                    } catch (Exception e) {
                        System.out.println("erro de conexao");
                    }
                   
                        break;
                        
                    case 5:
                    System.out.println("digite o id da conta que deseja apagar");
                    int r = Integer.parseInt(in.nextLine());
                    try {
                        if(c.conta(r)==false){
                            System.out.println("CONTA INEXISTENTE");
                            continue;
                         }  
                    } catch (Exception RemoteException) {
                        System.out.println("erro de conexao");
                    }
                        try {
                            c.apagaConta(r);
                        } catch (Exception RemoteException) {
                            System.out.println("erro de conexao");
                            //zerar o numero da operacao
                            //talvez
                        }   
              
                        System.out.printf(" conta foi apagada\n");
                        try{
                        System.out.println(c.getContadorApagaConta());
                        }catch(Exception RemoteException){
                            System.out.println("erro de conexao");
                        }
                        break;
                                     
                        
                    case 6:
                        System.out.printf("Digite sua id\n");
                        int idG=Integer.parseInt(in.nextLine());
                        try {
                            System.out.println(c.nome(idG)+":"+ c.getSaldoS(idG)+":"+c.id(idG));
                        } catch (Exception e) {
                            System.out.println("erro de conexao");
                        }
                       
                        break;
                    case 0:
                    escolha=0;
                    System.out.println("encerrando");
                    default:
                    System.out.println("Digitou opcao invalida");
                     break;
                    
           }
           
           System.out.println("Deseja novamente realizar a operacao? digite[1] para continuar");
           escolha=Integer.parseInt(in.nextLine());
           if(escolha!=1){
            System.out.println("encerrando");
           }
           
        }
        
    }
}
          
      