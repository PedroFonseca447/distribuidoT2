/**
 *  Cliente da calculadora
 */

import java.rmi.Naming;
import java.util.Scanner;

public class CalculadoraCliente {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            //Procura pelo servico da calculadora no IP e porta definidos
            Calculadora c = (Calculadora) Naming.lookup("rmi://localhost:1099/CalcService");
   
            System.out.println("1 - cria conta");
            System.out.println("2 - consulta saldo");
            System.out.println("3 - mult");
            System.out.println("4 - div");
            System.out.println("5 - store");
            System.out.println("6 - load");
            System.out.println("0 - sair");
            boolean exec = true;
            double result;
            int result1;
          c.cadastraConta(2,200.0 , "pedro");
            while (exec) {

                int key = in.nextInt();
                switch (key) {
                    case 1:
                    System.out.println("Digite o numero da conta,valor de saldo e nome do usuario");
                       c.cadastraConta(in.nextInt(), in.nextDouble(),in.toString());
                      System.out.printf("Result: %.2f\n",c.getConta(in.nextInt()) );
                        break;
                    case 2:
        
                        System.out.printf("Result: %.2f\n",c.getSaldo(in.nextInt()) );
                        break;
                    case 3:
                       // result = c.mult(in.nextDouble(), in.nextDouble());
                      //  System.out.printf("Result: %.2f\n", result);
                        break;
                    case 4:
                
                        System.out.printf("Result: %.2f\n",  c.deposito(in.nextInt(),in.nextDouble()));
                        break;
                    case 5:
                        System.out.printf("Input position and then the number to be stored\n");
                        c.store(in.nextInt(), in.nextDouble());
                        break;
                    case 6:
                        System.out.printf("Input the position to be loaded\n");
                        result = c.load(in.nextInt());
                        System.out.printf("Result: %.2f\n", result);
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