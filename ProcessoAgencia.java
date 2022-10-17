
/**
 *  Processo agência
 * solicita abertura de conta,autenticação de conta
 * fechamento de conta, deposito,saque e consulta de saldo
 * idepo= apaga conta e solicita saldo
 * n-idepo= saque,deposito,cria conta
 */

import java.rmi.Naming;
import java.util.Scanner;

public class ProcessoAgencia {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            //Procura pelo servico da calculadora no IP e porta definidos
            Banco c = (Banco) Naming.lookup("rmi://localhost:1099/CalcService");
   
            System.out.println("1 - cria conta");
            System.out.println("2 - consulta saldo");
            System.out.println("3 - saque");
            System.out.println("4 - deposito");
            System.out.println("5 - fechamento de conta");
            System.out.println("6 - confirma conta");
            System.out.println("0 - sair");
            boolean exec = true;
          
          c.cadastraConta(2,200.0 , "pedro");
          c.cadastraConta(3,200.0 , "tiago");
            while (exec) {
//while no lugar do switch
// contador para operacoes
//tradador de erros com o try para evitar que o programa morra


                int key = Integer.parseInt(in.nextLine());
                switch (key) {
                    case 1:
                    //erro estranho!!
                    System.out.println("Digite o numero da nova conta");
                    int idC=Integer.parseInt(in.nextLine());
                    System.out.println("Digite o saldo inicial da nova conta");
                    double saldoC= Double.parseDouble(in.nextLine());
                    System.out.println("Digite o nome vinculado a conta");
                    String nome = in.nextLine();
                    c.cadastraConta(idC, saldoC, nome);
                      System.out.printf("Sua conta é\n",c.getConta(idC) );

                        break;
                    case 2:
                         System.out.println("Digite seu id para identificarmos sua conta");
                        System.out.printf("Result: %.2f\n",c.getSaldo(in.nextInt()) );
                        break;
                    case 3:
                    System.out.println("Digite sua id");
                    int idS=in.nextInt();
                    System.out.println("Digite o valor a ser sacado");
                    double valorSaque=in.nextDouble();
                    c.saque(idS, valorSaque);
                   
                    System.out.println("Saldo em conta pos saque: "+c.getSaldo(idS));
                        break;
                    case 4:
                    System.out.println("Digite sua id");
                    int id=in.nextInt();
                    System.out.println("Digite o valor a ser depositado");
                    double valorDeposito=in.nextDouble();
                    c.deposito(id, valorDeposito);
                    
                    System.out.println("Saldo em conta pos saque: "+c.getSaldo(id));
                        break;
                    case 5:                    
                        c.apagaConta(in.nextInt());
                        System.out.printf(" conta foi apagada\n");

                       
                        break;
                    case 6:
                        System.out.printf("Digite sua id\n");
                        int idG=in.nextInt();
                        System.out.println(c.nome(idG)+":"+ c.getSaldo(idG)+":"+c.id(idG));
                        break;
                    case 0:
                        exec = false;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
