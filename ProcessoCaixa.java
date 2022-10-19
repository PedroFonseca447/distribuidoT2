
    // Processo caixa
    // garante operações de saque , deposito e consulta de saldo
    // sendo nao idempotentes saque e deposito
    import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ProcessoCaixa {
    public static void main(String args[]) throws MalformedURLException, RemoteException, NotBoundException{
        Scanner in = new Scanner(System.in);
        System.out.println("digite 1 para iniciar");
         int escolha=Integer.parseInt(in.nextLine());
         while(escolha==1) {
            //Procura pelo servico da calculadora no IP e porta definidos
            Banco c = (Banco) Naming.lookup("rmi://localhost:1099/CalcService");
   
            System.out.println("1 - saque");
            System.out.println("2 - deposito");
            System.out.println("3 - consulta de saldo");
      

            int key = Integer.parseInt(in.nextLine());
                switch (key) {
                    case 1:
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
                    case 2:
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
                    System.out.println("Saldo em conta pos saque: "+c.getSaldo(id));
                        break;
                    case 3:
                    System.out.printf("Digite sua id\n");
                    int idG=Integer.parseInt(in.nextLine());
                    System.out.println(c.nome(idG)+":"+ c.getSaldo(idG)+":"+c.id(idG));
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
