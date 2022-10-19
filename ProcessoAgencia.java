
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

        Scanner in = new Scanner(System.in);
        System.out.println("digite 1 para iniciar");
         int escolha=Integer.parseInt(in.nextLine());

        while(escolha==1) {
            //Procura pelo servico da calculadora no IP e porta definidos
            Banco c = (Banco) Naming.lookup("rmi://localhost:1099/CalcService");
          
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
                  
                    if(c.conta(idC)==true){
                        throw new ILLEGAL_ARGUMENT_EXCEPTION("CONTA COM ID JA EXISTENTE TENTE NOVAMENTE COM OUTRO ID");
                    }
                  
                    c.cadastraConta(idC, saldoC, nome);
                    
                      System.out.printf("Operacao é: \n"+c.getOperacoesCadastro() );

                        break;
                   case 2:
                    contadorOperacoes++;
                    // if(contadorOperacoes!=contadorOperacoes){
                    //     throw new ILLEGAL_ARGUMENT_EXCEPTION("operação ja realizada");
                    // }
                    System.out.println("Digite seu id para identificarmos sua conta");
                    int sc =Integer.parseInt(in.nextLine());
                    if(c.conta(sc)==false){
                        throw new ILLEGAL_ARGUMENT_EXCEPTION("CONTA COM ID Invalido");
                    }
                    System.out.println("Digite seu id para identificarmos sua /conta");
                    System.out.printf("Result: %.2f\n",c.getSaldoS(sc) );
                    System.out.println(contadorOperacoes);
                        
                    break;
               case 3:

                    System.out.println("Digite sua id");
                    int idS=Integer.parseInt(in.nextLine());
                    if(c.conta(idS)==false){
                        throw new ILLEGAL_ARGUMENT_EXCEPTION("CONTA INEXISTENTE");
                    }
                    System.out.println("Digite o valor a ser sacado");
                    double valorSaque=Double.parseDouble(in.nextLine());
                    if(valorSaque<0){
                        throw new ILLEGAL_ARGUMENT_EXCEPTION("VALOR SACADO INVALIDO");
                    }
                    if(valorSaque>c.getSaldo(idS)){
                        throw new ILLEGAL_ARGUMENT_EXCEPTION("FUNDOS INSUFICIENTES ");
                    }
                    c.saque(idS, valorSaque);
                    System.out.println("Saldo em conta pos saque: "+c.getSaldoS(idS));
                    System.out.println("Numero da operacao: "+c.getOperacaoSaque());
                        break;
                     case 4:
                    System.out.println("Digite sua id");
                    int id=Integer.parseInt(in.nextLine());
                    if(c.conta(id)==false){
                        throw new ILLEGAL_ARGUMENT_EXCEPTION("CONTA INEXISTENTE");
                    }
                    System.out.println("Digite o valor a ser depositado");
                    double valorDeposito=Double.parseDouble(in.nextLine());
                    if(valorDeposito<0){
                        throw new ILLEGAL_ARGUMENT_EXCEPTION("VALOR INSERIDO INVALIDO");
                    }
                
                    c.deposito(id, valorDeposito);
                    
                    System.out.println("Saldo em conta pos deposito: "+c.getSaldoS(id));
                        break;
                        
                    case 5:
                    System.out.println("digite o id da conta que deseja apagar");
                    int r = Integer.parseInt(in.nextLine());
                    if(c.conta(r)==false){
                        throw new ILLEGAL_ARGUMENT_EXCEPTION("CONTA INEXISTENTE");
                    }             
                        c.apagaConta(r);
                        System.out.printf(" conta foi apagada\n");
                        System.out.println(c.getContadorApagaConta());
                        break;
                                     
                        
                    case 6:
                        System.out.printf("Digite sua id\n");
                        int idG=Integer.parseInt(in.nextLine());
                        System.out.println(c.nome(idG)+":"+ c.getSaldoS(idG)+":"+c.id(idG));
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
          
      